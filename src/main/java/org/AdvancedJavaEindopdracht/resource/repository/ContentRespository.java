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

    /**
     * Select queries all content and returns them in a list.
     *
     * @return      response entity with list of all content
     */
    public List<Content> get() {
        TypedQuery<Content> query = entityManager.createQuery("SELECT c FROM Content c", Content.class);
        return query.getResultList();
    }

    /**
     * Find a single content and return it.
     *
     * @param id    id of the content to find
     * @return      response entity with single content
     */
    public Content getById(long id) {
        return entityManager.find(Content.class, id);
    }

    /**
     * Post a single content.
     *
     * @param content   content to post
     * @return          response entity with posted content
     */
    public Content persist(Content content) {
        entityManager.persist(content);
        return content;
    }

    /**
     * Put a single content.
     * Updates all fields.
     *
     * @param id        id of the content to put
     * @param content   content to put
     * @return          response entity with put content
     */
    public Content put(long id, Content content) {
        content.setId(id);
        return entityManager.merge(content);
    }

    /**
     * Patch a single content.
     * Updates whatever field is set in the new object.
     * If a field is null, it will not be updated.
     *
     * @param id        id of the content to patch
     * @param content   content to patch
     * @return          response entity with patched content
     */
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
