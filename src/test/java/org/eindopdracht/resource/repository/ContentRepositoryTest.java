package org.eindopdracht.resource.repository;

import org.eindopdracht.resource.model.event.content.Content;
import org.eindopdracht.resource.model.event.content.contentType.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class ContentRepositoryTest {
    @Autowired
    private ContentRespository contentRespository;

    @Test
    void getAll()
    {
        List<Content> contentList = contentRespository.get();
        assertEquals(3, contentList.size());
        assertEquals(1, contentList.get(0).getContentType().getId());
        assertEquals("Location Path", contentList.get(0).getPath());
        assertEquals(2, contentList.get(1).getContentType().getId());
        assertEquals("Location test", contentList.get(1).getPath());
        assertEquals(3, contentList.get(2).getContentType().getId());
        assertEquals("Location", contentList.get(2).getPath());
    }

    @Test
    void getById()
    {
        Content content = contentRespository.getById(1);
        assertEquals(1, content.getContentType().getId());
        assertEquals("Location Path", content.getPath());
    }

    @Test
    void postContent()
    {
        ContentType contentType = new ContentType();
        contentType.setName("Text");

        Content content = new Content();
        content.setContentType(contentType);
        content.setPath("Post Path");

        Content persistedContent = contentRespository.persist(content);

        assertEquals("Text", persistedContent.getContentType().getName());
        assertEquals("Post Path", persistedContent.getPath());
    }

    @Test
    void putContent()
    {
        ContentType contentType = new ContentType();
        contentType.setId(1L);
        contentType.setName("Text");

        Content content = new Content();
        content.setContentType(contentType);

        Content putContent = contentRespository.put(1, content);

        assertEquals("Text", putContent.getContentType().getName());
        assertNull(putContent.getPath());
    }

    @Test
    void patchContent()
    {
        Content content = new Content();
        content.setPath("Patch Path");

        Content patchedContent = contentRespository.patch(1, content);

        assertEquals(1, patchedContent.getContentType().getId());
        assertEquals("Patch Path", patchedContent.getPath());
    }
}
