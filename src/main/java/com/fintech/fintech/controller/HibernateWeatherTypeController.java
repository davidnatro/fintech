package com.fintech.fintech.controller;

import com.fintech.fintech.data.entity.WeatherType;
import com.fintech.fintech.service.HibernateCrudService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("hibernate/weather-type")
public class HibernateWeatherTypeController {

    private final HibernateCrudService<WeatherType, Long> weatherTypeService;

    @GetMapping
    public ResponseEntity<List<WeatherType>> getAll() {
        return ResponseEntity.ok(weatherTypeService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<WeatherType> getById(@PathVariable Long id) {
        return ResponseEntity.ok(weatherTypeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody WeatherType weatherType) {
        weatherTypeService.create(weatherType);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody WeatherType weatherType) {
        weatherTypeService.update(id, weatherType);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        weatherTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
