package org.eindopdracht.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eindopdracht.resource.model.event.Event;
import org.eindopdracht.resource.model.event.content.Content;
import org.eindopdracht.resource.model.event.content.contentType.ContentType;
import org.junit.jupiter.api.BeforeEach;
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
class EventControllerTest
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
    void getAll() throws Exception
    {
        this.mockMvc.perform(get("/event").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[0].description").value("Test"))
                .andExpect(jsonPath("$.[0].startDateTime").value("08-11-2021 00:00:00"))
                .andExpect(jsonPath("$.[0].duration").value(2000));
    }

    @Test
    void getById() throws Exception
    {
        this.mockMvc.perform(get("/event/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.description").value("Test"))
                .andExpect(jsonPath("$.startDateTime").value("08-11-2021 00:00:00"))
                .andExpect(jsonPath("$.duration").value(2000));
    }

    @Test
    void postEvent() throws Exception
    {
        ContentType contentType = new ContentType();
        contentType.setName("Text");

        Content content = new Content();
        content.setPath("path");
        content.setContentType(contentType);

        Event event = new Event();
        event.setContent(content);
        event.setDescription("Description");
        event.setDuration(2000L);
        event.setEndDateTime(null);
        event.setStartDateTime(null);
        event.setUser_id(1L);

        this.mockMvc.perform(post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(event)))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.description").value("Description"))
                .andExpect(jsonPath("$.duration").value(2000L));
    }

    @Test
    void putEvent() throws Exception
    {
        ContentType contentType = new ContentType();
        contentType.setName("Text");

        Content content = new Content();
        content.setPath("path");
        content.setContentType(contentType);

        Event event = new Event();
        event.setContent(content);
        event.setDescription("Description");
        event.setDuration(2000L);
        event.setEndDateTime(null);
        event.setStartDateTime(null);
        event.setUser_id(1L);

        this.mockMvc.perform(put("/event/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(event)))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.description").value("Description"))
                .andExpect(jsonPath("$.duration").value(2000L));
    }

    @Test
    void patchEvent() throws Exception
    {
        Event event = new Event();
        event.setDescription("Description");
        event.setDuration(2000L);
        event.setUser_id(1L);
        this.mockMvc.perform(patch("/event/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(event)))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.description").value("Description"))
                .andExpect(jsonPath("$.duration").value(2000L));
    }

    @Test
    void deleteEvent() throws Exception
    {
        this.mockMvc.perform(delete("/event/1"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.description").value("Test"))
                .andExpect(jsonPath("$.duration").value(2000L));
    }
}