package com.fintech.fintech.repository;

import com.fintech.fintech.data.entity.Weather;
import java.util.List;
import java.util.Optional;

public interface WeatherRepository {

    List<Weather> findAll();

    Optional<Weather> findById(Long id);

    List<Weather> findAllByTemperatureGreaterThan(Double temperature);
}
