package com.qa.assignment.controller;

import com.qa.assignment.exception.*;
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
            if (name == null || existingSurvey.getSurveyName().equalsIgnoreCase(name) || name.equals("")) {
                throw new SurveyCreateException();

            }
        }
        surveys.add(survey);
    }

    public void addQuestion(String surveyName, String questionName) {
        if (questionName == null || questionName.equals("")) {
            throw new QuestionCreateException();
        }
        for (Survey s : surveys) {
            if (s.getSurveyName().equalsIgnoreCase(surveyName)) {
                if (s.getQuestions().size() < 10) {
                    for (Question q : s.getQuestions()) {
                        if (q.getQuestion().equalsIgnoreCase(questionName)) {
                            throw new QuestionCreateException();
                        }
                    }
                    s.getQuestions().add(new Question(questionName));
                } else {
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

    public void createSurveyResponse(String name, String surveyName) {
        SurveyResponse surveyResponse = new SurveyResponse(name);
        Survey survey = null;
        if (name == null || name.equals("")) {
            throw new SurveyResponseCreateException();
        }

        for (Survey existingSurvey : surveys) {
            if (existingSurvey.getSurveyName().equalsIgnoreCase(surveyName)) {
                survey = existingSurvey;
                break;


            }
        }

        if (survey == null) {
            throw new SurveyNotFoundException();
        }

        surveyResponse.setSurvey(survey);

        for (SurveyResponse surveyResp : surveyResponses) {
            if (surveyResp.getName().equalsIgnoreCase(name)) {
                throw new SurveyResponseCreateException();

            }
        }
        surveyResponses.add(surveyResponse);
    }

    public void addAnswerToSurveyResponse(int answer, String surveyResponseName, String surveyName, String question) {

        if (answer < 1 || answer > 5) {
            throw new InvalidAnswerException();
        }


        Survey survey = getSurveyByName(surveyName);

        boolean found = false;
        for (Question quest : survey.getQuestions()) {
            if (question.equalsIgnoreCase(quest.getQuestion())) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new QuestionNotFoundException();
        }

        SurveyResponse sv = null;
        for (SurveyResponse surveyResponses : getSurveyResponses()) {
            if (surveyResponses.getName().equalsIgnoreCase(surveyResponseName)) {
                sv = surveyResponses;
                break;
            }
        }
        if (sv == null) {
            throw new SurveyResponseNotFound();
        }
        sv.getResponses().put(question, answer);


    }

    public List<SurveyResponse> getAllSurveyResponsesForSurvey(String surveyName) {
        List<SurveyResponse> responses = new ArrayList<>();
        getSurveyByName(surveyName);

        for (SurveyResponse surveyResp : surveyResponses) {
            if (surveyResp.getSurvey().getSurveyName().equalsIgnoreCase(surveyName)) {
                responses.add(surveyResp);
            }
        }
        return responses;
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
