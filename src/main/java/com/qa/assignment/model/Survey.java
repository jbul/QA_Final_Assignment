package com.qa.assignment.model;

import java.util.ArrayList;
import java.util.List;

public class Survey {
    private static int SEQUENCE = 0;
    private String surveyName;
    private int surveyId;
    private List<Question> questions;

    public Survey(){
        this.surveyId = SEQUENCE++;
        questions = new ArrayList<>();
    }

    public Survey(String name) {
        this();
        this.surveyName = name;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
