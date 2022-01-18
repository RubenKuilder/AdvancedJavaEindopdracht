package org.AdvancedJavaEindopdracht.resource.repository;

import org.AdvancedJavaEindopdracht.resource.model.event.content.contentType.ContentType;
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
public class ContentTypeRepositoryTest {
    @Autowired
    private ContentTypeRepository contentTypeRepository;

    @Test
    void getAllContent() throws Exception
    {
        List<ContentType> contentTypeList = contentTypeRepository.get();

        assertEquals(3, contentTypeList.size());
        assertEquals("Text", contentTypeList.get(0).getName());
        assertEquals("Video", contentTypeList.get(1).getName());
        assertEquals("TikTok", contentTypeList.get(2).getName());
    }

    @Test
    void getById() throws Exception
    {
        ContentType contentType = contentTypeRepository.getById(1);
        assertEquals("Text", contentType.getName());
    }

    @Test
    void postContentType() throws Exception
    {
        ContentType contentType = new ContentType();
        contentType.setName("New content type");

        ContentType persistedContentTypeDto = contentTypeRepository.persist(contentType);

        assertEquals("New content type", persistedContentTypeDto.getName());
    }

    @Test
    void putContentType() throws Exception
    {
        ContentType contentType = new ContentType();
        contentType.setName("New content type");

        ContentType putContentTypeDto = contentTypeRepository.put(1, contentType);

        assertEquals("New content type", putContentTypeDto.getName());
    }

    @Test
    void patchContentType() throws Exception
    {
        ContentType contentType = new ContentType();
        contentType.setName("New content type");

        ContentType patchedContentTypeDto = contentTypeRepository.patch(1, contentType);

        assertEquals("New content type", patchedContentTypeDto.getName());
    }

    @Test
    void deleteContentType() throws Exception
    {
        ContentType deletedContentType = contentTypeRepository.delete(1);

        assertEquals("Text", deletedContentType.getName());
    }
}
