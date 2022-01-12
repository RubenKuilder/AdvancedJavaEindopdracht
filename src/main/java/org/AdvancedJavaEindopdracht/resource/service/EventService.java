package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.resource.model.event.EventDto;
import org.AdvancedJavaEindopdracht.resource.model.event.EventMapper;
import org.AdvancedJavaEindopdracht.resource.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public List<EventDto> get() {
        return eventMapper.mapFromEntityList(eventRepository.get());
    }

    public EventDto getById(long id) {
        return eventMapper.mapFromEntity(eventRepository.getById(id));
    }

    public EventDto persist(EventDto eventDto) {
        return eventMapper.mapFromEntity(
                eventRepository.persist(eventMapper.mapToEntity(eventDto))
        );
    }

    public EventDto put(long id, EventDto eventDto) {
        return eventMapper.mapFromEntity(eventRepository.put(id, eventMapper.mapToEntity(eventDto)));
    }

    public EventDto patch(long id, EventDto eventDto) {
        return eventMapper.mapFromEntity(eventRepository.patch(id, eventMapper.mapToEntity(eventDto)));
    }

    public EventDto delete(long id) {
        return eventMapper.mapFromEntity(eventRepository.delete(id));
    }
}
