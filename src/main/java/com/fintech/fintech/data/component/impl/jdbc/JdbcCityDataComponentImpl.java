package com.fintech.fintech.data.component.impl.jdbc;

import com.fintech.fintech.data.component.JdbcDataComponent;
import com.fintech.fintech.data.entity.City;
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
public class JdbcCityDataComponentImpl implements JdbcDataComponent<City, Long> {

    private final CrudRepository<City, Long> cityRepository;

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Long id) {
        Optional<City> city = cityRepository.findById(id);

        if (city.isEmpty()) {
            log.warn("city with '{}' not found", id);
            throw new NotFoundException("city not found");
        }

        return city.get();
    }

    @Override
    public void create(City object) {
        if (cityRepository.findById(object.getId()).isPresent()) {
            log.warn("city with id '{}' already exists", object.getId());
            throw new AlreadyExistsException("city already exists");
        }

        cityRepository.save(object);
    }

    @Override
    public void update(Long id, City object) {
        if (cityRepository.findById(id).isEmpty()) {
            log.warn("city with id '{}' not found", object.getId());
            throw new NotFoundException("city not found");
        }

        cityRepository.save(object);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }
}
