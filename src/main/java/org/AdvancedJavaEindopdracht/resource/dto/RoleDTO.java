package org.AdvancedJavaEindopdracht.resource.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
public class RoleDTO {
    @NotBlank
    private String role;

}
