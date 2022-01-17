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

    public List<PowerpointDTO> getPowerpoints() { return repository.getPowerpoints().stream().map(convertToDTO::toPowerpointDTO).collect(Collectors.toList());}

    public PowerpointDTO getPowerpoint(Integer id){
        return convertToDTO.toPowerpointDTO(repository.getPowerpoint(id));
    }

    public PowerpointDTO create(Powerpoint powerpoint){
        return convertToDTO.toPowerpointDTO(repository.postPowerpoint(powerpoint));
    }

    public PowerpointDTO update(Powerpoint powerpoint, Integer id){
        return convertToDTO.toPowerpointDTO(repository.putPowerpoint(powerpoint, id));
    }

    public PowerpointDTO delete(Integer id){
        return convertToDTO.toPowerpointDTO(repository.deletePowerpoint(id));
    }
}
