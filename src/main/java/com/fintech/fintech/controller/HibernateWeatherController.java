package com.fintech.fintech.controller;

import com.fintech.fintech.data.entity.Weather;
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
@RequestMapping("hibernate/weather")
public class HibernateWeatherController {

    private final HibernateCrudService<Weather, Long> weatherService;

    @GetMapping
    public ResponseEntity<List<Weather>> getAll() {
        return ResponseEntity.ok(weatherService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Weather> getById(@PathVariable Long id) {
        return ResponseEntity.ok(weatherService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Weather weather) {
        weatherService.create(weather);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Weather weather) {
        weatherService.update(id, weather);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        weatherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
