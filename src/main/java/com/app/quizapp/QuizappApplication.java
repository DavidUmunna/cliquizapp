package com.app.quizapp;
import com.app.quizapp.users.*;

import java.util.*;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizappApplication {
	private static final SessionFactory SESSION_FACTORY = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Trainer.class)      // ✅ Class literal
			.addAnnotatedClass(Question.class)     // ✅ Add ALL related entities
			.addAnnotatedClass(student.class)
			.buildSessionFactory();


	public static void SaveStudent(student user){
		try(Session session = SESSION_FACTORY.openSession()) {

			Transaction tx = session.beginTransaction();
			try{

				session.persist(user);
				tx.commit();
			}catch(Exception e){
				if(tx.isActive()) tx.rollback();
				throw e;
			}

		}

	}
	public static String  AnswerQuestions( String name,int Age,ArrayList<Trainer> TrainerList){
		Configuration config=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(student.class);

		student user=new student(name,Age);
		SaveStudent(user);
		try  {
			if(TrainerList.size()==0) throw new  IndexOutOfBoundsException("the list is empty");
			List<Integer> userquestions=TrainerList.getFirst().getquestions();
			if(userquestions.isEmpty()) {
				return "nothing here, please tell tutor to create questions";
				
			}
			if(userquestions.size()>0){
				int i=0;
				String userAnswer="";
				try(SessionFactory sf= config.buildSessionFactory();
					Session session=sf.openSession()){

					while(i<userquestions.size()){
                        Integer question=userquestions.get(i);

						Question q=session.get(Question.class,question);
						System.out.println(q.getQuestion());
						System.out.println(q.getOptions());
						Scanner input=new Scanner(System.in);
						Character userOption=input.next().charAt(0);
						input.nextLine();
						for(Character key:q.getOptions().keySet()){
							if(key==userOption){
								userAnswer=q.getOptions().get(key);
							}
						}
						boolean Correct=q.getAnswer().equals(userAnswer);
						if(Correct){
							user.increaseScore();

							i++;

						}else{
							System.out.println("your answer was wrong please try again");


						}

					}
				}
				System.out.println(user.toString());
			
		}else{
			System.out.println("there are no questions for the user to answer");
		}
	}catch(Error nullError){
		System.out.println("there was an error");
	}
	
	return user.toString();
	

        
	}


	public static void saveQuestion(Question question) {
		try (Session session = SESSION_FACTORY.openSession()) {  // ✅ Reuse!
			Transaction tx = session.beginTransaction();
			try {
				session.persist(question);
				tx.commit();
			} catch (Exception e) {
				if (tx != null && tx.isActive()) tx.rollback();
				throw e;
			}
		}
	}


	// STATIC - Create ONCE for entire app


	public static void saveTrainer(Trainer trainer) {
		try (Session session = SESSION_FACTORY.openSession()) {  // ✅ Reuse SessionFactory
			Transaction tx = session.beginTransaction();
			try {
				session.persist(trainer);  // ✅ Cascades correctly
				tx.commit();
			} catch (Exception e) {
				if (tx != null && tx.isActive()) tx.rollback();
				throw e;
			}
		}
	}


	public static void SetQuestions(Trainer T,String trainerName){
		boolean CreatingQuestions=true;
		HashMap<Character,String> options=new HashMap<Character,String>();
		while(CreatingQuestions){
			System.out.println("Do you want to create a question?");
			Scanner input=new Scanner(System.in);
			String choice=input.next();
			if(choice.equalsIgnoreCase("yes")){
				System.out.println("Enter the question: ");
				input.nextLine();
				T.setName(trainerName);

				String questionString=input.nextLine();

				Question q=new Question();
				q.setQuestions(questionString);
				System.out.println("Enter the Answer:");
				String questionAnswer=input.nextLine();
				q.setAnswers(questionAnswer);
				System.out.println("add options:");
				System.out.println("how many options do you want:");
				int optioncount=input.nextInt();
				input.nextLine();
				char label = 'A';

				/*
				* this for loop is for setting inserting the options into the temporary options hashmap
				* */
                for (int i = 0; i < optioncount; i++) {
                    System.out.println("Enter option:");
                    String newOption = input.nextLine();
					options.put(label,newOption);
                    //q.addOption(label, newOption);
                    label++;  
                }
				q.setOptions(options);// after the options have been set in the options hashmap then the hashmap gets set in the questions object
				q.setTrainer(T);
				saveQuestion(q);
				T.addQuestions(q);
				saveTrainer(T);
				System.out.println("your question has been created\n");
				System.out.println("Do you want to create another question:");
				String nextchoice=input.nextLine();
				if(nextchoice.equals("yes")){
					continue;
				}else{
					CreatingQuestions=false;
					break;
				}

			}else if(choice.equalsIgnoreCase("no")){
				CreatingQuestions=false;
				
			}else{
				System.out.println("you just entered some rubbish");
				continue;
			}

		}




	}

	

	public static void main(String[] args) {
		//SpringApplication.run(QuizappApplication.class, args);
		ArrayList<Trainer> TrainerList=new ArrayList<Trainer>();
		try(Session session = SESSION_FACTORY.openSession()){
			List<Trainer> trainers = session.createQuery("FROM Trainer", Trainer.class).getResultList();

            TrainerList.addAll(trainers);

		}

        boolean running=true;
		
		while(running){
			try{
				System.out.println("Are you a student or a trainer:\n"+"A. Student\n"+"B. Trainer\n"+"C. Quit");
			
			Scanner input=new Scanner(System.in);
			String option=input.next();
			switch(option){
				case "A":
					System.out.println("enter the user name:");
					String name=input.nextLine();
					input.nextLine();
					System.out.println("enter Age:");
					int age=input.nextInt();
					input.nextLine();
					if(TrainerList.size()==0){
						
						throw new  IndexOutOfBoundsException("the list is empty");
						
					} 
					System.out.println(TrainerList.get(0).getquestions());
					if(TrainerList.get(0).getquestions().size()>0){

						AnswerQuestions(name,age,TrainerList);
					}else{
						System.out.println("please tell the tutor to enter questions");
						
					}
					break;
				case "B":
					Trainer T=new Trainer();
					System.out.println("enter the user name:");
					String trainerName=input.nextLine();
					input.nextLine();

					TrainerList.add(T);
					SetQuestions(T,trainerName);
					break;
				case "C":
					running=false;
					break;
					
					default:
						System.out.println("please enter an option");
						break;
						
						
					
					}
					
					
				}catch(IndexOutOfBoundsException e){
					System.out.println("the exception was caught:\n"+"the index was out of bounds");
					
				}
			}
			}

}
