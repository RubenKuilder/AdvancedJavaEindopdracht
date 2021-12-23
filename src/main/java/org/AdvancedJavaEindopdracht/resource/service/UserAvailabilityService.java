package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.ConvertToDTO;
import org.AdvancedJavaEindopdracht.resource.dto.UserAvailabilityDTO;
import org.AdvancedJavaEindopdracht.resource.model.UserAvailability;
import org.AdvancedJavaEindopdracht.resource.repository.UserAvailabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAvailabilityService {
    private final ConvertToDTO convertToDTO = new ConvertToDTO();

    private final UserAvailabilityRepository userAvailabilityRepository;

    public UserAvailabilityService(UserAvailabilityRepository userAvailabilityRepository){
        this.userAvailabilityRepository = userAvailabilityRepository;
    }

    public List<UserAvailabilityDTO> getUserAvailabilities() { return userAvailabilityRepository.getUserAvailabilities().stream().map(convertToDTO::toUserAvailabilityDTO).collect(Collectors.toList());}

    public UserAvailabilityDTO getUserAvailability(Integer id){
        return convertToDTO.toUserAvailabilityDTO(userAvailabilityRepository.getUserAvailability(id));
    }

    public UserAvailabilityDTO create(UserAvailability userAvailability){
        return convertToDTO.toUserAvailabilityDTO(userAvailabilityRepository.postUserAvailability(userAvailability));
    }

    public UserAvailabilityDTO update(UserAvailability userAvailability, Integer id){
        return convertToDTO.toUserAvailabilityDTO(userAvailabilityRepository.putUserAvailability(userAvailability, id));
    }

    public void delete(Integer id){
        userAvailabilityRepository.deleteUserAvailability(id);
    }
}
