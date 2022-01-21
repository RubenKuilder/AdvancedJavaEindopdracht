package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.UserDTO;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.model.User;
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
class UserServiceTest {
    @Autowired
    private UserService service;

    @Test
    @Transactional
    void getUsers() {
        List<UserDTO> list = service.getUsers();

        assertEquals(3, list.size());
    }

    @Test
    @Transactional
    void getUser() {
        UserDTO dto = service.getUser(1);

        assertEquals("Madlyaza", dto.getName());

        assertThrows(DataNotFoundException.class, () -> {
            service.getUser(2431);
        });
    }

    @Test
    @Transactional
    void createUser() {
        User user = new User();
        user.setName("test22");
        user.setApproved(true);
        user.setEmail("true");
        user.setPassword("true");
        user.setProfileImagePath("true");


        UserDTO dto = service.create(user);

        assertEquals("test22", dto.getName());
    }

    @Test
    @Transactional
    void deleteUser() {
        assertEquals("Madlyaza", service.delete(1).getName());
        assertThrows(NoContentException.class, () -> {
            service.delete(241);
        });
    }

    @Test
    @Transactional
    void updateUser() {
        User user = new User();
        user.setName("test33");
        user.setApproved(true);
        user.setEmail("true");
        user.setPassword("true");
        user.setProfileImagePath("true");
        UserDTO dto = service.update(user, 1);

        assertEquals("test33", dto.getName());
    }
}