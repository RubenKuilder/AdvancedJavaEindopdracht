package org.eindopdracht.resource.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class LoginDTO {
    @NotNull
    private String name;

    @NotNull
    private String password;

    private String role;

    private String profileImagePath;

    private String email;

    private boolean isApproved;
}