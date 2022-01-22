package org.eindopdracht.resource.mapper;

import org.eindopdracht.resource.dto.ScheduleDTO;
import org.eindopdracht.resource.dto.UserDTO;
import org.eindopdracht.resource.model.Schedule;
import org.eindopdracht.resource.model.User;
import org.eindopdracht.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper implements EntityMapper<User, UserDTO> {
    @Override
    public UserDTO mapFromEntity(User user) {
        return new UserDTO(
                user.getId(),
                user.getConsultations(),
                user.getSchedules(),
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getProfileImagePath(),
                user.isApproved(),
                user.getRole(),
                user.isEnabled()
        );
    }

    @Override
    public User mapToEntity(UserDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getConsultations(),
                userDTO.getSchedules(),
                userDTO.getName(),
                userDTO.getPassword(),
                userDTO.getEmail(),
                userDTO.getProfileImagePath(),
                userDTO.isApproved(),
                userDTO.getRole(),
                userDTO.isEnabled()
        );
    }

    public List<UserDTO> mapFromEntityList(List<User> entities) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User entity : entities) {
            userDTOList.add(mapFromEntity(entity));
        }

        return userDTOList;
    }
}
