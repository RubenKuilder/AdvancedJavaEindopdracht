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

    @Transactional
    public List<Role> getRoles(){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Role> query = cb.createQuery(Role.class);
        return manager.createQuery(query.select(query.from(Role.class))).getResultList();
    }

    @Transactional
    public Role getRole(int id){
        return manager.find(Role.class, id);
    }

    @Transactional
    public Role postRole(Role role){
        manager.persist(role);
        return manager.find(Role.class, role.getId());
    }

    @Transactional
    public void putRole(Role role, int id){
        Role update = manager.find(Role.class, id);
        update.setRole(role.getRole());
    }

    @Transactional
    public void deleteRole(int id){
        Role role = manager.find(Role.class, id);
        manager.remove(role);
    }
}
