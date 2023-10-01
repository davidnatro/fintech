package com.fintech.fintech.repository;

import com.fintech.fintech.data.entity.Weather;
import java.util.List;
import java.util.Optional;

public interface WeatherRepository {

    List<Weather> findAll();

    Optional<Weather> findById(Long id);

    Optional<Weather> findByName(String name);

    Weather add(Weather weather);

    Weather update(Weather weather);

    boolean existsById(Long id);

    boolean existsByName(String name);

    void deleteById(Long id);

    void deleteByName(String name);
}
