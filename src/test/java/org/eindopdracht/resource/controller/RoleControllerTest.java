package org.eindopdracht.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eindopdracht.resource.model.*;
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
@ContextConfiguration(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
public class RoleControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetRoles() throws Exception {
        mockMvc.perform(get("/roles").contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].role").value("user"))
                .andExpect(jsonPath("$.[1].role").value("admin"));
    }

    @Test
    public void testGetRole() throws Exception {
        mockMvc.perform(get("/roles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("user"));
    }

    @Test
    @DirtiesContext
    public void testDeleteRoles() throws Exception {
        mockMvc.perform(delete("/roles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("user"));
    }

    @Test
    public void testPostRole() throws Exception {
        Role role = new Role();
        role.setRole("test");

        mockMvc.perform(post("/roles")
                        .content(new ObjectMapper().writeValueAsString(role))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("test"));

    }

    @Test
    public void testPutRoles() throws Exception {
        Role role = new Role();
        role.setRole("test");

        mockMvc.perform(put("/roles/1")
                        .content(new ObjectMapper().writeValueAsString(role))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("test"));

    }
}
