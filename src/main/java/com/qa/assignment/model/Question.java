package com.qa.assignment.model;

import java.util.ArrayList;

public class Question {

    private static int SEQUENCE = 0;
    private String question;
    private int questionID;

    public Question() {
        this.questionID = SEQUENCE++;
    }

    public Question(String question){
        this();
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestionContent(String question) {
        this.question = question;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }
}
