/*
 * ThingPark things management Networks API
 * REST interface for networks management. 
 *
 * The version of the OpenAPI document: 7.3
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package lora.ns.actility.api.model.basestation;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * BsLrcsLrcInner
 */
@JsonPropertyOrder({ BsLrcsLrcInner.JSON_PROPERTY_ADDRESS, BsLrcsLrcInner.JSON_PROPERTY_I_D,
    BsLrcsLrcInner.JSON_PROPERTY_LAST_COM, BsLrcsLrcInner.JSON_PROPERTY_NAME, BsLrcsLrcInner.JSON_PROPERTY_STATE,
    BsLrcsLrcInner.JSON_PROPERTY_STATE_SINCE })
@JsonTypeName("Bs_lrcs_lrc_inner")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-05-31T16:08:44.630492043+02:00[Europe/Paris]", comments = "Generator version: 7.6.0")
public class BsLrcsLrcInner {
  public static final String JSON_PROPERTY_ADDRESS = "address";
  private String address;

  public static final String JSON_PROPERTY_I_D = "ID";
  private String ID;

  public static final String JSON_PROPERTY_LAST_COM = "lastCom";
  private Long lastCom;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_STATE = "state";
  private Integer state;

  public static final String JSON_PROPERTY_STATE_SINCE = "stateSince";
  private Long stateSince;

  public BsLrcsLrcInner() {
  }

  public BsLrcsLrcInner address(String address) {

    this.address = address;
    return this;
  }

  /**
   * LRC address
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

  public BsLrcsLrcInner ID(String ID) {

    this.ID = ID;
    return this;
  }

  /**
   * LRC identifier
   * 
   * @return ID
   **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_I_D)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getID() {
    return ID;
  }

  @JsonProperty(JSON_PROPERTY_I_D)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setID(String ID) {
    this.ID = ID;
  }

  public BsLrcsLrcInner lastCom(Long lastCom) {

    this.lastCom = lastCom;
    return this;
  }

  /**
   * LRC last communication timestamp, epoch time in milliseconds
   * 
   * @return lastCom
   **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LAST_COM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Long getLastCom() {
    return lastCom;
  }

  @JsonProperty(JSON_PROPERTY_LAST_COM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLastCom(Long lastCom) {
    this.lastCom = lastCom;
  }

  public BsLrcsLrcInner name(String name) {

    this.name = name;
    return this;
  }

  /**
   * LRC name
   * 
   * @return name
   **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getName() {
    return name;
  }

  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setName(String name) {
    this.name = name;
  }

  public BsLrcsLrcInner state(Integer state) {

    this.state = state;
    return this;
  }

  /**
   * LRC state: Down (0), Stopped (1), Started (2)
   * 
   * @return state
   **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_STATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getState() {
    return state;
  }

  @JsonProperty(JSON_PROPERTY_STATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setState(Integer state) {
    this.state = state;
  }

  public BsLrcsLrcInner stateSince(Long stateSince) {

    this.stateSince = stateSince;
    return this;
  }

  /**
   * LRC state since, epoch time in milliseconds
   * 
   * @return stateSince
   **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_STATE_SINCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Long getStateSince() {
    return stateSince;
  }

  @JsonProperty(JSON_PROPERTY_STATE_SINCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setStateSince(Long stateSince) {
    this.stateSince = stateSince;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BsLrcsLrcInner bsLrcsLrcInner = (BsLrcsLrcInner) o;
    return Objects.equals(this.address, bsLrcsLrcInner.address) && Objects.equals(this.ID, bsLrcsLrcInner.ID)
            && Objects.equals(this.lastCom, bsLrcsLrcInner.lastCom) && Objects.equals(this.name, bsLrcsLrcInner.name)
            && Objects.equals(this.state, bsLrcsLrcInner.state)
            && Objects.equals(this.stateSince, bsLrcsLrcInner.stateSince);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, ID, lastCom, name, state, stateSince);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BsLrcsLrcInner {\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    ID: ").append(toIndentedString(ID)).append("\n");
    sb.append("    lastCom: ").append(toIndentedString(lastCom)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    stateSince: ").append(toIndentedString(stateSince)).append("\n");
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
