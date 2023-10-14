package com.fintech.fintech.data.repository;

import com.fintech.fintech.data.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> { }
