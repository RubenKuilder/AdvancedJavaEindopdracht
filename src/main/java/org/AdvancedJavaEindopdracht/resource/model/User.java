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
    @Column(name = "name", nullable = false)
    @Getter @Setter private String name;

    @NotBlank
    @Column(name = "password", nullable = false)
    @Getter @Setter private String password;

    @NotBlank
    @Column(name = "email", nullable = false)
    @Getter @Setter private String email;

    @Column(name = "profileImagePath", nullable = false)
    @Getter @Setter private String profileImagePath;

    @Column(name = "isApproved")
    @Getter @Setter private boolean approved;

    public User() {

    }

}