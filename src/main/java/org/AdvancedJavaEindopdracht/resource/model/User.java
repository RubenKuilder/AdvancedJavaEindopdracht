package org.AdvancedJavaEindopdracht.resource.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.AdvancedJavaEindopdracht.resource.model.consultation.Consultation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    List<Consultation> consultations;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "profileImagePath", nullable = false)
    private String profileImagePath;

    @Column(name = "isApproved")
    private boolean isApproved;

}