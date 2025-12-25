package com.app.quizapp.repository;

import com.app.quizapp.users.student;
import jakarta.persistence.EntityManager;

public class StudentRepository {

    public void save(EntityManager em, student s) {
        em.persist(s);
    }
}
