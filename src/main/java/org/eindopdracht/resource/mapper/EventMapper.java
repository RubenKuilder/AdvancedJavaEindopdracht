package org.eindopdracht.resource.mapper;

import org.eindopdracht.resource.dto.EventDTO;
import org.eindopdracht.resource.model.Event;
import org.eindopdracht.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("eventMapperComponent")
public class EventMapper implements EntityMapper<Event, EventDTO> {
    @Override
    public EventDTO mapFromEntity(Event event) {
        return new EventDTO(
                event.getId(),
                event.getContent(),
                event.getUser_id(),
                event.getDescription(),
                event.getStartDateTime(),
                event.getEndDateTime(),
                event.getDuration()
        );
    }

    @Override
    public Event mapToEntity(EventDTO eventDto) {
        return new Event(
                eventDto.getId(),
                eventDto.getContent(),
                eventDto.getUser_id(),
                eventDto.getDescription(),
                eventDto.getStartDateTime(),
                eventDto.getEndDateTime(),
                eventDto.getDuration()
        );
    }

    public List<EventDTO> mapFromEntityList(List<Event> entities) {
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (Event entity : entities) {
            eventDTOList.add(mapFromEntity(entity));
        }

        return eventDTOList;
    }
}
