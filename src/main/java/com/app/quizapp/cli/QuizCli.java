package com.app.quizapp.cli;

import com.app.quizapp.service.*;
import com.app.quizapp.users.Trainer;

import java.util.List;
import java.util.Scanner;

public class QuizCli {

    private final TrainerService trainerService = new TrainerService();
    private final QuizService quizService = new QuizService();
    private final StudentService studentService = new StudentService();

    public void start(List<Trainer> trainers) {

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("A. Student\nB. Trainer\nC. Quit");
            String option = input.nextLine();

            if (option.equalsIgnoreCase("C")) break;

            if (option.equalsIgnoreCase("A")) {
                System.out.println("Enter name:");
                String name = input.nextLine();
                System.out.println("Enter age:");
                int age = Integer.parseInt(input.nextLine());

                studentService.createStudent(name, age);
                quizService.askQuestions(trainers.get(0));
            }

            if (option.equalsIgnoreCase("B")) {
                Trainer t = new Trainer();
                System.out.println("Enter trainer name:");
                t.setName(input.nextLine());
                trainerService.createTrainerWithQuestions(t);
            }
        }
    }
}
