package org.eindopdracht.resource.mapper;

import org.eindopdracht.resource.dto.UserAvailabilityDTO;
import org.eindopdracht.resource.model.UserAvailability;
import org.eindopdracht.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAvailabilityMapper implements EntityMapper<UserAvailability, UserAvailabilityDTO> {
    @Override
    public UserAvailabilityDTO mapFromEntity(UserAvailability userAvailability) {
        return new UserAvailabilityDTO(
                userAvailability.getId(),
                userAvailability.getUser(),
                userAvailability.getDate()
        );
    }

    @Override
    public UserAvailability mapToEntity(UserAvailabilityDTO userAvailabilityDTO) {
        return new UserAvailability(
                userAvailabilityDTO.getId(),
                userAvailabilityDTO.getUser(),
                userAvailabilityDTO.getDate()
        );
    }

    public List<UserAvailabilityDTO> mapFromEntityList(List<UserAvailability> entities) {
        List<UserAvailabilityDTO> userAvailabilityDTOOList = new ArrayList<>();
        for (UserAvailability entity : entities) {
            userAvailabilityDTOOList.add(mapFromEntity(entity));
        }

        return userAvailabilityDTOOList;
    }
}
