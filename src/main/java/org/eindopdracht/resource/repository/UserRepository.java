package org.eindopdracht.resource.repository;

import org.eindopdracht.resource.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepository {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Returns a list of all users.
     *
     * @return      response entity with list of all users
     */
    public List<User> getUsers(){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        return manager.createQuery(query.select(query.from(User.class))).getResultList();
    }

    /**
     * Find a single user and return it.
     *
     * @param id    id of the user to find
     * @return      response entity with a single user
     */
    public User getUser(int id){
            return manager.find(User.class, id);
    }

    /**
     * Post a single user.
     *
     * @param user  user to post
     * @return      response entity with posted user
     */
    public User postUser(User user){
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setApproved(false);
            user.setRole("ROLE_USER");
            user.setEnabled(false);

            manager.persist(user);
            return manager.find(User.class, user.getId());
    }

    /**
     * Put a single user.
     * Updates all fields.
     *
     * @param id    id of the user to put
     * @param user  user to put
     * @return      response entity with put user
     */
    public User putUser(User user, int id){
        User update = manager.find(User.class, id);
        update.setName(user.getName());
        update.setEmail(user.getEmail());
        update.setProfileImagePath(user.getProfileImagePath());
        update.setPassword(user.getPassword());
        update.setApproved(user.isApproved());
        return update;
    }

    /**
     * Delete a single user and return it.
     *
     * @param id    id of the user to delete
     * @return      response entity with deleted user
     */
    public User deleteUser(int id){
        User user = manager.find(User.class, id);
        manager.remove(user);
        return user;
    }
}
