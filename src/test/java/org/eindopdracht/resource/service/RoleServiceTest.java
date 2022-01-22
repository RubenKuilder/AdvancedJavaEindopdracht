package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.RoleDTO;
import org.eindopdracht.resource.model.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
class RoleServiceTest
{
    @Autowired
    private RoleService service;

    @Test
    @Transactional
    void getRoles()
    {
        List<RoleDTO>  list = service.getRoles();

        assertEquals(2, list.size());
    }

    @Test
    @Transactional
    void getRole()
    {
        RoleDTO role = service.getRole(1);

        assertEquals("user", role.getRole());
    }

    @Test
    @Transactional
    void createRole()
    {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRole("test");
        RoleDTO persistedRoleDTO = service.create(roleDTO);

        assertEquals("test", persistedRoleDTO.getRole());
    }

    @Test
    @Transactional
    void deleteRole()
    {
        service.delete(1);
    }

    @Test
    @Transactional
    void updateRole()
    {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRole("test");
        RoleDTO persistedRoleDTO = service.update(roleDTO, 1);

        assertEquals("test", persistedRoleDTO.getRole());
    }
}