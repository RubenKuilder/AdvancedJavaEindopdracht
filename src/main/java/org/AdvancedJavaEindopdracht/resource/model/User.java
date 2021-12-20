package org.AdvancedJavaEindopdracht.resource.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private int id;

    @NotBlank
    @Column(name = "name", nullable = false, length = 50)
    @Getter @Setter private String name;

    @NotBlank
    @Column(name = "password", nullable = false, length = 50)
    @Getter @Setter private String password;

    @NotBlank
    @Column(name = "email", nullable = false, length = 50)
    @Getter @Setter private String email;

    @Column(name = "avatarLink")
    @Getter @Setter private String avatarLink;

    @Column(name = "approved")
    @Getter @Setter private boolean approved;

    public User() {

    }

}