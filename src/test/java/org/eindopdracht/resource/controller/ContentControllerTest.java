package org.eindopdracht.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eindopdracht.resource.model.Content;
import org.eindopdracht.resource.model.ContentType;
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
class ContentControllerTest {
    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webContext).build();
    }

    @Test
    void getAll() throws Exception {
        this.mockMvc.perform(get("/content").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[0].contentType.id").value(1))
                .andExpect(jsonPath("$.[0].path").value("Location Path"))
                .andExpect(jsonPath("$.[1].contentType.id").value(2))
                .andExpect(jsonPath("$.[1].path").value("Location test"));
    }

    @Test
    void getById() throws Exception {
        this.mockMvc.perform(get("/content/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.contentType.id").value(1))
                .andExpect(jsonPath("$.path").value("Location Path"));
    }

    @Test
    void postContent() throws Exception {
        ContentType contentType = new ContentType();
        contentType.setName("Text");

        Content content = new Content();
        content.setContentType(contentType);
        content.setPath("Post Path");

        this.mockMvc.perform(post("/content")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(content)))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.contentType.name").value("Text"))
                .andExpect(jsonPath("$.path").value("Post Path"));
    }

    @Test
    void putContent() throws Exception {
        ContentType contentType = new ContentType();
        contentType.setName("Text");
        contentType.setId(1L);

        Content content = new Content();
        content.setContentType(contentType);
        content.setPath("Put Path");

        this.mockMvc.perform(put("/content/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(content)))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.contentType.name").value("Text"))
                .andExpect(jsonPath("$.path").value("Put Path"));
    }
}