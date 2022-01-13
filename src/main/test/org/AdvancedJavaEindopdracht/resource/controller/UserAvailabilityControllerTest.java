package org.AdvancedJavaEindopdracht.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.AdvancedJavaEindopdracht.resource.model.Powerpoint;
import org.AdvancedJavaEindopdracht.resource.model.RssFeed;
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

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                .andReturn();
    }

    @Test
    public void testGetUserAvailability() throws Exception {
        mockMvc.perform(get("/availability/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DirtiesContext
    public void testDeleteUserAvailability() throws Exception {
        mockMvc.perform(delete("/availability/1"))
                .andExpect(status().isOk())
                .andReturn();
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

        ua.setUser(user);
        ua.setDate(new Date(2010, 3, 5));

        mockMvc.perform(post("/availability")
                        .content(new ObjectMapper().writeValueAsString(ua))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

    }
}
