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

    /**
     * Maps Entity to DTO and returns a list of all events.
     *
     * @return      response entity with list of all events
     */
    public List<EventDto> get() {
        return eventMapper.mapFromEntityList(eventRepository.get());
    }

    /**
     * Maps Entity to DTO and returns a single event.
     *
     * @param id    id of the event to find
     * @return      response entity with single event
     */
    public EventDto getById(long id) {
        return eventMapper.mapFromEntity(eventRepository.getById(id));
    }

    /**
     * Maps Entity to DTO and posts a single event.
     *
     * @param eventDto  event to post
     * @return          response entity with posted event
     */
    public EventDto persist(EventDto eventDto) {
        return eventMapper.mapFromEntity(
                eventRepository.persist(eventMapper.mapToEntity(eventDto))
        );
    }

    /**
     * Maps Entity to DTO and puts a single event.
     *
     * @param id        id of the event to put
     * @param eventDto  event to put
     * @return          response entity with put event
     */
    public EventDto put(long id, EventDto eventDto) {
        return eventMapper.mapFromEntity(eventRepository.put(id, eventMapper.mapToEntity(eventDto)));
    }


    /**
     * Maps Entity to DTO and deletes a single event.
     *
     * @param id    id of the event to delete
     * @return      response entity with deleted event
     */
    public EventDto delete(long id) {
        return eventMapper.mapFromEntity(eventRepository.delete(id));
    }
}
