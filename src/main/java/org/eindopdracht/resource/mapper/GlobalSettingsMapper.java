package org.eindopdracht.resource.mapper;

import org.eindopdracht.resource.dto.GlobalSettingsDTO;
import org.eindopdracht.resource.model.GlobalSettings;
import org.eindopdracht.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GlobalSettingsMapper implements EntityMapper<GlobalSettings, GlobalSettingsDTO> {
    @Override
    public GlobalSettingsDTO mapFromEntity(GlobalSettings globalSettings) {
        return new GlobalSettingsDTO(
                globalSettings.getId(),
                globalSettings.isSoundOn(),
                globalSettings.getSwitchTime()
        );
    }

    @Override
    public GlobalSettings mapToEntity(GlobalSettingsDTO globalSettingsDto) {
        return new GlobalSettings(
                globalSettingsDto.getId(),
                globalSettingsDto.isSoundOn(),
                globalSettingsDto.getSwitchTime()
        );
    }

    public List<GlobalSettingsDTO> mapFromEntityList(List<GlobalSettings> entities) {
        List<GlobalSettingsDTO> globalSettingsDTOList = new ArrayList<>();
        for (GlobalSettings entity : entities) {
            globalSettingsDTOList.add(mapFromEntity(entity));
        }

        return globalSettingsDTOList;
    }
}
