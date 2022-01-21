package org.AdvancedJavaEindopdracht.resource.repository;

import org.AdvancedJavaEindopdracht.exception.general.DataNotFoundException;
import org.AdvancedJavaEindopdracht.resource.model.schedule.Schedule;
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
     * @return      response entity with list of all schedules
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
     * @param id    id of the schedule to find
     * @return      response entity with a single schedule
     */
    public Schedule getById(long id) {
        TypedQuery<Schedule> query = entityManager.createQuery("SELECT DISTINCT s FROM Schedule s JOIN FETCH s.users u WHERE s.id = :id", Schedule.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * Post a single schedule.
     *
     * @param schedule  schedule to post
     * @return          response entity with posted schedule
     */
    public Schedule post(Schedule schedule) {
        entityManager.persist(schedule);
        return schedule;
    }

    /**
     * Put a single schedule.
     * Updates all fields.
     *
     * @param id        id of the schedule to put
     * @param schedule  schedule to put
     * @return          response entity with put schedule
     */
    public Schedule put(long id, Schedule schedule) {
        schedule.setId(id);
        return entityManager.merge(schedule);
    }

    /**
     * Patch a single schedule.
     * Updates whatever field is set in the new object.
     * If a field is null, it will not be updated.
     *
     * @param id        id of the schedule to patch
     * @param schedule  consultation to patch
     * @return          response entity with patched schedule
     */
    public Schedule patch(long id, Schedule schedule) {
        if (getById(id) == null)
            throw new DataNotFoundException();

        Schedule updatedSchedule = getById(id);

        if (schedule.getUsers() != null) {
            updatedSchedule.setUsers(schedule.getUsers());
        }

        if (schedule.getStartDateTime() != null) {
            updatedSchedule.setStartDateTime(schedule.getStartDateTime());
        }

        if (schedule.getEndDateTime() != null) {
            updatedSchedule.setEndDateTime(schedule.getEndDateTime());
        }

        if(schedule.getDescription() != null && !Objects.equals(schedule.getDescription(), "")) {
            updatedSchedule.setDescription(schedule.getDescription());
        }

        if(schedule.getTitle() != null && !Objects.equals(schedule.getTitle(), ""))
        {
            updatedSchedule.setTitle(schedule.getTitle());
        }

        return updatedSchedule;
    }

    /**
     * Delete a single schedule and return it.
     *
     * @param id    id of the schedule to delete
     * @return      response entity with deleted schedule
     */
    public Schedule delete(long id) {
        Schedule scheduleToDelete = getById(id);

        entityManager.remove(entityManager.contains(scheduleToDelete) ? scheduleToDelete : entityManager.merge(scheduleToDelete));
        return scheduleToDelete;
    }
}
