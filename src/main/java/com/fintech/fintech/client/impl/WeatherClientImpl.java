package com.fintech.fintech.client.impl;

import com.fintech.fintech.client.WeatherClient;
import com.fintech.fintech.config.property.WeatherClientProperty;
import com.fintech.fintech.data.model.WeatherModel;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherClientImpl implements WeatherClient {

  private final RestTemplate restTemplate;
  private final WeatherClientProperty property;

  @Override
  public WeatherModel getCurrentWeather(String city, String language) {
    if (StringUtils.isBlank(language)) {
      language = property.getDefaultLanguage();
    }

    return restTemplate.getForObject(property.getCurrentWeatherUri(), WeatherModel.class,
                                     Map.of("city", city, "language", language, "key",
                                            property.getApiKey()));
  }
}

