package org.eindopdracht.resource.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
public class RoleDTO {
    @NotBlank
    private String role;

}
