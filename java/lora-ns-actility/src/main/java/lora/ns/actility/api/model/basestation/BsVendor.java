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
 * BsVendor
 */
@JsonPropertyOrder({ BsVendor.JSON_PROPERTY_I_D, BsVendor.JSON_PROPERTY_LOGO, BsVendor.JSON_PROPERTY_NAME,
    BsVendor.JSON_PROPERTY_COMMERCIAL_NAME, BsVendor.JSON_PROPERTY_COMMERCIAL_DESCRIPTION })
@JsonTypeName("Bs_vendor")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-05-31T16:08:44.630492043+02:00[Europe/Paris]", comments = "Generator version: 7.6.0")
public class BsVendor {
  public static final String JSON_PROPERTY_I_D = "ID";
  private String ID;

  public static final String JSON_PROPERTY_LOGO = "logo";
  private String logo;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_COMMERCIAL_NAME = "commercialName";
  private String commercialName;

  public static final String JSON_PROPERTY_COMMERCIAL_DESCRIPTION = "commercialDescription";
  private String commercialDescription;

  public BsVendor() {
  }

  public BsVendor ID(String ID) {

    this.ID = ID;
    return this;
  }

  /**
   * BS vendor ID
   * 
   * @return ID
   **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_I_D)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getID() {
    return ID;
  }

  @JsonProperty(JSON_PROPERTY_I_D)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setID(String ID) {
    this.ID = ID;
  }

  public BsVendor logo(String logo) {

    this.logo = logo;
    return this;
  }

  /**
   * BS vendor logo HREF
   * 
   * @return logo
   **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_LOGO)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getLogo() {
    return logo;
  }

  @JsonProperty(JSON_PROPERTY_LOGO)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setLogo(String logo) {
    this.logo = logo;
  }

  public BsVendor name(String name) {

    this.name = name;
    return this;
  }

  /**
   * BS vendor name This property must no longer be used: the property
   * &#x60;commercialName&#x60; must be used in replacement.
   * 
   * @return name
   * @deprecated
   **/
  @Deprecated
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getName() {
    return name;
  }

  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setName(String name) {
    this.name = name;
  }

  public BsVendor commercialName(String commercialName) {

    this.commercialName = commercialName;
    return this;
  }

  /**
   * Commercial name of the base station manufacturer
   * 
   * @return commercialName
   **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_COMMERCIAL_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCommercialName() {
    return commercialName;
  }

  @JsonProperty(JSON_PROPERTY_COMMERCIAL_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCommercialName(String commercialName) {
    this.commercialName = commercialName;
  }

  public BsVendor commercialDescription(String commercialDescription) {

    this.commercialDescription = commercialDescription;
    return this;
  }

  /**
   * Commercial description of the base station manufacturer
   * 
   * @return commercialDescription
   **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_COMMERCIAL_DESCRIPTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCommercialDescription() {
    return commercialDescription;
  }

  @JsonProperty(JSON_PROPERTY_COMMERCIAL_DESCRIPTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCommercialDescription(String commercialDescription) {
    this.commercialDescription = commercialDescription;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BsVendor bsVendor = (BsVendor) o;
    return Objects.equals(this.ID, bsVendor.ID) && Objects.equals(this.logo, bsVendor.logo)
            && Objects.equals(this.name, bsVendor.name) && Objects.equals(this.commercialName, bsVendor.commercialName)
            && Objects.equals(this.commercialDescription, bsVendor.commercialDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ID, logo, name, commercialName, commercialDescription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BsVendor {\n");
    sb.append("    ID: ").append(toIndentedString(ID)).append("\n");
    sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    commercialName: ").append(toIndentedString(commercialName)).append("\n");
    sb.append("    commercialDescription: ").append(toIndentedString(commercialDescription)).append("\n");
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