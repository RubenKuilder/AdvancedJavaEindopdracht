package org.AdvancedJavaEindopdracht.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.AdvancedJavaEindopdracht.resource.model.Powerpoint;
import org.AdvancedJavaEindopdracht.resource.model.RssFeed;
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

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@ContextConfiguration(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@SpringJUnitWebConfig(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
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
        mockMvc.perform(get("/rss").contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].link").value("test"))
                .andExpect(jsonPath("$.[0].startDateTime").value("08-12-2021 00:00:00"))
                .andExpect(jsonPath("$.[1].link").value("test2"));
    }

    @Test
    public void testGetRssFeed() throws Exception {
        mockMvc.perform(get("/rss/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.link").value("test"))
                .andExpect(jsonPath("$.startDateTime").value("08-12-2021 00:00:00"));
    }

    @Test
    @DirtiesContext
    public void testDeleteRssFeed() throws Exception {
        mockMvc.perform(delete("/rss/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.link").value("test"))
                .andExpect(jsonPath("$.startDateTime").value("08-12-2021 00:00:00"));
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

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("10-01-2022 15:40:10");
        Date endDateTime = sdf.parse("10-01-2022 15:50:10");

        feed.setUser(user);
        feed.setLink("testtest");
        feed.setStartDateTime(startDateTime);
        feed.setEndDateTime(endDateTime);

        mockMvc.perform(post("/rss")
                        .content(new ObjectMapper().writeValueAsString(feed))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.link").value("testtest"))
                .andExpect(jsonPath("$.startDateTime").value("10-01-2022 15:40:10"));

    }

    @Test
    public void testPutRssFeed() throws Exception {
        RssFeed feed = new RssFeed();
        User user = new User();

        user.setName("test");
        user.setPassword("test");
        user.setApproved(true);
        user.setProfileImagePath("test");
        user.setEmail("test");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("10-01-2022 15:40:10");
        Date endDateTime = sdf.parse("10-01-2022 15:50:10");

        feed.setUser(user);
        feed.setLink("testtest");
        feed.setStartDateTime(startDateTime);
        feed.setEndDateTime(endDateTime);

        mockMvc.perform(put("/rss/1")
                        .content(new ObjectMapper().writeValueAsString(feed))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.link").value("testtest"))
                .andExpect(jsonPath("$.startDateTime").value("10-01-2022 15:40:10"));

    }
}
