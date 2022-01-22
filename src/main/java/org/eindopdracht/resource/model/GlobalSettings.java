package org.eindopdracht.resource.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private boolean soundOn;

    @NotNull
    private Time switchTime;

}
