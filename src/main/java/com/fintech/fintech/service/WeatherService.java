package com.fintech.fintech.service;

import com.fintech.fintech.data.dto.WeatherDto;
import com.fintech.fintech.data.entity.Weather;
import java.util.List;
import java.util.Map;

public interface WeatherService {

    Double calculateAverageTemperature();

    Double findCurrentTemperatureByCity(String name);

    Weather create(String city, WeatherDto weatherDto);

    Weather update(String city, WeatherDto weatherDto);

    List<Weather> findAllByTemperatureGreaterThan(Double temperature);

    Map<Long, Double> getIdTemperatureMap();

    Map<Double, List<Weather>> getTemperatureWeathersMap();

    void deleteByName(String name);
}
