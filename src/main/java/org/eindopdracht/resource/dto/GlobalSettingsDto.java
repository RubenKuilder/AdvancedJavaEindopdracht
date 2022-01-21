package org.eindopdracht.resource.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Time;

@Data
@NoArgsConstructor
public class GlobalSettingsDto
{
    @NotNull
    private boolean soundOn;

    @NotNull
    private Time switchTime;
}
