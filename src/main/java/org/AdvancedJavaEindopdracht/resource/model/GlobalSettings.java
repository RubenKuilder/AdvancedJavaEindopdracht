package org.AdvancedJavaEindopdracht.resource.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Entity
public class GlobalSettings
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Integer id;

    @NotNull
    @Setter
    @Getter
    private boolean soundOn;

    @NotNull
    @Setter
    @Getter
    private Time switchTime;

    public GlobalSettings()
    {

    }
}
