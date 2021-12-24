package org.AdvancedJavaEindopdracht.resource.repository;

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
        TypedQuery<Schedule> query = entityManager.createQuery("SELECT c FROM Content c", Schedule.class);
        return query.getResultList();
    }

    public Schedule getById(long id) {
        return entityManager.find(Schedule.class, id);
    }

    public Schedule persist(Schedule schedule) {
        entityManager.persist(schedule);
        return schedule;
    }
}
