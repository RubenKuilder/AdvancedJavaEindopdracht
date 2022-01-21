package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.GlobalSettingsDto;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
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
@ContextConfiguration(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
class GlobalSettingsServiceTest {
    @Autowired
    private GlobalSettingsService globalSettingsService;

    @Test
    @Transactional
    void getGlobalSettings() {
        List<GlobalSettingsDto> globalSettingsDtoList = globalSettingsService.getGlobalSettings();

        assertEquals(3, globalSettingsDtoList.size());
        assertTrue(globalSettingsDtoList.get(0).isSoundOn());
        assertFalse(globalSettingsDtoList.get(1).isSoundOn());
    }

    @Test
    @Transactional
    void getGlobalSettingsById() {
        GlobalSettingsDto globalSettingsDto = globalSettingsService.getGlobalSettingsById(1);
        assertTrue(globalSettingsDto.isSoundOn());
        assertEquals(new Time(00, 00, 00), globalSettingsDto.getSwitchTime());

        assertThrows(DataNotFoundException.class, () -> {
            globalSettingsService.getGlobalSettingsById(1124);
        });
    }

    @Test
    @Transactional
    void createGlobalSettings() {
        GlobalSettings globalSettings = new GlobalSettings();
        globalSettings.setSwitchTime(new Time(12, 12, 12));
        globalSettings.setSoundOn(false);
        GlobalSettingsDto globalSettingsDto = globalSettingsService.createGlobalSettings(globalSettings);

        assertFalse(globalSettingsDto.isSoundOn());
        assertEquals(new Time(12, 12, 12), globalSettingsDto.getSwitchTime());
    }

    @Test
    @Transactional
    void deleteGlobalSettings() {
        GlobalSettingsDto globalSettingsDto = globalSettingsService.deleteGlobalSettings(1);
        assertTrue(globalSettingsDto.isSoundOn());
        assertEquals(new Time(00, 00, 00), globalSettingsDto.getSwitchTime());

        assertThrows(NoContentException.class, () -> {
            globalSettingsService.deleteGlobalSettings(1421);
        });
    }

    @Test
    @Transactional
    void updateGlobalSettings() {
        GlobalSettings globalSettings = new GlobalSettings();
        globalSettings.setSwitchTime(new Time(12, 12, 12));
        globalSettings.setSoundOn(false);
        GlobalSettingsDto globalSettingsDto = globalSettingsService.updateGlobalSettings(globalSettings, 1);

        assertFalse(globalSettingsDto.isSoundOn());
        assertEquals(new Time(12, 12, 12), globalSettingsDto.getSwitchTime());
    }
}