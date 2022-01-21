package org.AdvancedJavaEindopdracht.resource.repository;

import org.AdvancedJavaEindopdracht.resource.exception.general.BadRequestException;
import org.AdvancedJavaEindopdracht.resource.exception.general.DataNotFoundException;
import org.AdvancedJavaEindopdracht.resource.model.consultation.Consultation;
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
        try {
            return entityManager.find(Content.class, id);
        }catch(Exception e){
            throw new DataNotFoundException();
        }
    }

    /**
     * Post a single content.
     *
     * @param content   content to post
     * @return          response entity with posted content
     */
    public Content persist(Content content) {
        try {
            entityManager.persist(content);
            return content;
        }catch(Exception e){
            throw new BadRequestException();
        }
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
}
