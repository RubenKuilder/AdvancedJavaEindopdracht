package org.AdvancedJavaEindopdracht.resource.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank
    @Getter @Setter private String name;

    @NotBlank
    @Getter @Setter private String password;

    @NotBlank
    @Getter @Setter private String email;

    @NotBlank
    @Getter @Setter private String profileImagePath;

    @NotBlank
    @Getter @Setter
    private boolean approved;

    public UserDTO() {

    }

}
