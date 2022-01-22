package org.eindopdracht.resource.service;

import org.eindopdracht.resource.model.User;
import org.eindopdracht.resource.dto.ScheduleDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class ScheduleServiceTest {
    @Autowired
    private ScheduleService scheduleService;

    @Test
    @Order(1)
    void getScheduleByIDTest()
    {
        ScheduleDTO scheduleDto = scheduleService.getById(1);

        assertEquals("Mooie titel post", scheduleDto.getTitle());
        assertEquals("Mooie beschrijving post", scheduleDto.getDescription());
    }

    @Test
    @Order(2)
    void getScheduleTest()
    {
        List<ScheduleDTO> scheduleDTOList = scheduleService.getAll();

        assertEquals("Mooie titel post", scheduleDTOList.get(1).getTitle());
        assertEquals("Mooie beschrijving post", scheduleDTOList.get(1).getDescription());
    }

    @Test
    @Order(3)
    public void deleteScheduleTest() {
        ScheduleDTO scheduleDto = scheduleService.delete(1);

        assertEquals("Mooie titel post", scheduleDto.getTitle());
        assertEquals("Mooie beschrijving post", scheduleDto.getDescription());
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

        ScheduleDTO scheduleDto = new ScheduleDTO();
        scheduleDto.setTitle("Mooie titel post");
        scheduleDto.setDescription("Mooie beschrijving post");
        scheduleDto.setStartDateTime(startDateTime);
        scheduleDto.setEndDateTime(endDateTime);
        scheduleDto.setUsers(usersList);

        ScheduleDTO persistedScheduleDTO = scheduleService.persist(scheduleDto);

        assertEquals("Mooie titel post", persistedScheduleDTO.getTitle());
        assertEquals("Mooie beschrijving post", persistedScheduleDTO.getDescription());
        assertEquals(startDateTime, persistedScheduleDTO.getStartDateTime());
        assertEquals(endDateTime, persistedScheduleDTO.getEndDateTime());
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

        ScheduleDTO scheduleDto = new ScheduleDTO();
        scheduleDto.setTitle("Mooie titel put");
        scheduleDto.setDescription("Mooie beschrijving put");
        scheduleDto.setStartDateTime(startDateTime);
        scheduleDto.setEndDateTime(endDateTime);
        scheduleDto.setUsers(usersList);

        ScheduleDTO puttedScheduleDTO = scheduleService.put(1, scheduleDto);

        assertEquals("Mooie titel put", puttedScheduleDTO.getTitle());
        assertEquals("Mooie beschrijving put", puttedScheduleDTO.getDescription());
        assertEquals(startDateTime, puttedScheduleDTO.getStartDateTime());
        assertEquals(endDateTime, puttedScheduleDTO.getEndDateTime());
    }

    @Test
    void patchScheduleTest() {
        ScheduleDTO scheduleDto = new ScheduleDTO();
        scheduleDto.setTitle("Mooie titel patch");
        scheduleDto.setDescription("Mooie beschrijving patch");

        ScheduleDTO patchedScheduleDTO = scheduleService.put(1, scheduleDto);

        assertEquals("Mooie titel patch", patchedScheduleDTO.getTitle());
        assertEquals("Mooie beschrijving patch", patchedScheduleDTO.getDescription());
    }
}
