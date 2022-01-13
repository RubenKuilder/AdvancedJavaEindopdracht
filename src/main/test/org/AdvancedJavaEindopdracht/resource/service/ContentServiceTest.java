package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.resource.model.consultation.ConsultationDto;
import org.AdvancedJavaEindopdracht.resource.model.event.content.ContentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@ContextConfiguration(classes = org.AdvancedJavaEindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class ContentServiceTest {
    @Autowired
    private ContentService contentService;

    @Test
    void getAllContent() throws Exception
    {
        List<ContentDto> contentDtoList = contentService.get();

        assertEquals(3, contentDtoList.size());
        assertEquals(1, contentDtoList.get(0).getContentType().getId());
        assertEquals("Location Path", contentDtoList.get(0).getPath());
        assertEquals(2, contentDtoList.get(1).getContentType().getId());
        assertEquals("Location test", contentDtoList.get(1).getPath());
        assertEquals(3, contentDtoList.get(2).getContentType().getId());
        assertEquals("Location", contentDtoList.get(2).getPath());
    }

    @Test
    void getById() throws Exception
    {
        ContentDto contentDto = contentService.getById(1);
        assertEquals(1, contentDto.getContentType().getId());
        assertEquals("Location Path", contentDto.getPath());
    }
}
