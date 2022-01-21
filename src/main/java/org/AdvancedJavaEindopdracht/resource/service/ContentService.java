package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.resource.model.event.content.ContentDto;
import org.AdvancedJavaEindopdracht.resource.model.event.content.ContentMapper;
import org.AdvancedJavaEindopdracht.resource.repository.ContentRespository;
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
     * @return      response entity with list of all content
     */
    public List<ContentDto> get() {
        return contentMapper.mapFromEntityList(contentRespository.get());
    }

    /**
     * Maps Entity to DTO and returns a single content.
     *
     * @param id    id of the content to find
     * @return      response entity with single content
     */
    public ContentDto getById(long id) {
        return contentMapper.mapFromEntity(contentRespository.getById(id));
    }

    /**
     * Maps Entity to DTO and posts a single content.
     *
     * @param contentDto    content to post
     * @return              response entity with posted content
     */
    public ContentDto persist(ContentDto contentDto) {
        return contentMapper.mapFromEntity(
                contentRespository.persist(contentMapper.mapToEntity(contentDto))
        );
    }

    /**
     * Maps Entity to DTO and puts a single content.
     *
     * @param id            id of the content to put
     * @param contentDto    content to put
     * @return              response entity with put content
     */
    public ContentDto put(long id, ContentDto contentDto) {
        return contentMapper.mapFromEntity(contentRespository.put(id, contentMapper.mapToEntity(contentDto)));
    }

}
