package org.AdvancedJavaEindopdracht.repository;

import org.AdvancedJavaEindopdracht.config.TestApplicationConfig;
import org.AdvancedJavaEindopdracht.resource.model.Powerpoint;
import org.AdvancedJavaEindopdracht.resource.model.Role;
import org.AdvancedJavaEindopdracht.resource.model.User;
import org.AdvancedJavaEindopdracht.resource.repository.PowerpointRepository;
import org.AdvancedJavaEindopdracht.resource.repository.RoleRepository;
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
public class PowerpointRepositoryTest {
    @Autowired
    private PowerpointRepository repository;

    private Powerpoint powerpoint;
    private User user;

    @BeforeEach
    void setUp() {
        this.powerpoint = new Powerpoint();
        this.user = new User();
        this.user.setName("test");
        this.user.setPassword("test");
        this.user.setApproved(true);
        this.user.setProfileImagePath("test");
        this.user.setEmail("test");

        powerpoint.setPath("test");
        powerpoint.setUser(this.user);
    }

    @Test
    @DisplayName("Testing if getPowerpoints() returns a list of users")
    public void testMethodGetPowerpoints() {
        List<Powerpoint> pps = repository.getPowerpoints();
        assertNotNull(pps);
    }

    @Test
    @DisplayName("Testing if getPowerpoint retrieves the right user")
    public void testMethodGetPowerpoint() {
        Powerpoint pp = repository.getPowerpoint(1);
        assertEquals("test", pp.getPath());
    }

    @Test
    @DisplayName("Testing if postPowerpoint has posted the right user")
    public void testMethodPostPowerpoint() {
        Powerpoint pp = repository.postPowerpoint(this.powerpoint);
        assertEquals("test", pp.getPath());
    }

    @Test
    @DisplayName("Testing if putPowerpoint changed the entry")
    public void testMethodPutPowerpoint() {
        repository.putPowerpoint(this.powerpoint, 1);
        Powerpoint pp = repository.getPowerpoint(1);
        assertEquals("test", pp.getPath());
    }

    @Test
    @DisplayName("Testing if deletePowerpoint works")
    public void testMethodDeletePowerpoint() {
        repository.deletePowerpoint(1);
    }
}
