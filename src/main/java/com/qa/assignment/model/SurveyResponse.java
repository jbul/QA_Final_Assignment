package com.qa.assignment.model;

import java.util.Map;

public class SurveyResponse {

    private Survey survey;
    //First integer refers to the question id, second integer refers to the answer (1-5)
    private Map<Integer, Integer> responses;

    public SurveyResponse(){}

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Map<Integer, Integer> getResponses() {
        return responses;
    }

    public void setResponses(Map<Integer, Integer> responses) {
        this.responses = responses;
    }
}
