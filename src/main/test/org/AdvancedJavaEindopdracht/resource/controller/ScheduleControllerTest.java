package org.AdvancedJavaEindopdracht.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.AdvancedJavaEindopdracht.resource.model.User;
import org.AdvancedJavaEindopdracht.resource.model.schedule.Schedule;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringJUnitWebConfig(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class ScheduleControllerTest {
    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webContext).build();
    }

    @Test
    @Order(1)
    public void getScheduleByIDTest() throws Exception {
        //Aangezien het niet lukt om data toe te voegen via een changeset, wordt er eerst data gepost
        postScheduleTest();

        this.mockMvc.perform(get("/schedule/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Mooie titel post"))
                .andExpect(jsonPath("$.description").value("Mooie beschrijving post"))
                .andExpect(jsonPath("$.startDateTime").value("10-01-2022 15:40:10"))
                .andExpect(jsonPath("$.endDateTime").value("10-01-2022 15:50:10"))
                .andReturn();
    }

    @Test
    public void getSchedulesTest() throws Exception {
        //Aangezien het niet lukt om data toe te voegen via een changeset, wordt er eerst data gepost
        postScheduleTest();

        this.mockMvc.perform(get("/schedule").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title").value("Mooie titel post"))
                .andExpect(jsonPath("$.[0].description").value("Mooie beschrijving post"))
                .andExpect(jsonPath("$.[0].startDateTime").value("10-01-2022 15:40:10"))
                .andExpect(jsonPath("$.[0].endDateTime").value("10-01-2022 15:50:10"))
                .andReturn();
    }

    @Test
    public void postScheduleTest() throws Exception {
        User user = new User();
        user.setId(1);
        List<User> usersList = Arrays.asList(user);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("10-01-2022 15:40:10");
        Date endDateTime = sdf.parse("10-01-2022 15:50:10");

        Schedule schedule = new Schedule();
        schedule.setTitle("Mooie titel post");
        schedule.setDescription("Mooie beschrijving post");
        schedule.setStartDateTime(startDateTime);
        schedule.setEndDateTime(endDateTime);
        schedule.setUsers(usersList);

        mockMvc.perform(post("/schedule")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(schedule)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Mooie titel post"))
                .andExpect(jsonPath("$.description").value("Mooie beschrijving post"))
                .andExpect(jsonPath("$.startDateTime").value("10-01-2022 15:40:10"))
                .andExpect(jsonPath("$.endDateTime").value("10-01-2022 15:50:10"))
                .andReturn();
    }

    @Test
    public void putScheduleTest() throws Exception {
        User user = new User();
        user.setId(1);
        List<User> usersList = Arrays.asList(user);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("10-01-2022 15:40:10");
        Date endDateTime = sdf.parse("10-01-2022 15:50:10");

        Schedule schedule = new Schedule();
        schedule.setTitle("Mooie titel put");
        schedule.setDescription("Mooie beschrijving put");
        schedule.setStartDateTime(startDateTime);
        schedule.setEndDateTime(endDateTime);
        schedule.setUsers(usersList);

        mockMvc.perform(put("/schedule/2")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(schedule)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Mooie titel put"))
                .andExpect(jsonPath("$.description").value("Mooie beschrijving put"))
                .andExpect(jsonPath("$.startDateTime").value("10-01-2022 15:40:10"))
                .andExpect(jsonPath("$.endDateTime").value("10-01-2022 15:50:10"))
                .andReturn();
    }

    @Test
    public void patchScheduleTest() throws Exception {
        Schedule schedule = new Schedule();
        schedule.setTitle("Mooie titel patch");
        schedule.setDescription("Mooie beschrijving patch");

        mockMvc.perform(put("/schedule/2")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(schedule)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Mooie titel patch"))
                .andExpect(jsonPath("$.description").value("Mooie beschrijving patch"))
                .andReturn();
    }
}
