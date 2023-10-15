package com.fintech.fintech.data.component.impl.jdbc;

import com.fintech.fintech.data.component.JdbcDataComponent;
import com.fintech.fintech.data.entity.City;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class JdbcCityDataComponentImpl implements JdbcDataComponent<City, Long> {

    @Override
    public List<City> findAll() {
        return null;
    }

    @Override
    public City findById(Long aLong) {
        return null;
    }

    @Override
    public void create(City object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
