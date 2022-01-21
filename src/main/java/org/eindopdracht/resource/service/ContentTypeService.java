package org.eindopdracht.resource.service;

import org.eindopdracht.ConvertToDTO;
import org.eindopdracht.resource.exception.general.BadRequestException;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.model.event.content.contentType.ContentTypeDto;
import org.eindopdracht.resource.model.event.content.contentType.ContentTypeMapper;
import org.eindopdracht.resource.repository.ContentTypeRepository;
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
        try {
            return contentTypeMapper.mapFromEntity(contentTypeRepository.getById(id));
        }
        catch (Exception ex) {
            throw new DataNotFoundException();
        }
    }

    /**
     * Maps Entity to DTO and posts a single content type.
     *
     * @param contentTypeDto    content type to post
     * @return                  response entity with posted content type
     */
    public ContentTypeDto persist(ContentTypeDto contentTypeDto) {
        try{
        return contentTypeMapper.mapFromEntity(
                contentTypeRepository.persist(contentTypeMapper.mapToEntity(contentTypeDto))
        );
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and puts a single content type.
     *
     * @param id                id of the content type to put
     * @param contentTypeDto    content type to put
     * @return                  response entity with put content type
     */
    public ContentTypeDto put(long id, ContentTypeDto contentTypeDto) {
        try{
        return contentTypeMapper.mapFromEntity(contentTypeRepository.put(id, contentTypeMapper.mapToEntity(contentTypeDto)));
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and patches a single content type.
     *
     * @param id                id of the content type to patch
     * @param contentTypeDto    content type to patch
     * @return                  response entity with patched content type
     */
    public ContentTypeDto patch(long id, ContentTypeDto contentTypeDto) {
        return contentTypeMapper.mapFromEntity(contentTypeRepository.patch(id, contentTypeMapper.mapToEntity(contentTypeDto)));
    }

    /**
     * Maps Entity to DTO and deletes a single content type.
     *
     * @param id    id of the content type to delete
     * @return      response entity with deleted content type
     */
    public ContentTypeDto delete(long id) {
        try{
        return convertToDto.toContentTypeDTO(contentTypeRepository.delete(id));
        }
        catch(Exception ex)
        {
            throw new NoContentException();
        }
    }
}
