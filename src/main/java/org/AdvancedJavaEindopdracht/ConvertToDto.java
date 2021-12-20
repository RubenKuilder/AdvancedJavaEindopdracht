package org.AdvancedJavaEindopdracht;

import org.AdvancedJavaEindopdracht.resource.dto.GlobalSettingsDto;
import org.AdvancedJavaEindopdracht.resource.model.GlobalSettings;

public class ConvertToDto
{
    public GlobalSettingsDto toGlobalSettingsDto(GlobalSettings globalSettings)
    {
        GlobalSettingsDto globalSettingsDto = new GlobalSettingsDto();
        globalSettingsDto.setSoundOn(globalSettings.isSoundOn());
        globalSettingsDto.setSwitchTime(globalSettings.getSwitchTime());
        return globalSettingsDto;
    }
}
