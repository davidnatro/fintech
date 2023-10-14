package com.fintech.fintech.data.repository;

import com.fintech.fintech.data.entity.Weather;
import com.fintech.fintech.data.entity.WeatherType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> { }
