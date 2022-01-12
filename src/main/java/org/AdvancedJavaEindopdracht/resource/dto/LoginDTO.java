package org.AdvancedJavaEindopdracht.resource.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {

    private String name;

    private String password;

    private String role;

    private String profileImagePath;

    private String email;

    private boolean isApproved;
}