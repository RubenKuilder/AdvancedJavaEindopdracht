package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.EventDTO;
import org.eindopdracht.resource.model.Content;
import org.eindopdracht.resource.model.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class EventServiceTest {
    @Autowired
    private EventService eventService;

    @Test
    void getAllEvents()
    {
        List<EventDTO> eventDTOList = eventService.get();

        assertEquals(2, eventDTOList.size());
        assertEquals(1, eventDTOList.get(0).getContent().getId());
        assertEquals(1, eventDTOList.get(0).getUser_id());
        assertEquals("Test", eventDTOList.get(0).getDescription());
        assertEquals("2021-11-08 00:00:00.0", eventDTOList.get(0).getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", eventDTOList.get(0).getEndDateTime().toString());
        assertEquals(2000, eventDTOList.get(0).getDuration());

        assertEquals(1, eventDTOList.get(1).getContent().getId());
        assertEquals(1, eventDTOList.get(1).getUser_id());
        assertEquals("Test", eventDTOList.get(1).getDescription());
        assertEquals("2021-11-08 00:00:00.0", eventDTOList.get(1).getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", eventDTOList.get(1).getEndDateTime().toString());
        assertEquals(2000, eventDTOList.get(1).getDuration());
    }

    @Test
    void getById()
    {
        EventDTO eventDto = eventService.getById(1);

        assertEquals(1, eventDto.getContent().getId());
        assertEquals(1, eventDto.getUser_id());
        assertEquals("Test", eventDto.getDescription());
        assertEquals("2021-11-08 00:00:00.0", eventDto.getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", eventDto.getEndDateTime().toString());
        assertEquals(2000, eventDto.getDuration());
    }

    @Test
    void postEvent() throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("12-12-2021 00:00:00");
        Date endDateTime = sdf.parse("01-01-2022 00:00:00");

        EventDTO eventDto = new EventDTO();
        Content content = new Content();
        ContentType contentType = new ContentType();
        contentType.setName("New content type");
        content.setContentType(contentType);
        content.setPath("Hele mooie path");
        eventDto.setContent(content);
        eventDto.setUser_id(1L);
        eventDto.setDescription("Description");
        eventDto.setStartDateTime(startDateTime);
        eventDto.setEndDateTime(endDateTime);
        eventDto.setDuration(500L);

        EventDTO persistedEventDTO = eventService.persist(eventDto);

        assertEquals("New content type", persistedEventDTO.getContent().getContentType().getName());
        assertEquals("Hele mooie path", persistedEventDTO.getContent().getPath());
        assertEquals(1L, persistedEventDTO.getUser_id());
        assertEquals("Description", persistedEventDTO.getDescription());
        assertEquals(startDateTime, persistedEventDTO.getStartDateTime());
        assertEquals(endDateTime, persistedEventDTO.getEndDateTime());
        assertEquals(500L, persistedEventDTO.getDuration());
    }

    @Test
    void putEvent() throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("12-12-2021 00:00:00");

        EventDTO eventDto = new EventDTO();
        eventDto.setUser_id(1L);
        eventDto.setDescription("Description");
        eventDto.setStartDateTime(startDateTime);

        EventDTO putEventDTO = eventService.put(1, eventDto);

        assertEquals(1L, putEventDTO.getUser_id());
        assertEquals("Description", putEventDTO.getDescription());
        assertEquals(startDateTime, putEventDTO.getStartDateTime());
        assertNull(putEventDTO.getEndDateTime());
        assertNull(putEventDTO.getDuration());
    }

    @Test
    void patchEvent()
    {
        EventDTO eventDto = new EventDTO();
        eventDto.setUser_id(2L);
        eventDto.setDescription("Description");

        EventDTO patchedEventDTO = eventService.patch(1, eventDto);


        assertEquals(1, patchedEventDTO.getContent().getId());
        assertEquals(2, patchedEventDTO.getUser_id());
        assertEquals("Description", patchedEventDTO.getDescription());
        assertEquals("2021-11-08 00:00:00.0", patchedEventDTO.getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", patchedEventDTO.getEndDateTime().toString());
        assertEquals(2000, patchedEventDTO.getDuration());
    }
}
