package com.fintech.fintech.config;

import com.fintech.fintech.data.entity.City;
import com.fintech.fintech.data.entity.Weather;
import com.fintech.fintech.data.entity.WeatherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Данный класс требуется для того, чтобы проверять
 * информацию о том, содержит ли entity кастомную аннотацию @Table
 */
@Configuration
public class ClassTypesConfig {

    @Bean
    public Class<City> cityClass() {
        return City.class;
    }

    @Bean
    public Class<Weather>  weatherClass() {
        return Weather.class;
    }

    @Bean
    public Class<WeatherType> weatherTypeClass() {
        return WeatherType.class;
    }
}
