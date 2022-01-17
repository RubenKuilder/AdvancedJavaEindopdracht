package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.resource.model.consultation.ConsultationDto;
import org.AdvancedJavaEindopdracht.resource.model.consultation.ConsultationMapper;
import org.AdvancedJavaEindopdracht.resource.model.event.content.ContentDto;
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
        return consultationMapper.mapFromEntityList(consultationRepository.get());
    }

    public ConsultationDto getById(long id) {
        return consultationMapper.mapFromEntity(consultationRepository.getById(id));
    }

    public ConsultationDto persist(ConsultationDto consultationDto) {
        return consultationMapper.mapFromEntity(
                consultationRepository.persist(consultationMapper.mapToEntity(consultationDto))
        );
    }

    public ConsultationDto put(long id, ConsultationDto consultationDto) {
        return consultationMapper.mapFromEntity(consultationRepository.put(id, consultationMapper.mapToEntity(consultationDto)));
    }

    public ConsultationDto patch(long id, ConsultationDto consultationDto) {
        return consultationMapper.mapFromEntity(consultationRepository.patch(id, consultationMapper.mapToEntity(consultationDto)));
    }

    public ConsultationDto delete(long id) throws Exception {
        return consultationMapper.mapFromEntity(consultationRepository.delete(id));
    }
}
