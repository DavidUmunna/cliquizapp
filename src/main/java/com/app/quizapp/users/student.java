package com.app.quizapp.users;

import jakarta.persistence.*;

@Entity
@Table(name="student")
public class student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    private int marks=0;
    private String sname;
    private int sage;



    public student(int sid, int marks, String sname, int sage) {
        this.sid = sid;
        this.marks = marks;
        this.sname = sname;
        this.sage = sage;
    }

    public student(String sname,int sage){
        this.sname=sname;
        this.sage=sage;
    }

    public student() {

    }


    public int getScore() {
        return marks;
    }
    public String getName() {
        return sname;
    }
    public void increaseScore(){
        marks+=1;
    }
    public String toString(){
        return this.getName()+"scored"+this.getScore();
    }

    

}
