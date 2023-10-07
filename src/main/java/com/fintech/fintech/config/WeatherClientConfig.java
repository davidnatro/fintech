package com.fintech.fintech.config;

import com.fintech.fintech.config.property.WeatherClientProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class WeatherClientConfig {

    private final WeatherClientProperties properties;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .rootUri(properties.getBaseUrl())
                .build();
    }
}
