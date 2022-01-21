package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.RoleDTO;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.model.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@ContextConfiguration(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
class RoleServiceTest {
    @Autowired
    private RoleService service;

    @Test
    @Transactional
    void getRoles() {
        List<RoleDTO> list = service.getRoles();

        assertEquals(2, list.size());
    }

    @Test
    @Transactional
    void getRole() {
        RoleDTO role = service.getRole(1);

        assertEquals("user", role.getRole());
        assertThrows(DataNotFoundException.class, () -> {
            service.getRole(323);
        });
    }

    @Test
    @Transactional
    void createRole() {
        Role role = new Role();
        role.setRole("test");
        RoleDTO dto = service.create(role);

        assertEquals("test", dto.getRole());
    }

    @Test
    @Transactional
    void deleteRole() {
        service.delete(1);
        assertThrows(NoContentException.class, () -> {
            service.delete(324);
        });
    }

    @Test
    @Transactional
    void updateRole() {
        Role role = new Role();
        role.setRole("test");
        RoleDTO dto = service.update(role, 1);

        assertEquals("test", dto.getRole());
    }
}