package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.ContentTypeDTO;
import org.eindopdracht.resource.exception.general.BadRequestException;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.mapper.ContentTypeMapper;
import org.eindopdracht.resource.repository.ContentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentTypeService {
    private final ContentTypeRepository contentTypeRepository;
    private final ContentTypeMapper contentTypeMapper;

    public ContentTypeService(ContentTypeRepository contentTypeRepository, ContentTypeMapper contentTypeMapper) {
        this.contentTypeRepository = contentTypeRepository;
        this.contentTypeMapper = contentTypeMapper;
    }

    /**
     * Maps Entity to DTO and returns a list of all content types.
     *
     * @return response entity with list of all content types
     */
    public List<ContentTypeDTO> get() {
        return contentTypeMapper.mapFromEntityList(contentTypeRepository.get());
    }

    /**
     * Maps Entity to DTO and returns a single content type.
     *
     * @param id id of the content type to find
     * @return response entity with single content type
     */
    public ContentTypeDTO getById(int id) {
        try {
            return contentTypeMapper.mapFromEntity(contentTypeRepository.getById(id));
        } catch (Exception ex) {
            throw new DataNotFoundException("id: " + id);
        }
    }

    /**
     * Maps Entity to DTO and posts a single content type.
     *
     * @param contentTypeDto content type to post
     * @return response entity with posted content type
     */
    public ContentTypeDTO persist(ContentTypeDTO contentTypeDto) {
        try {
            return contentTypeMapper.mapFromEntity(
                    contentTypeRepository.persist(contentTypeMapper.mapToEntity(contentTypeDto))
            );
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and puts a single content type.
     *
     * @param id             id of the content type to put
     * @param contentTypeDto content type to put
     * @return response entity with put content type
     */
    public ContentTypeDTO put(int id, ContentTypeDTO contentTypeDto) {
        try {
            return contentTypeMapper.mapFromEntity(contentTypeRepository.put(id, contentTypeMapper.mapToEntity(contentTypeDto)));
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and deletes a single content type.
     *
     * @param id id of the content type to delete
     * @return response entity with deleted content type
     */
    public ContentTypeDTO delete(int id) {
        try {
            return contentTypeMapper.mapFromEntity(contentTypeRepository.delete(id));
        } catch (Exception ex) {
            throw new NoContentException("id: " + id); // Wat is het verschil met DataNotFoundException?
        }
    }
}
