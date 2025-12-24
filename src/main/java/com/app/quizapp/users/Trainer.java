package com.app.quizapp.users;

import jakarta.persistence.*;


import java.util.*;


@Entity
@Table(name="trainer")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ElementCollection
    @CollectionTable(name = "trainer_question_ids", joinColumns = @JoinColumn(name = "trainer_id"))
    @Column(name = "question_id")
    private List<Integer> questionIds = new ArrayList<>();


    public Trainer(){


    }
    public void setName(String name){
        this.name=name;
    }
    public  void addQuestions(Question q){
        questionIds.add(q.getQuestionid());
    }
    public List<Integer> getquestions(){
        return questionIds;
    }



    
}
