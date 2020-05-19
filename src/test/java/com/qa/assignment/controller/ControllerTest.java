package com.qa.assignment.controller;

import com.qa.assignment.exception.*;
import com.qa.assignment.model.Question;
import com.qa.assignment.model.Survey;
import com.qa.assignment.model.SurveyResponse;
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
    public void createSurveyTest() {
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
    public void duplicateSurveyNameTest() {
        Controller tester = new Controller();
        String expectedName = "testSurvey";
        tester.createSurvey(expectedName);

        tester.createSurvey(expectedName);

    }

    /**
     * Verify an exception is thrown in a attempt to create a survey with an empty name
     */
    @Test(expected = SurveyCreateException.class)
    public void emptySurveyNameTest() {
        Controller tester = new Controller();
        String expectedName = "";

        tester.createSurvey(expectedName);

        tester.createSurvey(expectedName);

    }

    /**
     * Verify an exception in thrown in a attempt to create a survey with a null value as name
     */
    @Test(expected = SurveyCreateException.class)
    public void surveyNameNullTest() {
        Controller tester = new Controller();
        String expectedName = null;

        tester.createSurvey(expectedName);

        tester.createSurvey(expectedName);
    }


    /**
     * Checks the question is added to the survey from survey name
     */
    @Test
    public void addQuestionTest() {
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
    public void addMoreThan10QuestionsToASurveyTest() {
        Controller tester = new Controller();

        Survey survey = new Survey("TestSurvey");
        tester.setSurveys(Arrays.asList(survey));

        List<Question> questions = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            questions.add(new Question("Question" + i));
        }

        survey.setQuestions(questions);
        tester.addQuestion("TestSurvey", "new question");

    }

    /**
     * Verify an exception is thrown in attempt to add an empty question to a survey
     */
    @Test(expected = QuestionCreateException.class)
    public void questionIsEmtpyTest() {
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
    public void questionIsNullTest() {
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
    public void addDuplicateQuestionTest() {
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
    public void SurveyNotFoundTest() {
        Controller tester = new Controller();
        tester.getSurveyByName("Survey testt");
    }


    /**
     * Check it is possible to retrieve a survey from the name given when created
     */
    @Test
    public void getSurveyByNameTest() {
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
    public void getAllSurveysTest() {
        Controller tester = new Controller();

        for (int i = 1; i < 4; i++) {
            tester.createSurvey("Survey" + i);
        }

        Assert.assertEquals(3, tester.getSurveys().size());

        for (int i = 1; i < 4; i++) {
            Assert.assertEquals("Survey" + i, tester.getSurveys().get(i - 1).getSurveyName());
        }
    }

    /**
     * Ensure the list of survey responses is increased by one when the method create surveyResponse is called
     */
    @Test
    public void createSurveyResponseTest() {
        Controller tester = new Controller();
        String name = "Julie SurveyResponse";
        String surveyName = "Survey Test";
        tester.createSurvey(surveyName);

        int initialSize = tester.getSurveyResponses().size();

        tester.createSurveyResponse(name, surveyName);

        Assert.assertEquals(1, tester.getSurveyResponses().size() - initialSize);
        Assert.assertEquals(name, tester.getSurveyResponses().get(tester.getSurveyResponses().size() - 1).getName());
    }

    /**
     * Verify an exception is thrown in a attempt to create a surveyResponse when the survey name is not found
     */
    @Test(expected = SurveyNotFoundException.class)
    public void createSurveyResponseSurveyNameNotFoundTest() {
        Controller tester = new Controller();
        String name = "Julie SurveyResponse";
        String surveyName = "Survey Test";
        tester.createSurvey(surveyName);

        tester.createSurveyResponse(name, "Survey Testt");
    }

    /**
     * Verify an exception is thrown in a attempt to create a surveyResponse with a surveyResponse name already existing
     */
    @Test(expected = SurveyResponseCreateException.class)
    public void createDuplicateSurveyResponseNameTest() {
        Controller tester = new Controller();
        String name = "Julie SurveyResponse";
        String surveyName = "Survey Test";

        tester.createSurvey(surveyName);
        tester.createSurveyResponse(name, surveyName);
        tester.createSurveyResponse(name, surveyName);
    }

    /**
     * Verify an exception is thrown in a attempt to create a surveyResponse with an empty name
     */
    @Test(expected = SurveyResponseCreateException.class)
    public void createSurveyResponseWithEmptyNameTest() {
        Controller tester = new Controller();
        String name = "";
        String surveyName = "Survey Test";

        tester.createSurvey(surveyName);
        tester.createSurveyResponse(name, surveyName);

    }

    /**
     * Verify an exception is thrown in a attempt to create a surveyResponse with a null value as name
     */
    @Test(expected = SurveyResponseCreateException.class)
    public void createSurveyResponseWithNullValueNameTest() {
        Controller tester = new Controller();
        String name = null;
        String surveyName = "Survey Test";
        tester.createSurvey(surveyName);
        tester.createSurveyResponse(name, surveyName);

    }

    /**
     * Check that the answer is added to the surveyResponse
     */
    @Test
    public void addAnswerToSurveyResponseTest() {
        Controller tester = new Controller();
        String name = "Julie SurveyResponse";
        String surveyName = "Survey Test";
        String expectedQuestion = "Test Question";
        Integer answer = 3;

        tester.createSurvey(surveyName);
        tester.addQuestion(surveyName, expectedQuestion);
        tester.createSurveyResponse(name, surveyName);
        tester.addAnswerToSurveyResponse(answer, name, surveyName, expectedQuestion);

        Assert.assertEquals(answer, tester.getSurveyResponses().get(0).getResponses().get(expectedQuestion));
    }

    /**
     * Verify an exception is thrown in a attempt to add a question to a surveyResponse, when the question is not found.
     */
    @Test(expected = QuestionNotFoundException.class)
    public void addAnswerToSurveyResponseQuestionNotFoundTest() {
        Controller tester = new Controller();
        String name = "Julie SurveyResponse";
        String surveyName = "Survey Test";
        String expectedQuestion = "Test Question";
        Integer answer = 3;

        tester.createSurvey(surveyName);
        tester.addQuestion(surveyName, expectedQuestion);
        tester.createSurveyResponse(name, surveyName);
        tester.addAnswerToSurveyResponse(answer, name, surveyName, "Another question");
    }


    /**
     * Verify an exception is thrown in a attempt to add a question to a surveyResponse, when the SurveyResponse is not found.
     */
    @Test(expected = SurveyResponseNotFound.class)
    public void addAnswerToSurveyResponseNotFoundTest() {
        Controller tester = new Controller();
        String name = "Julie SurveyResponse";
        String surveyName = "Survey Test";
        String expectedQuestion = "Test Question";
        Integer answer = 3;

        tester.createSurvey(surveyName);
        tester.addQuestion(surveyName, expectedQuestion);
        tester.createSurveyResponse(name, surveyName);
        tester.addAnswerToSurveyResponse(answer, "Julia SurveyResponse", surveyName, expectedQuestion);
    }

    /**
     * Verify an exception is thrown in a attempt to add a question to a surveyResponse, when the answer is not between 1-5
     */
    @Test(expected = InvalidAnswerException.class)
    public void addInvalidAnswerToSurveyResponseTest() {
        Controller tester = new Controller();
        String name = "Julie SurveyResponse";
        String surveyName = "Survey Test";
        String expectedQuestion = "Test Question";
        Integer answer = 6;

        tester.createSurvey(surveyName);
        tester.addQuestion(surveyName, expectedQuestion);
        tester.createSurveyResponse(name, surveyName);
        tester.addAnswerToSurveyResponse(answer, name, surveyName, expectedQuestion);
    }


    /**
     * Check that it is returning all surveys for a specific survey.
     */
    @Test
    public void getAllSurveyResponsesForSurvey() {
        Controller tester = new Controller();
        String name = "Julie SurveyResponse";
        String name2 = "Joe SurveyReponse";
        String surveyName = "Survey Test";
        String expectedQuestion = "Test Question";
        String question2 = "Test Question2";
        Integer answer = 4;
        Integer answer2 = 3;

        tester.createSurvey(surveyName);
        tester.addQuestion(surveyName, expectedQuestion);
        tester.addQuestion(surveyName, question2);
        tester.createSurveyResponse(name, surveyName);
        tester.createSurveyResponse(name2, surveyName);
        tester.addAnswerToSurveyResponse(answer, name, surveyName, expectedQuestion);
        tester.addAnswerToSurveyResponse(answer2, name2, surveyName, question2);
        List<SurveyResponse> responses = tester.getAllSurveyResponsesForSurvey(surveyName);

        for (SurveyResponse surveyResponse : responses) {
            Assert.assertEquals(surveyName, surveyResponse.getSurvey().getSurveyName());
        }
    }


    /**
     * Verify an exception is thrown in a attempt to get all surveyResponses when the Survey name is not found.
     */
    @Test(expected = SurveyNotFoundException.class)
    public void getAllSurveyResponsesForSurveyNotFoundTest() {
        Controller tester = new Controller();
        String name = "Julie SurveyResponse";
        String surveyName = "Survey Test";
        String expectedQuestion = "Test Question";
        Integer answer = 4;

        tester.createSurvey(surveyName);
        tester.addQuestion(surveyName, expectedQuestion);
        tester.createSurveyResponse(name, surveyName);
        tester.addAnswerToSurveyResponse(answer, name, surveyName,expectedQuestion);
        tester.getAllSurveyResponsesForSurvey("New survey");
    }

    /**
     * Check that the average value is correct for a given survey name
     */
    @Test
    public void getSurveyAverageTest(){
        Controller tester = new Controller();
        String name = "Julie SurveyResponse";
        String name2 = "Joe SurveyReponse";
        String surveyName = "Survey Test";
        String expectedQuestion = "Test Question";
        String question2 = "Test Question2";
        Integer answer = 4;
        Integer answer2 = 2;

        tester.createSurvey(surveyName);
        tester.addQuestion(surveyName, expectedQuestion);
        tester.addQuestion(surveyName, question2);
        tester.createSurveyResponse(name, surveyName);
        tester.createSurveyResponse(name2, surveyName);
        tester.addAnswerToSurveyResponse(answer, name, surveyName, expectedQuestion);
        tester.addAnswerToSurveyResponse(answer2, name2, surveyName, question2);
        Assert.assertEquals(Double.valueOf(3.0), tester.getSurveyAverage(surveyName));

    }

    /**
     * Verify an exception is thrown in a attempt to get the survey average when the survey name is not found
     */
    @Test(expected = SurveyNotFoundException.class)
    public void getSurveyAverageSurveyNotFoundTest(){
        Controller tester = new Controller();
        String name = "Julie SurveyResponse";
        String name2 = "Joe SurveyReponse";
        String surveyName = "Survey Test";
        String expectedQuestion = "Test Question";
        String question2 = "Test Question2";
        Integer answer = 4;
        Integer answer2 = 2;

        tester.createSurvey(surveyName);
        tester.addQuestion(surveyName, expectedQuestion);
        tester.addQuestion(surveyName, question2);
        tester.createSurveyResponse(name, surveyName);
        tester.createSurveyResponse(name2, surveyName);
        tester.addAnswerToSurveyResponse(answer, name, surveyName, expectedQuestion);
        tester.addAnswerToSurveyResponse(answer2, name2, surveyName, question2);
        tester.getSurveyAverage("Survey tests");
    }

    /**
     * Check that the standard deviation value is correct for a given survey name
     */
    @Test
    public void getSurveyStandardDeviation(){
        Controller tester = new Controller();
        String name = "Julie SurveyResponse";
        String name2 = "Joe SurveyReponse";
        String surveyName = "Survey Test";
        String expectedQuestion = "Test Question";
        String question2 = "Test Question2";
        Integer answer = 4;
        Integer answer2 = 2;

        tester.createSurvey(surveyName);
        tester.addQuestion(surveyName, expectedQuestion);
        tester.addQuestion(surveyName, question2);
        tester.createSurveyResponse(name, surveyName);
        tester.createSurveyResponse(name2, surveyName);
        tester.addAnswerToSurveyResponse(answer, name, surveyName, expectedQuestion);
        tester.addAnswerToSurveyResponse(answer2, name2, surveyName, question2);
        Assert.assertEquals(Double.valueOf(1.0), tester.getSurveyStandardDeviation(surveyName));
    }


    /**
     * Verify an exception is thrown in a attempt to get the survey standard deviation when the survey name is not found
     */
    @Test(expected = SurveyNotFoundException.class)
    public void getSurveyStandardDeviationSurveyNotFoundTest(){
        Controller tester = new Controller();
        String name = "Julie SurveyResponse";
        String name2 = "Joe SurveyReponse";
        String surveyName = "Survey Test";
        String expectedQuestion = "Test Question";
        String question2 = "Test Question2";
        Integer answer = 4;
        Integer answer2 = 2;

        tester.createSurvey(surveyName);
        tester.addQuestion(surveyName, expectedQuestion);
        tester.addQuestion(surveyName, question2);
        tester.createSurveyResponse(name, surveyName);
        tester.createSurveyResponse(name2, surveyName);
        tester.addAnswerToSurveyResponse(answer, name, surveyName, expectedQuestion);
        tester.addAnswerToSurveyResponse(answer2, name2, surveyName, question2);
        tester.getSurveyStandardDeviation("Survey tests");
    }


    /**
     * Check that the minimum score value is correct for a given survey name
     */
    @Test
    public void getSurveyMinScoreTest(){
        Controller tester = new Controller();
        String name = "Julie SurveyResponse";
        String name2 = "Joe SurveyReponse";
        String surveyName = "Survey Test";
        String expectedQuestion = "Test Question";
        String question2 = "Test Question2";
        Integer answer = 4;
        Integer answer2 = 2;

        tester.createSurvey(surveyName);
        tester.addQuestion(surveyName, expectedQuestion);
        tester.addQuestion(surveyName, question2);
        tester.createSurveyResponse(name, surveyName);
        tester.createSurveyResponse(name2, surveyName);
        tester.addAnswerToSurveyResponse(answer, name, surveyName, expectedQuestion);
        tester.addAnswerToSurveyResponse(answer2, name2, surveyName, question2);
        Assert.assertEquals(Integer.valueOf(2), tester.getSurveyMinScore(surveyName));
    }

    /**
     * Verify an exception is thrown in a attempt to get the survey minimum score when the survey name is not found
     */
    @Test(expected = SurveyNotFoundException.class)
    public void getSurveyMinScoreSurveyNotFoundTest() {
        Controller tester = new Controller();
        String name = "Julie SurveyResponse";
        String name2 = "Joe SurveyReponse";
        String surveyName = "Survey Test";
        String expectedQuestion = "Test Question";
        String question2 = "Test Question2";
        Integer answer = 4;
        Integer answer2 = 2;

        tester.createSurvey(surveyName);
        tester.addQuestion(surveyName, expectedQuestion);
        tester.addQuestion(surveyName, question2);
        tester.createSurveyResponse(name, surveyName);
        tester.createSurveyResponse(name2, surveyName);
        tester.addAnswerToSurveyResponse(answer, name, surveyName, expectedQuestion);
        tester.addAnswerToSurveyResponse(answer2, name2, surveyName, question2);
        tester.getSurveyMinScore("Survey tests");
    }

    /**
     * Check that the maximum score value is correct for a given survey name
     */
    @Test
    public void getSurveyMaxScoreTest(){
        Controller tester = new Controller();
        String name = "Julie SurveyResponse";
        String name2 = "Joe SurveyReponse";
        String surveyName = "Survey Test";
        String expectedQuestion = "Test Question";
        String question2 = "Test Question2";
        Integer answer = 4;
        Integer answer2 = 2;

        tester.createSurvey(surveyName);
        tester.addQuestion(surveyName, expectedQuestion);
        tester.addQuestion(surveyName, question2);
        tester.createSurveyResponse(name, surveyName);
        tester.createSurveyResponse(name2, surveyName);
        tester.addAnswerToSurveyResponse(answer, name, surveyName, expectedQuestion);
        tester.addAnswerToSurveyResponse(answer2, name2, surveyName, question2);
        Assert.assertEquals(Integer.valueOf(4), tester.getSurveyMaxScore(surveyName));
    }

    /**
     * Verify an exception is thrown in a attempt to get the survey maximum score when the survey name is not found
     */
    @Test(expected = SurveyNotFoundException.class)
    public void getSurveyMaxScoreSurveyNotFoundTest() {
        Controller tester = new Controller();
        String name = "Julie SurveyResponse";
        String name2 = "Joe SurveyReponse";
        String surveyName = "Survey Test";
        String expectedQuestion = "Test Question";
        String question2 = "Test Question2";
        Integer answer = 4;
        Integer answer2 = 2;

        tester.createSurvey(surveyName);
        tester.addQuestion(surveyName, expectedQuestion);
        tester.addQuestion(surveyName, question2);
        tester.createSurveyResponse(name, surveyName);
        tester.createSurveyResponse(name2, surveyName);
        tester.addAnswerToSurveyResponse(answer, name, surveyName, expectedQuestion);
        tester.addAnswerToSurveyResponse(answer2, name2, surveyName, question2);
        tester.getSurveyMaxScore("Survey tests");
    }
}
