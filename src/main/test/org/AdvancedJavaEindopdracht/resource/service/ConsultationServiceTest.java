package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.resource.model.consultation.ConsultationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@ContextConfiguration(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class ConsultationServiceTest {
    @Autowired
    private ConsultationService consultationService;

    @Test
    void getAllConsultation()
    {
        List<ConsultationDto> consultationDtoList = consultationService.get();

        assertEquals(2, consultationDtoList.size());
        assertEquals("2021-11-08 00:00:00.0", consultationDtoList.get(0).getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", consultationDtoList.get(0).getEndDateTime().toString());
        assertEquals("2021-01-01 00:00:00.0", consultationDtoList.get(1).getStartDateTime().toString());
        assertEquals("2022-01-01 00:00:00.0", consultationDtoList.get(1).getEndDateTime().toString());
    }

    @Test
    void getById()
    {
        ConsultationDto consultationDto = consultationService.getById(1);
        assertEquals("2021-11-08 00:00:00.0", consultationDto.getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", consultationDto.getEndDateTime().toString());
    }

    @Test
    void postConsultation() throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("12-12-2021 00:00:00");
        Date endDateTime = sdf.parse("01-01-2022 00:00:00");

        ConsultationDto consultationDto = new ConsultationDto();
        consultationDto.setStartDateTime(startDateTime);
        consultationDto.setEndDateTime(endDateTime);
        ConsultationDto persistedConsultation = consultationService.persist(consultationDto);

        assertEquals(startDateTime, persistedConsultation.getStartDateTime());
        assertEquals(endDateTime, persistedConsultation.getEndDateTime());
    }

    @Test
    void putConsultation() throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("12-12-2021 00:00:00");

        ConsultationDto consultationDto = new ConsultationDto();
        consultationDto.setStartDateTime(startDateTime);
        ConsultationDto putConsultation = consultationService.put(1, consultationDto);

        assertEquals(startDateTime, putConsultation.getStartDateTime());
        assertNull(putConsultation.getEndDateTime());
    }

    @Test
    void patchConsultation() throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("12-12-2021 00:00:00");

        ConsultationDto consultationDto = new ConsultationDto();
        consultationDto.setStartDateTime(startDateTime);
        ConsultationDto patchedConsultation = consultationService.patch(1, consultationDto);

        assertEquals(startDateTime, patchedConsultation.getStartDateTime());
        assertEquals("2022-12-08 00:00:00.0", patchedConsultation.getEndDateTime().toString());
    }

    @Test
    void deleteConsultation()
    {
        ConsultationDto consultationDto = consultationService.delete(1);
        assertEquals("2021-11-08 00:00:00.0", consultationDto.getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", consultationDto.getEndDateTime().toString());
    }
}
