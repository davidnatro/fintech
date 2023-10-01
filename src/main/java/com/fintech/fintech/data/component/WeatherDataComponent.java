package com.fintech.fintech.data.component;

import com.fintech.fintech.data.entity.Weather;
import java.util.List;

public interface WeatherDataComponent {

    List<Weather> findAll();

    Weather findByName(String name);

    Weather findById(Long id);

    Weather create(Weather weather);

    Weather update(Weather weather);

    void deleteByName(String name);
}
