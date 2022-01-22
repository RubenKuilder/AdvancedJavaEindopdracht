package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.UserAvailabilityDTO;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.model.User;
import org.eindopdracht.resource.model.UserAvailability;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class UserAvailibilityServiceTest {
    @Autowired
    private UserAvailabilityService service;

    @Test
    @Transactional
    void getUserAvailabilities() {
        List<UserAvailabilityDTO> list = service.getUserAvailabilities();

        assertEquals(2, list.size());
    }

    @Test
    @Transactional
    void getUserAvailability() {
        UserAvailabilityDTO dto = service.getUserAvailability(1);

        assertEquals("Madlyaza", dto.getUser().getName());

        assertThrows(DataNotFoundException.class, () -> {
            service.getUserAvailability(234234);
        });
    }

    @Test
    @Transactional
    void createUserAvailability() {
        User user = new User();
        user.setName("test22");
        user.setApproved(true);
        user.setEmail("true");
        user.setPassword("true");
        user.setProfileImagePath("true");

        UserAvailabilityDTO userAvailabilityDTO = new UserAvailabilityDTO();
        userAvailabilityDTO.setUser(user);
        userAvailabilityDTO.setDate(new Date(2010, 3, 5, 5, 5, 5));

        UserAvailabilityDTO persisteduserAvailabilityDTO = service.create(userAvailabilityDTO);

        assertEquals("test22", persisteduserAvailabilityDTO.getUser().getName());
    }

    @Test
    @Transactional
    void deleteUserAvailability() {
        service.delete(1);

        assertThrows(NoContentException.class, () -> {
            service.delete(234);
        });
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

        UserAvailabilityDTO userAvailabilityDTO = new UserAvailabilityDTO();
        userAvailabilityDTO.setUser(user);
        userAvailabilityDTO.setDate(new Date(2010, 3, 5));


        UserAvailabilityDTO persisteduserAvailabilityDTO = service.update(userAvailabilityDTO, 1);

        assertEquals("test33", persisteduserAvailabilityDTO.getUser().getName());
    }
}
