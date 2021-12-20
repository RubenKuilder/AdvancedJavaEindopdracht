package org.AdvancedJavaEindopdracht;

import org.AdvancedJavaEindopdracht.resource.dto.UserDTO;
import org.AdvancedJavaEindopdracht.resource.model.User;


public class ConvertToDTO {

    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO(user.getName(), user.getPassword(), user.getEmail(), user.getAvatarLink(), user.isApproved());
        return userDTO;
    }
}