package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.ConvertToDTO;
import org.AdvancedJavaEindopdracht.resource.dto.RssFeedDTO;
import org.AdvancedJavaEindopdracht.resource.model.RssFeed;
import org.AdvancedJavaEindopdracht.resource.repository.RssFeedRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RssFeedService {
    private final ConvertToDTO convertToDTO = new ConvertToDTO();

    private final RssFeedRepository repository;

    public RssFeedService(RssFeedRepository rssFeedRepository){
        this.repository = rssFeedRepository;
    }

    /**
     * Maps Entity to DTO and returns a list of all RSS feeds.
     *
     * @return      response entity with list of all RSS feeds
     */
    public List<RssFeedDTO> getRssFeeds() { return repository.getRssFeeds().stream().map(convertToDTO::toRssFeedDTO).collect(Collectors.toList());}

    /**
     * Maps Entity to DTO and returns a single RSS feed.
     *
     * @param id    id of the RSS feed to find
     * @return      response entity with single RSS feed
     */
    public RssFeedDTO getRssFeed(Integer id){
        return convertToDTO.toRssFeedDTO(repository.getRssFeed(id));
    }

    /**
     * Maps Entity to DTO and posts a single RSS feed.
     *
     * @param rssFeed   RSS feed to post
     * @return          response entity with posted RSS feed
     */
    public RssFeedDTO create(RssFeed rssFeed){
        return convertToDTO.toRssFeedDTO(repository.postRssFeed(rssFeed));
    }

    /**
     * Maps Entity to DTO and puts a single RSS feed.
     *
     * @param id        id of the RSS feed to put
     * @param rssFeed   RSS feed to put
     * @return          response entity with put RSS feed
     */
    public RssFeedDTO update(RssFeed rssFeed, Integer id){
        return convertToDTO.toRssFeedDTO(repository.putRssFeed(rssFeed, id));
    }

    /**
     * Maps Entity to DTO and deletes a single RSS feed.
     *
     * @param id    id of the RSS feed to delete
     * @return      response entity with deleted RSS feed
     */
    public RssFeedDTO delete(Integer id){
        return convertToDTO.toRssFeedDTO(repository.deleteRssFeed(id));
    }
}
