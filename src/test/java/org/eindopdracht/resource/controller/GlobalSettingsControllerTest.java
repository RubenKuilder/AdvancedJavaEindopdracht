package org.eindopdracht.resource.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
class GlobalSettingsControllerTest
{
    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webContext).build();
    }

    @Test
    @Order(1)
    void getGlobalSettings() throws Exception
    {
        this.mockMvc.perform(get("/settings").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[0].soundOn").value(true))
                .andExpect(jsonPath("$.[0].switchTime").value("00:00:00"))
                .andExpect(jsonPath("$.[1].soundOn").value(false))
                .andExpect(jsonPath("$.[1].switchTime").value("12:00:00"));
    }

    @Test
    @Order(2)
    void getGlobalSettingsById() throws Exception
    {
        this.mockMvc.perform(get("/settings/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.soundOn").value(true))
                .andExpect(jsonPath("$.switchTime").value("00:00:00"));
    }

    @Test
    @Order(3)
    void createGlobalSettings() throws Exception
    {
        this.mockMvc.perform(post("/settings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"soundOn\":false,\"switchTime\":\"00:12:03\"}"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.soundOn").value(false))
                .andExpect(jsonPath("$.switchTime").value("00:12:03"));
    }

    @Test
    @Order(4)
    void deleteGlobalSettings() throws Exception
    {
        this.mockMvc.perform(delete("/settings/1"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.soundOn").value(true))
                .andExpect(jsonPath("$.switchTime").value("00:00:00"));
    }

    @Test
    @Order(5)
    void updateGlobalSettings() throws Exception
    {
        this.mockMvc.perform(put("/settings/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"soundOn\":false,\"switchTime\":\"00:12:03\"}"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.soundOn").value(false))
                .andExpect(jsonPath("$.switchTime").value("00:12:03"));
    }
}