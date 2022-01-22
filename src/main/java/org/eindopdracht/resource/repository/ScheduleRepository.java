package org.eindopdracht.resource.repository;

import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.model.Schedule;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class ScheduleRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Select queries all schedules and returns them in a list.
     * Distinct is used to prevent duplicate data.
     * If distinct wasn't used, a schedule with two users would've been returned twice.
     *
     * @return response entity with list of all schedules
     */
    public List<Schedule> get() {
        TypedQuery<Schedule> query = entityManager.createQuery("SELECT DISTINCT s FROM Schedule s JOIN FETCH s.users u", Schedule.class);
        return query.getResultList();
    }

    /**
     * Find a single schedule and return it.
     * Distinct is used to prevent duplicate data.
     * If distinct wasn't used, a schedule with two users would've been returned twice.
     * We didn't use entityManager.find here because this gave a lazyInitialize error.
     *
     * @param id id of the schedule to find
     * @return response entity with a single schedule
     */
    public Schedule getById(long id) {
        TypedQuery<Schedule> query = entityManager.createQuery("SELECT DISTINCT s FROM Schedule s JOIN FETCH s.users u WHERE s.id = :id", Schedule.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * Post a single schedule.
     *
     * @param schedule schedule to post
     * @return response entity with posted schedule
     */
    public Schedule post(Schedule schedule) {
        entityManager.persist(schedule);
        return schedule;
    }

    /**
     * Put a single schedule.
     * Updates all fields.
     *
     * @param id       id of the schedule to put
     * @param schedule schedule to put
     * @return response entity with put schedule
     */
    public Schedule put(long id, Schedule schedule) {
        schedule.setId(id);
        return entityManager.merge(schedule);
    }


    /**
     * Delete a single schedule and return it.
     *
     * @param id id of the schedule to delete
     * @return response entity with deleted schedule
     */
    public Schedule delete(long id) {
        Schedule scheduleToDelete = getById(id);

        entityManager.remove(entityManager.contains(scheduleToDelete) ? scheduleToDelete : entityManager.merge(scheduleToDelete));
        return scheduleToDelete;
    }
}
