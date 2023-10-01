package com.fintech.fintech.service.impl;

import com.fintech.fintech.data.component.WeatherDataComponent;
import com.fintech.fintech.data.dto.WeatherDto;
import com.fintech.fintech.data.entity.Weather;
import com.fintech.fintech.service.WeatherService;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherDataComponent weatherDataComponent;

    @Override
    public Double calculateAverageTemperature() {
        return weatherDataComponent.findAll()
                .stream()
                .mapToDouble(Weather::getTemperature)
                .average()
                .orElse(-1);
    }

    @Override
    public Double findCurrentTemperatureByCity(String name) {
        return weatherDataComponent.findByName(name).getTemperature();
    }

    @Override
    public Weather create(String city, WeatherDto weatherDto) {
        Weather weather = new Weather();
        weather.setRegionName(city);
        weather.setTemperature(weatherDto.getTemperature());
        weather.setDateTime(weatherDto.getLocalDateTime());
        return weatherDataComponent.create(weather);
    }

    @Override
    public Weather update(String city, WeatherDto weatherDto) {
        Weather weather = weatherDataComponent.findByName(city);
        weather.setTemperature(weatherDto.getTemperature());
        weather.setDateTime(weatherDto.getLocalDateTime());
        return weatherDataComponent.update(weather);
    }

    @Override
    public List<Weather> findAllByTemperatureGreaterThan(Double temperature) {
        return weatherDataComponent.findAll()
                .stream()
                .filter(w -> w.getTemperature() > temperature)
                .toList();
    }

    @Override
    public Map<Long, Double> getIdTemperatureMap() {
        return weatherDataComponent.findAll()
                .stream()
                .collect(Collectors.toMap(Weather::getId, Weather::getTemperature));
    }

    @Override
    public Map<Double, List<Weather>> getTemperatureWeathersMap() {
        return weatherDataComponent.findAll()
                .stream()
                .collect(Collectors.groupingBy(Weather::getTemperature,
                                               Collectors.toCollection(LinkedList::new)));
    }

    @Override
    public void deleteByName(String name) {
        weatherDataComponent.deleteByName(name);
    }
}
