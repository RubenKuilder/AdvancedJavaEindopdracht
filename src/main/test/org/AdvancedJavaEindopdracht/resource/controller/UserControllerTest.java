package org.AdvancedJavaEindopdracht.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.AdvancedJavaEindopdracht.resource.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Transactional
@ContextConfiguration(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@SpringJUnitWebConfig(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetUsers() throws Exception {
        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("Madlyaza"))
                .andExpect(jsonPath("$.[0].email").value("thijs"));
    }

    @Test
    public void testGetUser() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Madlyaza"))
                .andExpect(jsonPath("$.email").value("thijs"));
    }

    @Test
    @DirtiesContext
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Madlyaza"))
                .andExpect(jsonPath("$.email").value("thijs"));
    }

    @Test
    public void testPostusers() throws Exception {

        User user = new User();

        user.setName("test");
        user.setPassword("test");
        user.setApproved(true);
        user.setProfileImagePath("test");
        user.setEmail("test");


        mockMvc.perform(post("/users")
                        .content(new ObjectMapper().writeValueAsString(user))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.email").value("test"));

    }

    @Test
    public void testPutUsers() throws Exception {

        User user = new User();

        user.setName("test");
        user.setPassword("test");
        user.setApproved(true);
        user.setProfileImagePath("test");
        user.setEmail("test");


        mockMvc.perform(put("/users/1")
                        .content(new ObjectMapper().writeValueAsString(user))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.email").value("test"));

    }
}
