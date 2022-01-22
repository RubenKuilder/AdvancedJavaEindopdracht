package org.eindopdracht.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eindopdracht.resource.model.ContentType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentDTO {
    private int id;

    @NotNull
    private ContentType contentType;

    @NotBlank
    private String path;
}
