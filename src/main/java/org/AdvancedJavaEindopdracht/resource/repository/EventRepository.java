package org.AdvancedJavaEindopdracht.resource.repository;

import org.AdvancedJavaEindopdracht.resource.exception.general.BadRequestException;
import org.AdvancedJavaEindopdracht.resource.exception.general.DataNotFoundException;
import org.AdvancedJavaEindopdracht.resource.model.event.Event;
import org.AdvancedJavaEindopdracht.resource.model.event.content.contentType.ContentType;
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
        try {
            return entityManager.find(Event.class, id);
        }catch(Exception e){
            throw new DataNotFoundException();
        }
    }

    /**
     * Post a single event.
     *
     * @param event event to post
     * @return      response entity with posted event
     */
    public Event persist(Event event) {
        try {
            entityManager.persist(event);
            return event;
        }catch(Exception e){
            throw new BadRequestException();
        }
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
