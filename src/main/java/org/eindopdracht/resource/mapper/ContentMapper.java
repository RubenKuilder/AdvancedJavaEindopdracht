package org.eindopdracht.resource.mapper;

import org.eindopdracht.resource.model.event.content.Content;
import org.eindopdracht.resource.model.event.content.ContentDto;
import org.eindopdracht.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContentMapper implements EntityMapper<Content, ContentDto> {
    @Override
    public ContentDto mapFromEntity(Content content) {
        return new ContentDto(
                content.getId(),
                content.getContentType(),
                content.getPath()
        );
    }

    @Override
    public Content mapToEntity(ContentDto contentDto) {
        return new Content(
                contentDto.getId(),
                contentDto.getContentType(),
                contentDto.getPath()
        );
    }

    public List<ContentDto> mapFromEntityList(List<Content> entities) {
        List<ContentDto> ContentDtoList = new ArrayList<>();
        for (Content entity : entities) {
            ContentDtoList.add(mapFromEntity(entity));
        }

        return ContentDtoList;
    }
}
