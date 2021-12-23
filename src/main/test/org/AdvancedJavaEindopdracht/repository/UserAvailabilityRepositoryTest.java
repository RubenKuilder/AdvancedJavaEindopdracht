package org.AdvancedJavaEindopdracht.repository;

import org.AdvancedJavaEindopdracht.config.TestApplicationConfig;
import org.AdvancedJavaEindopdracht.resource.model.Role;
import org.AdvancedJavaEindopdracht.resource.model.User;
import org.AdvancedJavaEindopdracht.resource.model.UserAvailability;
import org.AdvancedJavaEindopdracht.resource.repository.UserAvailabilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestApplicationConfig.class)
@Transactional
public class UserAvailabilityRepositoryTest {

    @Autowired
    private UserAvailabilityRepository userAvailabilityRepository;

    private User user;
    private UserAvailability ua;

    @BeforeEach
    void setUp() {
        this.user = new User();
        user.setName("test");
        user.setApproved(true);
        user.setEmail("true");
        user.setPassword("true");
        user.setProfileImagePath("true");

        this.ua = new UserAvailability();
        this.ua.setUser(this.user);
        this.ua.setDate(new Date(2010, 3, 5));
    }

    @Test
    @DisplayName("Testing if getUserAvailabilities() returns a list of users")
    public void testMethodGetUserAvailabilities() {
        List<UserAvailability> uas = userAvailabilityRepository.getUserAvailabilities();
        assertNotNull(uas);
    }

    @Test
    @DisplayName("Testing if getUserAvailability retrieves the right user")
    public void testMethodGetUserAvailability() {
        UserAvailability userAvailability = userAvailabilityRepository.getUserAvailability(1);
        assertEquals("thijs", userAvailability.getUser().getName());
    }

    @Test
    @DisplayName("Testing if postUserAvailability has posted the right user")
    public void testMethodPostUserAvailability() {
        UserAvailability userAvailability = userAvailabilityRepository.postUserAvailability(this.ua);
        assertEquals("test", userAvailability.getUser().getName());
    }

    @Test
    @DisplayName("Testing if putUserAvailability changed the entry")
    public void testMethodPutUserAvailability() {
        this.ua.getUser().setName("test2");
        userAvailabilityRepository.putUserAvailability(this.ua, 1);
        UserAvailability userAvailability = userAvailabilityRepository.getUserAvailability(1);
        assertEquals("test2", userAvailability.getUser().getName());
    }

    @Test
    @DisplayName("Testing if deleteUserAvailability works")
    public void testMethodDeleteUserAvailability() {
        userAvailabilityRepository.deleteUserAvailability(1);
    }
}