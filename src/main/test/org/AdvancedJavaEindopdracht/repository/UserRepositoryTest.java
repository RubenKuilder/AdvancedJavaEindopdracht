package org.AdvancedJavaEindopdracht.repository;

import org.AdvancedJavaEindopdracht.config.TestApplicationConfig;
import org.AdvancedJavaEindopdracht.resource.model.User;
import org.AdvancedJavaEindopdracht.resource.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestApplicationConfig.class)
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;


    @BeforeEach
    void setUp() {
        this.user = new User();
        user.setId(69);
        user.setName("TobiasVanArkelen");
        user.setPassword("password]");
        user.setEmail("email");
        user.setAvatarLink("link");
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
        User usertest = userRepository.getUser(1);
        assertEquals("cat", usertest.getName());
    }

    @Test
    @DisplayName("Testing if postUser has posted the right user")
    public void testMethodPostUser() {
        User user = userRepository.postUser(this.user);
        assertEquals("TobiasVanArkelen", user.getName());
    }

    @Test
    @DisplayName("Testing if putUser changed the entry")
    public void testMethodPutAnimal() {
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
