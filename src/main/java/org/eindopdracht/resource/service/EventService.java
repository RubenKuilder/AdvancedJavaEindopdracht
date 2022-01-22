package org.eindopdracht.resource.service;

import org.eindopdracht.resource.exception.general.BadRequestException;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.dto.EventDTO;
import org.eindopdracht.resource.mapper.EventMapper;
import org.eindopdracht.resource.repository.EventRepository;
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
    public List<EventDTO> get() {
        return eventMapper.mapFromEntityList(eventRepository.get());
    }

    /**
     * Maps Entity to DTO and returns a single event.
     *
     * @param id    id of the event to find
     * @return      response entity with single event
     */
    public EventDTO getById(long id) {
        try{
        return eventMapper.mapFromEntity(eventRepository.getById(id));
    }
        catch (Exception ex)
    {
        throw new DataNotFoundException();
    }
    }

    /**
     * Maps Entity to DTO and posts a single event.
     *
     * @param eventDto  event to post
     * @return          response entity with posted event
     */
    public EventDTO persist(EventDTO eventDto) {
        try{
        return eventMapper.mapFromEntity(
                eventRepository.persist(eventMapper.mapToEntity(eventDto))
        );
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and puts a single event.
     *
     * @param id        id of the event to put
     * @param eventDto  event to put
     * @return          response entity with put event
     */
    public EventDTO put(long id, EventDTO eventDto) {
        try{
        return eventMapper.mapFromEntity(eventRepository.put(id, eventMapper.mapToEntity(eventDto)));
    }
        catch (Exception ex)
    {
        throw new BadRequestException();
    }
    }

    /**
     * Maps Entity to DTO and patches a single event.
     *
     * @param id        id of the event to patch
     * @param eventDto  event to patch
     * @return          response entity with patched event
     */
    public EventDTO patch(long id, EventDTO eventDto) {
        try{
        return eventMapper.mapFromEntity(eventRepository.patch(id, eventMapper.mapToEntity(eventDto)));
    }
        catch (Exception ex)
    {
        throw new BadRequestException();
    }
    }

    /**
     * Maps Entity to DTO and deletes a single event.
     *
     * @param id    id of the event to delete
     * @return      response entity with deleted event
     */
    public EventDTO delete(long id) {
        try{
        return eventMapper.mapFromEntity(eventRepository.delete(id));
        }
        catch(Exception ex)
        {
            throw new NoContentException();
        }
    }
}
