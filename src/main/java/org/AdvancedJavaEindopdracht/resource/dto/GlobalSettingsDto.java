package org.AdvancedJavaEindopdracht.resource.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
