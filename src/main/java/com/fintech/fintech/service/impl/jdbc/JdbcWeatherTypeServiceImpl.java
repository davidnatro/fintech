package com.fintech.fintech.service.impl.jdbc;

import com.fintech.fintech.data.component.HibernateDataComponent;
import com.fintech.fintech.data.entity.WeatherType;
import com.fintech.fintech.service.JdbcCrudService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JdbcWeatherTypeServiceImpl implements JdbcCrudService<WeatherType, Long> {

    private final HibernateDataComponent<WeatherType, Long> weatherTypeDataComponent;

    @Override
    public List<WeatherType> findAll() {
        return weatherTypeDataComponent.findAll();
    }

    @Override
    public WeatherType findById(Long id) {
        return weatherTypeDataComponent.findById(id);
    }

    @Override
    public void create(WeatherType object) {
        weatherTypeDataComponent.create(object);
    }

    @Override
    public void update(Long id, WeatherType object) {
        weatherTypeDataComponent.update(id, object);
    }

    @Override
    public void deleteById(Long id) {
        weatherTypeDataComponent.deleteById(id);
    }
}
