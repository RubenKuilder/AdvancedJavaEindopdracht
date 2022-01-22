package org.eindopdracht.resource.mapper;

import org.eindopdracht.resource.dto.RssFeedDTO;
import org.eindopdracht.resource.model.RssFeed;
import org.eindopdracht.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RssFeedMapper implements EntityMapper<RssFeed, RssFeedDTO> {
    @Override
    public RssFeedDTO mapFromEntity(RssFeed rssFeed) {
        return new RssFeedDTO(
                rssFeed.getId(),
                rssFeed.getUser(),
                rssFeed.getLink(),
                rssFeed.getStartDateTime(),
                rssFeed.getEndDateTime()
        );
    }

    @Override
    public RssFeed mapToEntity(RssFeedDTO rssFeedDTO) {
        return new RssFeed(
                rssFeedDTO.getId(),
                rssFeedDTO.getUser(),
                rssFeedDTO.getLink(),
                rssFeedDTO.getStartDateTime(),
                rssFeedDTO.getEndDateTime()
        );
    }

    public List<RssFeedDTO> mapFromEntityList(List<RssFeed> entities) {
        List<RssFeedDTO> RssFeedDTOList = new ArrayList<>();
        for (RssFeed entity : entities) {
            RssFeedDTOList.add(mapFromEntity(entity));
        }

        return RssFeedDTOList;
    }
}
