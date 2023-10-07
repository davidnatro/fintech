package com.fintech.fintech.controller;

import com.fintech.fintech.data.model.WeatherModel;
import com.fintech.fintech.service.WeatherService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("{city}/current")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "weather of passed city",
                    content = @Content(schema = @Schema(contentMediaType = MediaType.APPLICATION_JSON_VALUE,
                            implementation = WeatherModel.class))),
            @ApiResponse(responseCode = "401", description = "invalid api key", content = @Content) })
    public ResponseEntity<WeatherModel> getCurrentWeather(@PathVariable String city,
                                                          @RequestParam(required = false) String language) {
        return ResponseEntity.ok(weatherService.getCurrentWeatherByCity(city, language));
    }
}
