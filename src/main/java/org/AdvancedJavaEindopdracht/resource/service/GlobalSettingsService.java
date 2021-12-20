package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.ConvertToDto;
import org.AdvancedJavaEindopdracht.resource.dto.GlobalSettingsDto;
import org.AdvancedJavaEindopdracht.resource.model.GlobalSettings;
import org.AdvancedJavaEindopdracht.resource.repository.GlobalSettingsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GlobalSettingsService
{
    private final GlobalSettingsRepository globalSettingsRepository;
    private final ConvertToDto convertToDto = new ConvertToDto();

    public GlobalSettingsService(GlobalSettingsRepository globalSettingsRepository)
    {
        this.globalSettingsRepository = globalSettingsRepository;
    }

    public List<GlobalSettingsDto> getGlobalSettings()
    {
        return globalSettingsRepository.getSettings().stream().map(convertToDto::toGlobalSettingsDto).collect(Collectors.toList());
    }

    public GlobalSettingsDto getGlobalSettingsById(Integer id)
    {
        return convertToDto.toGlobalSettingsDto(globalSettingsRepository.getSettingsById(id));
    }

    public GlobalSettingsDto createGlobalSettings(GlobalSettings globalSettings)
    {
        return convertToDto.toGlobalSettingsDto(globalSettingsRepository.uploadGlobalSettings(globalSettings));
    }

    public GlobalSettingsDto deleteGlobalSettings(Integer id)
    {
        return convertToDto.toGlobalSettingsDto(globalSettingsRepository.deleteGlobalSettings(id));
    }

    public GlobalSettingsDto updateGlobalSettings(GlobalSettings globalSettings, Integer id)
    {
        return convertToDto.toGlobalSettingsDto(globalSettingsRepository.updateGlobalSettings(globalSettings, id));
    }
}
