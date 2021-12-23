package org.AdvancedJavaEindopdracht.resource.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.AdvancedJavaEindopdracht.resource.model.User;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserAvailabilityDTO {
    @NotBlank
    private User user;

    @NotBlank
    private Date date;
}
