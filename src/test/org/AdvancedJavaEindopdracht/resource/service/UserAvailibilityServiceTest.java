package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.resource.dto.UserAvailabilityDTO;
import org.AdvancedJavaEindopdracht.resource.model.User;
import org.AdvancedJavaEindopdracht.resource.model.UserAvailability;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@ContextConfiguration(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class UserAvailibilityServiceTest {
    @Autowired
    private UserAvailabilityService service;

    @Test
    @Transactional
    void getUserAvailabilities()
    {
        List<UserAvailabilityDTO> list = service.getUserAvailabilities();

        assertEquals(2, list.size());
    }

    @Test
    @Transactional
    void getUserAvailability()
    {
        UserAvailabilityDTO dto = service.getUserAvailability(1);

        assertEquals("Madlyaza", dto.getUser().getName());
    }

    @Test
    @Transactional
    void createUserAvailability()
    {
        User user = new User();
        user.setName("test22");
        user.setApproved(true);
        user.setEmail("true");
        user.setPassword("true");
        user.setProfileImagePath("true");

        UserAvailability ua = new UserAvailability();
        ua.setUser(user);
        ua.setDate(new Date(2010, 3, 5, 5, 5, 5));

        UserAvailabilityDTO dto = service.create(ua);

        assertEquals("test22", dto.getUser().getName());
    }

    @Test
    @Transactional
    void deleteUserAvailability()
    {
        service.delete(1);
    }

    @Test
    @Transactional
    void updateUserAvailability() {
        User user = new User();
        user.setName("test33");
        user.setApproved(true);
        user.setEmail("true");
        user.setPassword("true");
        user.setProfileImagePath("true");

        UserAvailability ua = new UserAvailability();
        ua.setUser(user);
        ua.setDate(new Date(2010, 3, 5));


        UserAvailabilityDTO dto = service.update(ua, 1);

        assertEquals("test33", dto.getUser().getName());
    }
}
