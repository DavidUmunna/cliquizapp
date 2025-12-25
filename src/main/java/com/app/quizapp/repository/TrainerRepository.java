package com.app.quizapp.repository;

import com.app.quizapp.users.Trainer;
import jakarta.persistence.EntityManager;
import java.util.List;

public class TrainerRepository {

    public void save(EntityManager em, Trainer trainer) {
        em.persist(trainer);
    }

    public List<Trainer> findAll(EntityManager em) {
        return em.createQuery("FROM Trainer", Trainer.class)
                .getResultList();
    }
}
