package org.eindopdracht.resource.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data // Je kan @Data beter niet gebruiken voor database entiteiten. Heeft te maken met de toString, equals en hashCode methodes.
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Consultation") // Waarom heeft deze een hoofdletter en bijvoorbeeld 'content' niet?
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(
            name = "userConsultation",
            joinColumns = @JoinColumn(name = "consultation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<User> users;

    private Date startDateTime;

    private Date endDateTime;

}
