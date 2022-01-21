package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.ConvertToDTO;
import org.AdvancedJavaEindopdracht.resource.model.event.content.contentType.ContentTypeDto;
import org.AdvancedJavaEindopdracht.resource.model.event.content.contentType.ContentTypeMapper;
import org.AdvancedJavaEindopdracht.resource.repository.ContentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentTypeService {
    private final ContentTypeRepository contentTypeRepository;
    private final ContentTypeMapper contentTypeMapper;
    private final ConvertToDTO convertToDto = new ConvertToDTO();

    public ContentTypeService(ContentTypeRepository contentTypeRepository, ContentTypeMapper contentTypeMapper) {
        this.contentTypeRepository = contentTypeRepository;
        this.contentTypeMapper = contentTypeMapper;
    }

    /**
     * Maps Entity to DTO and returns a list of all content types.
     *
     * @return      response entity with list of all content types
     */
    public List<ContentTypeDto> get() {
        return contentTypeMapper.mapFromEntityList(contentTypeRepository.get());
    }

    /**
     * Maps Entity to DTO and returns a single content type.
     *
     * @param id    id of the content type to find
     * @return      response entity with single content type
     */
    public ContentTypeDto getById(long id) {
        return contentTypeMapper.mapFromEntity(contentTypeRepository.getById(id));
    }

    /**
     * Maps Entity to DTO and posts a single content type.
     *
     * @param contentTypeDto    content type to post
     * @return                  response entity with posted content type
     */
    public ContentTypeDto persist(ContentTypeDto contentTypeDto) {
        return contentTypeMapper.mapFromEntity(
                contentTypeRepository.persist(contentTypeMapper.mapToEntity(contentTypeDto))
        );
    }

    /**
     * Maps Entity to DTO and puts a single content type.
     *
     * @param id                id of the content type to put
     * @param contentTypeDto    content type to put
     * @return                  response entity with put content type
     */
    public ContentTypeDto put(long id, ContentTypeDto contentTypeDto) {
        return contentTypeMapper.mapFromEntity(contentTypeRepository.put(id, contentTypeMapper.mapToEntity(contentTypeDto)));
    }

    /**
     * Maps Entity to DTO and deletes a single content type.
     *
     * @param id    id of the content type to delete
     * @return      response entity with deleted content type
     */
    public ContentTypeDto delete(long id) {
        return convertToDto.toContentTypeDTO(contentTypeRepository.delete(id));
    }
}
