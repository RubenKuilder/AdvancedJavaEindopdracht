package org.AdvancedJavaEindopdracht.resource.repository;

import org.AdvancedJavaEindopdracht.resource.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@SpringJUnitWebConfig(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;


    @BeforeEach
    void setUp() {
        this.user = new User();
        user.setName("TobiasVanArkelen");
        user.setPassword("password]");
        user.setEmail("email");
        user.setProfileImagePath("link");
        user.setApproved(false);
    }

    @Test
    @DisplayName("Testing if getUsers() returns a list of users")
    public void testMethodGetUsers() {
        List<User> users = userRepository.getUsers();
        assertNotNull(users);
    }

    @Test
    @DisplayName("Testing if getUser retrieves the right user")
    public void testMethodGetUser() {
        User test = userRepository.getUser(1);
        assertEquals("thijs", test.getName());
    }

    @Test
    @DisplayName("Testing if postUser has posted the right user")
    public void testMethodPostUser() {
        User user = userRepository.postUser(this.user);
        assertEquals("TobiasVanArkelen", user.getName());
    }

    @Test
    @DisplayName("Testing if putUser changed the entry")
    public void testMethodPutUser() {
        userRepository.putUser(this.user, 1);
        User usertest = userRepository.getUser(1);
        assertEquals("TobiasVanArkelen", usertest.getName());
    }

    @Test
    @DisplayName("Testing if deleteUser works")
    public void testMethodDeleteAnimal() {
        userRepository.deleteUser(1);
    }

}
