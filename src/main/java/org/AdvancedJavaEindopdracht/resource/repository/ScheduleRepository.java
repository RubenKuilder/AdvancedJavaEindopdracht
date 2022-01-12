package org.AdvancedJavaEindopdracht.resource.repository;

import org.AdvancedJavaEindopdracht.exception.general.DataNotFoundException;
import org.AdvancedJavaEindopdracht.resource.model.GlobalSettings;
import org.AdvancedJavaEindopdracht.resource.model.consultation.Consultation;
import org.AdvancedJavaEindopdracht.resource.model.event.content.Content;
import org.AdvancedJavaEindopdracht.resource.model.schedule.Schedule;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class ScheduleRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Schedule> get() {
        TypedQuery<Schedule> query = entityManager.createQuery("SELECT DISTINCT s FROM Schedule s JOIN FETCH s.users u", Schedule.class);
        return query.getResultList();
    }

    // Hier gebruik ik geen entityManager.find omdat die een lazyInitialize error gaf.
    public Schedule getById(long id) {
        TypedQuery<Schedule> query = entityManager.createQuery("SELECT DISTINCT s FROM Schedule s JOIN FETCH s.users u WHERE s.id = :id", Schedule.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public Schedule persist(Schedule schedule) {
        entityManager.persist(schedule);
        return schedule;
    }

    public Schedule put(long id, Schedule schedule) {
        schedule.setId(id);
        return entityManager.merge(schedule);
    }

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

        return updatedSchedule;
    }

    public Schedule delete(long id) {
        Schedule scheduleToDelete = getById(id);

        entityManager.remove(entityManager.contains(scheduleToDelete) ? scheduleToDelete : entityManager.merge(scheduleToDelete));
        return scheduleToDelete;
    }
}
