package org.AdvancedJavaEindopdracht.resource.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.AdvancedJavaEindopdracht.resource.model.User;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class PowerpointDTO {
    @NotBlank
    private User user;

    @NotBlank
    private String path;
}
