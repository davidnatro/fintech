package com.fintech.fintech.data.component.impl.jdbc;

import com.fintech.fintech.data.component.JdbcDataComponent;
import com.fintech.fintech.data.entity.WeatherType;
import com.fintech.fintech.data.repository.jdbc.CrudRepository;
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
public class JdbcWeatherTypeDataComponentImpl implements JdbcDataComponent<WeatherType, Long> {

    private final CrudRepository<WeatherType, Long> weatherTypeRepository;

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
        if (weatherTypeRepository.findById(object.getId()).isPresent()) {
            log.warn("weather type with id '{}' already exists", object.getId());
            throw new AlreadyExistsException("weather type already exists");
        }

        weatherTypeRepository.save(object);
    }

    @Override
    public void update(Long id, WeatherType object) {
        if (weatherTypeRepository.findById(id).isEmpty()) {
            log.warn("weather type with id '{}' not found", object.getId());
            throw new NotFoundException("weather type not found");
        }

        weatherTypeRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        weatherTypeRepository.deleteById(id);
    }
}
