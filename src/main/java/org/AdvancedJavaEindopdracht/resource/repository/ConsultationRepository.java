package org.AdvancedJavaEindopdracht.resource.repository;

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

    public List<Consultation> get() {
        TypedQuery<Consultation> query = entityManager.createQuery("SELECT DISTINCT c FROM Consultation c JOIN FETCH c.users u", Consultation.class);

        return query.getResultList();
    }

    // Hier gebruik ik geen entityManager.find omdat die een lazyInitialize error gaf.
    public Consultation getById(long id) {
        TypedQuery<Consultation> query = entityManager.createQuery("SELECT DISTINCT c FROM Consultation c JOIN FETCH c.users u WHERE c.id = :id", Consultation.class);

        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public Consultation persist(Consultation consultation) {
        entityManager.persist(consultation);
        return consultation;
    }

    public Consultation put(long id, Consultation consultation) {
        consultation.setId(id);
        return entityManager.merge(consultation);
    }

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

    public Consultation delete(long id) throws Exception {
        Consultation consultationToDelete = getById(id);

        entityManager.remove(entityManager.contains(consultationToDelete) ? consultationToDelete : entityManager.merge(consultationToDelete));
        return consultationToDelete;
    }
}
