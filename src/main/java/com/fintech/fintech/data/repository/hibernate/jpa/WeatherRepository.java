package com.fintech.fintech.data.repository.hibernate.jpa;

import com.fintech.fintech.data.entity.Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> { }
