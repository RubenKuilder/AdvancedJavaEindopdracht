package org.AdvancedJavaEindopdracht.resource.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class RoleDTO {
    @NotBlank
    @Getter
    @Setter
    private String role;

    public RoleDTO() {

    }
}
