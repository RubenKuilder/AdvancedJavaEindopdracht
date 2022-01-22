package org.eindopdracht.resource.repository;

import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.model.Event;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class EventRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Select queries all events and returns them in a list.
     *
     * @return      response entity with list of all events
     */
    public List<Event> get() {
        TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e", Event.class);
        return query.getResultList();
    }

    /**
     * Find a single event and return it.
     *
     * @param id    id of the event to find
     * @return      response entity with a single event
     */
    public Event getById(long id) {
            return entityManager.find(Event.class, id);
    }

    /**
     * Post a single event.
     *
     * @param event event to post
     * @return      response entity with posted event
     */
    public Event persist(Event event) {
            entityManager.persist(event);
            return event;
    }

    /**
     * Put a single event.
     * Updates all fields.
     *
     * @param id    id of the event to put
     * @param event event to put
     * @return      response entity with put event
     */
    public Event put(long id, Event event) {
        event.setId(id);
        return entityManager.merge(event);
    }

    /**
     * Patch a single event.
     * Updates whatever field is set in the new object.
     * If a field is null, it will not be updated.
     *
     * @param id    id of the event to patch
     * @param event event to patch
     * @return      response entity with patched event
     */
    public Event patch(long id, Event event) {
        if (getById(id) == null)
            throw new DataNotFoundException();

        Event updatedEvent = getById(id);

        if (event.getContent() != null) {
            updatedEvent.setContent(event.getContent());
        }

        if (event.getUser_id() != null) {
            updatedEvent.setUser_id(event.getUser_id());
        }

        if (event.getDescription() != null) {
            updatedEvent.setDescription(event.getDescription());
        }

        if (event.getStartDateTime() != null) {
            updatedEvent.setStartDateTime(event.getStartDateTime());
        }

        if (event.getEndDateTime() != null) {
            updatedEvent.setEndDateTime(event.getEndDateTime());
        }

        if (event.getDuration() != null) {
            updatedEvent.setDuration(event.getDuration());
        }

        return updatedEvent;
    }

    /**
     * Delete a single event and return it.
     *
     * @param id    id of the event to delete
     * @return      response entity with deleted event
     */
    public Event delete(long id) {
        Event eventToDelete = getById(id);

        entityManager.remove(entityManager.contains(eventToDelete) ? eventToDelete : entityManager.merge(eventToDelete));
        return eventToDelete;
    }
}
