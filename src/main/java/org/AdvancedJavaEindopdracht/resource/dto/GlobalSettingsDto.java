package org.AdvancedJavaEindopdracht.resource.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Time;

public class GlobalSettingsDto
{
    @NotNull
    @Setter
    @Getter
    private boolean soundOn;

    @NotNull
    @Setter
    @Getter
    private Time switchTime;

    public GlobalSettingsDto()
    {

    }
}
