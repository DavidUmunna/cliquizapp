package com.app.quizapp.service;

import com.app.quizapp.config.JPAUtil;
import com.app.quizapp.repository.QuestionRepository;
import com.app.quizapp.users.Question;
import com.app.quizapp.users.Trainer;
import jakarta.persistence.EntityManager;

import java.util.Scanner;

public class QuizService {

    private final QuestionRepository repo = new QuestionRepository();

    public void askQuestions(Trainer trainer) {

        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Scanner input = new Scanner(System.in);

        for (Integer qId : trainer.getquestions()) {

            Question q = repo.findById(em, qId);

            System.out.println(q.getQuestion());
            System.out.println(q.getOptions());

            char choice = input.next().charAt(0);
            input.nextLine();

            String answer = q.getOptions().get(choice);
            if (q.getAnswer().equals(answer)) {
                System.out.println("Correct ✅");
            } else {
                System.out.println("Wrong ❌");
            }
        }

        em.getTransaction().commit();
        em.close();
    }
}
