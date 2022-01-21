package org.eindopdracht.resource.service;

import org.eindopdracht.resource.model.event.content.ContentDto;
import org.eindopdracht.resource.model.event.content.contentType.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@ContextConfiguration(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class ContentServiceTest {
    @Autowired
    private ContentService contentService;

    @Test
    void getAllContent()
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
    void getById()
    {
        ContentDto contentDto = contentService.getById(1);
        assertEquals(1, contentDto.getContentType().getId());
        assertEquals("Location Path", contentDto.getPath());
    }

    @Test
    void postContent()
    {
        ContentType contentType = new ContentType();
        contentType.setName("Text");

        ContentDto contentDto = new ContentDto();
        contentDto.setContentType(contentType);
        contentDto.setPath("Post Path");

        ContentDto persistedContent = contentService.persist(contentDto);

        assertEquals("Text", persistedContent.getContentType().getName());
        assertEquals("Post Path", persistedContent.getPath());
    }

    @Test
    void putContent()
    {
        ContentType contentType = new ContentType();
        contentType.setId(1L);
        contentType.setName("Text");

        ContentDto contentDto = new ContentDto();
        contentDto.setContentType(contentType);

        ContentDto putContent = contentService.put(1, contentDto);

        assertEquals("Text", putContent.getContentType().getName());
        assertNull(putContent.getPath());
    }

    @Test
    void patchContent()
    {
        ContentDto contentDto = new ContentDto();
        contentDto.setPath("Patch Path");

        ContentDto patchedContent = contentService.patch(1, contentDto);

        assertEquals(1, patchedContent.getContentType().getId());
        assertEquals("Patch Path", patchedContent.getPath());
    }
}
