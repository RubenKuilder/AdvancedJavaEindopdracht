package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.resource.model.event.content.contentType.ContentTypeDto;
import org.AdvancedJavaEindopdracht.resource.model.event.content.contentType.ContentTypeMapper;
import org.AdvancedJavaEindopdracht.resource.repository.ContentTypeRepository;
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

    public List<ContentTypeDto> get() {
        return contentTypeMapper.mapFromEntityList(contentTypeRepository.get());
    }

    public ContentTypeDto getById(long id) {
        return contentTypeMapper.mapFromEntity(contentTypeRepository.getById(id));
    }

    public ContentTypeDto persist(ContentTypeDto contentTypeDto) {
        return contentTypeMapper.mapFromEntity(
                contentTypeRepository.persist(contentTypeMapper.mapToEntity(contentTypeDto))
        );
    }

    public ContentTypeDto put(long id, ContentTypeDto contentTypeDto) {
        return contentTypeMapper.mapFromEntity(contentTypeRepository.put(id, contentTypeMapper.mapToEntity(contentTypeDto)));
    }

    public ContentTypeDto patch(long id, ContentTypeDto contentTypeDto) {
        return contentTypeMapper.mapFromEntity(contentTypeRepository.patch(id, contentTypeMapper.mapToEntity(contentTypeDto)));
    }

    public void delete(long id) {
        contentTypeRepository.delete(id);
    }
}
