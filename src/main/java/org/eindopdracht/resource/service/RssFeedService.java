package org.eindopdracht.resource.service;

import org.eindopdracht.ConvertToDTO;
import org.eindopdracht.resource.dto.RssFeedDTO;
import org.eindopdracht.resource.exception.general.BadRequestException;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.model.RssFeed;
import org.eindopdracht.resource.repository.RssFeedRepository;
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
        try{
        return convertToDTO.toRssFeedDTO(repository.getRssFeed(id));
    }
        catch (Exception ex)
    {
        throw new DataNotFoundException();
    }
    }

    /**
     * Maps Entity to DTO and posts a single RSS feed.
     *
     * @param rssFeed   RSS feed to post
     * @return          response entity with posted RSS feed
     */
    public RssFeedDTO create(RssFeed rssFeed){
        try{
        return convertToDTO.toRssFeedDTO(repository.postRssFeed(rssFeed));
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and puts a single RSS feed.
     *
     * @param id        id of the RSS feed to put
     * @param rssFeed   RSS feed to put
     * @return          response entity with put RSS feed
     */
    public RssFeedDTO update(RssFeed rssFeed, Integer id){
        try{
        return convertToDTO.toRssFeedDTO(repository.putRssFeed(rssFeed, id));
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and deletes a single RSS feed.
     *
     * @param id    id of the RSS feed to delete
     * @return      response entity with deleted RSS feed
     */
    public RssFeedDTO delete(Integer id){
        try{
        return convertToDTO.toRssFeedDTO(repository.deleteRssFeed(id));
        }
        catch(Exception ex)
        {
            throw new NoContentException();
        }
    }
}
