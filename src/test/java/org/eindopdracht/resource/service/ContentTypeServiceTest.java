package org.eindopdracht.resource.service;

import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.eindopdracht.resource.model.event.content.ContentDto;
import org.eindopdracht.resource.model.event.content.contentType.ContentTypeDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@ContextConfiguration(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class ContentTypeServiceTest {
    @Autowired
    private ContentTypeService contentTypeService;

    @Test
    void getAllContent() {
        List<ContentTypeDto> contentTypeDtoList = contentTypeService.get();

        assertEquals(3, contentTypeDtoList.size());
        assertEquals("Text", contentTypeDtoList.get(0).getName());
        assertEquals("Video", contentTypeDtoList.get(1).getName());
        assertEquals("TikTok", contentTypeDtoList.get(2).getName());
    }

    @Test
    void getById() {
        ContentTypeDto contentTypeDto = contentTypeService.getById(1);
        assertEquals("Text", contentTypeDto.getName());

        assertThrows(DataNotFoundException.class, () -> {
            contentTypeService.getById(2123);
        });
    }

    @Test
    void postContentType() {
        ContentTypeDto contentTypeDto = new ContentTypeDto();
        contentTypeDto.setName("New content type");

        ContentTypeDto persistedContentTypeDto = contentTypeService.persist(contentTypeDto);

        assertEquals("New content type", persistedContentTypeDto.getName());
    }

    @Test
    void putContentType() {
        ContentTypeDto contentTypeDto = new ContentTypeDto();
        contentTypeDto.setName("New content type");

        ContentTypeDto putContentTypeDto = contentTypeService.put(1, contentTypeDto);

        assertEquals("New content type", putContentTypeDto.getName());
    }

    @Test
    void deleteContentType() {
        ContentTypeDto deletedContentType = contentTypeService.delete(1);

        assertEquals("Text", deletedContentType.getName());

        assertThrows(NoContentException.class, () -> {
            contentTypeService.delete(3224);
        });
    }
}
