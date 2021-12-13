package lora.ns.actility.rest;

import java.util.List;

import lora.ns.actility.rest.model.BaseStation;
import lora.ns.actility.rest.model.BaseStationProfile;
import lora.ns.actility.rest.model.Connection;
import lora.ns.actility.rest.model.ConnectionRequest;
import lora.ns.actility.rest.model.DeviceCreate;
import lora.ns.actility.rest.model.DeviceProfile;
import lora.ns.actility.rest.model.DownlinkMessage;
import lora.ns.actility.rest.model.RFRegion;
import lora.ns.actility.rest.model.Route;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ActilityCoreService {
	@Headers("Accept: application/json")
	@GET("connections")
	Call<List<Connection>> getConnections();

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("connections")
	Call<Connection> createConnection(@Body ConnectionRequest connectionRequest);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@PUT("connections/{connectionId}")
	Call<Connection> updateConnection(@Path("connectionId") String connectionId, @Body ConnectionRequest connectionRequest);

	@DELETE("connections/{connectionId}")
	Call<ResponseBody> deleteConnection(@Path("connectionId") String connectionId);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@GET("routes")
	Call<List<Route>> getRoutes();

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@GET("routes/{routeRef}")
	Call<Route> getRoute(@Path("routeRef") String routeRef);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("routes")
	Call<Route> createRoute(@Body Route route);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@PUT("routes/{routeRef}")
	Call<Route> updateRoute(@Path("routeRef") String routeRef, @Body Route route);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("devices")
	Call<DeviceCreate> createDevice(@Body DeviceCreate device);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@PUT("devices/{deviceRef}")
	Call<DeviceCreate> updateDevice(@Path("deviceRef") String deviceRef, @Body DeviceCreate device);

	@Headers("Accept: application/json")
	@GET("devices")
	Call<List<DeviceCreate>> getDevices();

	@Headers("Accept: application/json")
	@GET("devices")
	Call<List<DeviceCreate>> getDeviceByEUI(@Query("deviceEUI") String devEUI);

	@Headers("Accept: application/json")
	@GET("device/{deviceRef}")
	Call<DeviceCreate> getDevice(@Path("deviceRef") String deviceRef);

	@DELETE("devices/{deviceRef}")
	Call<ResponseBody> deleteDevice(@Path("deviceRef") String deviceRef);

	@Headers("Accept: application/json")
	@GET("deviceProfiles")
	Call<List<DeviceProfile>> getDeviceProfiles();

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("devices/{devEUI}/downlinkMessages")
	Call<DownlinkMessage> sendDownlink(@Path("devEUI") String devEUI, @Body DownlinkMessage downlinkMessage);

	@Headers("Accept: application/json")
	@GET("baseStations")
	Call<List<BaseStation>> getBaseStations();

	@Headers("Accept: application/json")
	@GET("baseStations/{baseStationRef}")
	Call<BaseStation> getBaseStation(@Path("baseStationRef") String baseStationRef);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("baseStations")
	Call<BaseStation> createBaseStation(@Body BaseStation baseStation);

	@DELETE("baseStations/{baseStationRef}")
	Call<ResponseBody> deleteBaseStation(@Path("baseStationRef") String baseStationRef);

	@Headers("Accept: application/json")
	@GET("baseStationProfiles")
	Call<List<BaseStationProfile>> getBaseStationProfiles();

	@Headers("Accept: application/json")
	@GET("rfRegions")
	Call<List<RFRegion>> getRFRegions();
}
