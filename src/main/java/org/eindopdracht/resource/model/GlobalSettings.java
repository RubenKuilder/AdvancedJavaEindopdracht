package org.eindopdracht.resource.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Entity
@Data
@NoArgsConstructor
public class GlobalSettings
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private boolean soundOn;

    @NotNull
    private Time switchTime;

}
