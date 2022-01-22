package org.eindopdracht.resource.mapper;

import org.eindopdracht.resource.dto.PowerpointDTO;
import org.eindopdracht.resource.dto.RoleDTO;
import org.eindopdracht.resource.model.Powerpoint;
import org.eindopdracht.resource.model.Role;
import org.eindopdracht.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleMapper implements EntityMapper<Role, RoleDTO> {
    @Override
    public RoleDTO mapFromEntity(Role role) {
        return new RoleDTO(
                role.getId(),
                role.getRole()
        );
    }

    @Override
    public Role mapToEntity(RoleDTO roleDTO) {
        return new Role(
                roleDTO.getId(),
                roleDTO.getRole()
        );
    }

    public List<RoleDTO> mapFromEntityList(List<Role> entities) {
        List<RoleDTO> roleDTOList = new ArrayList<>();
        for (Role entity : entities) {
            roleDTOList.add(mapFromEntity(entity));
        }

        return roleDTOList;
    }
}
