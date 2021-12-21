package org.AdvancedJavaEindopdracht.resource.repository;

import org.AdvancedJavaEindopdracht.resource.model.Role;
import org.AdvancedJavaEindopdracht.resource.model.UserAvailability;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserAvailabilityRepository {

    @PersistenceContext
    private EntityManager manager;

    public List<UserAvailability> getUserAvailabilities(){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<UserAvailability> query = cb.createQuery(UserAvailability.class);
        return manager.createQuery(query.select(query.from(UserAvailability.class))).getResultList();
    }

    public UserAvailability getUserAvailability(int id){
        return manager.find(UserAvailability.class, id);
    }

    public UserAvailability postUserAvailability(UserAvailability userAvailability){
        manager.persist(userAvailability);
        return manager.find(UserAvailability.class, userAvailability.getId());
    }

    public UserAvailability putUserAvailability(UserAvailability userAvailability, int id){
        UserAvailability update = manager.find(UserAvailability.class, id);
        update.setUser(userAvailability.getUser());
        update.setDate(userAvailability.getDate());
        return update;
    }

    public void deleteUserAvailability(int id){
        UserAvailability ua = manager.find(UserAvailability.class, id);
        manager.remove(ua);
    }
}
