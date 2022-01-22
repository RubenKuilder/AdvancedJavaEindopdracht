package org.eindopdracht.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eindopdracht.resource.model.Consultation;
import org.eindopdracht.resource.model.Schedule;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;

    List<Consultation> consultations;

    List<Schedule> schedules;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    @NotBlank
    private String profileImagePath;

    private boolean isApproved;

    private String role;

    private boolean enabled;
}
