package org.eindopdracht.resource.repository;

import org.eindopdracht.resource.model.Consultation;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ConsultationRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository; // Je roept vanuit een repository geen andere repositories aan. Nu gebruiken jullie deze ook niet, maar toch. Vreemd.

    public ConsultationRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Select queries all consultations and returns them in a list.
     * Distinct is used to prevent duplicate data.
     * If distinct wasn't used, a consultation with two users would've been returned twice.
     *
     * @return response entity with list of all consultations
     */
    public List<Consultation> get() {
        TypedQuery<Consultation> query = entityManager.createQuery("SELECT DISTINCT c FROM Consultation c JOIN FETCH c.users u order by c.id", Consultation.class);
        return query.getResultList();
    }

    /**
     * Find a single consultation and return it.
     * Distinct is used to prevent duplicate data.
     * If distinct wasn't used, a consultation with two users would've been returned twice.
     * We didn't use entityManager.find here because this gave a lazyInitialize error.
     *
     * @param id id of the consultation to find
     * @return response entity with a single consultation
     */
    public Consultation getById(int id) {
        TypedQuery<Consultation> query = entityManager.createQuery("SELECT DISTINCT c FROM Consultation c JOIN FETCH c.users u WHERE c.id = :id", Consultation.class);
        query.setParameter("id", id);

        // Dit kan ook zo!
//        Consultation consultation = entityManager.find(Consultation.class, id);
//        Hibernate.initialize(consultation.getUsers());
//        return consultation;
        return query.getSingleResult();
    }

    /**
     * Post a single consultation.
     *
     * @param consultation consultation to post
     * @return response entity with posted consultation
     */
    public Consultation persist(Consultation consultation) {
        entityManager.persist(consultation);
        return consultation;
    }

    /**
     * Put a single consultation.
     * Updates all fields.
     *
     * @param id           id of the consultation to put
     * @param consultation consultation to put
     * @return response entity with put consultation
     */
    public Consultation put(int id, Consultation consultation) {
        consultation.setId(id);
        return entityManager.merge(consultation);
    }

    /**
     * Delete a single consultation and return it.
     *
     * @param id id of the consultation to delete
     * @return response entity with deleted consultation
     */
    public Consultation delete(int id) {
        Consultation consultationToDelete = getById(id); // Als je getById doet, dan zit het resultaat toch al per definitie in de Persistence Context? Dus dan hoef je ook niet te checken of het er nog in zit.

        entityManager.remove(entityManager.contains(consultationToDelete) ? consultationToDelete : entityManager.merge(consultationToDelete));
        return consultationToDelete;
    }
}
