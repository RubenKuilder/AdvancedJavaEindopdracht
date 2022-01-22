package org.eindopdracht.resource.service;

import org.eindopdracht.ConvertToDTO;
import org.eindopdracht.resource.dto.PowerpointDTO;
import org.eindopdracht.resource.exception.general.BadRequestException;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.model.Powerpoint;
import org.eindopdracht.resource.repository.PowerpointRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PowerpointService {
    private final ConvertToDTO convertToDTO = new ConvertToDTO();

    private final PowerpointRepository repository;

    public PowerpointService(PowerpointRepository roleRepository) {
        this.repository = roleRepository;
    }

    /**
     * Maps Entity to DTO and returns a list of all powerpoints.
     *
     * @return response entity with list of all powerpoints
     */
    public List<PowerpointDTO> getPowerpoints() {
        return repository.getPowerpoints().stream().map(convertToDTO::toPowerpointDTO).collect(Collectors.toList());
    }

    /**
     * Maps Entity to DTO and returns a single powerpoint.
     *
     * @param id id of the powerpoint to find
     * @return response entity with single powerpoint
     */
    public PowerpointDTO getPowerpoint(Integer id) {
        try {
            return convertToDTO.toPowerpointDTO(repository.getPowerpoint(id));
        } catch (Exception ex) {
            throw new DataNotFoundException("id: " +id);
        }
    }

    /**
     * Maps Entity to DTO and posts a single powerpoint.
     *
     * @param powerpoint powerpoint to post
     * @return response entity with posted powerpoint
     */
    public PowerpointDTO create(Powerpoint powerpoint) {
        try {
            return convertToDTO.toPowerpointDTO(repository.postPowerpoint(powerpoint));
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and puts a single powerpoint.
     *
     * @param id         id of the powerpoint to put
     * @param powerpoint powerpoint to put
     * @return response entity with put powerpoint
     */
    public PowerpointDTO update(Powerpoint powerpoint, Integer id) {
        try {
            return convertToDTO.toPowerpointDTO(repository.putPowerpoint(powerpoint, id));
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
            return convertToDTO.toPowerpointDTO(repository.deletePowerpoint(id));
        } catch (Exception ex) {
            throw new NoContentException("id: " +id);
        }
    }
}
