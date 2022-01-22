package org.eindopdracht.resource.mapper;

import org.eindopdracht.resource.dto.ConsultationDTO;
import org.eindopdracht.resource.model.Consultation;
import org.eindopdracht.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsultationMapper implements EntityMapper<Consultation, ConsultationDTO> {
    @Override
    public ConsultationDTO mapFromEntity(Consultation consultation) {
        return new ConsultationDTO(
                consultation.getId(),
                consultation.getUsers(),
                consultation.getStartDateTime(),
                consultation.getEndDateTime()
        );
    }

    @Override
    public Consultation mapToEntity(ConsultationDTO consultationDto) {
        return new Consultation(
                consultationDto.getId(),
                consultationDto.getUsers(),
                consultationDto.getStartDateTime(),
                consultationDto.getEndDateTime()
        );
    }

    public List<ConsultationDTO> mapFromEntityList(List<Consultation> entities) {
        List<ConsultationDTO> consultationDTOList = new ArrayList<>();
        for (Consultation entity : entities) {
            consultationDTOList.add(mapFromEntity(entity));
        }

        return consultationDTOList;
    }
}
