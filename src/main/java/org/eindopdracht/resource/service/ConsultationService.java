package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.ConsultationDTO;
import org.eindopdracht.resource.exception.general.BadRequestException;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.mapper.ConsultationMapper;
import org.eindopdracht.resource.repository.ConsultationRepository;
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

    /**
     * Maps Entity to DTO and returns a list of all consultations.
     *
     * @return response entity with list of all consultations
     */
    public List<ConsultationDTO> get() {
        return consultationMapper.mapFromEntityList(consultationRepository.get());
    }

    /**
     * Maps Entity to DTO and returns a single consultation.
     *
     * @param id id of the consultation to find
     * @return response entity with single consultation
     */
    public ConsultationDTO getById(long id) {
        try {
            return consultationMapper.mapFromEntity(consultationRepository.getById(id));
        } catch (Exception ex) {
            throw new DataNotFoundException();
        }
    }

    /**
     * Maps Entity to DTO and posts a single consultation.
     *
     * @param consultationDto consultation to post
     * @return response entity with posted consultation
     */
    public ConsultationDTO persist(ConsultationDTO consultationDto) {
        try {
            return consultationMapper.mapFromEntity(
                    consultationRepository.persist(consultationMapper.mapToEntity(consultationDto))
            );
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and puts a single consultation.
     *
     * @param id              id of the consultation to put
     * @param consultationDto consultation to put
     * @return response entity with put consultation
     */
    public ConsultationDTO put(long id, ConsultationDTO consultationDto) {
        try {
            return consultationMapper.mapFromEntity(consultationRepository.put(id, consultationMapper.mapToEntity(consultationDto)));
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and patches a single consultation.
     *
     * @param id              id of the consultation to patch
     * @param consultationDto consultation to patch
     * @return response entity with patched consultation
     */
    public ConsultationDTO patch(long id, ConsultationDTO consultationDto) {
        return consultationMapper.mapFromEntity(consultationRepository.patch(id, consultationMapper.mapToEntity(consultationDto)));
    }

    /**
     * Maps Entity to DTO and deletes a single consultation.
     *
     * @param id id of the consultation to delete
     * @return response entity with deleted consultation
     */
    public ConsultationDTO delete(long id) throws Exception {
        try {
            return consultationMapper.mapFromEntity(consultationRepository.delete(id));
        } catch (Exception ex) {
            throw new NoContentException();
        }
    }
}
