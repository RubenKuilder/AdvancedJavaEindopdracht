package org.eindopdracht.resource.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.eindopdracht.resource.model.User;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class PowerpointDTO {
    @NotBlank
    private User user;

    @NotBlank
    private String path;
}
