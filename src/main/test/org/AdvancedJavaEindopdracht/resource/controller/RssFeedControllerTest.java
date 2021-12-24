package org.AdvancedJavaEindopdracht.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.AdvancedJavaEindopdracht.resource.model.Powerpoint;
import org.AdvancedJavaEindopdracht.resource.model.RssFeed;
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

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = com.configuration.DatabaseConfigTest.class)
@SpringJUnitWebConfig(classes = com.configuration.DatabaseConfigTest.class)
public class RssFeedControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetRssFeeds() throws Exception {
        mockMvc.perform(get("/rssfeed").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetRssFeed() throws Exception {
        mockMvc.perform(get("/rssfeed/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DirtiesContext
    public void testDeleteRssFeed() throws Exception {
        mockMvc.perform(delete("/rssfeed/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testPostRssfeed() throws Exception {
        RssFeed feed = new RssFeed();
        User user = new User();

        user.setName("test");
        user.setPassword("test");
        user.setApproved(true);
        user.setProfileImagePath("test");
        user.setEmail("test");

        feed.setUser(user);
        feed.setLink("test");
        feed.setStartDate(new Date(2010, 3, 5));
        feed.setEndDate(new Date(2010, 3, 5));

        mockMvc.perform(post("/rssfeed")
                        .content(new ObjectMapper().writeValueAsString(feed))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

    }
}
