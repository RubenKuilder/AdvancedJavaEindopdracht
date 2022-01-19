package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.ConvertToDTO;
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
    private final ConvertToDTO convertToDto = new ConvertToDTO();

    public GlobalSettingsService(GlobalSettingsRepository globalSettingsRepository)
    {
        this.globalSettingsRepository = globalSettingsRepository;
    }

    /**
     * Maps Entity to DTO and returns a list of all global settings.
     *
     * @return      response entity with list of all global settings
     */
    public List<GlobalSettingsDto> getGlobalSettings()
    {
        return globalSettingsRepository.getSettings().stream().map(convertToDto::toGlobalSettingsDto).collect(Collectors.toList());
    }

    /**
     * Maps Entity to DTO and returns a single global setting.
     *
     * @param id    id of the global setting to find
     * @return      response entity with single global setting
     */
    public GlobalSettingsDto getGlobalSettingsById(Integer id)
    {

        return convertToDto.toGlobalSettingsDto(globalSettingsRepository.getSettingsById(id));
    }

    /**
     * Maps Entity to DTO and posts a single global setting.
     *
     * @param globalSettings    global setting to post
     * @return                  response entity with posted global setting
     */
    public GlobalSettingsDto createGlobalSettings(GlobalSettings globalSettings)
    {
        return convertToDto.toGlobalSettingsDto(globalSettingsRepository.uploadGlobalSettings(globalSettings));
    }

    /**
     * Maps Entity to DTO and deletes a single global setting.
     *
     * @param id    id of the global setting to delete
     * @return      response entity with deleted global setting
     */
    public GlobalSettingsDto deleteGlobalSettings(Integer id)
    {
        return convertToDto.toGlobalSettingsDto(globalSettingsRepository.deleteGlobalSettings(id));
    }

    /**
     * Maps Entity to DTO and puts a single global setting.
     *
     * @param id                id of the global setting to put
     * @param globalSettings    global setting to put
     * @return                  response entity with put global setting
     */
    public GlobalSettingsDto updateGlobalSettings(GlobalSettings globalSettings, Integer id)
    {
        return convertToDto.toGlobalSettingsDto(globalSettingsRepository.updateGlobalSettings(globalSettings, id));
    }
}
