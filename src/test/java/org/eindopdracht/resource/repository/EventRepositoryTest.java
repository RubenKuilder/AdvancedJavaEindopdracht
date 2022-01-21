package org.eindopdracht.resource.repository;

import org.eindopdracht.resource.model.event.Event;
import org.eindopdracht.resource.model.event.content.Content;
import org.eindopdracht.resource.model.event.content.contentType.ContentType;
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
@ContextConfiguration(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class EventRepositoryTest {
    @Autowired
    private EventRepository eventRepository;

    @Test
    void getAllEvents()
    {
        List<Event> eventList = eventRepository.get();

        assertEquals(2, eventList.size());
        assertEquals(1, eventList.get(0).getContent().getId());
        assertEquals(1, eventList.get(0).getUser_id());
        assertEquals("Test", eventList.get(0).getDescription());
        assertEquals("2021-11-08 00:00:00.0", eventList.get(0).getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", eventList.get(0).getEndDateTime().toString());
        assertEquals(2000, eventList.get(0).getDuration());

        assertEquals(1, eventList.get(1).getContent().getId());
        assertEquals(1, eventList.get(1).getUser_id());
        assertEquals("Test", eventList.get(1).getDescription());
        assertEquals("2021-11-08 00:00:00.0", eventList.get(1).getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", eventList.get(1).getEndDateTime().toString());
        assertEquals(2000, eventList.get(1).getDuration());
    }

    @Test
    void getById()
    {
        Event event = eventRepository.getById(1);

        assertEquals(1, event.getContent().getId());
        assertEquals(1, event.getUser_id());
        assertEquals("Test", event.getDescription());
        assertEquals("2021-11-08 00:00:00.0", event.getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", event.getEndDateTime().toString());
        assertEquals(2000, event.getDuration());
    }

    @Test
    void postEvent() throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("12-12-2021 00:00:00");
        Date endDateTime = sdf.parse("01-01-2022 00:00:00");

        Event event = new Event();
        Content content = new Content();
        ContentType contentType = new ContentType();
        contentType.setName("New content type");
        content.setContentType(contentType);
        content.setPath("Hele mooie path");
        event.setContent(content);
        event.setUser_id(1L);
        event.setDescription("Description");
        event.setStartDateTime(startDateTime);
        event.setEndDateTime(endDateTime);
        event.setDuration(500L);

        Event persistedEvent = eventRepository.persist(event);

        assertEquals("New content type", persistedEvent.getContent().getContentType().getName());
        assertEquals("Hele mooie path", persistedEvent.getContent().getPath());
        assertEquals(1L, persistedEvent.getUser_id());
        assertEquals("Description", persistedEvent.getDescription());
        assertEquals(startDateTime, persistedEvent.getStartDateTime());
        assertEquals(endDateTime, persistedEvent.getEndDateTime());
        assertEquals(500L, persistedEvent.getDuration());
    }

    @Test
    void putEvent() throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("12-12-2021 00:00:00");

        Event event = new Event();
        event.setUser_id(1L);
        event.setDescription("Description");
        event.setStartDateTime(startDateTime);

        Event putEvent = eventRepository.put(1, event);

        assertEquals(1L, putEvent.getUser_id());
        assertEquals("Description", putEvent.getDescription());
        assertEquals(startDateTime, putEvent.getStartDateTime());
        assertNull(putEvent.getEndDateTime());
        assertNull(putEvent.getDuration());
    }

}
