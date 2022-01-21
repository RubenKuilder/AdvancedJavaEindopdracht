package org.AdvancedJavaEindopdracht.resource.repository;

import org.AdvancedJavaEindopdracht.resource.exception.general.BadRequestException;
import org.AdvancedJavaEindopdracht.resource.exception.general.DataNotFoundException;
import org.AdvancedJavaEindopdracht.resource.model.consultation.Consultation;
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

    private final UserRepository userRepository;

    public ConsultationRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Select queries all consultations and returns them in a list.
     * Distinct is used to prevent duplicate data.
     * If distinct wasn't used, a consultation with two users would've been returned twice.
     *
     * @return      response entity with list of all consultations
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
     * @param id    id of the consultation to find
     * @return      response entity with a single consultation
     */
    public Consultation getById(long id) {
        try {
            TypedQuery<Consultation> query = entityManager.createQuery("SELECT DISTINCT c FROM Consultation c JOIN FETCH c.users u WHERE c.id = :id", Consultation.class);

            query.setParameter("id", id);
            return query.getSingleResult();
        }catch(Exception e){
            throw new DataNotFoundException();
        }
    }

    /**
     * Post a single consultation.
     *
     * @param consultation  consultation to post
     * @return              response entity with posted consultation
     */
    public Consultation persist(Consultation consultation) {
        try {
            entityManager.persist(consultation);
            return consultation;
        }catch(Exception e){
            throw new BadRequestException();
        }
    }

    /**
     * Put a single consultation.
     * Updates all fields.
     *
     * @param id            id of the consultation to put
     * @param consultation  consultation to put
     * @return              response entity with put consultation
     */
    public Consultation put(long id, Consultation consultation) {
        consultation.setId(id);
        return entityManager.merge(consultation);
    }

    /**
     * Patch a single consultation.
     * Updates whatever field is set in the new object.
     * If a field is null, it will not be updated.
     *
     * @param id            id of the consultation to patch
     * @param consultation  consultation to patch
     * @return              response entity with patched consultation
     */
    public Consultation patch(long id, Consultation consultation) {
        Consultation updatedConsultation = getById(id);

        if (consultation.getUsers() != null) {
            updatedConsultation.setUsers(consultation.getUsers());
        }

        if (consultation.getStartDateTime() != null) {
            updatedConsultation.setStartDateTime(consultation.getStartDateTime());
        }

        if (consultation.getEndDateTime() != null) {
            updatedConsultation.setEndDateTime(consultation.getEndDateTime());
        }

        return updatedConsultation;
    }

    /**
     * Delete a single consultation and return it.
     *
     * @param id    id of the consultation to delete
     * @return      response entity with deleted consultation
     */
    public Consultation delete(long id) {
        Consultation consultationToDelete = getById(id);

        entityManager.remove(entityManager.contains(consultationToDelete) ? consultationToDelete : entityManager.merge(consultationToDelete));
        return consultationToDelete;
    }
}
