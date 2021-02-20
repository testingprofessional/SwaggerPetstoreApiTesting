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
          order.feature             Feature containing BDD scenarios
      + schema                      Folder containing json schema for API schema validation
      Serenity.conf                 Configurations file

```
### Install and executing the tests

Run `mvn clean verify` from the command line.

The test results will be recorded here `target/site/serenity/index.html`.

#### Write new tests

- Make a new .feature file in the folder: resources/features
  Define the test cases in the Given, When, Then format
  
  ```Gherkin
  Scenario: test scenario
    (Given) some context
    (When) some action is carried out
    (Then) a particular set of observable consequences should obtain
  ```
  
- Make a new .java file in the folder: stepdefinitions
  Define the test scenario steps from the feature file to 'glue' them with the actual code

  @Given("^having a new pet defined with id (.*) name (.*) and status (.*)$") 
    public void function(int id, String name, String status) {
      code;
    }

  When using tables in the feature file, make sure using the ^ and $ in the @Given @When and @Then definitions
  The (.*) is used as a placeholder for the variables coming from the feature file
  Make sure to give the exact amount of variables as arguments in the function

```
