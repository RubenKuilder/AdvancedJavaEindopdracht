package org.AdvancedJavaEindopdracht.resource.controller;

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

@SpringJUnitWebConfig(classes = com.configuration.DatabaseConfigTest.class)
@Transactional
class ConsultationControllerTest
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
    void getAllConsultation() throws Exception
    {
        this.mockMvc.perform(get("/consultation").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[0].startDateTime").value("08-11-2021 00:00:00"))
                .andExpect(jsonPath("$.[0].endDateTime").value("08-12-2022 00:00:00"))
                .andExpect(jsonPath("$.[1].startDateTime").value("01-01-2021 00:00:00"))
                .andExpect(jsonPath("$.[1].endDateTime").value("01-01-2022 00:00:00"));
    }

    @Test
    void getById() throws Exception
    {
        this.mockMvc.perform(get("/consultation/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.startDateTime").value("08-11-2021 00:00:00"))
                .andExpect(jsonPath("$.endDateTime").value("08-12-2022 00:00:00"));
    }

    @Test
    void postConsultation() throws Exception
    {
        this.mockMvc.perform(post("/consultation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"startDateTime\":\"12-12-2021 00:00:00\",\"endDateTime\":\"01-01-2022 00:00:00\"}"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.startDateTime").value("12-12-2021 00:00:00"))
                .andExpect(jsonPath("$.endDateTime").value("01-01-2022 00:00:00"));
    }

    @Test
    void putConsultation() throws Exception
    {

    }

    @Test
    void patchConsultation() throws Exception
    {

    }

    @Test
    void deleteConsultation() throws Exception
    {

    }
}