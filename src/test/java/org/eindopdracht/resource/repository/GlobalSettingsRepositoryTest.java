package org.eindopdracht.resource.repository;

import org.eindopdracht.resource.model.GlobalSettings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
class GlobalSettingsRepositoryTest
{
    @Autowired
    private GlobalSettingsRepository globalSettingsRepository;

    @Test
    void getSettings()
    {
        List<GlobalSettings> globalSettingsList = globalSettingsRepository.getSettings();
        assertEquals(3, globalSettingsList.size());
        assertTrue(globalSettingsList.get(0).isSoundOn());
        assertEquals(new Time(00,00,00), globalSettingsList.get(0).getSwitchTime());
    }

    @Test
    void getSettingsById()
    {
        GlobalSettings globalSettings = globalSettingsRepository.getSettingsById(1);
        assertTrue(globalSettings.isSoundOn());
        assertEquals(new Time(00,00,00), globalSettings.getSwitchTime());
    }

    @Test
    void uploadGlobalSettings()
    {
        GlobalSettings globalSettings = new GlobalSettings();
        globalSettings.setSoundOn(true);
        globalSettings.setSwitchTime(new Time(12, 12, 12));
        GlobalSettings uploadedSettings = globalSettingsRepository.uploadGlobalSettings(globalSettings);
        assertTrue(uploadedSettings.isSoundOn());
        assertEquals(new Time(12, 12 ,12), uploadedSettings.getSwitchTime());
    }

    @Test
    void deleteGlobalSettings()
    {
        GlobalSettings globalSettingsDeleted = globalSettingsRepository.deleteGlobalSettings(1);
        assertTrue(globalSettingsDeleted.isSoundOn());
        assertEquals(new Time(00,00,00), globalSettingsDeleted.getSwitchTime());
    }

    @Test
    void updateGlobalSettings()
    {
        GlobalSettings globalSettings = new GlobalSettings();
        globalSettings.setSoundOn(false);
        globalSettings.setSwitchTime(new Time(10, 10, 10));
        GlobalSettings updatedGlobalSettings = globalSettingsRepository.updateGlobalSettings(globalSettings, 1);

        assertFalse(updatedGlobalSettings.isSoundOn());
        assertEquals(new Time(10, 10, 10), updatedGlobalSettings.getSwitchTime());
    }
}