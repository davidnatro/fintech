package com.fintech.fintech.service.impl;

import com.fintech.fintech.client.WeatherClient;
import com.fintech.fintech.data.model.WeatherModel;
import com.fintech.fintech.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherClient weatherClient;

    @Override
    public WeatherModel getCurrentWeatherByCity(String city, String language) {
        return weatherClient.getCurrentWeather(city, language);
    }
}
