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
        Survey survey = new Survey("TestSurvey");
        tester.setSurveys(Arrays.asList(survey));

        String nullQuestion = "";
        tester.setSurveys(Arrays.asList(survey));

        tester.addQuestion("TestSurvey", nullQuestion);

    }


    /**
     * Verify an exception is thrown in attempt to add a duplicate question to a survey
     */
    @Test(expected = QuestionCreateException.class)
    public void addDuplicateQuestionTest(){
        Controller tester = new Controller();
        Survey survey = new Survey("TestSurvey");
        tester.setSurveys(Arrays.asList(survey));

        String questionTest = "Test Question";
        tester.setSurveys(Arrays.asList(survey));
        tester.addQuestion("TestSurvey", questionTest);

        tester.addQuestion("TestSurvey", questionTest);
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
        Survey survey = new Survey("TestSurvey");
        Controller tester = new Controller();
        String surveyName = "TestSurvey";
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
        tester.createSurvey("Survey1");
        tester.createSurvey("Survey2");
        tester.createSurvey("Survey3");

        Assert.assertEquals(3, tester.getSurveys().size());
        Assert.assertEquals("Survey1", tester.getSurveys().get(0).getSurveyName());
        Assert.assertEquals("Survey2", tester.getSurveys().get(1).getSurveyName());
        Assert.assertEquals("Survey3", tester.getSurveys().get(2).getSurveyName());

    }
}
