package org.eindopdracht.resource.model.event.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eindopdracht.resource.model.event.content.contentType.ContentType;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentDto {
    private Long id;
    private ContentType contentType;

    @NotBlank
    private String path;
}
