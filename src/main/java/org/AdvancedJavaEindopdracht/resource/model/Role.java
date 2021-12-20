package org.AdvancedJavaEindopdracht.resource.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private int id;

    @NotBlank
    @Column(name = "role", nullable = false)
    @Getter @Setter private String role;

    public Role() {

    }
}
