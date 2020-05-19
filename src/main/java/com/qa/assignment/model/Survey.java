package com.qa.assignment.model;

import java.util.ArrayList;
import java.util.List;

public class Survey {
    private String surveyName;
    private List<Question> questions;

    public Survey(){
        questions = new ArrayList<>();
    }

    public Survey(String name) {
        this();
        this.surveyName = name;
    }

    public String getSurveyName() {
        return surveyName;
    }


    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
