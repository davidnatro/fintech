package com.fintech.fintech.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.fintech.data.entity.City;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerTest {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  private MockMvc mvc;

  @Test
  void createCityUnauthorizedTest() throws Exception {
    City city = new City();
    city.setId(1L);
    city.setName(UUID.randomUUID().toString());

    mvc.perform(post("/hibernate/city").contentType(MediaType.APPLICATION_JSON)
                                       .content(objectMapper.writeValueAsString(city)))
       .andExpect(status().isUnauthorized());
  }

  @Test
  void createCityAsAdminTest() throws Exception {
    City city = new City();
    city.setId(1L);
    city.setName(UUID.randomUUID().toString());

    mvc.perform(post("/hibernate/city").contentType(MediaType.APPLICATION_JSON)
                                       .content(objectMapper.writeValueAsString(city))
                                       .with(httpBasic("admin", "password")))
       .andExpect(status().isNoContent());
  }

  @Test
  void createCityAsUserTest() throws Exception {
    City city = new City();
    city.setId(1L);
    city.setName(UUID.randomUUID().toString());

    mvc.perform(post("/hibernate/city").contentType(MediaType.APPLICATION_JSON)
                                       .content(objectMapper.writeValueAsString(city))
                                       .with(httpBasic("user", "password")))
       .andExpect(status().isForbidden());
  }

  @Test
  void getCityUnauthorizedTest() throws Exception {
    long id = 1;

    mvc.perform(get("/hibernate/city/{id}", id)).andExpect(status().isUnauthorized());
  }

  @Test
  void getCityTest() throws Exception {
    long id = 1;

    mvc.perform(get("/hibernate/city/{id}", id).with(httpBasic("user", "password")))
       .andExpect(status().isOk());
  }
}
