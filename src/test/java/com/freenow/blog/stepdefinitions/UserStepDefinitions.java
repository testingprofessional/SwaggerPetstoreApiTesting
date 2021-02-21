package com.freenow.blog.stepdefinitions;

import com.freenow.blog.models.User;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class UserStepDefinitions {

    @Steps
    UserSteps userSteps;

    User generatedUser;
    User generatedUserUpdating;
    String jsonUserUpdating;
    String jsonUser;
    String usernameDeleted;

    //Create user
    @Given("^an user with name (.*)$")
    public void anUserWithAName(String username) {
        generatedUser = new User(username);
        jsonUser = userSteps.generateUser(generatedUser.getId(),
                                          generatedUser.getUsername(), 
                                          generatedUser.getFirstname(), 
                                          generatedUser.getLastname(), 
                                          generatedUser.getEmail(), 
                                          generatedUser.getPassword(), 
                                          generatedUser.getPhone(),
                                          generatedUser.getUserStatus());
    }

    @When("creating the user")
    public void createNewUser() {
        userSteps.addUserToPetStore(jsonUser);
    }

    @Then("the new user is stored in the petstore") 
    public void checkUserInPetStore() {
        userSteps.theUserShouldBeAvailable(generatedUser.getUsername(), generatedUser.getPhone());
    }

    //Updated user
    @When("^updating the user (.*) with phonenumber (.*)$")
    public void updateUserWithNewPhone(String userName, String updatePhone) {
        generatedUserUpdating = new User(userName);
        generatedUserUpdating.setPhone(updatePhone);
        jsonUserUpdating = userSteps.generateUser(generatedUserUpdating.getId(),
                                                  generatedUserUpdating.getUsername(), 
                                                  generatedUserUpdating.getFirstname(), 
                                                  generatedUserUpdating.getLastname(), 
                                                  generatedUserUpdating.getEmail(), 
                                                  generatedUserUpdating.getPassword(), 
                                                  generatedUserUpdating.getPhone(),
                                                  generatedUserUpdating.getUserStatus());

        userSteps.updateUserInThePetStore(jsonUserUpdating, generatedUserUpdating.getUsername());
    }

    @Then("the user is updated with the new phonenumber")
    public void userIsUpdatedWithPhonenumber() {
        userSteps.theUserShouldBeAvailable(generatedUserUpdating.getUsername(), generatedUserUpdating.getPhone());
    }

    //Delete user
    @When("^deleting the user (.*)$")
    public void deleteUser(String username) {
        usernameDeleted = username;
        userSteps.deleteUser(usernameDeleted);
    }
    
    @Then("the user is not longer in the store")
    public void checkDeletedUser() {
        userSteps.theUserIsNotAvailable(usernameDeleted);
    }
}

