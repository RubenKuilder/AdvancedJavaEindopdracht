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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    void setUp() throws ParseException
    {
        this.user = new User();
        user.setName("test");
        user.setApproved(true);
        user.setEmail("true");
        user.setPassword("true");
        user.setProfileImagePath("true");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("01-01-2022 00:00:00");
        Date endDateTime = sdf.parse("01-01-2022 00:00:00");

        this.consultation= new Consultation();
        consultation.setStartDateTime(startDateTime);
        consultation.setEndDateTime(endDateTime);
        List<User> usersList = Arrays.asList(user);
        consultation.setUsers(usersList);
    }

    @Test
    public void getAll() {
        List<Consultation> consultations = consultationRepository.get();
        assertNotNull(consultations);
    }

    @Test
    public void getById() {
        Consultation consultation = consultationRepository.getById(1);
        assertEquals("Madlyaza", consultation.getUsers().get(0).getName());
    }

    @Test
    public void postTest() {
        Consultation con = consultationRepository.persist(this.consultation);
        assertEquals("test", con.getUsers().get(0).getName());
    }

    @Test
    public void putTest() throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("10-01-2022 15:40:10");
        this.consultation.setStartDateTime(startDateTime);
        Consultation con = consultationRepository.put(1,this.consultation);
        assertEquals(startDateTime, con.getStartDateTime());
    }

    @Test
    public void deleteTest()
    {
        Consultation consultation = consultationRepository.delete(1);
        assertEquals("Madlyaza", consultation.getUsers().get(0).getName());
    }
}
