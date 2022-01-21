package org.eindopdracht.resource.mapper;

import org.eindopdracht.resource.model.event.Event;
import org.eindopdracht.resource.model.event.EventDto;
import org.eindopdracht.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventMapper implements EntityMapper<Event, EventDto> {
    @Override
    public EventDto mapFromEntity(Event event) {
        return new EventDto(
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
    public Event mapToEntity(EventDto eventDto) {
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

    public List<EventDto> mapFromEntityList(List<Event> entities) {
        List<EventDto> EventDtoList = new ArrayList<>();
        for (Event entity : entities) {
            EventDtoList.add(mapFromEntity(entity));
        }

        return EventDtoList;
    }
}
