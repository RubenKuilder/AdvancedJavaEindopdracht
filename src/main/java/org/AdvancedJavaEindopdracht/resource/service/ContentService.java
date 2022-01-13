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

    public List<ContentDto> get() {
        return contentMapper.mapFromEntityList(contentRespository.get());
    }

    public ContentDto getById(long id) {
        return contentMapper.mapFromEntity(contentRespository.getById(id));
    }

    public ContentDto persist(ContentDto contentDto) {
        return contentMapper.mapFromEntity(
                contentRespository.persist(contentMapper.mapToEntity(contentDto))
        );
    }

    public ContentDto put(long id, ContentDto contentDto) {
        return contentMapper.mapFromEntity(contentRespository.put(id, contentMapper.mapToEntity(contentDto)));
    }

    public ContentDto patch(long id, ContentDto contentDto) {
        return contentMapper.mapFromEntity(contentRespository.patch(id, contentMapper.mapToEntity(contentDto)));
    }
}
