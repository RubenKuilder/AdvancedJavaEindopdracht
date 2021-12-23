package org.AdvancedJavaEindopdracht.resource.repository;

import org.AdvancedJavaEindopdracht.exception.general.DataNotFoundException;
import org.AdvancedJavaEindopdracht.resource.model.event.content.contentType.ContentType;
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

    public List<ContentType> get() {
        TypedQuery<ContentType> query = entityManager.createQuery("SELECT c FROM ContentType c", ContentType.class);
        return query.getResultList();
    }

    public ContentType getById(long id) {
        return entityManager.find(ContentType.class, id);
    }

    public ContentType persist(ContentType contentType) {
        entityManager.persist(contentType);
        return contentType;
    }

    public ContentType put(long id, ContentType contentType) {
        contentType.setId(id);
        return entityManager.merge(contentType);
    }

    public ContentType patch(long id, ContentType contentType) {
        if (getById(id) == null)
            throw new DataNotFoundException();

        ContentType updatedContentType = getById(id);

        if (contentType.getName() != null) {
            updatedContentType.setName(contentType.getName());
        }

        return updatedContentType;
    }

    public void delete(long id) {
        ContentType contentTypeToDelete = getById(id);
        entityManager.remove(entityManager.contains(contentTypeToDelete) ? contentTypeToDelete : entityManager.merge(contentTypeToDelete));
    }
}
