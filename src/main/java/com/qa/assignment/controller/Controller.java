package com.qa.assignment.controller;

import com.qa.assignment.exception.QuestionCreateException;
import com.qa.assignment.exception.QuestionsLimitReachedException;
import com.qa.assignment.exception.SurveyCreateException;
import com.qa.assignment.exception.SurveyNotFoundException;
import com.qa.assignment.model.Question;
import com.qa.assignment.model.Survey;
import com.qa.assignment.model.SurveyResponse;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<Survey> surveys = new ArrayList<>();
    private List<SurveyResponse> surveyResponses = new ArrayList<>();

    public Controller() {
    }

    public void createSurvey(String name) {
        Survey survey = new Survey(name);
        for (Survey existingSurvey : surveys) {
            if (name == null || existingSurvey.getSurveyName().equalsIgnoreCase(name) || name.equals("") ) {
                throw new SurveyCreateException();

            }
        }
        surveys.add(survey);
    }

    public void addQuestion(String surveyName, String questionName) {
        if (questionName == null || questionName.equals("")){
            throw new QuestionCreateException();
        }
        for (Survey s : surveys) {
            if (s.getSurveyName().equalsIgnoreCase(surveyName)) {
                if (s.getQuestions().size() < 10) {
                    for(Question q: s.getQuestions()){
                        if(q.getQuestion().equalsIgnoreCase(questionName)){
                            throw new QuestionCreateException();
                        }
                    }
                    s.getQuestions().add(new Question(questionName));
                }
                else {
                    throw new QuestionsLimitReachedException();
                }
            }
        }
    }

    public Survey getSurveyByName(String surveyName) {
        for (Survey existingSurvey : surveys) {
            if (existingSurvey.getSurveyName().equals(surveyName)) {
                return existingSurvey;
            }
        }
        throw new SurveyNotFoundException();
    }

    public void createSurveyResponse(String name, String surveyName){

    }

    public void addAnswerToSurveyResponse(int answer, String surveyResponseName, String question){

    }

    public List<SurveyResponse> getAllSurveyResponsesForSurvey(String surveyName){
        return null;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }


    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public List<SurveyResponse> getSurveyResponses() {
        return surveyResponses;
    }

    public void setSurveyResponses(List<SurveyResponse> surveyResponses) {
        this.surveyResponses = surveyResponses;
    }

}
