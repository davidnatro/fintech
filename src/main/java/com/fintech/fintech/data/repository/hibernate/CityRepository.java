package com.fintech.fintech.data.repository.hibernate;

import com.fintech.fintech.data.entity.City;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends ListCrudRepository<City, Long> { }
