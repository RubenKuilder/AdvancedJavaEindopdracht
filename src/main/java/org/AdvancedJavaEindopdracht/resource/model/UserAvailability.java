package org.AdvancedJavaEindopdracht.resource.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_availability")
public class UserAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @NotBlank
    @Column(name = "date", nullable = false)
    private Date date;
}
