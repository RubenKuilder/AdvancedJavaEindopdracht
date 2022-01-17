package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.resource.dto.GlobalSettingsDto;
import org.AdvancedJavaEindopdracht.resource.dto.RoleDTO;
import org.AdvancedJavaEindopdracht.resource.model.GlobalSettings;
import org.AdvancedJavaEindopdracht.resource.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Time;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@ContextConfiguration(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
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
        Role role = new Role();
        role.setRole("test");
        RoleDTO dto = service.create(role);

        assertEquals("test", dto.getRole());
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
        Role role = new Role();
        role.setRole("test");
        RoleDTO dto = service.update(role, 1);

        assertEquals("test", dto.getRole());
    }
}