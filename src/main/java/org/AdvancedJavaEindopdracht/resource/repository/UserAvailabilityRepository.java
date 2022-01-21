package org.AdvancedJavaEindopdracht.resource.repository;

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

    /**
     * Returns a list of all user availabilities.
     *
     * @return      response entity with list of all user availabilities
     */
    public List<UserAvailability> getUserAvailabilities(){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<UserAvailability> query = cb.createQuery(UserAvailability.class);
        return manager.createQuery(query.select(query.from(UserAvailability.class))).getResultList();
    }

    /**
     * Find a single user availability and return it.
     *
     * @param id    id of the user availability to find
     * @return      response entity with a single user availability
     */
    public UserAvailability getUserAvailability(int id){
        return manager.find(UserAvailability.class, id);
    }

    /**
     * Post a single user availability.
     *
     * @param userAvailability  user availability to post
     * @return                  response entity with posted user availability
     */
    public UserAvailability postUserAvailability(UserAvailability userAvailability){
        manager.persist(userAvailability);
        return manager.find(UserAvailability.class, userAvailability.getId());
    }

    /**
     * Put a single user availability.
     * Updates all fields.
     *
     * @param id                id of the user availability to put
     * @param userAvailability  user availability to put
     * @return                  response entity with put user availability
     */
    public UserAvailability putUserAvailability(UserAvailability userAvailability, int id){
        UserAvailability update = manager.find(UserAvailability.class, id);
        update.setUser(userAvailability.getUser());
        update.setDate(userAvailability.getDate());
        return update;
    }

    /**
     * Delete a single user availability and return it.
     *
     * @param id    id of the user availability to delete
     * @return      response entity with deleted user availability
     */
    public UserAvailability deleteUserAvailability(int id){
        UserAvailability ua = manager.find(UserAvailability.class, id);
        manager.remove(ua);
        return ua;
    }
}
