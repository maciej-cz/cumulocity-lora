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
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * BsUpFrameMinRC
 */
@JsonPropertyOrder({ BsUpFrameMinRC.JSON_PROPERTY_ANT, BsUpFrameMinRC.JSON_PROPERTY_DC, BsUpFrameMinRC.JSON_PROPERTY_LC,
    BsUpFrameMinRC.JSON_PROPERTY_RC })
@JsonTypeName("Bs_upFrame_minRC")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-05-31T16:08:44.630492043+02:00[Europe/Paris]", comments = "Generator version: 7.6.0")
public class BsUpFrameMinRC {
  public static final String JSON_PROPERTY_ANT = "ant";
  private String ant;

  public static final String JSON_PROPERTY_DC = "dc";
  private Float dc;

  public static final String JSON_PROPERTY_LC = "lc";
  private String lc;

  public static final String JSON_PROPERTY_RC = "rc";
  private Float rc;

  public BsUpFrameMinRC() {
  }

  public BsUpFrameMinRC ant(String ant) {

    this.ant = ant;
    return this;
  }

  /**
   * Min uplink RC antenna
   * 
   * @return ant
   **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ANT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getAnt() {
    return ant;
  }

  @JsonProperty(JSON_PROPERTY_ANT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setAnt(String ant) {
    this.ant = ant;
  }

  public BsUpFrameMinRC dc(Float dc) {

    this.dc = dc;
    return this;
  }

  /**
   * Max uplink DC (duty cycle)
   * 
   * @return dc
   **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DC)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Float getDc() {
    return dc;
  }

  @JsonProperty(JSON_PROPERTY_DC)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDc(Float dc) {
    this.dc = dc;
  }

  public BsUpFrameMinRC lc(String lc) {

    this.lc = lc;
    return this;
  }

  /**
   * Min uplink RC logical channel (LC1 ... LC7)
   * 
   * @return lc
   **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LC)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getLc() {
    return lc;
  }

  @JsonProperty(JSON_PROPERTY_LC)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLc(String lc) {
    this.lc = lc;
  }

  public BsUpFrameMinRC rc(Float rc) {

    this.rc = rc;
    return this;
  }

  /**
   * Min uplink RC (remaining capacity)
   * 
   * @return rc
   **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_RC)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Float getRc() {
    return rc;
  }

  @JsonProperty(JSON_PROPERTY_RC)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setRc(Float rc) {
    this.rc = rc;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BsUpFrameMinRC bsUpFrameMinRC = (BsUpFrameMinRC) o;
    return Objects.equals(this.ant, bsUpFrameMinRC.ant) && Objects.equals(this.dc, bsUpFrameMinRC.dc)
            && Objects.equals(this.lc, bsUpFrameMinRC.lc) && Objects.equals(this.rc, bsUpFrameMinRC.rc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ant, dc, lc, rc);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BsUpFrameMinRC {\n");
    sb.append("    ant: ").append(toIndentedString(ant)).append("\n");
    sb.append("    dc: ").append(toIndentedString(dc)).append("\n");
    sb.append("    lc: ").append(toIndentedString(lc)).append("\n");
    sb.append("    rc: ").append(toIndentedString(rc)).append("\n");
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
