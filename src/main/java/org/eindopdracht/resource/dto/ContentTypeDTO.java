package org.eindopdracht.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentTypeDTO {
    private int id;

    @NotBlank
    private String name;
}
