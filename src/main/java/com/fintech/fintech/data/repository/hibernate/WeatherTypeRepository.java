package com.fintech.fintech.data.repository.hibernate;

import com.fintech.fintech.data.entity.WeatherType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherTypeRepository extends JpaRepository<WeatherType, Long> { }
