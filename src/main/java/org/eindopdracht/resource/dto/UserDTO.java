package org.eindopdracht.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    List<Consultation> consultations; // Waarom geen DTO's?

    @JsonIgnore
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
