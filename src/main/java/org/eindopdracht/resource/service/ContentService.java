package org.eindopdracht.resource.service;

import org.eindopdracht.resource.exception.general.BadRequestException;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.dto.ContentDTO;
import org.eindopdracht.resource.mapper.ContentMapper;
import org.eindopdracht.resource.repository.ContentRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {
    private final ContentRespository contentRespository;
    private final ContentMapper contentMapper;

    public ContentService(ContentRespository contentRespository, ContentMapper contentMapper) {
        this.contentRespository = contentRespository;
        this.contentMapper = contentMapper;
    }

    /**
     * Maps Entity to DTO and returns a list of all content.
     *
     * @return response entity with list of all content
     */
    public List<ContentDTO> get() {
        return contentMapper.mapFromEntityList(contentRespository.get());
    }

    /**
     * Maps Entity to DTO and returns a single content.
     *
     * @param id id of the content to find
     * @return response entity with single content
     */
    public ContentDTO getById(long id) {
        try
        {
            return contentMapper.mapFromEntity(contentRespository.getById(id));
        } catch (Exception ex) {
            throw new DataNotFoundException("id: " + id);
        }
    }

    /**
     * Maps Entity to DTO and posts a single content.
     *
     * @param contentDto content to post
     * @return response entity with posted content
     */
    public ContentDTO persist(ContentDTO contentDto) {
        try{
        return contentMapper.mapFromEntity(
                contentRespository.persist(contentMapper.mapToEntity(contentDto))
        );
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and puts a single content.
     *
     * @param id         id of the content to put
     * @param contentDto content to put
     * @return response entity with put content
     */
    public ContentDTO put(long id, ContentDTO contentDto) {
        try{
            return contentMapper.mapFromEntity(contentRespository.put(id, contentMapper.mapToEntity(contentDto)));
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }
}
