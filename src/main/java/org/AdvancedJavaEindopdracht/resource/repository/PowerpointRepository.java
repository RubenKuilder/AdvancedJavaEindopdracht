package org.AdvancedJavaEindopdracht.resource.repository;

import org.AdvancedJavaEindopdracht.resource.model.GlobalSettings;
import org.AdvancedJavaEindopdracht.resource.model.Powerpoint;
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
public class PowerpointRepository {

    @PersistenceContext
    private EntityManager manager;

    public List<Powerpoint> getPowerpoints(){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Powerpoint> query = cb.createQuery(Powerpoint.class);
        return manager.createQuery(query.select(query.from(Powerpoint.class))).getResultList();
    }

    public Powerpoint getPowerpoint(int id){
        return manager.find(Powerpoint.class, id);
    }

    public Powerpoint postPowerpoint(Powerpoint powerpoint){
        manager.persist(powerpoint);
        return manager.find(Powerpoint.class, powerpoint.getId());
    }

    public Powerpoint putPowerpoint(Powerpoint powerpoint, int id){
        Powerpoint update = manager.find(Powerpoint.class, id);
        update.setUser(powerpoint.getUser());
        update.setPath(powerpoint.getPath());
        return update;
    }

    public Powerpoint deletePowerpoint(int id){

        Powerpoint powerpoint = manager.find(Powerpoint.class, id);
        manager.remove(powerpoint);
        return powerpoint;
    }
}
