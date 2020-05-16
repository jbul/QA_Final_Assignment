package com.qa.assignment.model;



public class Question {

    private String question;
    private int questionID;

    public Question() {

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
