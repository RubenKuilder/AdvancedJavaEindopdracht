package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.ConvertToDTO;
import org.AdvancedJavaEindopdracht.resource.dto.PowerpointDTO;
import org.AdvancedJavaEindopdracht.resource.dto.RoleDTO;
import org.AdvancedJavaEindopdracht.resource.model.Powerpoint;
import org.AdvancedJavaEindopdracht.resource.model.Role;
import org.AdvancedJavaEindopdracht.resource.repository.PowerpointRepository;
import org.AdvancedJavaEindopdracht.resource.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PowerpointService {
    private final ConvertToDTO convertToDTO = new ConvertToDTO();

    private final PowerpointRepository repository;

    public PowerpointService(PowerpointRepository roleRepository){
        this.repository = roleRepository;
    }

    /**
     * Maps Entity to DTO and returns a list of all powerpoints.
     *
     * @return      response entity with list of all powerpoints
     */
    public List<PowerpointDTO> getPowerpoints() { return repository.getPowerpoints().stream().map(convertToDTO::toPowerpointDTO).collect(Collectors.toList());}

    /**
     * Maps Entity to DTO and returns a single powerpoint.
     *
     * @param id    id of the powerpoint to find
     * @return      response entity with single powerpoint
     */
    public PowerpointDTO getPowerpoint(Integer id){
        return convertToDTO.toPowerpointDTO(repository.getPowerpoint(id));
    }

    /**
     * Maps Entity to DTO and posts a single powerpoint.
     *
     * @param powerpoint    powerpoint to post
     * @return              response entity with posted powerpoint
     */
    public PowerpointDTO create(Powerpoint powerpoint){
        return convertToDTO.toPowerpointDTO(repository.postPowerpoint(powerpoint));
    }

    /**
     * Maps Entity to DTO and puts a single powerpoint.
     *
     * @param id            id of the powerpoint to put
     * @param powerpoint    powerpoint to put
     * @return              response entity with put powerpoint
     */
    public PowerpointDTO update(Powerpoint powerpoint, Integer id){
        return convertToDTO.toPowerpointDTO(repository.putPowerpoint(powerpoint, id));
    }

    /**
     * Maps Entity to DTO and deletes a single powerpoint.
     *
     * @param id    id of the powerpoint to delete
     * @return      response entity with deleted powerpoint
     */
    public PowerpointDTO delete(Integer id){
        return convertToDTO.toPowerpointDTO(repository.deletePowerpoint(id));
    }
}
