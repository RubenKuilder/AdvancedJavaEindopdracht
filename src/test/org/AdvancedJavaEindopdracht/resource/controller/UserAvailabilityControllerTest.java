package org.AdvancedJavaEindopdracht.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.AdvancedJavaEindopdracht.resource.model.User;
import org.AdvancedJavaEindopdracht.resource.model.UserAvailability;
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

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Transactional
@ContextConfiguration(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@SpringJUnitWebConfig(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
public class UserAvailabilityControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetUserAvailabilities() throws Exception {
        mockMvc.perform(get("/availability").contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].date").value("08-12-2021 00:00:00"));
    }

    @Test
    public void testGetUserAvailability() throws Exception {
        mockMvc.perform(get("/availability/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value("08-12-2021 00:00:00"));
    }

    @Test
    @DirtiesContext
    public void testDeleteUserAvailability() throws Exception {
        mockMvc.perform(delete("/availability/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value("08-12-2021 00:00:00"));
    }

    @Test
    public void testPostUserAvailability() throws Exception {
        UserAvailability ua = new UserAvailability();
        User user = new User();

        user.setName("test");
        user.setPassword("test");
        user.setApproved(true);
        user.setProfileImagePath("test");
        user.setEmail("test");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = sdf.parse("08-12-2021 00:00:00");

        ua.setUser(user);
        ua.setDate(date);

        mockMvc.perform(post("/availability")
                        .content(new ObjectMapper().writeValueAsString(ua))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value("08-12-2021 00:00:00"));

    }

    @Test
    public void testPutUserAvailability() throws Exception {
        UserAvailability ua = new UserAvailability();
        User user = new User();

        user.setName("test");
        user.setPassword("test");
        user.setApproved(true);
        user.setProfileImagePath("test");
        user.setEmail("test");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = sdf.parse("08-12-2021 00:00:00");

        ua.setUser(user);
        ua.setDate(date);

        mockMvc.perform(put("/availability/1")
                        .content(new ObjectMapper().writeValueAsString(ua))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value("08-12-2021 00:00:00"));

    }
}
