package com.fintech.fintech.config;

import com.fintech.fintech.config.property.WeatherClientProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class WeatherClientConfig {

  private final WeatherClientProperty property;

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplateBuilder().rootUri(property.getBaseUrl()).build();
  }
}