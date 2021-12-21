package org.AdvancedJavaEindopdracht.resource.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
public class UserDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    @NotBlank
    private String profileImagePath;

    @NotBlank
    private boolean approved;

}
