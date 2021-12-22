package org.AdvancedJavaEindopdracht.resource.repository;

import org.AdvancedJavaEindopdracht.exception.general.DataNotFoundException;
import org.AdvancedJavaEindopdracht.resource.model.event.Event;
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

    public List<Event> get() {
        TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e", Event.class);
        return query.getResultList();
    }

    public Event getById(long id) {
        return entityManager.find(Event.class, id);
    }

    public Event persist(Event event) {
        entityManager.persist(event);
        return event;
    }

    public Event put(long id, Event event) {
        event.setId(id);
        return entityManager.merge(event);
    }

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

    public void delete(long id) {
        Event eventToDelete = getById(id);
        entityManager.remove(entityManager.contains(eventToDelete) ? eventToDelete : entityManager.merge(eventToDelete));
    }
}
