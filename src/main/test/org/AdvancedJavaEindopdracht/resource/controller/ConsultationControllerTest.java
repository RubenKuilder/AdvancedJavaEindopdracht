package org.AdvancedJavaEindopdracht.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.AdvancedJavaEindopdracht.resource.model.User;
import org.AdvancedJavaEindopdracht.resource.model.consultation.Consultation;
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

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        User user = new User();
        List<User> usersList = Arrays.asList(user);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("12-12-2021 00:00:00");
        Date endDateTime = sdf.parse("01-01-2022 00:00:00");

        Consultation consultation = new Consultation();
        consultation.setStartDateTime(startDateTime);
        consultation.setEndDateTime(endDateTime);
        consultation.setUsers(usersList);

        this.mockMvc.perform(post("/consultation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(consultation)))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.startDateTime").value("12-12-2021 00:00:00"))
                .andExpect(jsonPath("$.endDateTime").value("01-01-2022 00:00:00"));
    }

    @Test
    void putConsultation() throws Exception
    {
        User user = new User();
        List<User> usersList = Arrays.asList(user);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("12-12-1999 00:00:00");
        Date endDateTime = sdf.parse("01-01-2030 00:00:00");

        Consultation consultation = new Consultation();
        consultation.setStartDateTime(startDateTime);
        consultation.setEndDateTime(endDateTime);
        consultation.setUsers(usersList);

        this.mockMvc.perform(put("/consultation/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(consultation)))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.startDateTime").value("12-12-1999 00:00:00"))
                .andExpect(jsonPath("$.endDateTime").value("01-01-2030 00:00:00"));
    }

    @Test
    void patchConsultation() throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("12-12-1999 00:00:00");

        Consultation consultation = new Consultation();
        consultation.setStartDateTime(startDateTime);

        this.mockMvc.perform(patch("/consultation/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(consultation)))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.startDateTime").value("12-12-1999 00:00:00"))
                .andExpect(jsonPath("$.endDateTime").value("08-12-2022 00:00:00"));
    }

    @Test
    void deleteConsultation() throws Exception
    {
        this.mockMvc.perform(delete("/consultation/1"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.startDateTime").value("08-11-2021 00:00:00"))
                .andExpect(jsonPath("$.endDateTime").value("08-12-2022 00:00:00"));
    }
}