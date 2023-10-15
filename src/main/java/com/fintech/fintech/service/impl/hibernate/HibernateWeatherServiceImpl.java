package com.fintech.fintech.service.impl.hibernate;

import com.fintech.fintech.data.component.HibernateDataComponent;
import com.fintech.fintech.data.entity.Weather;
import com.fintech.fintech.service.HibernateCrudService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HibernateWeatherServiceImpl implements HibernateCrudService<Weather, Long> {

    private final HibernateDataComponent<Weather, Long> weatherDataComponent;

    @Override
    public List<Weather> findAll() {
        return weatherDataComponent.findAll();
    }

    @Override
    public Weather findById(Long id) {
        return weatherDataComponent.findById(id);
    }

    @Override
    public void create(Weather object) {
        weatherDataComponent.create(object);
    }

    @Override
    public void update(Long id, Weather object) {
        weatherDataComponent.update(id, object);
    }

    @Override
    public void deleteById(Long id) {
        weatherDataComponent.deleteById(id);
    }
}
