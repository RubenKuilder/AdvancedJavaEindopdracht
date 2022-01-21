package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.PowerpointDTO;
import org.eindopdracht.resource.model.Powerpoint;
import org.eindopdracht.resource.model.User;
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
@ContextConfiguration(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
class PowerpointServiceTest
{
    @Autowired
    private PowerpointService service;

    @Test
    @Transactional
    void getPowerpoints()
    {
        List<PowerpointDTO>  list = service.getPowerpoints();

        assertEquals(1, list.size());
    }

    @Test
    @Transactional
    void getPowerpoint()
    {
        PowerpointDTO dto = service.getPowerpoint(1);

        assertEquals("Madlyaza", dto.getUser().getName());
    }

    @Test
    @Transactional
    void createRole()
    {
        Powerpoint powerpoint = new Powerpoint();
        User user = new User();
        user.setName("test");
        user.setApproved(true);
        user.setEmail("true");
        user.setPassword("true");
        user.setProfileImagePath("true");

        powerpoint.setPath("test");
        powerpoint.setUser(user);

        PowerpointDTO dto = service.create(powerpoint);

        assertEquals("test", dto.getUser().getName());
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
        Powerpoint powerpoint = new Powerpoint();
        User user = new User();
        user.setName("test22");
        user.setApproved(true);
        user.setEmail("true");
        user.setPassword("true");
        user.setProfileImagePath("true");

        powerpoint.setPath("test");
        powerpoint.setUser(user);

        PowerpointDTO dto = service.update(powerpoint, 1);

        assertEquals("test22", dto.getUser().getName());
    }
}