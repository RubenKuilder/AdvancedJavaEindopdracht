package org.eindopdracht.resource.mapper;

import org.eindopdracht.resource.dto.ContentTypeDTO;
import org.eindopdracht.resource.model.ContentType;
import org.eindopdracht.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContentTypeMapper implements EntityMapper<ContentType, ContentTypeDTO> {
    @Override
    public ContentTypeDTO mapFromEntity(ContentType contentType) {
        return new ContentTypeDTO(
                contentType.getId(),
                contentType.getName()
        );
    }

    @Override
    public ContentType mapToEntity(ContentTypeDTO contentTypeDto) {
        return new ContentType(
                contentTypeDto.getId(),
                contentTypeDto.getName()
        );
    }

    public List<ContentTypeDTO> mapFromEntityList(List<ContentType> entities) {
        List<ContentTypeDTO> contentTypeDTOList = new ArrayList<>();
        for (ContentType entity : entities) {
            contentTypeDTOList.add(mapFromEntity(entity));
        }

        return contentTypeDTOList;
    }
}
