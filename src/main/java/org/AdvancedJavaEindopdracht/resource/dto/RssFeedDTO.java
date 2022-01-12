package org.AdvancedJavaEindopdracht.resource.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.AdvancedJavaEindopdracht.resource.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
public class RssFeedDTO {

    @NotBlank
    private User user;

    @NotBlank
    private String link;

    @NotBlank
    private Date startDate;

    @NotBlank
    private Date endDate;
}
