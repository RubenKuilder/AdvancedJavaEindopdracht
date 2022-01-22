package org.eindopdracht.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalSettingsDTO {
    private Integer id;

    @NotBlank
    private boolean soundOn;

    @NotBlank
    private Time switchTime;
}
