package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.ConvertToDTO;
import org.AdvancedJavaEindopdracht.resource.dto.RoleDTO;
import org.AdvancedJavaEindopdracht.resource.model.Role;
import org.AdvancedJavaEindopdracht.resource.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private final ConvertToDTO convertToDTO = new ConvertToDTO();

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public List<RoleDTO> getRoles() { return roleRepository.getRoles().stream().map(convertToDTO::toRoleDTO).collect(Collectors.toList());}

    public RoleDTO getRole(Integer id){
        return convertToDTO.toRoleDTO(roleRepository.getRole(id));
    }

    public RoleDTO create(Role role){
        return convertToDTO.toRoleDTO(roleRepository.postRole(role));
    }

    public RoleDTO update(Role role, Integer id){
        return convertToDTO.toRoleDTO(roleRepository.putRole(role, id));
    }

    public RoleDTO delete(Integer id){
        return convertToDTO.toRoleDTO(roleRepository.deleteRole(id));
    }
}
