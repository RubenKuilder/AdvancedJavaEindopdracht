package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.resource.model.User;
import org.AdvancedJavaEindopdracht.resource.model.schedule.ScheduleDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@ContextConfiguration(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class ScheduleServiceTest {
    @Autowired
    private ScheduleService scheduleService;

    @Test
    @Transactional
    @Order(1)
    void getScheduleByIDTest() throws Exception
    {
        //TODO: Populate liquibase database with CSV instead of POST
        //Aangezien het niet lukt om data toe te voegen via een changeset, wordt er eerst data gepost
        postScheduleTest();

        ScheduleDto scheduleDto = scheduleService.getById(1);

        assertEquals("Mooie titel post", scheduleDto.getTitle());
        assertEquals("Mooie beschrijving post", scheduleDto.getDescription());
    }

    @Test
    @Order(2)
    public void deleteScheduleTest() throws Exception {
        //TODO: Populate liquibase database with CSV instead of POST
        //Aangezien het niet lukt om data toe te voegen via een changeset, wordt er eerst data gepost
        postScheduleTest();

        ScheduleDto scheduleDto = scheduleService.delete(2);

        assertEquals("Mooie titel post", scheduleDto.getTitle());
        assertEquals("Mooie beschrijving post", scheduleDto.getDescription());
    }

    @Test
    @Transactional
    void getScheduleTest() throws Exception
    {
        //TODO: Populate liquibase database with CSV instead of POST
        //Aangezien het niet lukt om data toe te voegen via een changeset, wordt er eerst data gepost
        postScheduleTest();

        List<ScheduleDto> scheduleDtoList = scheduleService.get();

        assertEquals("Mooie titel post", scheduleDtoList.get(0).getTitle());
        assertEquals("Mooie beschrijving post", scheduleDtoList.get(0).getDescription());
    }

    @Test
    @Transactional
    void postScheduleTest() throws Exception
    {
        User user = new User();
        user.setId(1);
        List<User> usersList = Arrays.asList(user);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("10-01-2022 15:40:10");
        Date endDateTime = sdf.parse("10-01-2022 15:50:10");

        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setTitle("Mooie titel post");
        scheduleDto.setDescription("Mooie beschrijving post");
        scheduleDto.setStartDateTime(startDateTime);
        scheduleDto.setEndDateTime(endDateTime);
        scheduleDto.setUsers(usersList);

        ScheduleDto persistedScheduleDto = scheduleService.persist(scheduleDto);

        assertEquals("Mooie titel post", persistedScheduleDto.getTitle());
        assertEquals("Mooie beschrijving post", persistedScheduleDto.getDescription());
        assertEquals(startDateTime, persistedScheduleDto.getStartDateTime());
        assertEquals(endDateTime, persistedScheduleDto.getEndDateTime());
    }

    @Test
    @Transactional
    void putScheduleTest() throws Exception
    {
        User user = new User();
        user.setId(1);
        List<User> usersList = Arrays.asList(user);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("10-01-2022 15:40:10");
        Date endDateTime = sdf.parse("10-01-2022 15:50:10");

        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setTitle("Mooie titel put");
        scheduleDto.setDescription("Mooie beschrijving put");
        scheduleDto.setStartDateTime(startDateTime);
        scheduleDto.setEndDateTime(endDateTime);
        scheduleDto.setUsers(usersList);

        ScheduleDto puttedScheduleDto = scheduleService.put(1, scheduleDto);

        assertEquals("Mooie titel put", puttedScheduleDto.getTitle());
        assertEquals("Mooie beschrijving put", puttedScheduleDto.getDescription());
        assertEquals(startDateTime, puttedScheduleDto.getStartDateTime());
        assertEquals(endDateTime, puttedScheduleDto.getEndDateTime());
    }

    @Test
    @Transactional
    void patchScheduleTest() throws Exception {
        //TODO: Populate liquibase database with CSV instead of POST
        //Aangezien het niet lukt om data toe te voegen via een changeset, wordt er eerst data gepost
        postScheduleTest();

        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setTitle("Mooie titel patch");
        scheduleDto.setDescription("Mooie beschrijving patch");

        ScheduleDto patchedScheduleDto = scheduleService.put(1, scheduleDto);

        assertEquals("Mooie titel patch", patchedScheduleDto.getTitle());
        assertEquals("Mooie beschrijving patch", patchedScheduleDto.getDescription());
    }
}
