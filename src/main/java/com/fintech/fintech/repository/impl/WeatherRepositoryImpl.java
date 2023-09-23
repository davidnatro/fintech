package com.fintech.fintech.repository.impl;

import com.fintech.fintech.data.entity.Weather;
import com.fintech.fintech.repository.WeatherRepository;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.random.RandomGenerator;

public class WeatherRepositoryImpl implements WeatherRepository {

    private static final RandomGenerator randomGenerator = new SecureRandom();

    private final List<Weather> weatherList = new LinkedList<>();

    public WeatherRepositoryImpl() {
        for (int i = 1; i <= 10; i++) {
            Weather weather = new Weather();
            weather.setId((long) i);
            weather.setRegionName("Region #" + i);
            weather.setTemperature(randomGenerator.nextDouble(-50, 50));
            weather.setDateTime(LocalDateTime.now());
            weatherList.add(weather);
        }
    }

    @Override
    public List<Weather> findAll() {
        return weatherList;
    }

    @Override
    public Optional<Weather> findById(Long id) {
        return weatherList.stream()
                .filter(w -> w.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Weather> findAllByTemperatureGreaterThan(Double temperature) {
        return weatherList.stream()
                .filter(w -> w.getTemperature() > temperature)
                .toList();
    }
}
