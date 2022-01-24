package org.eindopdracht.resource.mapper;

import org.eindopdracht.resource.dto.ContentDTO;
import org.eindopdracht.resource.model.Content;
import org.eindopdracht.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("contentMapperComponent")
public class ContentMapper implements EntityMapper<Content, ContentDTO> {
    @Override
    public ContentDTO mapFromEntity(Content content) {
        return new ContentDTO(
                content.getId(),
                content.getContentType(),
                content.getPath()
        );
    }

    @Override
    public Content mapToEntity(ContentDTO contentDto) {
        return new Content(
                contentDto.getId(),
                contentDto.getContentType(),
                contentDto.getPath()
        );
    }

    public List<ContentDTO> mapFromEntityList(List<Content> entities) {
        List<ContentDTO> contentDTOList = new ArrayList<>();
        for (Content entity : entities) {
            contentDTOList.add(mapFromEntity(entity));
        }
        // Dit kan ook zo!
//        return entities.stream()
//                .map(this::mapFromEntity)
//                .collect(Collectors.toList());

        return contentDTOList;
    }
}
