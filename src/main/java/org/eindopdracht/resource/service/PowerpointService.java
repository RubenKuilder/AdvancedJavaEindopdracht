package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.PowerpointDTO;
import org.eindopdracht.resource.exception.general.BadRequestException;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.mapper.PowerpointMapper;
import org.eindopdracht.resource.model.Powerpoint;
import org.eindopdracht.resource.repository.PowerpointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowerpointService {
    private final PowerpointRepository powerpointRepository;
    private final PowerpointMapper powerpointMapper;

    public PowerpointService(PowerpointRepository roleRepository, PowerpointMapper powerpointMapper) {
        this.powerpointRepository = roleRepository;
        this.powerpointMapper = powerpointMapper;
    }

    /**
     * Maps Entity to DTO and returns a list of all powerpoints.
     *
     * @return response entity with list of all powerpoints
     */
    public List<PowerpointDTO> getPowerpoints() {
        return powerpointMapper.mapFromEntityList(powerpointRepository.getPowerpoints());
    }

    /**
     * Maps Entity to DTO and returns a single powerpoint.
     *
     * @param id id of the powerpoint to find
     * @return response entity with single powerpoint
     */
    public PowerpointDTO getPowerpoint(Integer id) {
        try {
            return powerpointMapper.mapFromEntity(powerpointRepository.getPowerpoint(id));
        } catch (Exception ex) {
            throw new DataNotFoundException();
        }
    }

    /**
     * Maps Entity to DTO and posts a single powerpoint.
     *
     * @param powerpointDTO powerpoint to post
     * @return response entity with posted powerpoint
     */
    public PowerpointDTO create(PowerpointDTO powerpointDTO) {
        try {
            return powerpointMapper.mapFromEntity(
                    powerpointRepository.postPowerpoint(powerpointMapper.mapToEntity(powerpointDTO))
            );
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and puts a single powerpoint.
     *
     * @param id         id of the powerpoint to put
     * @param powerpointDTO powerpoint to put
     * @return response entity with put powerpoint
     */
    public PowerpointDTO update(PowerpointDTO powerpointDTO, Integer id) {
        try {
            return powerpointMapper.mapFromEntity(powerpointRepository.putPowerpoint(powerpointMapper.mapToEntity(powerpointDTO), id));
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and deletes a single powerpoint.
     *
     * @param id id of the powerpoint to delete
     * @return response entity with deleted powerpoint
     */
    public PowerpointDTO delete(Integer id) {
        try {
            return powerpointMapper.mapFromEntity(powerpointRepository.deletePowerpoint(id));
        } catch (Exception ex) {
            throw new NoContentException();
        }
    }
}
