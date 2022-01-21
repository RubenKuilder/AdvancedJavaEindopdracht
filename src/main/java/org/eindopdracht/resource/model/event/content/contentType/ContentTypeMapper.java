package org.eindopdracht.resource.model.event.content.contentType;

import org.eindopdracht.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContentTypeMapper implements EntityMapper<ContentType, ContentTypeDto> {
    @Override
    public ContentTypeDto mapFromEntity(ContentType contentType) {
        return new ContentTypeDto(
                contentType.getId(),
                contentType.getName()
        );
    }

    @Override
    public ContentType mapToEntity(ContentTypeDto contentTypeDto) {
        return new ContentType(
                contentTypeDto.getId(),
                contentTypeDto.getName()
        );
    }

    public List<ContentTypeDto> mapFromEntityList(List<ContentType> entities) {
        List<ContentTypeDto> ContentTypeDtoList = new ArrayList<>();
        for (ContentType entity : entities) {
            ContentTypeDtoList.add(mapFromEntity(entity));
        }

        return ContentTypeDtoList;
    }
}
