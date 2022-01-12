package org.AdvancedJavaEindopdracht.resource.controller;

import org.AdvancedJavaEindopdracht.security.JWTProvider;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ContextConfiguration(classes = com.configuration.DatabaseConfigTest.class)
@SpringJUnitWebConfig(classes = com.configuration.DatabaseConfigTest.class)
class LoginControllerTest
{
    @Autowired
    private WebApplicationContext webContext;

    @Autowired
    private JWTProvider jwtProvider;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    void login() throws Exception
    {
        MvcResult mvcResult = this.mockMvc.perform(post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Madlyaza\",\"password\":\"Password\"}"))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertTrue(jwtProvider.validateToken(mvcResult.getResponse().getContentAsString()));
    }
}