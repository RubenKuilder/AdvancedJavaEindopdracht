package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.ContentTypeDTO;
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
    void putContentType()
    {
        ContentTypeDTO contentTypeDto = new ContentTypeDTO();
        contentTypeDto.setName("New content type");

        ContentTypeDTO putContentTypeDTO = contentTypeService.put(1, contentTypeDto);

        assertEquals("New content type", putContentTypeDTO.getName());
    }

    @Test
    void patchContentType()
    {
        ContentTypeDTO contentTypeDto = new ContentTypeDTO();
        contentTypeDto.setName("New content type");

        ContentTypeDTO patchedContentTypeDTO = contentTypeService.patch(1, contentTypeDto);

        assertEquals("New content type", patchedContentTypeDTO.getName());
    }

    @Test
    void deleteContentType()
    {
        ContentTypeDTO deletedContentType = contentTypeService.delete(1);

        assertEquals("Text", deletedContentType.getName());
    }
}
