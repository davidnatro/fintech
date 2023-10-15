package com.fintech.fintech.controller;

import com.fintech.fintech.data.entity.City;
import com.fintech.fintech.data.repository.jdbc.impl.CityRepositoryImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("jdbc/weather")
public class JdbcWeatherController {

    private final CityRepositoryImpl cityRepository;

    @GetMapping
    public ResponseEntity<List<City>> getCities() {
        return ResponseEntity.ok(cityRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<City> getCities(@PathVariable Long id) {
        return ResponseEntity.ok(cityRepository.findById(id).get());
    }
}