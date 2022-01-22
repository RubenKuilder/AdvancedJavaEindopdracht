package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.ContentTypeDTO;
import org.eindopdracht.resource.exception.general.DataNotFoundException;
import org.eindopdracht.resource.exception.general.NoContentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class ContentTypeServiceTest {
    @Autowired
    private ContentTypeService contentTypeService;

    @Test
    void getAllContent()
    {
        List<ContentTypeDTO> contentTypeDTOList = contentTypeService.get();

        assertEquals(3, contentTypeDTOList.size());
        assertEquals("Text", contentTypeDTOList.get(0).getName());
        assertEquals("Video", contentTypeDTOList.get(1).getName());
        assertEquals("TikTok", contentTypeDTOList.get(2).getName());
    }

    @Test
    void getById()
    {
        ContentTypeDTO contentTypeDto = contentTypeService.getById(1);
        assertEquals("Text", contentTypeDto.getName());

        assertThrows(DataNotFoundException.class, () -> {
            contentTypeService.getById(2123);
        });
    }

    @Test
    void postContentType()
    {
        ContentTypeDTO contentTypeDto = new ContentTypeDTO();
        contentTypeDto.setName("New content type");

        ContentTypeDTO persistedContentTypeDTO = contentTypeService.persist(contentTypeDto);

        assertEquals("New content type", persistedContentTypeDTO.getName());
    }

    @Test
    void putContentType() {
        ContentTypeDTO contentTypeDto = new ContentTypeDTO();
        contentTypeDto.setName("New content type");

        ContentTypeDTO putContentTypeDTO = contentTypeService.put(1, contentTypeDto);

        assertEquals("New content type", putContentTypeDTO.getName());
    }

    @Test
    void deleteContentType()
    {
        ContentTypeDTO deletedContentType = contentTypeService.delete(1);

        assertEquals("Text", deletedContentType.getName());

        assertThrows(NoContentException.class, () -> {
            contentTypeService.delete(3224);
        });
    }
}
