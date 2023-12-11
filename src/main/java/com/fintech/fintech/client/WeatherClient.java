package com.fintech.fintech.client;

import com.fintech.fintech.data.model.WeatherModel;

public interface WeatherClient {

  WeatherModel getCurrentWeather(String city, String language);
}
