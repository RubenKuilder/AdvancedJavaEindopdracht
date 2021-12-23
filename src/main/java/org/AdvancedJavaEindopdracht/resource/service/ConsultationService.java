package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.resource.model.consultation.ConsultationDto;
import org.AdvancedJavaEindopdracht.resource.model.consultation.ConsultationMapper;
import org.AdvancedJavaEindopdracht.resource.repository.ConsultationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;

    public ConsultationService(ConsultationRepository consultationRepository, ConsultationMapper consultationMapper) {
        this.consultationRepository = consultationRepository;
        this.consultationMapper = consultationMapper;
    }

    public List<ConsultationDto> get() {
        return contentMapper.mapFromEntityList(contentRespository.get());
    }

    public ConsultationDto getById(long id) {
        return contentMapper.mapFromEntity(contentRespository.getById(id));
    }

    public ConsultationDto persist(ConsultationDto consultationDto) {
        return contentMapper.mapFromEntity(
                contentRespository.persist(contentMapper.mapToEntity(consultationDto))
        );
    }
}
