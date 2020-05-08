# Documentation

## Sprint Backlog

| No   | Branch Name | Title         | Description  | Estimate| Comment |
| ---- | ----|:-------------| :------------| :---| :-----   |     
| 1 |initial_documentation| Initial documentation| Detail the documentation by explaining the sprint backlog, TDD, Test Coverage Metric, Team Version Control and Code Reviews | 2 |
| 2|base_model| Create base model |   Create entities (Survey, Questions and SurveyResponse) and Controller class. Add test class for the Controller class | 2 | 
| 3|survey_features| Create Survey components/features | Create a survey, add a question to a survey, get a list of all surveys, get a survey by name + associated tests| 3 |
| 4| survey_response_features| Create Survey Response components/features | Create a survey response, add an answer to a survey response, get all responses for a specific survey + associated tests| 3 |
| 5|survey_calculation| Summary calculation for a survey| Get the average, standard deviation, minimum and maximum score + associated tests| 3 |
| 6|survey_question_calculation| Summary calculation for a question on a survey| Get the average, standard deviation, minimum and maximum score + associated tests| 3 |

##### How these estimates were decicded? 
We use the Fibonacci sequence to assign a point value to a feature. It is important to say that these numbers represent relative size, not time.

##### How velocity is calculated?

## Test Driven Development 

## Test Coverage Metric 
A code coverage tool should provide the means not only to measure code coverage, but also to enforce it.
JaCoCo is currently the most actively maintained and sophisticated code coverage measurement tool for the Java ecosystem.

##### How to run JaCoCo  
Inside *build.gradle*, apply the jacoco plugin  
<code>apply plugin: 'jacoco'</code>



## Team Version Control

##### Branching Naming Convention:
- Feature:  
Example: *feature/initial_documentation*
- Bug Fix:    
Example: *bugfix/branch_name_x* 
- Hot Fix:  
Example: *hotfix/branch_name_x*

## Code Reviews 

## References
- https://reflectoring.io/jacoco/