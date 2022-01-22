package org.eindopdracht.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eindopdracht.resource.model.Consultation;
import org.eindopdracht.resource.model.Schedule;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotNull
    private int id;

    List<Consultation> consultations;

    List<Schedule> schedules;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    private String profileImagePath;

    @NotNull
    private boolean isApproved;

    @NotNull
    private String role;

    @NotNull
    private boolean enabled;
}
