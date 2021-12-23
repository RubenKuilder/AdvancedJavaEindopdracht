package org.AdvancedJavaEindopdracht.resource.repository;

import org.AdvancedJavaEindopdracht.resource.model.GlobalSettings;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class GlobalSettingsRepository
{
    @PersistenceContext
    private EntityManager manager;

    public List<GlobalSettings> getSettings()
    {
        TypedQuery<GlobalSettings> query = manager.createQuery("SELECT g FROM GlobalSettings g", GlobalSettings.class);
        return query.getResultList();
    }

    public GlobalSettings getSettingsById(Integer id)
    {
        TypedQuery<GlobalSettings> query = manager.createQuery("SELECT g FROM GlobalSettings g WHERE id = :id", GlobalSettings.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public GlobalSettings uploadGlobalSettings(GlobalSettings globalSettings)
    {
        manager.persist(globalSettings);
        return manager.find(GlobalSettings.class, globalSettings.getId());
    }

    public GlobalSettings deleteGlobalSettings(Integer id)
    {
        GlobalSettings globalSettings = manager.find(GlobalSettings.class, id);
        manager.remove(globalSettings);
        return globalSettings;
    }

    public GlobalSettings updateGlobalSettings(GlobalSettings globalSettings, Integer id)
    {
        GlobalSettings globalSettingsToUpdate = manager.find(GlobalSettings.class, id);
        globalSettingsToUpdate.setSwitchTime(globalSettings.getSwitchTime());
        globalSettingsToUpdate.setSoundOn(globalSettings.isSoundOn());
        return globalSettingsToUpdate;
    }
}
