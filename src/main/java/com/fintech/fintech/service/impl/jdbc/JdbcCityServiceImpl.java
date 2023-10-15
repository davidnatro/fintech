package com.fintech.fintech.service.impl.jdbc;

import com.fintech.fintech.data.component.JdbcDataComponent;
import com.fintech.fintech.data.entity.City;
import com.fintech.fintech.service.JdbcCrudService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JdbcCityServiceImpl implements JdbcCrudService<City, Long> {

    private final JdbcDataComponent<City, Long> cityDataComponent;

    @Override
    public List<City> findAll() {
        return cityDataComponent.findAll();
    }

    @Override
    public City findById(Long id) {
        return cityDataComponent.findById(id);
    }

    @Override
    public void create(City object) {
        cityDataComponent.create(object);
    }

    @Override
    public void update(Long id, City object) {
        cityDataComponent.update(id, object);
    }

    @Override
    public void deleteById(Long id) {
        cityDataComponent.deleteById(id);
    }
}
