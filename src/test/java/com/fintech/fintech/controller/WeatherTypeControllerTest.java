package com.fintech.fintech.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.fintech.data.entity.WeatherType;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WeatherTypeControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Test
    void createWeatherTypeUnauthorizedTest() throws Exception {
        WeatherType weatherType = new WeatherType();
        weatherType.setId(1L);
        weatherType.setType(UUID.randomUUID().toString());

        mvc.perform(post("/hibernate/weather-type").contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(weatherType)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void createWeatherTypeAsUserTest() throws Exception {
        WeatherType weatherType = new WeatherType();
        weatherType.setId(1L);
        weatherType.setType(UUID.randomUUID().toString());

        mvc.perform(post("/hibernate/weather-type").with(httpBasic("user", "password"))
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(weatherType)))
                .andExpect(status().isForbidden());
    }

    @Test
    void createWeatherTypeAsAdminTest() throws Exception {
        WeatherType weatherType = new WeatherType();
        weatherType.setId(1L);
        weatherType.setType(UUID.randomUUID().toString());

        mvc.perform(post("/hibernate/weather-type").with(httpBasic("admin", "password"))
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(weatherType)))
                .andExpect(status().isNoContent());
    }

    @Test
    void getWeatherTypeUnauthorizedTest() throws Exception {
        long id = 1;

        mvc.perform(get("/hibernate/weather-type/{id}", id)).andExpect(status().isUnauthorized());
    }

    @Test
    void getWeatherTypeTest() throws Exception {
        long id = 1;

        mvc.perform(get("/hibernate/weather-type/{id}", id).with(httpBasic("user", "password")))
                .andExpect(status().isOk());
    }
}
