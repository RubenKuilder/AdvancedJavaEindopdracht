package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.RoleDTO;
import org.eindopdracht.resource.exception.general.BadRequestException;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.mapper.RoleMapper;
import org.eindopdracht.resource.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    /**
     * Maps Entity to DTO and returns a list of all roles.
     *
     * @return response entity with list of all roles
     */
    public List<RoleDTO> getRoles() {
        return roleMapper.mapFromEntityList(roleRepository.getRoles());
    }

    /**
     * Maps Entity to DTO and returns a single role.
     *
     * @param id id of the role to find
     * @return response entity with single role
     */
    public RoleDTO getRole(Integer id) {
        try {
            return roleMapper.mapFromEntity(roleRepository.getRole(id));
        } catch (Exception ex) {
            throw new DataNotFoundException("id: " + id);
        }
    }

    /**
     * Maps Entity to DTO and posts a single role.
     *
     * @param roleDTO role to post
     * @return response entity with posted role
     */
    public RoleDTO create(RoleDTO roleDTO) {
        try {
            return roleMapper.mapFromEntity(
                    roleRepository.postRole(roleMapper.mapToEntity(roleDTO))
            );
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and puts a single role.
     *
     * @param id      id of the role to put
     * @param roleDTO role to put
     * @return response entity with put role
     */
    public RoleDTO update(RoleDTO roleDTO, Integer id) {
        try {
            return roleMapper.mapFromEntity(roleRepository.putRole(roleMapper.mapToEntity(roleDTO), id));
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
            return roleMapper.mapFromEntity(roleRepository.deleteRole(id));
        } catch (Exception ex) {
            throw new NoContentException("id: " + id);
        }
    }
}
