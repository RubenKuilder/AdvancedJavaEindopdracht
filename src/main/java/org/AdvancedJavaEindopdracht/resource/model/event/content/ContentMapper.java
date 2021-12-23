package org.AdvancedJavaEindopdracht.resource.model.event.content;

import org.AdvancedJavaEindopdracht.resource.model.event.Event;
import org.AdvancedJavaEindopdracht.resource.model.event.EventDto;
import org.AdvancedJavaEindopdracht.util.EntityMapper;
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
