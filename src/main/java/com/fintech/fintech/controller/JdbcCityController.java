package com.fintech.fintech.controller;

import com.fintech.fintech.data.entity.City;
import com.fintech.fintech.service.JdbcCrudService;
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
@RequestMapping("jdbc/city")
public class JdbcCityController {

    private final JdbcCrudService<City, Long> cityService;

    @GetMapping
    public ResponseEntity<List<City>> getAll() {
        return ResponseEntity.ok(cityService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<City> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody City city) {
        cityService.create(city);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody City city) {
        cityService.update(id, city);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
