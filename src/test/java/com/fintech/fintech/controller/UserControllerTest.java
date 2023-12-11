package com.fintech.fintech.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.fintech.data.entity.User;
import com.fintech.fintech.service.HibernateCrudService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void getUserUnauthorizedTest() throws Exception {
        long id = 1;

        mvc.perform(get("/user/{id}", id)).andExpect(status().isUnauthorized());
    }

    @Test
    void getUserTest() throws Exception {
        long id = 1;

        mvc.perform(get("/user/{id}", id).with(httpBasic("user", "password")))
                .andExpect(status().isOk());
    }

    @Test
    void createUserUnauthorizedTest() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode("password"));

        mvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void createUserAsUserTest() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode("password"));

        mvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(user))
                            .with(httpBasic("user", "password"))).andExpect(status().isForbidden());
    }

    @Test
    void createUserAsAdminTest() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode("password"));

        mvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(user))
                            .with(httpBasic("admin", "password"))).andExpect(status().isOk());
    }
}
