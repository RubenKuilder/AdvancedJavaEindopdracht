package org.AdvancedJavaEindopdracht;

import org.AdvancedJavaEindopdracht.resource.dto.GlobalSettingsDto;
import org.AdvancedJavaEindopdracht.resource.dto.UserDTO;
import org.AdvancedJavaEindopdracht.resource.model.GlobalSettings;
import org.AdvancedJavaEindopdracht.resource.dto.RoleDTO;
import org.AdvancedJavaEindopdracht.resource.dto.UserAvailabilityDTO;
import org.AdvancedJavaEindopdracht.resource.model.Role;
import org.AdvancedJavaEindopdracht.resource.model.User;
import org.AdvancedJavaEindopdracht.resource.model.UserAvailability;


public class ConvertToDTO
{
    public GlobalSettingsDto toGlobalSettingsDto(GlobalSettings globalSettings)
    {
        GlobalSettingsDto globalSettingsDto = new GlobalSettingsDto();
        globalSettingsDto.setSoundOn(globalSettings.isSoundOn());
        globalSettingsDto.setSwitchTime(globalSettings.getSwitchTime());
        return globalSettingsDto;
    }

    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setApproved(user.isApproved());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setProfileImagePath(user.getProfileImagePath());
        return userDTO;
    }

    public RoleDTO toRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRole(role.getRole());
        return roleDTO;
    }
    public UserAvailabilityDTO toUserAvailabilityDTO (UserAvailability userAvailability) {
        UserAvailabilityDTO userAvailabilityDTO = new UserAvailabilityDTO();
        userAvailabilityDTO.setUser(userAvailability.getUser());
        userAvailabilityDTO.setDate(userAvailability.getDate());
        return userAvailabilityDTO;
    }
}