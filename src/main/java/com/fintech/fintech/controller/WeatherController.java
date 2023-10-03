package com.fintech.fintech.controller;

import com.fintech.fintech.data.dto.WeatherDto;
import com.fintech.fintech.data.entity.Weather;
import com.fintech.fintech.service.WeatherService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
@RequestMapping("weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("{city}")
    @ApiResponses(value = { @ApiResponse(responseCode = "200",
            description = "current temperature for city",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Double.class))),
            @ApiResponse(responseCode = "404",
                    description = "city not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema)) })
    public ResponseEntity<Double> getCurrentWeatherByCityName(@PathVariable String city) {
        return ResponseEntity.ok(weatherService.findCurrentTemperatureByCity(city));
    }

    @PostMapping("{city}")
    @ApiResponses(value = { @ApiResponse(responseCode = "200",
            description = "weather created successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Weather.class))),
            @ApiResponse(responseCode = "400",
                    description = "failed parameters validation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "409",
                    description = "weather already exists for that city",
                    content = @Content) })
    public ResponseEntity<Weather> addWeatherByCityName(@PathVariable String city,
                                                        @Valid @RequestBody WeatherDto weatherDto) {
        return ResponseEntity.ok(weatherService.create(city, weatherDto));
    }

    @PutMapping("{city}")
    @ApiResponses(value = { @ApiResponse(responseCode = "200",
            description = "weather updated successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Weather.class))),
            @ApiResponse(responseCode = "400",
                    description = "failed parameters validation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description = "weather for that city not found",
                    content = @Content) })
    public ResponseEntity<Weather> updateWeatherByCityName(@PathVariable String city,
                                                           @Valid @RequestBody WeatherDto weatherDto) {
        return ResponseEntity.ok(weatherService.update(city, weatherDto));
    }

    @DeleteMapping("{city}")
    @ApiResponses(value = { @ApiResponse(responseCode = "204",
            description = "weather was deleted successfully if existed",
            content = @Content) })
    public ResponseEntity<Void> deleteWeatherByCityName(@PathVariable String city) {
        weatherService.deleteByName(city);
        return ResponseEntity.noContent().build();
    }
}
