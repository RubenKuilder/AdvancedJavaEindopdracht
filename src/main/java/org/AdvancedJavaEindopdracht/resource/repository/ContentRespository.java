package org.AdvancedJavaEindopdracht.resource.repository;

import org.AdvancedJavaEindopdracht.exception.general.DataNotFoundException;
import org.AdvancedJavaEindopdracht.resource.model.event.content.Content;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class ContentRespository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Content> get() {
        TypedQuery<Content> query = entityManager.createQuery("SELECT c FROM Content c", Content.class);
        return query.getResultList();
    }

    public Content getById(long id) {
        return entityManager.find(Content.class, id);
    }

    public Content persist(Content content) {
        entityManager.persist(content);
        return content;
    }

    public Content put(long id, Content content) {
        content.setId(id);
        return entityManager.merge(content);
    }

    public Content patch(long id, Content content) {
        if (getById(id) == null)
            throw new DataNotFoundException();

        Content updatedContent = getById(id);

        if (content.getContentType() != null) {
            updatedContent.setContentType(content.getContentType());
        }

        if (content.getPath() != null) {
            updatedContent.setPath(content.getPath());
        }

        return updatedContent;
    }
}
