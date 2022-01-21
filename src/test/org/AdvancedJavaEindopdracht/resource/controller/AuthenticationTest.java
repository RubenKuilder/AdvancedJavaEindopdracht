package org.AdvancedJavaEindopdracht.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.AdvancedJavaEindopdracht.resource.model.User;
import org.AdvancedJavaEindopdracht.resource.model.consultation.Consultation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class AuthenticationTest
{
    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    private final String jwtTokenHeader = "jwt-new-token";

    @BeforeEach
    public void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    void testNotAuthenticated() throws Exception
    {
        this.mockMvc.perform(post("/content/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(header().doesNotExist(jwtTokenHeader));
    }

    @Test
    void testDisabledAccount() throws Exception
    {
        MvcResult mvcResult = this.mockMvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Disabled\",\"password\":\"password\"}"))
                .andReturn();
        String token = mvcResult.getResponse().getContentAsString();

        this.mockMvc.perform(post("/contenttype")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("authorization", "Bearer " + token)
                        .content("{\"name\":\"Text\"}"))
                .andExpect(status().isUnauthorized());
    }
}
