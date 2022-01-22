package org.eindopdracht.resource.repository;


import org.eindopdracht.resource.model.User;
import org.eindopdracht.resource.model.Schedule;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class ScheduleRepositoryTest {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    @Order(1)
    void getScheduleByIDTest()
    {
        Schedule schedule = scheduleRepository.getById(1);

        assertEquals("Mooie titel post", schedule.getTitle());
        assertEquals("Mooie beschrijving post", schedule.getDescription());
    }

    @Test
    @Order(2)
    public void deleteScheduleTest()
    {
        Schedule schedule = scheduleRepository.delete(1);

        assertEquals("Mooie titel post", schedule.getTitle());
        assertEquals("Mooie beschrijving post", schedule.getDescription());
    }

    @Test
    void getScheduleTest()
    {

        List<Schedule> scheduleList = scheduleRepository.get();

        assertEquals("Mooie titel post", scheduleList.get(1).getTitle());
        assertEquals("Mooie beschrijving post", scheduleList.get(1).getDescription());
    }

    @Test
    void postScheduleTest() throws Exception
    {
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

        Schedule persistedScheduleDto = scheduleRepository.post(schedule);

        assertEquals("Mooie titel post", persistedScheduleDto.getTitle());
        assertEquals("Mooie beschrijving post", persistedScheduleDto.getDescription());
        assertEquals(startDateTime, persistedScheduleDto.getStartDateTime());
        assertEquals(endDateTime, persistedScheduleDto.getEndDateTime());
    }

    @Test
    void putScheduleTest() throws Exception
    {
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

        Schedule puttedSchedule = scheduleRepository.put(1, schedule);

        assertEquals("Mooie titel put", puttedSchedule.getTitle());
        assertEquals("Mooie beschrijving put", puttedSchedule.getDescription());
        assertEquals(startDateTime, puttedSchedule.getStartDateTime());
        assertEquals(endDateTime, puttedSchedule.getEndDateTime());
    }

    @Test
    void patchScheduleTest() throws ParseException
    {
        User user = new User();
        user.setId(1);
        List<User> usersList = Arrays.asList(user);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("10-01-2022 15:40:10");
        Date endDateTime = sdf.parse("10-01-2022 15:50:10");

        Schedule schedule = new Schedule();
        schedule.setTitle("Mooie titel patch");
        schedule.setDescription("Mooie beschrijving patch");
        schedule.setUsers(usersList);
        schedule.setStartDateTime(startDateTime);
        schedule.setEndDateTime(endDateTime);

        Schedule patchedSchedule = scheduleRepository.patch(1, schedule);

        assertEquals("Mooie titel patch", patchedSchedule.getTitle());
        assertEquals("Mooie beschrijving patch", patchedSchedule.getDescription());
    }
}
