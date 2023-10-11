package com.fintech.fintech.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application.weather-api")
public class WeatherClientProperties {

    private String baseUrl;
    private String apiKey;
    private String defaultLanguage;
    private String currentWeatherUri;
}
