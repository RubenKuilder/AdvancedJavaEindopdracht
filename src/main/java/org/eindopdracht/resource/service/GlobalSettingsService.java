package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.GlobalSettingsDTO;
import org.eindopdracht.resource.exception.general.BadRequestException;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.mapper.GlobalSettingsMapper;
import org.eindopdracht.resource.model.GlobalSettings;
import org.eindopdracht.resource.repository.GlobalSettingsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalSettingsService {
    private final GlobalSettingsRepository globalSettingsRepository;
    private final GlobalSettingsMapper globalSettingsMapper;

    public GlobalSettingsService(GlobalSettingsRepository globalSettingsRepository, GlobalSettingsMapper globalSettingsMapper) {
        this.globalSettingsRepository = globalSettingsRepository;
        this.globalSettingsMapper = globalSettingsMapper;
    }

    /**
     * Maps Entity to DTO and returns a list of all global settings.
     *
     * @return response entity with list of all global settings
     */
    public List<GlobalSettingsDTO> getGlobalSettings() {
        return globalSettingsMapper.mapFromEntityList(globalSettingsRepository.getSettings());
    }

    /**
     * Maps Entity to DTO and returns a single global setting.
     *
     * @param id id of the global setting to find
     * @return response entity with single global setting
     */
    public GlobalSettingsDTO getGlobalSettingsById(Integer id) {
        try {
            return globalSettingsMapper.mapFromEntity(globalSettingsRepository.getSettingsById(id));
        } catch (Exception ex) {
            throw new DataNotFoundException();
        }
    }

    /**
     * Maps Entity to DTO and posts a single global setting.
     *
     * @param globalSettingsDTO global setting to post
     * @return response entity with posted global setting
     */
    public GlobalSettingsDTO createGlobalSettings(GlobalSettingsDTO globalSettingsDTO) {
        try {
            return globalSettingsMapper.mapFromEntity(
                    globalSettingsRepository.uploadGlobalSettings(globalSettingsMapper.mapToEntity(globalSettingsDTO))
            );
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and deletes a single global setting.
     *
     * @param id id of the global setting to delete
     * @return response entity with deleted global setting
     */
    public GlobalSettingsDTO deleteGlobalSettings(Integer id) {
        try {
            return globalSettingsMapper.mapFromEntity(globalSettingsRepository.deleteGlobalSettings(id));
        } catch (Exception ex) {
            throw new NoContentException();
        }
    }

    /**
     * Maps Entity to DTO and puts a single global setting.
     *
     * @param id             id of the global setting to put
     * @param globalSettingsDTO global setting to put
     * @return response entity with put global setting
     */
    public GlobalSettingsDTO updateGlobalSettings(GlobalSettingsDTO globalSettingsDTO, Integer id) {
        try {
            return globalSettingsMapper.mapFromEntity(globalSettingsRepository.updateGlobalSettings(globalSettingsMapper.mapToEntity(globalSettingsDTO), id));
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }
}
