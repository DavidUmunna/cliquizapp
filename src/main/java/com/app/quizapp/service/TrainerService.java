package com.app.quizapp.service;

import com.app.quizapp.config.JPAUtil;
import com.app.quizapp.repository.TrainerRepository;
import com.app.quizapp.users.Trainer;
import jakarta.persistence.EntityManager;

public class TrainerService {

    private final TrainerRepository repo = new TrainerRepository();

    public Trainer createTrainerWithQuestions(Trainer trainer) {

        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        repo.save(em, trainer);

        em.getTransaction().commit();
        em.close();

        return trainer;
    }
}
