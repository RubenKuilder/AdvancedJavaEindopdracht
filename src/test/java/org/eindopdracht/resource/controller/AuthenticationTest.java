package org.eindopdracht.resource.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class AuthenticationTest {
    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    private final String jwtTokenHeader = "jwt-new-token";

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    void testNotAuthenticated() throws Exception {
        this.mockMvc.perform(post("/content/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(header().doesNotExist(jwtTokenHeader));
    }

    @Test
    void testDisabledAccount() throws Exception {
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
