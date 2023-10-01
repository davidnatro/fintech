package com.fintech.fintech.repository.impl;

import com.fintech.fintech.data.entity.Weather;
import com.fintech.fintech.exception.NotFoundException;
import com.fintech.fintech.repository.WeatherRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class WeatherRepositoryImpl implements WeatherRepository {

    private static final AtomicLong idCounter = new AtomicLong(0);
    private final Set<Weather> weatherList = new HashSet<>();

    @Override
    public List<Weather> findAll() {
        return weatherList.stream().toList();
    }

    @Override
    public Optional<Weather> findById(Long id) {
        return weatherList.stream().filter(w -> w.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Weather> findByName(String name) {
        return weatherList.stream().filter(w -> w.getRegionName().equals(name)).findFirst();
    }

    @Override
    public Weather add(Weather weather) {
        return this.add(weather, idCounter.incrementAndGet());
    }

    @Override
    public Weather update(Weather weather) {
        if (!this.existsById(weather.getId())) {
            log.warn("Weather with id '{}' doesnt exists", weather.getId());
            throw new NotFoundException("weather not found");
        }

        this.deleteById(weather.getId());
        return this.add(weather, weather.getId());
    }

    @Override
    public boolean existsById(Long id) {
        return weatherList.stream().anyMatch(w -> w.getId().equals(id));
    }

    @Override
    public boolean existsByName(String name) {
        return weatherList.stream().anyMatch(w -> w.getRegionName().equals(name));
    }

    @Override
    public void deleteById(Long id) {
        weatherList.removeIf(w -> w.getId().equals(id));
    }

    @Override
    public void deleteByName(String name) {
        weatherList.removeIf(w -> w.getRegionName().equals(name));
    }

    private Weather add(Weather weather, Long id) {
        if (weather.getRegionName() == null) {
            log.warn("weather persistent saving failed -> message: region name cannot be null");
            throw new IllegalArgumentException(
                    "constraint violation -> message: region name violates non null constraint");
        }

        weather.setId(id);
        weatherList.add(weather);
        return weather;
    }
}
