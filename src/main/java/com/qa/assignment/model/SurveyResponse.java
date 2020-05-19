package com.qa.assignment.model;

import java.util.HashMap;
import java.util.Map;

public class SurveyResponse {

    private String name;
    private Survey survey;
    //First integer refers to the question name, second integer refers to the answer (1-5)
    private Map<String, Integer> responses;

    public SurveyResponse(){}

    public SurveyResponse(String name) {
        responses = new HashMap<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Map<String, Integer> getResponses() {
        return responses;
    }

    public void setResponses(Map<String, Integer> responses) {
        this.responses = responses;
    }
}
