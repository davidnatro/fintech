package com.fintech.fintech.data.component.impl;

import com.fintech.fintech.data.component.WeatherDataComponent;
import com.fintech.fintech.data.entity.Weather;
import com.fintech.fintech.exception.AlreadyExistsException;
import com.fintech.fintech.exception.NotFoundException;
import com.fintech.fintech.repository.WeatherRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherDataComponentImpl implements WeatherDataComponent {

    private final WeatherRepository weatherRepository;

    @Override
    public List<Weather> findAll() {
        return weatherRepository.findAll();
    }

    @Override
    public Weather findByName(String name) {
        Optional<Weather> weather = weatherRepository.findByName(name);

        if (weather.isEmpty()) {
            log.warn("weather by city name '{}' not found", name);
            throw new NotFoundException("weather not found");
        }

        return weather.get();
    }

    @Override
    public Weather findById(Long id) {
        Optional<Weather> weather = weatherRepository.findById(id);

        if (weather.isEmpty()) {
            log.warn("weather by city id '{}' not found", id);
            throw new NotFoundException("weather not found");
        }

        return weather.get();
    }

    @Override
    public Weather create(Weather weather) {
        if (weatherRepository.existsByName(weather.getRegionName())) {
            log.warn("weather for region '{}' already exists", weather.getRegionName());
            throw new AlreadyExistsException("weather already exists");
        }

        return weatherRepository.add(weather);
    }

    @Override
    public Weather update(Weather weather) {
        if (weather.getId() == null || !weatherRepository.existsById(weather.getId())) {
            log.warn("weather with id '{}' not found", weather.getId());
            throw new NotFoundException("weather not found");
        }

        return weatherRepository.update(weather);
    }

    @Override
    public void deleteByName(String name) {
        weatherRepository.deleteByName(name);
    }
}
