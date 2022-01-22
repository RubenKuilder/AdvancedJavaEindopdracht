package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.ContentDTO;
import org.eindopdracht.resource.model.ContentType;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class ContentServiceTest {
    @Autowired
    private ContentService contentService;

    @Test
    void getAllContent()
    {
        List<ContentDTO> contentDTOList = contentService.get();

        assertEquals(3, contentDTOList.size());
        assertEquals(1, contentDTOList.get(0).getContentType().getId());
        assertEquals("Location Path", contentDTOList.get(0).getPath());
        assertEquals(2, contentDTOList.get(1).getContentType().getId());
        assertEquals("Location test", contentDTOList.get(1).getPath());
        assertEquals(3, contentDTOList.get(2).getContentType().getId());
        assertEquals("Location", contentDTOList.get(2).getPath());
    }

    @Test
    void getById()
    {
        ContentDTO contentDto = contentService.getById(1);
        assertEquals(1, contentDto.getContentType().getId());
        assertEquals("Location Path", contentDto.getPath());

        assertThrows(DataNotFoundException.class, () -> {
            ContentDTO contentDtoTst = contentService.getById(2313123);
        });
    }

    @Test
    void postContent() {
        ContentType contentType = new ContentType();
        contentType.setName("Text");

        ContentDTO contentDto = new ContentDTO();
        contentDto.setContentType(contentType);
        contentDto.setPath("Post Path");

        ContentDTO persistedContent = contentService.persist(contentDto);

        assertEquals("Text", persistedContent.getContentType().getName());
        assertEquals("Post Path", persistedContent.getPath());
    }

    @Test
    void putContent() {
        ContentType contentType = new ContentType();
        contentType.setId(1L);
        contentType.setName("Text");

        ContentDTO contentDto = new ContentDTO();
        contentDto.setContentType(contentType);

        ContentDTO putContent = contentService.put(1, contentDto);

        assertEquals("Text", putContent.getContentType().getName());
        assertNull(putContent.getPath());
    }
}
