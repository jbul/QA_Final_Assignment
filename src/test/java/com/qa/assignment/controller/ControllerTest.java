package com.qa.assignment.controller;

import com.qa.assignment.exception.QuestionCreateException;
import com.qa.assignment.exception.QuestionsLimitReachedException;
import com.qa.assignment.exception.SurveyCreateException;
import com.qa.assignment.exception.SurveyNotFoundException;
import com.qa.assignment.model.Question;
import com.qa.assignment.model.Survey;
import org.junit.Assert;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControllerTest {

    /**
     * Ensure the list of survey is increased by one when the method createSurvey is called
     */
    @Test
    public void createSurveyTest(){
        // Initialise
        Controller tester = new Controller();
        String expectedName = "testSurvey";
        int initialSize = tester.getSurveys().size();

        // Perform
        tester.createSurvey(expectedName);

        // Asserts
        Assert.assertEquals(1, tester.getSurveys().size() - initialSize);
        Assert.assertEquals(expectedName, tester.getSurveys().get(tester.getSurveys().size() - 1).getSurveyName());
    }


    /**
     * Verify an exception is thrown in a attempt to create a survey with a name already existing.
     */
    @Test(expected = SurveyCreateException.class)
    public void duplicateSurveyNameTest(){
        Controller tester = new Controller();
        String expectedName = "testSurvey";
        tester.createSurvey(expectedName);

        tester.createSurvey(expectedName);

    }

    /**
     * Verify an exception is thrown in a attempt to create a survey with an empty name
     */
    @Test(expected = SurveyCreateException.class)
    public void emptySurveyNameTest(){
        Controller tester = new Controller();
        String expectedName = "";

        tester.createSurvey(expectedName);

        tester.createSurvey(expectedName);

    }

    /**
     * Verify an exception in thrown in a attempt to create a survey with a null value as name
     */
    @Test(expected = SurveyCreateException.class)
    public void surveyNameNullTest(){
        Controller tester = new Controller();
        String expectedName = null;

        tester.createSurvey(expectedName);

        tester.createSurvey(expectedName);
    }


    /**
     * Checks the question is added to the survey from survey name
     */
    @Test
    public void addQuestionTest(){
        Survey survey = new Survey("TestSurvey");
        Controller tester = new Controller();
        String expectedQuestion = "Test Question";


        tester.setSurveys(Arrays.asList(survey));


        tester.addQuestion(survey.getSurveyName(), expectedQuestion);
        Assert.assertEquals(expectedQuestion, survey.getQuestions().get(0).getQuestion());
    }

    /**
     * Verify an exception is thrown in attempt to add more than 10 questions to a survey
     */
    @Test(expected = QuestionsLimitReachedException.class)
    public void addMoreThan10QuestionsToASurveyTest(){
        Controller tester = new Controller();

        Survey survey = new Survey("TestSurvey");
        tester.setSurveys(Arrays.asList(survey));

        List<Question> questions = new ArrayList<>();

        for (int i = 0; i< 10; i++) {
            questions.add(new Question("Question" + i));
        }

        survey.setQuestions(questions);
        tester.addQuestion("TestSurvey", "new question");

    }

    /**
     * Verify an exception is thrown in attempt to add an empty question to a survey
     */
    @Test(expected = QuestionCreateException.class)
    public void questionIsEmtpyTest(){
        Controller tester = new Controller();
        Survey survey = new Survey("TestSurvey");
        tester.setSurveys(Arrays.asList(survey));

        String emptyQuestion = "";
        tester.setSurveys(Arrays.asList(survey));

        tester.addQuestion("TestSurvey", emptyQuestion);
    }

    /**
     * Verify an exception is thrown in attempt to add a question with a null value to a survey
     */
    @Test(expected = QuestionCreateException.class)
    public void questionIsNullTest(){
        Controller tester = new Controller();
        String surveyName = "TestSurvey";
        Survey survey = new Survey(surveyName);
        tester.setSurveys(Arrays.asList(survey));

        String nullQuestion = "";
        tester.setSurveys(Arrays.asList(survey));

        tester.addQuestion(surveyName, nullQuestion);

    }


    /**
     * Verify an exception is thrown in attempt to add a duplicate question to a survey
     */
    @Test(expected = QuestionCreateException.class)
    public void addDuplicateQuestionTest(){
        Controller tester = new Controller();
        String surveyName = "TestSurvey";
        Survey survey = new Survey(surveyName);
        tester.setSurveys(Arrays.asList(survey));

        String questionTest = "Test Question";
        tester.setSurveys(Arrays.asList(survey));
        tester.addQuestion(surveyName, questionTest);

        tester.addQuestion(surveyName, questionTest);
    }

    /**
     * Verify an exception is thrown when a survey is not found.
     */
    @Test(expected = SurveyNotFoundException.class)
    public void SurveyNotFoundTest(){
        Controller tester = new Controller();
        tester.getSurveyByName("Survey testt");
    }


    /**
     * Check it is possible to retrieve a survey from the name given when created
     */
    @Test
    public void getSurveyByNameTest(){
        String surveyName = "TestSurvey";
        Survey survey = new Survey(surveyName);
        Controller tester = new Controller();
        tester.setSurveys(Arrays.asList(survey));

        Survey resultSurvey = tester.getSurveyByName(surveyName);
        Assert.assertEquals(survey, resultSurvey);
    }

    /**
     * Check it is possible to retrieve all the surveys
     */
    @Test
    public void getAllSurveysTest(){
        Controller tester = new Controller();

        for(int i = 1; i < 4; i++){
            tester.createSurvey("Survey" + i);
        }

        Assert.assertEquals(3, tester.getSurveys().size());

        for(int i = 1; i < 4; i++){
            Assert.assertEquals("Survey" + i, tester.getSurveys().get(i-1).getSurveyName());
        }


    }
}
