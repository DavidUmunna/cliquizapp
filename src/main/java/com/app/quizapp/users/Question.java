package com.app.quizapp.users;
import jakarta.persistence.*;

import java.util.Map;
import java.util.HashMap;
@Entity
@Table(name="question")
public class Question  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionid;
    private String questionString;
    private  String answer;
    @ElementCollection
    @CollectionTable(name="Options")
    @MapKeyColumn(name="OptionIndex")
    @Column(name="OptionValue")
    private  Map<Character,String> options;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Trainer_id")
    private Trainer trainer;

    public Question() {
        options=new HashMap<>();
    }

    public  String getQuestionString() {
        return questionString;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Trainer getTrainer() {
        return trainer;
    }
    public  String getQuestion() {
        return questionString;
    }
    public  String getAnswer() {
        return answer;
    }
    public   Map<Character, String> getOptions() {
        return options;
    }
    public void setQuestions(String questions) {
        this.questionString = questions;
    }
    public void setAnswers(String answers) {
        this.answer = answers;
    }
    public void setOptions(HashMap<Character, String> options) {
        this.options = options;
    }
    public void addOption(Character key, String option){

        options.put(key,option);

    }
    public  boolean equals(Question q){
        return answer.equals(q.answer);
    }





}


