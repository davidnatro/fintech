package com.fintech.fintech.client.impl;

import com.fintech.fintech.client.WeatherClient;
import com.fintech.fintech.config.property.WeatherClientProperties;
import com.fintech.fintech.data.model.WeatherModel;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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
    private final WeatherClientProperties properties;

    @Override
    @RateLimiter(name = "weather-api-limiter")
    public WeatherModel getCurrentWeather(String city, String language) {
        if (StringUtils.isBlank(language)) {
            language = properties.getDefaultLanguage();
        }

        return restTemplate.getForObject(
                properties.getCurrentWeatherUri(),
                WeatherModel.class,
                Map.of("city", city,
                       "language", language,
                       "key", properties.getApiKey()));
    }
}
