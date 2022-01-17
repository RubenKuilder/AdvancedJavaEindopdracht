package org.AdvancedJavaEindopdracht.resource.repository;

import org.AdvancedJavaEindopdracht.resource.model.consultation.Consultation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@SpringJUnitWebConfig(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class ConsultationRepositoryTest {
    @Autowired
    private ConsultationRepository consultationRepository;

    @Test
    void getAllConsultation() throws Exception
    {
        List<Consultation> consultationList = consultationRepository.get();

        assertEquals(2, consultationList.size());
        assertEquals("2021-11-08 00:00:00.0", consultationList.get(0).getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", consultationList.get(0).getEndDateTime().toString());
        assertEquals("2021-01-01 00:00:00.0", consultationList.get(1).getStartDateTime().toString());
        assertEquals("2022-01-01 00:00:00.0", consultationList.get(1).getEndDateTime().toString());
    }

    @Test
    void getById() throws Exception
    {
        Consultation consultation = consultationRepository.getById(1);
        assertEquals("2021-11-08 00:00:00.0", consultation.getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", consultation.getEndDateTime().toString());
    }

    @Test
    void postConsultation() throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("12-12-2021 00:00:00");
        Date endDateTime = sdf.parse("01-01-2022 00:00:00");

        Consultation consultation = new Consultation();
        consultation.setStartDateTime(startDateTime);
        consultation.setEndDateTime(endDateTime);
        Consultation persistedConsultation = consultationRepository.persist(consultation);

        assertEquals(startDateTime, persistedConsultation.getStartDateTime());
        assertEquals(endDateTime, persistedConsultation.getEndDateTime());
    }

    @Test
    void putConsultation() throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("12-12-2021 00:00:00");

        Consultation consultation = new Consultation();
        consultation.setStartDateTime(startDateTime);
        Consultation putConsultation = consultationRepository.put(1, consultation);

        assertEquals(startDateTime, putConsultation.getStartDateTime());
        assertNull(putConsultation.getEndDateTime());
    }

    @Test
    void patchConsultation() throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("12-12-2021 00:00:00");

        Consultation consultation = new Consultation();
        consultation.setStartDateTime(startDateTime);
        Consultation patchedConsultation = consultationRepository.patch(1, consultation);

        assertEquals(startDateTime, patchedConsultation.getStartDateTime());
        assertEquals("2022-12-08 00:00:00.0", patchedConsultation.getEndDateTime().toString());
    }

    @Test
    void deleteConsultation() throws Exception
    {
        Consultation consultation = consultationRepository.delete(1);
        assertEquals("2021-11-08 00:00:00.0", consultation.getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", consultation.getEndDateTime().toString());
    }
}
