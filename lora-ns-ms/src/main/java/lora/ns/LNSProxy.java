package lora.ns;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.cumulocity.microservice.context.credentials.MicroserviceCredentials;
import com.cumulocity.microservice.subscription.model.MicroserviceSubscriptionAddedEvent;
import com.cumulocity.microservice.subscription.service.MicroserviceSubscriptionsService;
import com.cumulocity.model.event.CumulocitySeverities;
import com.cumulocity.model.idtype.GId;
import com.cumulocity.model.operation.OperationStatus;
import com.cumulocity.rest.representation.alarm.AlarmRepresentation;
import com.cumulocity.rest.representation.event.EventRepresentation;
import com.cumulocity.rest.representation.identity.ExternalIDRepresentation;
import com.cumulocity.rest.representation.inventory.ManagedObjectRepresentation;
import com.cumulocity.rest.representation.measurement.MeasurementRepresentation;
import com.cumulocity.rest.representation.operation.OperationCollectionRepresentation;
import com.cumulocity.rest.representation.operation.OperationRepresentation;
import com.cumulocity.rest.representation.tenant.OptionRepresentation;
import com.cumulocity.sdk.client.Param;
import com.cumulocity.sdk.client.QueryParam;
import com.cumulocity.sdk.client.alarm.AlarmApi;
import com.cumulocity.sdk.client.devicecontrol.DeviceControlApi;
import com.cumulocity.sdk.client.devicecontrol.OperationCollection;
import com.cumulocity.sdk.client.devicecontrol.OperationFilter;
import com.cumulocity.sdk.client.event.EventApi;
import com.cumulocity.sdk.client.identity.ExternalIDCollection;
import com.cumulocity.sdk.client.identity.IdentityApi;
import com.cumulocity.sdk.client.inventory.InventoryApi;
import com.cumulocity.sdk.client.inventory.InventoryFilter;
import com.cumulocity.sdk.client.inventory.ManagedObject;
import com.cumulocity.sdk.client.inventory.ManagedObjectCollection;
import com.cumulocity.sdk.client.measurement.MeasurementApi;
import com.cumulocity.sdk.client.option.TenantOptionApi;

import c8y.Command;
import c8y.LpwanDevice;
import lora.codec.C8YData;
import lora.codec.DownlinkData;
import lora.codec.ms.CodecManager;
import lora.common.C8YUtils;
import lora.common.Component;
import lora.ns.connector.LNSInstance;
import lora.ns.connector.LNSInstanceRepresentation;
import lora.ns.connector.LNSInstanceWizardStep;
import lora.ns.device.LNSDeviceManager;
import lora.ns.operation.LNSOperationManager;

@EnableScheduling
public abstract class LNSProxy<I extends LNSInstance> implements Component {
	public static final String LNS_EXT_ID = "LoRa Network Server type ID";

	public static final String LNS_TYPE = "LoRa Network Server type";

	public static final String LNS_ID = "lnsId";

	public static final String LNS_INSTANCE_REF = "lnsInstanceId";

	public static final String DEVEUI_TYPE = "LoRa devEUI";

	public static final String LNS_INSTANCE_TYPE = "LNS Instance";

	@Autowired
	private C8YUtils c8yUtils;

	@Autowired
	private InventoryApi inventoryApi;

	@Autowired
	private IdentityApi identityApi;

	@Autowired
	private MeasurementApi measurementApi;

	@Autowired
	private EventApi eventApi;

	@Autowired
	private AlarmApi alarmApi;

	@Autowired
	private DeviceControlApi deviceControlApi;

	@Autowired
	private MicroserviceSubscriptionsService subscriptionsService;

	@Autowired
	private CodecManager codecManager;

	@Autowired
	private TenantOptionApi tenantOptionApi;
	
	@Autowired
	private LNSDeviceManager lnsDeviceManager;
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private LNSOperationManager lnsOperationManager;

	private Map<String, Map<String, LNSInstance>> instances = new HashMap<>();

	//protected Map<String, OperationRepresentation> operations = new HashMap<>();

	final Logger logger = LoggerFactory.getLogger(getClass());

	public abstract LinkedList<LNSInstanceWizardStep> getInstanceWizard();

	public abstract DeviceData processUplinkEvent(String eventString);

	public abstract OperationData processDownlinkEvent(String eventString);

	public abstract boolean isOperationUpdate(String eventString);

	protected I getInstance(ManagedObjectRepresentation instance) {
		@SuppressWarnings("unchecked")
		Class<I> instanceType = (Class<I>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		I result = null;
		try {
			result = instanceType.getConstructor(instance.getClass()).newInstance(instance);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Map<String, Map<String, LNSInstance>> getInstances() {
		return instances;
	}

	@EventListener
	private void init(MicroserviceSubscriptionAddedEvent event) {
		getAllLNSInstances(event);
		//registerLNSProxy();
		agentService.registerAgent(this);
	}

	private void getAllLNSInstances(MicroserviceSubscriptionAddedEvent event) {
		logger.info("Looking for LNS Instances in tenant {}", subscriptionsService.getTenant());
		InventoryFilter filter = new InventoryFilter().byType(LNS_INSTANCE_TYPE);
		ManagedObjectCollection col = inventoryApi.getManagedObjectsByFilter(filter);
		QueryParam queryParam = null;
		try {
			queryParam = new QueryParam(new Param() {
				@Override
				public String getName() {
					return "query";
				}
			}, URLEncoder.encode(LNS_ID + " eq " + this.getId() + " and type eq '" + LNS_INSTANCE_TYPE + "'", "utf8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (ManagedObjectRepresentation mor : col.get(queryParam).allPages()) {
			logger.info("Retrieved instance: {} of type {}", mor.getName(), mor.getProperty(LNS_ID));
			LNSInstance instance = getInstance(mor);
			Properties properties = new Properties();
			for (OptionRepresentation option : tenantOptionApi.getAllOptionsForCategory(instance.getId())) {
				properties.setProperty(option.getKey(), option.getValue());
			}
			instance.setProperties(properties);
			if (!instances.containsKey(subscriptionsService.getTenant())) {
				instances.put(subscriptionsService.getTenant(), new HashMap<String, LNSInstance>());
			}
			instances.get(subscriptionsService.getTenant()).put(instance.getId(), instance);
			configureRoutings(instance.getId(), event.getCredentials());
		}
	}

	public void mapEventToC8Y(String eventString, String lnsInstanceId) {
		DeviceData event = processUplinkEvent(eventString);
		if (instances.containsKey(subscriptionsService.getTenant())
				&& instances.get(subscriptionsService.getTenant()).containsKey(lnsInstanceId)) {
			event.setDeviceName(instances.get(subscriptionsService.getTenant()).get(lnsInstanceId)
					.getDevice(event.getDevEui()).getName());
			lnsDeviceManager.upsertDevice(lnsInstanceId, event, agentService.getAgent());
		}
	}

	public void updateOperation(String event, String lnsInstanceId) {
		if (instances.containsKey(subscriptionsService.getTenant())
				&& instances.get(subscriptionsService.getTenant()).containsKey(lnsInstanceId)) {
			logger.info("LNS instance {} of type {} is known", lnsInstanceId, getId());
			OperationData data = processDownlinkEvent(event);
			if (data.getStatus() != OperationStatus.FAILED) {
				OperationRepresentation operation = lnsOperationManager.retrieveOperation(lnsInstanceId, data.getCommandId());
            	operation.setStatus(data.getStatus().toString());
				deviceControlApi.update(operation);
				if (data.getStatus() == OperationStatus.SUCCESSFUL) {
					lnsOperationManager.removeOperation(lnsInstanceId, data.getCommandId());
				}
			} else {
				if (data.getCommandId() != null) {
					OperationRepresentation operation = lnsOperationManager.retrieveOperation(lnsInstanceId, data.getCommandId());
	            	operation.setStatus(OperationStatus.FAILED.toString());
	            	operation.setFailureReason(data.getErrorMessage());
					deviceControlApi.update(operation);
					lnsOperationManager.removeOperation(lnsInstanceId, data.getCommandId());
				} else {
					logger.error("Unknown operation");
				}
			}
		}
	}

	public ManagedObjectRepresentation provisionDevice(String lnsInstanceId, DeviceProvisioning deviceProvisioning) {
		ManagedObjectRepresentation mor = null;
		if (instances.get(subscriptionsService.getTenant()).get(lnsInstanceId).provisionDevice(deviceProvisioning)) {
			ExternalIDRepresentation extId = c8yUtils.findExternalId(deviceProvisioning.getDevEUI(), DEVEUI_TYPE);
			if (extId == null) {
				mor = lnsDeviceManager.createDevice(lnsInstanceId, deviceProvisioning.getName(), deviceProvisioning.getDevEUI(), agentService.getAgent());
				//mor = createDevice(lnsInstanceId, deviceProvisioning.getName(), deviceProvisioning.getDevEUI());
			} else {
				mor = extId.getManagedObject();
				ManagedObject agentApi = inventoryApi
						.getManagedObjectApi(agentService.getAgent().getId());
				agentApi.addChildDevice(mor.getId());
			}
			mor.set(new LpwanDevice().provisioned(true));
			mor.setLastUpdatedDateTime(null);
			if (!mor.hasProperty("codec")) {
				mor.setProperty("codec", deviceProvisioning.getDeviceModel());
			}
			inventoryApi.update(mor);
			EventRepresentation event = new EventRepresentation();
			event.setType("Device provisioned");
			event.setText("Device has been provisioned");
			event.setDateTime(new DateTime());
			event.setSource(mor);
			eventApi.create(event);
			getDeviceConfig(mor);
		} else {
			AlarmRepresentation alarm = new AlarmRepresentation();
			alarm.setType("Device provisioning error");
			alarm.setText("Couldn't provision device " + deviceProvisioning.getDevEUI() + " in LNS instance "
					+ lnsInstanceId);
			alarm.setDateTime(new DateTime());
			alarm.setSeverity(CumulocitySeverities.CRITICAL.name());
			mor = getDevice(deviceProvisioning.getDevEUI());
			if (mor != null) {
				alarm.setSource(mor);
			} else {
				alarm.setSource(agentService.getAgent());
			}
			alarmApi.create(alarm);
		}
		return mor;
	}

	private void getDeviceConfig(ManagedObjectRepresentation mor) {
		if (codecManager.getAvailableOperations(mor) != null
				&& codecManager.getAvailableOperations(mor).containsKey("get config")) {
			OperationRepresentation operation = new OperationRepresentation();
			Command command = new Command("get config");
			operation.set(command);
			operation.setDeviceId(mor.getId());
			deviceControlApi.create(operation);
		}
	}

	public List<EndDevice> getDevices(String lnsInstanceId) {
		return instances.get(subscriptionsService.getTenant()).get(lnsInstanceId).getDevices();
	}

	private void configureRoutings(String lnsInstanceId, MicroserviceCredentials credentials) {
		String url = "https://" + c8yUtils.getTenantDomain() + "/service/lora-ns-" + this.getId() + "/" + lnsInstanceId;
		instances.get(subscriptionsService.getTenant()).get(lnsInstanceId).configureRoutings(url,
				subscriptionsService.getTenant(), credentials.getUsername(), credentials.getPassword());
	}

	public ManagedObjectRepresentation addLNSInstance(LNSInstanceRepresentation instanceRepresentation) {
		ManagedObjectRepresentation mor = new ManagedObjectRepresentation();
		mor.setType(LNS_INSTANCE_TYPE);
		mor.setName(instanceRepresentation.getName());
		mor.setProperty(LNS_ID, this.getId());
		mor = inventoryApi.create(mor);

		String category = mor.getId().getValue();
		instanceRepresentation.getProperties().forEach((k, v) -> {
			OptionRepresentation option = new OptionRepresentation();
			option.setCategory(category);
			option.setKey(k.toString());
			option.setValue(v.toString());
			tenantOptionApi.save(option);
		});

		LNSInstance instance = getInstance(mor);
		instance.setProperties(instanceRepresentation.getProperties());

		if (!instances.containsKey(subscriptionsService.getTenant())) {
			instances.put(subscriptionsService.getTenant(), new HashMap<String, LNSInstance>());
		}
		instances.get(subscriptionsService.getTenant()).put(instance.getId(), instance);
		configureRoutings(instance.getId(),
				subscriptionsService.getCredentials(subscriptionsService.getTenant()).get());

		return mor;
	}

	protected void processOperation(String lnsInstanceId, DownlinkData operation, OperationRepresentation c8yOperation) {
		LNSInstance instance = instances.get(subscriptionsService.getTenant()).get(lnsInstanceId);
		if (instance != null) {
			String commandId = instance.processOperation(operation, c8yOperation);
			lnsOperationManager.storeOperation(lnsInstanceId, c8yOperation, commandId);
			deviceControlApi.update(c8yOperation);
		} else {
			logger.error("LNS instance {} of type {} could not be found on tenant {}", lnsInstanceId, getName(),
					subscriptionsService.getTenant());
		}
	}

	@Scheduled(initialDelay = 10000, fixedDelay = 10000)
	private void processPendingOperations() {
		subscriptionsService.runForEachTenant(() -> {
			OperationFilter filter = new OperationFilter();
			filter.byStatus(OperationStatus.PENDING)
					.byAgent(agentService.getAgent().getId().getValue());
			OperationCollectionRepresentation opCollectionRepresentation;
			OperationCollection oc = deviceControlApi.getOperationsByFilter(filter);
			if (oc != null) {
				for (opCollectionRepresentation = oc
						.get(); opCollectionRepresentation != null; opCollectionRepresentation = oc
								.getNextPage(opCollectionRepresentation)) {
					for (OperationRepresentation op : opCollectionRepresentation.getOperations()) {
						System.out.println(op.getStatus());
						lnsOperationManager.executePending(op);
					}
				}
			}
		});
	}

	@Scheduled(initialDelay = 10000, fixedDelay = 300000)
	private void sendMetrics() {
		subscriptionsService.runForEachTenant(() -> {
			C8YData c8yData = new C8YData();
			DateTime now = new DateTime();
			c8yData.addMeasurement(agentService.getAgent(), "Memory", "Max Memory", "bytes",
					BigDecimal.valueOf(Runtime.getRuntime().maxMemory()), now);
			c8yData.addMeasurement(agentService.getAgent(), "Memory", "Free Memory", "bytes",
					BigDecimal.valueOf(Runtime.getRuntime().freeMemory()), now);
			c8yData.addMeasurement(agentService.getAgent(), "Memory", "Total Memory", "bytes",
					BigDecimal.valueOf(Runtime.getRuntime().totalMemory()), now);
			for (MeasurementRepresentation m : c8yData.getMeasurements()) {
				measurementApi.create(m);
			}
		});
	}

	@Bean
	public ThreadPoolTaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(20);
		return taskScheduler;
	}

	public void removeLNSInstance(String lnsInstanceId) {
		instances.get(subscriptionsService.getTenant()).get(lnsInstanceId).removeRoutings();
		inventoryApi.delete(new GId(lnsInstanceId));
	}

	public ManagedObjectRepresentation getDevice(String devEui) {
		ManagedObjectRepresentation result = null;
		ExternalIDRepresentation extId = c8yUtils.findExternalId(devEui, DEVEUI_TYPE);
		if (extId != null) {
			result = inventoryApi.get(extId.getManagedObject().getId());
			result.setLastUpdatedDateTime(null);
		}
		return result;
	}

	public boolean deprovisionDevice(String lnsInstanceId, String deveui) {
		boolean result = false;
		if (instances.get(subscriptionsService.getTenant()).get(lnsInstanceId).deprovisionDevice(deveui)) {
			ExternalIDRepresentation extId = c8yUtils.findExternalId(deveui, DEVEUI_TYPE);
			if (extId != null) {
				ManagedObjectRepresentation mor = inventoryApi.get(extId.getManagedObject().getId());
				mor.removeProperty("c8y_RequiredInterval");
				mor.set(new LpwanDevice().provisioned(false));
				mor.setLastUpdatedDateTime(null);
				inventoryApi.update(mor);
			}
			result = true;
		}
		return result;
	}
}