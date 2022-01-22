package org.eindopdracht.resource.service;

import org.eindopdracht.ConvertToDTO;
import org.eindopdracht.resource.dto.RoleDTO;
import org.eindopdracht.resource.exception.general.BadRequestException;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.model.Role;
import org.eindopdracht.resource.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private final ConvertToDTO convertToDTO = new ConvertToDTO();

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Maps Entity to DTO and returns a list of all roles.
     *
     * @return response entity with list of all roles
     */
    public List<RoleDTO> getRoles() {
        return roleRepository.getRoles().stream().map(convertToDTO::toRoleDTO).collect(Collectors.toList());
    }

    /**
     * Maps Entity to DTO and returns a single role.
     *
     * @param id id of the role to find
     * @return response entity with single role
     */
    public RoleDTO getRole(Integer id) {
        try {
            return convertToDTO.toRoleDTO(roleRepository.getRole(id));
        } catch (Exception ex) {
            throw new DataNotFoundException("id: " +id);
        }
    }

    /**
     * Maps Entity to DTO and posts a single role.
     *
     * @param role role to post
     * @return response entity with posted role
     */
    public RoleDTO create(Role role) {
        try {
            return convertToDTO.toRoleDTO(roleRepository.postRole(role));
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and puts a single role.
     *
     * @param id   id of the role to put
     * @param role role to put
     * @return response entity with put role
     */
    public RoleDTO update(Role role, Integer id) {
        try {
            return convertToDTO.toRoleDTO(roleRepository.putRole(role, id));
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and deletes a single role.
     *
     * @param id id of the role to delete
     * @return response entity with deleted role
     */
    public RoleDTO delete(Integer id) {
        try {
            return convertToDTO.toRoleDTO(roleRepository.deleteRole(id));
        } catch (Exception ex) {
            throw new NoContentException("id: " +id);
        }
    }
}
