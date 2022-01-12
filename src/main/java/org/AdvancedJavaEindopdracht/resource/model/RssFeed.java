package org.AdvancedJavaEindopdracht.resource.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "rssfeed")
public class RssFeed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank
    @Column(name = "link", nullable = false)
    private String link;

    @NotNull
    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @NotNull
    @Column(name = "endDate", nullable = false)
    private Date endDate;
}