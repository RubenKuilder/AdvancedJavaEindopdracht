package org.eindopdracht.resource.repository;

import org.eindopdracht.resource.model.ContentType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class ContentTypeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Select queries all content types and returns them in a list.
     *
     * @return response entity with list of all content types
     */
    public List<ContentType> get() {
        TypedQuery<ContentType> query = entityManager.createQuery("SELECT c FROM ContentType c", ContentType.class);
        return query.getResultList();
    }

    /**
     * Find a single content type and return it.
     *
     * @param id id of the content type to find
     * @return response entity with single content type
     */
    public ContentType getById(long id) {
        return entityManager.find(ContentType.class, id);
    }

    /**
     * Post a single content type.
     *
     * @param contentType content type to post
     * @return response entity with posted content type
     */
    public ContentType persist(ContentType contentType) {
        entityManager.persist(contentType);
        return contentType;
    }

    /**
     * Put a single content type.
     * Updates all fields.
     *
     * @param id          id of the content type to put
     * @param contentType content type to put
     * @return response entity with put content type
     */
    public ContentType put(long id, ContentType contentType) {
        contentType.setId(id);
        return entityManager.merge(contentType);
    }

    /**
     * Delete a single content type.
     *
     * @param id id of the content type to delete
     * @return response entity with deleted content type
     */
    public ContentType delete(long id) {
        ContentType contentType = entityManager.find(ContentType.class, id);
        entityManager.remove(contentType);
        return contentType;
    }
}
