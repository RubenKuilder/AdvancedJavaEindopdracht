package org.AdvancedJavaEindopdracht.resource.model.event.content.contentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentTypeDto {
    private Long id;
    
    @NotBlank
    private String name;
}
