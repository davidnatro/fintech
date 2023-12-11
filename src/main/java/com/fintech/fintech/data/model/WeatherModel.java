package com.fintech.fintech.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class WeatherModel {

  private String name;
  private String description;
  private Double celsius;
  private Double fahrenheit;

  @JsonProperty("location")
  public void setName(Map<String, String> location) {
    this.name = location.get("name");
  }

  @JsonProperty("current")
  public void setTemperatureAndDescription(Map<String, Object> current) {
    try {
      this.description = ((Map<String, String>) current.get("condition")).get("text");
      this.celsius = Double.parseDouble(current.get("temp_c").toString());
      this.fahrenheit = Double.parseDouble(current.get("temp_f").toString());
    } catch (NumberFormatException exception) {
      log.warn("cannot parse temperature -> message: '{}'", exception.getMessage());
    } catch (ClassCastException exception) {
      log.warn("cannot parse json to get description -> message: '{}'", exception.getMessage());
    }
  }
}
