package com.fintech.fintech.service;

import com.fintech.fintech.data.model.WeatherModel;

public interface WeatherService {

    WeatherModel getCurrentWeatherByCity(String city, String language);
}
