package org.eindopdracht.resource.repository;

import org.eindopdracht.resource.model.Powerpoint;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PowerpointRepository {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Returns a list of all powerpoints.
     *
     * @return response entity with list of all powerpoints
     */
    public List<Powerpoint> getPowerpoints() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Powerpoint> query = cb.createQuery(Powerpoint.class);
        return manager.createQuery(query.select(query.from(Powerpoint.class))).getResultList();
    }

    /**
     * Find a single powerpoint and return it.
     *
     * @param id id of the powerpoint to find
     * @return response entity with a single powerpoint
     */
    public Powerpoint getPowerpoint(int id) {
        return manager.find(Powerpoint.class, id);
    }

    /**
     * Post a single powerpoint.
     *
     * @param powerpoint powerpoint to post
     * @return response entity with posted powerpoint
     */
    public Powerpoint postPowerpoint(Powerpoint powerpoint) {
        manager.persist(powerpoint);
        return manager.find(Powerpoint.class, powerpoint.getId());
    }

    /**
     * Put a single powerpoint.
     * Updates all fields.
     *
     * @param id         id of the powerpoint to put
     * @param powerpoint powerpoint to put
     * @return response entity with put powerpoint
     */
    public Powerpoint putPowerpoint(Powerpoint powerpoint, int id) {
        Powerpoint update = manager.find(Powerpoint.class, id);
        update.setUser(powerpoint.getUser());
        update.setPath(powerpoint.getPath());
        return update;
    }

    /**
     * Delete a single powerpoint and return it.
     *
     * @param id id of the powerpoint to delete
     * @return response entity with deleted powerpoint
     */
    public Powerpoint deletePowerpoint(int id) {
        Powerpoint powerpoint = manager.find(Powerpoint.class, id);
        manager.remove(powerpoint);
        return powerpoint;
    }
}
