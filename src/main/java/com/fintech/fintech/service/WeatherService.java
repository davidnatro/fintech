package com.fintech.fintech.service;

import com.fintech.fintech.data.entity.Weather;
import java.util.List;
import java.util.Map;

public interface WeatherService {

    Double calculateAverageTemperature();

    List<Weather> findAllByTemperatureGreaterThan(Double temperature);

    Map<Long, Double> getIdTemperatureMap();

    Map<Double, List<Weather>> getTemperatureWeathersMap();
}
