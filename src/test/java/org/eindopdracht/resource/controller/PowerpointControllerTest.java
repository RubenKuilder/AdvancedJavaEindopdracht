package org.eindopdracht.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eindopdracht.resource.model.Powerpoint;
import org.eindopdracht.resource.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
public class PowerpointControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetPowerpoints() throws Exception {
        mockMvc.perform(get("/powerpoint").contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].path").value("test"))
                .andExpect(jsonPath("$.[0].user.id").value(1));
    }

    @Test
    public void testGetPowerpoint() throws Exception {
        mockMvc.perform(get("/powerpoint/1"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.path").value("test"))
                .andExpect(jsonPath("$.user.id").value(1));
    }

    @Test
    public void testDeletePowerpoint() throws Exception {
        mockMvc.perform(delete("/powerpoint/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.path").value("test"))
                .andExpect(jsonPath("$.user.id").value(1));
    }

    @Test
    public void testPostPowerpoint() throws Exception {
        Powerpoint pp = new Powerpoint();
        User user = new User();

        user.setName("test");
        user.setPassword("test");
        user.setApproved(true);
        user.setProfileImagePath("test");
        user.setEmail("test");

        pp.setUser(user);
        pp.setPath("test");

        mockMvc.perform(post("/powerpoint")
                        .content(new ObjectMapper().writeValueAsString(pp))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.path").value("test"));

    }
}
