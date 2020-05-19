package com.qa.assignment.controller;

import com.qa.assignment.exception.*;
import com.qa.assignment.model.Question;
import com.qa.assignment.model.Survey;
import com.qa.assignment.model.SurveyResponse;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
                    for (Question question : s.getQuestions()) {
                        if (question.getQuestion().equalsIgnoreCase(questionName)) {
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
        for (Question currentQuestion : survey.getQuestions()) {
            if (question.equalsIgnoreCase(currentQuestion.getQuestion())) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new QuestionNotFoundException();
        }

        SurveyResponse foundSurveyResponse = null;
        for (SurveyResponse surveyResponse : getSurveyResponses()) {
            if (surveyResponse.getName().equalsIgnoreCase(surveyResponseName)) {
                foundSurveyResponse = surveyResponse;
                break;
            }
        }
        if (foundSurveyResponse == null) {
            throw new SurveyResponseNotFound();
        }
        foundSurveyResponse.getResponses().put(question, answer);


    }

    public List<SurveyResponse> getAllSurveyResponsesForSurvey(String surveyName) {
        List<SurveyResponse> responses = new ArrayList<>();
        getSurveyByName(surveyName);  //Calling method to ensure survey exists.

        for (SurveyResponse surveyResp : surveyResponses) {
            if (surveyResp.getSurvey().getSurveyName().equalsIgnoreCase(surveyName)) {
                responses.add(surveyResp);
            }
        }
        return responses;
    }

    public Double getSurveyAverage(String surveyName) {
        int total = 0;
        int numQuestion = 0;
        getSurveyByName(surveyName);  //Calling method to ensure survey exists.

        List<SurveyResponse> responses = getAllSurveyResponsesForSurvey(surveyName);
        for (SurveyResponse surveyResponse : responses) {
            for (Map.Entry<String, Integer> entry : surveyResponse.getResponses().entrySet()) {
                total += entry.getValue();
                numQuestion++;
            }
        }
        return Double.valueOf(total / (double) numQuestion);
    }

    public Double getSurveyStandardDeviation(String surveyName) {

        getSurveyByName(surveyName);  //Calling method to ensure survey exists.
        StandardDeviation sd = new StandardDeviation(false);
        List<Double> answers = new ArrayList<>();
        List<SurveyResponse> responses = getAllSurveyResponsesForSurvey(surveyName);

        for (SurveyResponse surveyResponse : responses) {
            for (Map.Entry<String, Integer> entry : surveyResponse.getResponses().entrySet()) {
                answers.add(Double.valueOf(entry.getValue()));
            }
        }
        double[] answersArray = answers.stream().mapToDouble(Double::doubleValue).toArray();
        return sd.evaluate(answersArray);

    }

    public Integer getSurveyMinScore(String surveyName) {
        List<Integer> answers = getSortedSurveyScores(surveyName);
        return answers.get(0);

    }

    public Integer getSurveyMaxScore(String surveyName) {
        List<Integer> answers = getSortedSurveyScores(surveyName);
        return answers.get(answers.size() - 1);
    }

    private List<Integer> getSortedSurveyScores(String surveyName) {
        getSurveyByName(surveyName);  //Calling method to ensure survey exists.
        List<SurveyResponse> responses = getAllSurveyResponsesForSurvey(surveyName);
        List<Integer> answers = new ArrayList<>();

        for (SurveyResponse surveyResponse : responses) {
            for (Map.Entry<String, Integer> entry : surveyResponse.getResponses().entrySet()) {
                answers.add(Integer.valueOf(entry.getValue()));
            }
        }

        Collections.sort(answers);
        return answers;
    }

    public Double getAverageForSpecificQuestion(String surveyName, String question) {
        int total = 0;
        int numQuestion = 0;
        getSurveyByName(surveyName);  //Calling method to ensure survey exists.
        boolean found = false;

        List<SurveyResponse> responses = getAllSurveyResponsesForSurvey(surveyName);
        for (SurveyResponse surveyResponse : responses) {
            for (Map.Entry<String, Integer> entry : surveyResponse.getResponses().entrySet()) {
                if (entry.getKey().equalsIgnoreCase(question)) {
                    total += entry.getValue();
                    numQuestion++;
                    found = true;
                }
            }
        }
        if (!found) {
            throw new QuestionNotFoundException();
        }
        return Double.valueOf(total / (double) numQuestion);
    }

    public Double getStandardDeviationForSpecificQuestion(String surveyName, String question) {

        getSurveyByName(surveyName);  //Calling method to ensure survey exists.
        StandardDeviation sd = new StandardDeviation(false);
        List<Double> answers = new ArrayList<>();
        List<SurveyResponse> responses = getAllSurveyResponsesForSurvey(surveyName);
        boolean found = false;

        for (SurveyResponse surveyResponse : responses) {
            for (Map.Entry<String, Integer> entry : surveyResponse.getResponses().entrySet()) {
                if (entry.getKey().equalsIgnoreCase(question)) {
                    answers.add(Double.valueOf(entry.getValue()));
                    found = true;
                }
            }
        }
        if (!found) {
            throw new QuestionNotFoundException();
        }
        double[] answersArray = answers.stream().mapToDouble(Double::doubleValue).toArray();
        return sd.evaluate(answersArray);
    }

    public Integer getMinScoreForSpecificQuestion(String surveyName, String question) {
        List<Integer> answers = getSortedQuestionScores(surveyName, question);
        return answers.get(0);
    }

    public Integer getMaxScoreForSpecificQuestion(String surveyName, String question) {
        List<Integer> answers = getSortedQuestionScores(surveyName, question);
        return answers.get(answers.size() - 1);
    }

    private List<Integer> getSortedQuestionScores(String surveyName, String question) {
        getSurveyByName(surveyName);  //Calling method to ensure survey exists.
        List<SurveyResponse> responses = getAllSurveyResponsesForSurvey(surveyName);
        List<Integer> answers = new ArrayList<>();
        boolean found = false;

        for (SurveyResponse surveyResponse : responses) {
            for (Map.Entry<String, Integer> entry : surveyResponse.getResponses().entrySet()) {
                if (entry.getKey().equalsIgnoreCase(question)) {
                    answers.add(Integer.valueOf(entry.getValue()));
                    found = true;
                }
            }
        }

        if (!found) {
            throw new QuestionNotFoundException();
        }

        Collections.sort(answers);
        return answers;
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
