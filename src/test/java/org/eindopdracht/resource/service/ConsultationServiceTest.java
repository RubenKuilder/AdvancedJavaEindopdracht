package org.eindopdracht.resource.service;

import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.dto.ConsultationDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
// Waarom doen jullie een integratietest voor de service? Mock die repositories gewoon!
@Transactional
public class ConsultationServiceTest {
    @Autowired
    private ConsultationService consultationService;

    @Test
    void getAllConsultation()
    {
        List<ConsultationDTO> consultationDTOList = consultationService.get();

        assertEquals(2, consultationDTOList.size());
        assertEquals("2021-11-08 00:00:00.0", consultationDTOList.get(0).getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", consultationDTOList.get(0).getEndDateTime().toString());
        assertEquals("2021-01-01 00:00:00.0", consultationDTOList.get(1).getStartDateTime().toString());
        assertEquals("2022-01-01 00:00:00.0", consultationDTOList.get(1).getEndDateTime().toString());
    }

    @Test
    void getById()
    {
        ConsultationDTO consultationDto = consultationService.getById(1);
        assertEquals("2021-11-08 00:00:00.0", consultationDto.getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", consultationDto.getEndDateTime().toString());

        assertThrows(DataNotFoundException.class, () -> {
            consultationService.getById(5135);
        });
    }

    @Test
    void postConsultation() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("12-12-2021 00:00:00");
        Date endDateTime = sdf.parse("01-01-2022 00:00:00");

        ConsultationDTO consultationDto = new ConsultationDTO();
        consultationDto.setStartDateTime(startDateTime);
        consultationDto.setEndDateTime(endDateTime);
        ConsultationDTO persistedConsultation = consultationService.persist(consultationDto);

        assertEquals(startDateTime, persistedConsultation.getStartDateTime());
        assertEquals(endDateTime, persistedConsultation.getEndDateTime());
    }

    @Test
    void putConsultation() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("12-12-2021 00:00:00");

        ConsultationDTO consultationDto = new ConsultationDTO();
        consultationDto.setStartDateTime(startDateTime);
        ConsultationDTO putConsultation = consultationService.put(1, consultationDto);

        assertEquals(startDateTime, putConsultation.getStartDateTime());
        assertNull(putConsultation.getEndDateTime());
    }

    @Test
    void deleteConsultation() throws Exception
    {
        ConsultationDTO consultationDto = consultationService.delete(1);
        assertEquals("2021-11-08 00:00:00.0", consultationDto.getStartDateTime().toString());
        assertEquals("2022-12-08 00:00:00.0", consultationDto.getEndDateTime().toString());

        assertThrows(NoContentException.class, () -> {
            consultationService.delete(3151);
        });
    }
}
