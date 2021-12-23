package org.AdvancedJavaEindopdracht.resource.model.consultation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.AdvancedJavaEindopdracht.resource.model.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Consultation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "consultations")
    Set<User> users;

    private Date startDateTime;

    private Date endDateTime;

}
