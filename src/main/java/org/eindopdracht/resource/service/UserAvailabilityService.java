package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.UserAvailabilityDTO;
import org.eindopdracht.resource.exception.general.BadRequestException;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.mapper.UserAvailabilityMapper;
import org.eindopdracht.resource.repository.UserAvailabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAvailabilityService {
    private final UserAvailabilityRepository userAvailabilityRepository;
    private final UserAvailabilityMapper userAvailabilityMapper;

    public UserAvailabilityService(UserAvailabilityRepository userAvailabilityRepository, UserAvailabilityMapper userAvailabilityMapper) {
        this.userAvailabilityRepository = userAvailabilityRepository;
        this.userAvailabilityMapper = userAvailabilityMapper;
    }

    /**
     * Maps Entity to DTO and returns a list of all user availabilities.
     *
     * @return response entity with list of all user availabilities
     */
    public List<UserAvailabilityDTO> getUserAvailabilities() {
        return userAvailabilityMapper.mapFromEntityList(userAvailabilityRepository.getUserAvailabilities());
    }

    /**
     * Maps Entity to DTO and returns a single user availability.
     *
     * @param id id of the user availability to find
     * @return response entity with single user availability
     */
    public UserAvailabilityDTO getUserAvailability(Integer id) {
        try {
            return userAvailabilityMapper.mapFromEntity(userAvailabilityRepository.getUserAvailability(id));
        } catch (Exception ex) {
            throw new DataNotFoundException("id: " + id);
        }
    }

    /**
     * Maps Entity to DTO and posts a single user availability.
     *
     * @param userAvailabilityDTO user availability to post
     * @return response entity with posted user availability
     */
    public UserAvailabilityDTO create(UserAvailabilityDTO userAvailabilityDTO) {
        try {
            return userAvailabilityMapper.mapFromEntity(
                    userAvailabilityRepository.postUserAvailability(userAvailabilityMapper.mapToEntity(userAvailabilityDTO))
            );
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and puts a single user availability.
     *
     * @param id                  id of the user availability to put
     * @param userAvailabilityDTO user availability to put
     * @return response entity with put user availability
     */
    public UserAvailabilityDTO update(UserAvailabilityDTO userAvailabilityDTO, Integer id) {
        try {
            return userAvailabilityMapper.mapFromEntity(userAvailabilityRepository.putUserAvailability(userAvailabilityMapper.mapToEntity(userAvailabilityDTO), id));
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    /**
     * Maps Entity to DTO and deletes a single user availability.
     *
     * @param id id of the user availability to delete
     * @return response entity with deleted user availability
     */
    public UserAvailabilityDTO delete(Integer id) {
        try {
            return userAvailabilityMapper.mapFromEntity(userAvailabilityRepository.deleteUserAvailability(id));
        } catch (Exception ex) {
            throw new NoContentException("id: " + id);
        }
    }
}
