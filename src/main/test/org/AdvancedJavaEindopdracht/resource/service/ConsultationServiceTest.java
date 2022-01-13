package org.AdvancedJavaEindopdracht.resource.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.AdvancedJavaEindopdracht.resource.dto.GlobalSettingsDto;
import org.AdvancedJavaEindopdracht.resource.model.GlobalSettings;
import org.AdvancedJavaEindopdracht.resource.model.User;
import org.AdvancedJavaEindopdracht.resource.model.consultation.Consultation;
import org.AdvancedJavaEindopdracht.resource.model.consultation.ConsultationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@ContextConfiguration(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class ConsultationServiceTest {
    @Autowired
    private ConsultationService consultationService;

    @Test
    void getAllConsultation() throws Exception
    {
        List<ConsultationDto> consultationDtoList = consultationService.get();

        assertEquals(2, consultationDtoList.size());
        assertEquals("2021-11-08 00:00:00.0", consultationDtoList.get(0).getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", consultationDtoList.get(0).getEndDateTime().toString());
        assertEquals("2021-01-01 00:00:00.0", consultationDtoList.get(1).getStartDateTime().toString());
        assertEquals("2022-01-01 00:00:00.0", consultationDtoList.get(1).getEndDateTime().toString());
    }

    @Test
    void getById() throws Exception
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
        ConsultationDto persistedConsultation = consultationService.put(1, consultationDto);

        assertEquals(startDateTime, persistedConsultation.getStartDateTime());
        assertNull(persistedConsultation.getEndDateTime());
    }

    @Test
    void patchConsultation() throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("12-12-2021 00:00:00");

        ConsultationDto consultationDto = new ConsultationDto();
        consultationDto.setStartDateTime(startDateTime);
        ConsultationDto persistedConsultation = consultationService.patch(1, consultationDto);

        assertEquals(startDateTime, persistedConsultation.getStartDateTime());
        assertEquals("2022-12-08 00:00:00.0", persistedConsultation.getEndDateTime().toString());
    }

    @Test
    void deleteConsultation() throws Exception
    {
        ConsultationDto consultationDto = consultationService.delete(1);
        assertEquals("2021-11-08 00:00:00.0", consultationDto.getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", consultationDto.getEndDateTime().toString());
    }
}
