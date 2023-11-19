package com.fintech.fintech.service.impl.hibernate;

import com.fintech.fintech.config.property.CacheProperty;
import com.fintech.fintech.data.cache.WeatherCache;
import com.fintech.fintech.data.component.HibernateDataComponent;
import com.fintech.fintech.data.entity.City;
import com.fintech.fintech.service.HibernateCrudService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HibernateCityServiceImpl implements HibernateCrudService<City, Long> {

    private final Map<Long, City> weatherCache;
    private final HibernateDataComponent<City, Long> cityDataComponent;

    public HibernateCityServiceImpl(CacheProperty cacheProperty,
                                    HibernateDataComponent<City, Long> cityDataComponent) {
        this.cityDataComponent = cityDataComponent;
        this.weatherCache = Collections.synchronizedMap(new WeatherCache<>(cacheProperty));
    }

    @Override
    public List<City> findAll() {
        return cityDataComponent.findAll();
    }

    @Override
    public City findById(Long id) {
        if (weatherCache.containsKey(id)) {
            return weatherCache.get(id);
        }

        City city = cityDataComponent.findById(id);
        weatherCache.put(city.getId(), city);
        return city;
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
