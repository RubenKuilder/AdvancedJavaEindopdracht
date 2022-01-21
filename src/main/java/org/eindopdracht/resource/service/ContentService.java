package org.eindopdracht.resource.service;

import org.eindopdracht.resource.exception.general.BadRequestException;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.model.event.content.ContentDto;
import org.eindopdracht.resource.model.event.content.ContentMapper;
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
    public List<ContentDto> get() {
        return contentMapper.mapFromEntityList(contentRespository.get());
    }

    /**
     * Maps Entity to DTO and returns a single content.
     *
     * @param id id of the content to find
     * @return response entity with single content
     */
    public ContentDto getById(long id) {
        try {
            return contentMapper.mapFromEntity(contentRespository.getById(id));
        } catch (Exception ex) {
            throw new DataNotFoundException("id: " +id);
        }
    }

    /**
     * Maps Entity to DTO and posts a single content.
     *
     * @param contentDto content to post
     * @return response entity with posted content
     */
    public ContentDto persist(ContentDto contentDto) {
        try {
            return contentMapper.mapFromEntity(
                    contentRespository.persist(contentMapper.mapToEntity(contentDto))
            );
        } catch (Exception ex) {
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
    public ContentDto put(long id, ContentDto contentDto) {
        try {
            return contentMapper.mapFromEntity(contentRespository.put(id, contentMapper.mapToEntity(contentDto)));
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and patches a single content.
     *
     * @param id         id of the content to patch
     * @param contentDto content to patch
     * @return response entity with patched content
     */
    public ContentDto patch(long id, ContentDto contentDto) {
        return contentMapper.mapFromEntity(contentRespository.patch(id, contentMapper.mapToEntity(contentDto)));
    }
}
