package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.ConvertToDTO;
import org.AdvancedJavaEindopdracht.resource.dto.UserAvailabilityDTO;
import org.AdvancedJavaEindopdracht.resource.exception.general.BadRequestException;
import org.AdvancedJavaEindopdracht.resource.exception.general.DataNotFoundException;
import org.AdvancedJavaEindopdracht.resource.exception.general.NoContentException;
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

    /**
     * Maps Entity to DTO and returns a list of all user availabilities.
     *
     * @return      response entity with list of all user availabilities
     */
    public List<UserAvailabilityDTO> getUserAvailabilities() { return userAvailabilityRepository.getUserAvailabilities().stream().map(convertToDTO::toUserAvailabilityDTO).collect(Collectors.toList());}

    /**
     * Maps Entity to DTO and returns a single user availability.
     *
     * @param id    id of the user availability to find
     * @return      response entity with single user availability
     */
    public UserAvailabilityDTO getUserAvailability(Integer id){
        try{
        return convertToDTO.toUserAvailabilityDTO(userAvailabilityRepository.getUserAvailability(id));
    }
        catch (Exception ex)
    {
        throw new DataNotFoundException();
    }
    }

    /**
     * Maps Entity to DTO and posts a single user availability.
     *
     * @param userAvailability  user availability to post
     * @return                  response entity with posted user availability
     */
    public UserAvailabilityDTO create(UserAvailability userAvailability){
        try{
        return convertToDTO.toUserAvailabilityDTO(userAvailabilityRepository.postUserAvailability(userAvailability));
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and puts a single user availability.
     *
     * @param id                id of the user availability to put
     * @param userAvailability  user availability to put
     * @return                  response entity with put user availability
     */
    public UserAvailabilityDTO update(UserAvailability userAvailability, Integer id){
        try{
        return convertToDTO.toUserAvailabilityDTO(userAvailabilityRepository.putUserAvailability(userAvailability, id));
        }
        catch (Exception ex)
        {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and deletes a single user availability.
     *
     * @param id    id of the user availability to delete
     * @return      response entity with deleted user availability
     */
    public UserAvailabilityDTO delete(Integer id){
        try{
        return convertToDTO.toUserAvailabilityDTO(userAvailabilityRepository.deleteUserAvailability(id));
        }
        catch(Exception ex)
        {
            throw new NoContentException();
        }
    }
}
