package com.fintech.fintech.data.component.impl.hibernate;

import com.fintech.fintech.data.component.HibernateDataComponent;
import com.fintech.fintech.data.entity.WeatherType;
import com.fintech.fintech.data.repository.hibernate.WeatherTypeRepository;
import com.fintech.fintech.exception.AlreadyExistsException;
import com.fintech.fintech.exception.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class HibernateWeatherTypeDataComponentImpl implements
        HibernateDataComponent<WeatherType, Long> {

    private final WeatherTypeRepository weatherTypeRepository;

    @Override
    public List<WeatherType> findAll() {
        return weatherTypeRepository.findAll();
    }

    @Override
    public WeatherType findById(Long id) {
        Optional<WeatherType> weatherType = weatherTypeRepository.findById(id);

        if (weatherType.isEmpty()) {
            log.warn("weather type with '{}' not found", id);
            throw new NotFoundException("weather type not found");
        }

        return weatherType.get();
    }

    @Override
    public void create(WeatherType object) {
        if (weatherTypeRepository.existsById(object.getId())) {
            log.warn("weather type with id '{}' already exists", object.getId());
            throw new AlreadyExistsException("weather type already exists");
        }

        weatherTypeRepository.save(object);
    }

    @Override
    public void update(Long id, WeatherType object) {
        if (!weatherTypeRepository.existsById(id)) {
            log.warn("weather type with id '{}' not found", object.getId());
            throw new NotFoundException("weather type not found");
        }

        weatherTypeRepository.save(object);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        weatherTypeRepository.deleteById(id);
    }
}
