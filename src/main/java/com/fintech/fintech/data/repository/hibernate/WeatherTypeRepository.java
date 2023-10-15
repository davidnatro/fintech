package com.fintech.fintech.data.repository.hibernate;

import com.fintech.fintech.data.entity.WeatherType;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherTypeRepository extends ListCrudRepository<WeatherType, Long> { }
