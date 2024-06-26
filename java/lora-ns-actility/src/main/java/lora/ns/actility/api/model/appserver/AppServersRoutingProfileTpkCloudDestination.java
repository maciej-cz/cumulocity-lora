/*
 * ThingPark things management Devices API
 * REST interface for Devices management. 
 *
 * The version of the OpenAPI document: 7.3
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package lora.ns.actility.api.model.appserver;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * AppServersRoutingProfileTpkCloudDestination
 */
@JsonPropertyOrder({ AppServersRoutingProfileTpkCloudDestination.JSON_PROPERTY_ADDRESS,
    AppServersRoutingProfileTpkCloudDestination.JSON_PROPERTY_PORTS })
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-05-29T11:38:36.048437806+02:00[Europe/Paris]", comments = "Generator version: 7.6.0")
public class AppServersRoutingProfileTpkCloudDestination {
  public static final String JSON_PROPERTY_ADDRESS = "address";
  private String address;

  public static final String JSON_PROPERTY_PORTS = "ports";
  private String ports;

  public AppServersRoutingProfileTpkCloudDestination() {
  }

  public AppServersRoutingProfileTpkCloudDestination address(String address) {

    this.address = address;
    return this;
  }

  /**
   * ThingPark Cloud destination address (URL).
   * 
   * @return address
   **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ADDRESS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getAddress() {
    return address;
  }

  @JsonProperty(JSON_PROPERTY_ADDRESS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setAddress(String address) {
    this.address = address;
  }

  public AppServersRoutingProfileTpkCloudDestination ports(String ports) {

    this.ports = ports;
    return this;
  }

  /**
   * Only the default wildcard \&quot;*\&quot; is supported.
   * 
   * @return ports
   **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PORTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getPorts() {
    return ports;
  }

  @JsonProperty(JSON_PROPERTY_PORTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPorts(String ports) {
    this.ports = ports;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppServersRoutingProfileTpkCloudDestination appServersRoutingProfileTpkCloudDestination = (AppServersRoutingProfileTpkCloudDestination) o;
    return Objects.equals(this.address, appServersRoutingProfileTpkCloudDestination.address)
            && Objects.equals(this.ports, appServersRoutingProfileTpkCloudDestination.ports);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, ports);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppServersRoutingProfileTpkCloudDestination {\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    ports: ").append(toIndentedString(ports)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
