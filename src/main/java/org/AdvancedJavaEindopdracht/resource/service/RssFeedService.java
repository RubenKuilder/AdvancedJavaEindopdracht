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

    public List<RssFeedDTO> getRssFeeds() { return repository.getRssFeeds().stream().map(convertToDTO::toRssFeedDTO).collect(Collectors.toList());}

    public RssFeedDTO getRssFeed(Integer id){
        return convertToDTO.toRssFeedDTO(repository.getRssFeed(id));
    }

    public RssFeedDTO create(RssFeed rssFeed){
        return convertToDTO.toRssFeedDTO(repository.postRssFeed(rssFeed));
    }

    public RssFeedDTO update(RssFeed rssFeed, Integer id){
        return convertToDTO.toRssFeedDTO(repository.putRssFeed(rssFeed, id));
    }

    public RssFeedDTO delete(Integer id){
        return convertToDTO.toRssFeedDTO(repository.deleteRssFeed(id));
    }
}
