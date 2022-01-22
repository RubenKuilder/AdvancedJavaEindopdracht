package org.eindopdracht.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eindopdracht.resource.model.User;
import org.eindopdracht.resource.model.Schedule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
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
        this.mockMvc.perform(get("/schedule/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Mooie titel post"))
                .andExpect(jsonPath("$.description").value("Mooie beschrijving post"))
                .andExpect(jsonPath("$.startDateTime").value("08-12-2022 00:00:00"))
                .andExpect(jsonPath("$.endDateTime").value("08-12-2022 00:00:00"))
                .andReturn();
    }

    @Test
    @Order(2)
    public void deleteScheduleTest() throws Exception {
        this.mockMvc.perform(delete("/schedule/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Mooie titel post"))
                .andExpect(jsonPath("$.description").value("Mooie beschrijving post"))
                .andExpect(jsonPath("$.startDateTime").value("08-12-2022 00:00:00"))
                .andExpect(jsonPath("$.endDateTime").value("08-12-2022 00:00:00"))
                .andReturn();
    }

    @Test
    public void getSchedulesTest() throws Exception {
        this.mockMvc.perform(get("/schedule").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[1].title").value("Mooie titel post"))
                .andExpect(jsonPath("$.[1].description").value("Mooie beschrijving post"))
                .andExpect(jsonPath("$.[1].startDateTime").value("08-12-2022 00:00:00"))
                .andExpect(jsonPath("$.[1].endDateTime").value("08-12-2022 00:00:00"))
                .andReturn();
    }

    @Test
    public void postScheduleTest() throws Exception {
        User user = new User();
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

        User user = new User();
        List<User> usersList = Arrays.asList(user);

        Schedule schedule = new Schedule();
        schedule.setTitle("Mooie titel patch test");
        schedule.setDescription("Mooie beschrijving patch test");
        schedule.setUsers(usersList);

        mockMvc.perform(put("/schedule/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(schedule)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Mooie titel patch test"))
                .andExpect(jsonPath("$.description").value("Mooie beschrijving patch test"))
                .andReturn();
    }
}
