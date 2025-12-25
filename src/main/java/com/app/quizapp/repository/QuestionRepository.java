package com.app.quizapp.repository;

import com.app.quizapp.users.Question;
import jakarta.persistence.EntityManager;

public class QuestionRepository {

    public void save(EntityManager em, Question q) {
        em.persist(q);
    }

    public Question findById(EntityManager em, int id) {
        return em.find(Question.class, id);
    }
}
