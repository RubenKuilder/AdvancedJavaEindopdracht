package org.eindopdracht;

import org.eindopdracht.resource.dto.*;
import org.eindopdracht.resource.model.*;
import org.eindopdracht.resource.model.ContentType;
import org.eindopdracht.resource.dto.ContentTypeDTO;


public class ConvertToDTO {

    public GlobalSettingsDTO toGlobalSettingsDto(GlobalSettings globalSettings)
    {
        GlobalSettingsDTO globalSettingsDto = new GlobalSettingsDTO();
        globalSettingsDto.setSoundOn(globalSettings.isSoundOn());
        globalSettingsDto.setSwitchTime(globalSettings.getSwitchTime());
        return globalSettingsDto;
    }

    public RssFeedDTO toRssFeedDTO(RssFeed rssFeed) {
        RssFeedDTO rssFeedDTO = new RssFeedDTO();
        rssFeedDTO.setLink(rssFeed.getLink());
        rssFeedDTO.setEndDateTime(rssFeed.getEndDateTime());
        rssFeedDTO.setStartDateTime(rssFeed.getStartDateTime());
        rssFeedDTO.setUser(rssFeed.getUser());
        return rssFeedDTO;
    }

    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
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

    public PowerpointDTO toPowerpointDTO (Powerpoint powerpoint) {
        PowerpointDTO powerpointDTO = new PowerpointDTO();
        powerpointDTO.setUser(powerpoint.getUser());
        powerpointDTO.setPath(powerpoint.getPath());
        return powerpointDTO;
    }

    public ContentTypeDTO toContentTypeDTO(ContentType contentType)
    {
        ContentTypeDTO contentTypeDto = new ContentTypeDTO();
        contentTypeDto.setName(contentType.getName());
        return contentTypeDto;
    }
}