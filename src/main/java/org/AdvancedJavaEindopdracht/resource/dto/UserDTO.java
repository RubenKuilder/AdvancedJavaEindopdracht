package org.AdvancedJavaEindopdracht.resource.dto;

import lombok.Getter;
import javax.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank
    @Getter private String name;

    @NotBlank
    @Getter private String password;

    @NotBlank
    @Getter private String email;

    @NotBlank
    @Getter private String avatarLink;

    @NotBlank
    @Getter private boolean approved;


    public UserDTO(String name, String password, String email, String avatarLink, boolean approved) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.avatarLink = avatarLink;
        this.approved = approved;
    }

}
