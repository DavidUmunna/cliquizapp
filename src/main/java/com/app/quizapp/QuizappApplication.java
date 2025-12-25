package com.app.quizapp;

import com.app.quizapp.config.JPAUtil;
import com.app.quizapp.repository.TrainerRepository;
import com.app.quizapp.cli.QuizCli;
import jakarta.persistence.EntityManager;

import java.util.List;

public class QuizappApplication {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManager();
		List trainers;

		em.getTransaction().begin();
		trainers = new TrainerRepository().findAll(em);
		em.getTransaction().commit();
		em.close();

		new QuizCli().start(trainers);

		JPAUtil.shutdown();
	}
}
