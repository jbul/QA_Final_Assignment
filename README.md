# Documentation

## Sprint Backlog

| No   | Branch Name | Title         | Description  | Estimate| Comment |
| ---- | ----|:-------------| :------------| :---| :-----   |     
| 1 |initial_documentation| Initial documentation| Detail the documentation by explaining the sprint backlog, TDD, Test Coverage Metric, Team Version Control and Code Reviews | 2 |
| 2|base_model| Create base model |   Create entities (Survey, Questions and SurveyResponse) and Controller class. Add test class for the Controller class | 2 | 
| 3|survey_features| Create Survey components/features | Create a survey, add a question to a survey, get a list of all surveys, get a survey by name + associated tests| 3 |
| 4| survey_response_features| Create Survey Response components/features | Create a survey response, add an answer to a survey response, get all responses for a specific survey + associated tests| 3 |
| 5|survey_calculation| Summary calculation for a survey| Get the average, standard deviation, minimum and maximum score + associated tests| 5 |
| 6|survey_question_calculation| Summary calculation for a question on a survey| Get the average, standard deviation, minimum and maximum score + associated tests| 5 |

##### How these estimates were decicded? 
We use the Fibonacci sequence to assign a point value to a feature. It is important to say that these numbers represent relative size, not time.  
Estimates were  based on 3 criteria: technical complexity, decision-making, quantity of work. 
- Task 1 - As this task is not a development task, there is no technical complexity. However, the task requires an investigation work that implies some decision making.
- Task 2 - This task does not involve any calculation or business logic. As a result, we can consider it a low technical complexity. However, there is a need to decide on the architecture, which involves some decision making. There are not many classes, as a result, we can tell it is low quantity of work.
- Task 3 / 4 - Those tasks involve some business logic. Which can decently be complicated. The amount of work is average and we do not expect much decision making as everything is documented in the subject.
- Task 5 - This task involves some calculation, which may require some extra testing and ensuring it does work correctly. Which qualifies it to be an important complexity task. Although, it involves low decision making as the calculation method is known. The amount of work should be low as it does not require to touch a lot of different areas.
- Task 6 - Similar to task no 5. However, we do note an extra complexity in order to retrieve the question from all the SurveyResponse.

##### How velocity is calculated?
Velocity is the measure of work completed by the development team within each sprint. Velocity is calculated at the end of the Sprint by summing up the points for all fully completed User Stories. 

## Test Driven Development 

## Test Coverage Metric 
A code coverage tool should provide the means not only to measure code coverage, but also to enforce it.
JaCoCo is currently the most actively maintained and sophisticated code coverage measurement tool for the Java ecosystem.

##### How to run JaCoCo  
Inside *build.gradle*, apply the jacoco plugin  
<code>apply plugin: 'jacoco'</code>



## Team Version Control

##### Branching Naming Convention
- Feature:  
Example: *feature/initial_documentation*
- Bug Fix:    
Example: *bugfix/branch_name_x* 
- Hot Fix:  
Example: *hotfix/branch_name_x*

##### Branches used 
- *feature/initial_documentation* 

## Code Review Checklist
- Are unit tests and integrations are passing?
- Does this Pull Request do what it is supposed to do? 
- Is the code readable/clear?
- Can the code be shorter/simpler? 
- Are changes covered by test? 
- Is the same code duplicated more than twice?
- Is this function or class too big? If yes, is the function or class having too many responsibilities?
- Are there any best practices, design patterns or language-specific patterns that could substantially improve this code?
- Is the code documented enough?

## References
- https://reflectoring.io/jacoco/
- https://medium.com/@taingmeng/what-you-should-look-out-for-when-you-review-pull-request-3f2d95a50ba9 