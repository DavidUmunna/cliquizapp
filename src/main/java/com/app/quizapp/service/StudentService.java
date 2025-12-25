package com.app.quizapp.service;

import com.app.quizapp.config.JPAUtil;
import com.app.quizapp.repository.StudentRepository;
import com.app.quizapp.users.student;
import jakarta.persistence.EntityManager;

public class StudentService {

    private final StudentRepository repo = new StudentRepository();

    public student createStudent(String name, int age) {

        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        student s = new student(name, age);
        repo.save(em, s);

        em.getTransaction().commit();
        em.close();

        return s;
    }
}
