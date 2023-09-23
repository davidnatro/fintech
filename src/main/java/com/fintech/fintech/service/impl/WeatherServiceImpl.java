package com.fintech.fintech.service.impl;

import com.fintech.fintech.data.entity.Weather;
import com.fintech.fintech.repository.WeatherRepository;
import com.fintech.fintech.service.WeatherService;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public Double calculateAverageTemperature() {
        return weatherRepository.findAll()
                .stream()
                .mapToDouble(Weather::getTemperature)
                .average()
                .orElse(-1);
    }

    @Override
    public List<Weather> findAllByTemperatureGreaterThan(Double temperature) {
        return weatherRepository.findAllByTemperatureGreaterThan(temperature);
    }

    @Override
    public Map<Long, Double> getIdsAndTemperatures() {
        return weatherRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Weather::getId, Weather::getTemperature));
    }

    @Override
    public Map<Double, List<Weather>> getAllWeathersByTemperature() {
        return weatherRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Weather::getTemperature,
                                               Collectors.toCollection(LinkedList::new)));
    }
}
