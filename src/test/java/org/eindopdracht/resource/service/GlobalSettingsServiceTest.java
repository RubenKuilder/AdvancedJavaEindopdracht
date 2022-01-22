package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.EventDTO;
import org.eindopdracht.resource.dto.GlobalSettingsDTO;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.model.GlobalSettings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
class GlobalSettingsServiceTest {
    @Autowired
    private GlobalSettingsService globalSettingsService;

    @Test
    @Transactional
    void getGlobalSettings()
    {
        List<GlobalSettingsDTO> globalSettingsDTOList = globalSettingsService.getGlobalSettings();

        assertEquals(3, globalSettingsDTOList.size());
        assertTrue(globalSettingsDTOList.get(0).isSoundOn());
        assertFalse(globalSettingsDTOList.get(1).isSoundOn());
    }

    @Test
    @Transactional
    void getGlobalSettingsById()
    {
        GlobalSettingsDTO globalSettingsDto = globalSettingsService.getGlobalSettingsById(1);
        assertTrue(globalSettingsDto.isSoundOn());
        assertEquals(new Time(00, 00, 00), globalSettingsDto.getSwitchTime());

        assertThrows(DataNotFoundException.class, () -> {
            globalSettingsService.getGlobalSettingsById(1124);
        });
    }

    @Test
    @Transactional
    void createGlobalSettings()
    {
        GlobalSettingsDTO globalSettingsDTO = new GlobalSettingsDTO();
        globalSettingsDTO.setSwitchTime(new Time(12,12,12));
        globalSettingsDTO.setSoundOn(false);
        GlobalSettingsDTO persistedGlobalSettingsDTO = globalSettingsService.createGlobalSettings(globalSettingsDTO);

        assertFalse(persistedGlobalSettingsDTO.isSoundOn());
        assertEquals(new Time(12,12,12),persistedGlobalSettingsDTO.getSwitchTime());
    }

    @Test
    @Transactional
    void deleteGlobalSettings()
    {
        GlobalSettingsDTO globalSettingsDto = globalSettingsService.deleteGlobalSettings(1);
        assertTrue(globalSettingsDto.isSoundOn());
        assertEquals(new Time(00, 00, 00), globalSettingsDto.getSwitchTime());

        assertThrows(NoContentException.class, () -> {
            globalSettingsService.deleteGlobalSettings(1421);
        });
    }

    @Test
    @Transactional
    void updateGlobalSettings()
    {
        GlobalSettingsDTO globalSettingsDTO = new GlobalSettingsDTO();
        globalSettingsDTO.setSwitchTime(new Time(12,12,12));
        globalSettingsDTO.setSoundOn(false);
        GlobalSettingsDTO putGlobalSettingsDTO = globalSettingsService.updateGlobalSettings(globalSettingsDTO, 1);

        assertFalse(putGlobalSettingsDTO.isSoundOn());
        assertEquals(new Time(12, 12, 12), putGlobalSettingsDTO.getSwitchTime());
    }
}