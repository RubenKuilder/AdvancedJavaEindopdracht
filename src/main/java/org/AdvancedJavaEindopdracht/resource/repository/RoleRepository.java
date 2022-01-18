package org.AdvancedJavaEindopdracht.resource.repository;

import org.AdvancedJavaEindopdracht.resource.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RoleRepository {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Returns a list of all roles.
     *
     * @return      response entity with list of all roles
     */
    public List<Role> getRoles(){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Role> query = cb.createQuery(Role.class);
        return manager.createQuery(query.select(query.from(Role.class))).getResultList();
    }

    /**
     * Find a single role and return it.
     *
     * @param id    id of the role to find
     * @return      response entity with a single role
     */
    public Role getRole(int id){
        return manager.find(Role.class, id);
    }

    /**
     * Post a single role.
     *
     * @param role  role to post
     * @return      response entity with posted role
     */
    public Role postRole(Role role){
        manager.persist(role);
        return manager.find(Role.class, role.getId());
    }

    /**
     * Put a single role.
     * Updates all fields.
     *
     * @param id    id of the role to put
     * @param role  role to put
     * @return      response entity with put role
     */
    public Role putRole(Role role, int id){
        Role update = manager.find(Role.class, id);
        update.setRole(role.getRole());
        return update;
    }

    /**
     * Delete a single role and return it.
     *
     * @param id    id of the role to delete
     * @return      response entity with deleted role
     */
    public Role deleteRole(int id){
        Role role = manager.find(Role.class, id);
        manager.remove(role);
        return role;
    }
}
