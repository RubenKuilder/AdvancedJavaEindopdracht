package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.resource.model.event.Event;
import org.AdvancedJavaEindopdracht.resource.model.event.EventDto;
import org.AdvancedJavaEindopdracht.resource.model.event.content.Content;
import org.AdvancedJavaEindopdracht.resource.model.event.content.contentType.ContentType;
import org.AdvancedJavaEindopdracht.resource.model.event.content.contentType.ContentTypeDto;
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
@SpringJUnitWebConfig(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@ContextConfiguration(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class EventServiceTest {
    @Autowired
    private EventService eventTypeService;

    @Test
    void getALlEvents() throws Exception
    {
        List<EventDto> eventDtoList = eventTypeService.get();

        assertEquals(2, eventDtoList.size());
        assertEquals(1, eventDtoList.get(0).getContent().getId());
        assertEquals(1, eventDtoList.get(0).getUser_id());
        assertEquals("Test", eventDtoList.get(0).getDescription());
        assertEquals("2021-11-08 00:00:00.0", eventDtoList.get(0).getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", eventDtoList.get(0).getEndDateTime().toString());
        assertEquals(2000, eventDtoList.get(0).getDuration());

        assertEquals(1, eventDtoList.get(1).getContent().getId());
        assertEquals(1, eventDtoList.get(1).getUser_id());
        assertEquals("Test", eventDtoList.get(1).getDescription());
        assertEquals("2021-11-08 00:00:00.0", eventDtoList.get(1).getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", eventDtoList.get(1).getEndDateTime().toString());
        assertEquals(2000, eventDtoList.get(1).getDuration());
    }

    @Test
    void getById() throws Exception
    {
        EventDto eventDto = eventTypeService.getById(1);

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

        EventDto eventDto = new EventDto();
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

        EventDto persistedEventDto = eventTypeService.persist(eventDto);

        assertEquals("New content type", persistedEventDto.getContent().getContentType().getName());
        assertEquals("Hele mooie path", persistedEventDto.getContent().getPath());
        assertEquals(1L, persistedEventDto.getUser_id());
        assertEquals("Description", persistedEventDto.getDescription());
        assertEquals(startDateTime, persistedEventDto.getStartDateTime());
        assertEquals(endDateTime, persistedEventDto.getEndDateTime());
        assertEquals(500L, persistedEventDto.getDuration());
    }

    @Test
    void putEvent() throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("12-12-2021 00:00:00");

        EventDto eventDto = new EventDto();
        eventDto.setUser_id(1L);
        eventDto.setDescription("Description");
        eventDto.setStartDateTime(startDateTime);

        EventDto putEventDto = eventTypeService.put(1, eventDto);

        assertEquals(1L, putEventDto.getUser_id());
        assertEquals("Description", putEventDto.getDescription());
        assertEquals(startDateTime, putEventDto.getStartDateTime());
        assertNull(putEventDto.getEndDateTime());
        assertNull(putEventDto.getDuration());
    }

    @Test
    void patchEvent() throws Exception
    {
        EventDto eventDto = new EventDto();
        eventDto.setUser_id(2L);
        eventDto.setDescription("Description");

        EventDto patchedEventDto = eventTypeService.patch(1, eventDto);


        assertEquals(1, patchedEventDto.getContent().getId());
        assertEquals(2, patchedEventDto.getUser_id());
        assertEquals("Description", patchedEventDto.getDescription());
        assertEquals("2021-11-08 00:00:00.0", patchedEventDto.getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", patchedEventDto.getEndDateTime().toString());
        assertEquals(2000, patchedEventDto.getDuration());
    }
}
