# Swagger petstore API Automation Solution

## Introduction

This is a Rest API test solution for endpoints available in https://petstore.swagger.io/. The published APIs represent a sample server petstore application.

Tests are written using a combination of SerenityBDD, RestAssured, Cucumber, Junit & Maven.

### The project directory structure

```Gherkin
src
  + test
    + java                          Test runners and supporting code
      + generic                     Generic code like constants
      + models                      Models (Like pet model)
      + stepdefinitions             Step definitions for the BDD feature
    + resources
      + features                    Feature files directory
          pet.feature               Feature containing BDD scenarios
          store.feature             Feature containing BDD scenarios
          user.feature             Feature containing BDD scenarios
      Serenity.conf                 Configurations file

```
### Install and executing the tests

Run `mvn clean verify` from the command line.

The test results will be recorded in folder: `target/site/serenity/index.html`.

### Write new tests

##### Testrunner
To make the tests running, a testrunner is needed. It is the starting point for Junit to start executing de tests. 
In the project it is defined in the file: CucumberTestSuite.java

```Gherkin
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    plugin = {"pretty"},
    features = "src/test/resources/features"
)
public class CucumberTestSuite {

}
```
More about test runners: https://www.toolsqa.com/cucumber/junit-test-runner-class/

##### Feature files
- Make a new .feature file in the folder: resources/features
  Define the test cases in the Given, When, Then format
  
  ```Gherkin
  Scenario: test scenario
    (Given) some context
    (When) some action is carried out
    (Then) a particular set of observable consequences should obtain
  ```
  Using tables in the feature file is a nice solution to store data used for the tests.
  It is also easy to make new test cases simply by adding a new row

  ```Gherkin 
  Scenario Outline: Add a new pet to the store
    Given having a new pet defined with id <id> name <name> and status <status>
    Examples:
    | id   | name  | status   |
    | 100  | Vido  | available|
  ```
##### Stepdefinitions
- Make a new .java file in the folder: stepdefinitions.
  Define the test scenario steps from the feature file to 'glue' them with the actual code

  ```Gherkin
  @Given("^having a new pet defined with id (.*) name (.*) and status (.*)$") 
    public void function(int id, String name, String status) {
      code;
    }
  ```

  When using tables in the feature file, make sure using the ^ and $ in the @Given @When and @Then definitions.
  The (.*) is used as a placeholder for the variables coming from the feature file.
  Make sure to give the exact amount of variables as arguments in the function

  More info about making testcases with cucumber: https://cucumber.io/

### Endpoint overview 

Swagger overview of endpoints: https://petstore.swagger.io/

Endpoints:
- Everything about pets: https://petstore.swagger.io/v2/pet
- Access to Petstore orders: https://petstore.swagger.io/v2/store
- Operations about user: https://petstore.swagger.io/v2/user

For a more detailed overview please visit the swagger overview or take a look at the Cucumber feature files in this project

### CI Pipeline GitLab

All tests will be running automaticly when pushing the changed functionality to GitLab (repo).
How to do this:

1) Make a GitLab account and started a new project https://gitlab.com/vndale/leaseplanopdrachtbe.git
2) Make a SSH key and put it in the Gitlab Project settings https://docs.gitlab.com/ee/ssh/
3) Install a Gitlab Runner to run jobs in the pipeline: 
      Info about Gitlab runners: https://docs.gitlab.com/runner/
      Install Gitlab Runner: https://docs.gitlab.com/runner/install/index.html
4) Write a (simple) yml file for the CI configuration and put it in the root of the repo.
      Info about YML files: https://docs.gitlab.com/ee/ci/yaml/
Example yml file:
```Gherkin
demo_job_1:
     tags:
       - ci
     script:
       - echo Hello Leaseplan
       - mvn clean verify
```
5) To bring changes to the Gitlab repo:
```Gherkin
      Command line:
      git status (to check if there are changes and which branch you are working)
      git add .
      git commit -m "commit message"
      git push -u origin master (when this is not working try: git push -u <url-to-gitlab-repo> master)

      Now in the section 'CI / CD > Pipelines' of your project in Gitlab the build en tests are running
```

```
