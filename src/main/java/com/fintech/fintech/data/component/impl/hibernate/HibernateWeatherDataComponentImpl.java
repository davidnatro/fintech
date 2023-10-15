package com.fintech.fintech.data.component.impl.hibernate;

import com.fintech.fintech.data.component.HibernateDataComponent;
import com.fintech.fintech.data.entity.Weather;
import com.fintech.fintech.data.repository.hibernate.WeatherRepository;
import com.fintech.fintech.exception.AlreadyExistsException;
import com.fintech.fintech.exception.NotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class HibernateWeatherDataComponentImpl implements HibernateDataComponent<Weather, Long> {

    private final WeatherRepository weatherRepository;

    @Override
    public List<Weather> findAll() {
        return weatherRepository.findAll();
    }

    @Override
    public Weather findById(Long id) {
        Optional<Weather> weather = weatherRepository.findById(id);

        if (weather.isEmpty()) {
            log.warn("weather with '{}' not found", id);
            throw new NotFoundException("weather not found");
        }

        return weather.get();
    }

    @Override
    public void create(Weather object) {
        if (weatherRepository.existsById(object.getId())) {
            log.warn("weather with id '{}' already exists", object.getId());
            throw new AlreadyExistsException("weather already exists");
        }

        weatherRepository.save(object);
    }

    @Override
    public void update(Long id, Weather object) {
        if (!weatherRepository.existsById(id)) {
            log.warn("weather with id '{}' not found", object.getId());
            throw new NotFoundException("weather not found");
        }

        weatherRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        weatherRepository.deleteById(id);
    }
}
