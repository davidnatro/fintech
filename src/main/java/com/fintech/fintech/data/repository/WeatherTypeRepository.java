package com.fintech.fintech.data.repository;

import com.fintech.fintech.data.entity.WeatherType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherTypeRepository extends CrudRepository<WeatherType, Long> { }
