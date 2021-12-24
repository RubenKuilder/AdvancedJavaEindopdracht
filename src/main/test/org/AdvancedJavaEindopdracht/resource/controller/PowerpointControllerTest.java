package org.AdvancedJavaEindopdracht.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.AdvancedJavaEindopdracht.resource.model.Powerpoint;
import org.AdvancedJavaEindopdracht.resource.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = com.configuration.DatabaseConfigTest.class)
@SpringJUnitWebConfig(classes = com.configuration.DatabaseConfigTest.class)
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
        mockMvc.perform(get("/powerpoint").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetPowerpoint() throws Exception {
        mockMvc.perform(get("/powerpoint/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DirtiesContext
    public void testDeletePowerpoint() throws Exception {
        mockMvc.perform(delete("/powerpoint/1"))
                .andExpect(status().isOk())
                .andReturn();
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
                .andReturn();

    }
}
