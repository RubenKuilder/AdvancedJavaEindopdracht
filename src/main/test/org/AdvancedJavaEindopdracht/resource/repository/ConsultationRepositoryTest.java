package org.AdvancedJavaEindopdracht.resource.repository;

import org.AdvancedJavaEindopdracht.resource.model.User;
import org.AdvancedJavaEindopdracht.resource.model.UserAvailability;
import org.AdvancedJavaEindopdracht.resource.model.consultation.Consultation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;


import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@SpringJUnitWebConfig(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class ConsultationRepositoryTest {

    @Autowired
    private ConsultationRepository consultationRepository;

    private User user;
    private Consultation consultation;

    @BeforeEach
    void setUp() {
        this.user = new User();
        user.setName("test");
        user.setApproved(true);
        user.setEmail("true");
        user.setPassword("true");
        user.setProfileImagePath("true");

        this.consultation= new Consultation();
        consultation.setStartDateTime(new  Date(01, 01, 2022, 00, 00, 00));
        consultation.setEndDateTime(new  Date(01, 01, 2022, 00, 00, 00));
        List<User> usersList = Arrays.asList(user);
        consultation.setUsers(usersList);
    }

    @Test
    public void testMethodGetUserAvailabilities() {
        List<Consultation> uas = consultationRepository.get();
        assertNotNull(uas);
    }

    @Test
    public void testMethodGetUserAvailability() {
        Consultation consultation = consultationRepository.getById(1);
        assertEquals("Madlyaza", consultation.getUsers().get(0).getName());
    }

    @Test
    public void testMethodPostUserAvailability() {
        Consultation con = consultationRepository.persist(this.consultation);
        assertEquals("test", con.getUsers().get(0).getName());
    }

    @Test
    public void testMethodPutUserAvailability() {
        this.consultation.getUsers().get(0).setName("test2");
        consultationRepository.put(1, this.consultation);
        Consultation con = consultationRepository.getById(1);
        assertEquals("test2", con.getUsers().get(0).getName());
    }

    @Test
    public void testMethodDeleteUserAvailability() {
        consultationRepository.delete(1);
    }
}
