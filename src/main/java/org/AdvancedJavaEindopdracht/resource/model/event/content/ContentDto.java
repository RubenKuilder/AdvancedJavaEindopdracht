package org.AdvancedJavaEindopdracht.resource.model.event.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.AdvancedJavaEindopdracht.resource.model.event.content.contentType.ContentType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentDto {
    private Long id;
    private ContentType contentType;

    @NotBlank
    private String path;
}
