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

    /**
     * Select queries all global settings and returns them in a list.
     *
     * @return      response entity with list of all global settings
     */
    public List<GlobalSettings> getSettings()
    {
        TypedQuery<GlobalSettings> query = manager.createQuery("SELECT g FROM GlobalSettings g", GlobalSettings.class);
        return query.getResultList();
    }

    /**
     * Find a single global setting and return it.
     *
     * @param id    id of the global setting to find
     * @return      response entity with a single global setting
     */
    public GlobalSettings getSettingsById(Integer id)
    {
        TypedQuery<GlobalSettings> query = manager.createQuery("SELECT g FROM GlobalSettings g WHERE id = :id", GlobalSettings.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * Post a single global setting.
     *
     * @param globalSettings    global setting to post
     * @return                  response entity with posted global setting
     */
    public GlobalSettings uploadGlobalSettings(GlobalSettings globalSettings)
    {
        manager.persist(globalSettings);
        return manager.find(GlobalSettings.class, globalSettings.getId());
    }

    /**
     * Delete a single global setting and return it.
     *
     * @param id    id of the global setting to delete
     * @return      response entity with deleted global setting
     */
    public GlobalSettings deleteGlobalSettings(Integer id)
    {
        GlobalSettings globalSettings = manager.find(GlobalSettings.class, id);
        manager.remove(globalSettings);
        return globalSettings;
    }

    /**
     * Put a single global setting.
     * Updates all fields.
     *
     * @param id    id of the global setting to put
     * @param globalSettings global setting to put
     * @return      response entity with put global setting
     */
    public GlobalSettings updateGlobalSettings(GlobalSettings globalSettings, Integer id)
    {
        GlobalSettings globalSettingsToUpdate = manager.find(GlobalSettings.class, id);
        globalSettingsToUpdate.setSwitchTime(globalSettings.getSwitchTime());
        globalSettingsToUpdate.setSoundOn(globalSettings.isSoundOn());
        return globalSettingsToUpdate;
    }
}
