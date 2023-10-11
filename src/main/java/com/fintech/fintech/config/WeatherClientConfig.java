package com.fintech.fintech.config;

import com.fintech.fintech.config.property.WeatherClientProperties;
import com.fintech.fintech.handler.RestTemplateErrorHandler;
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
    public RestTemplate restTemplate(RestTemplateErrorHandler restTemplateErrorHandler) {
        return new RestTemplateBuilder()
                .errorHandler(restTemplateErrorHandler)
                .rootUri(properties.getBaseUrl())
                .build();
    }
}
