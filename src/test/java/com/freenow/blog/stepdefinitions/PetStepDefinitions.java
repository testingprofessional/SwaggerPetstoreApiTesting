package com.freenow.blog.stepdefinitions;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.Matchers.*;

import java.util.Collections;

import com.freenow.blog.models.Category;
import com.freenow.blog.models.Pet;
import com.freenow.blog.models.Status;
import com.freenow.blog.models.Tag;
import com.freenow.blog.models.*;
import com.freenow.blog.generic.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.BeforeClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import net.thucydides.core.annotations.Steps;

import static net.serenitybdd.rest.SerenityRest.rest;

import static io.restassured.RestAssured.given;

public class PetStepDefinitions {

    @Steps
    PetSteps petSteps;

    Pet generatedPet;
    Pet generatedPetUpdating;
    int idJson;
    String jsonPet;
    Constants constants;
    int idDeletedPet;

    //Add a new pet to the store
    @Given("^having a new pet defined with id (.*) name (.*) and status (.*)$")
    public void havingANewPetDefined(int id, String name, String status) {

        generatedPet = new Pet(status, name);
        idJson = id;
        jsonPet = "{\"id\": " + idJson + " , \"name\": \""
                + generatedPet.getName() + "\", \"photoUrls\": [], \"status\": \""
                + generatedPet.getStatus() + "\"}";

        this.generatedPet.setId(idJson);
    }

    @When("adding the pet to the petstore")
    public void addingPetToPetstore() {
         rest().given().contentType("application/json")
                 .body(jsonPet).post(Constants.PET_ENDPOINT);
    }

    @Then("expecting the pet to be added in the petstore")
    public void expectThePetToBeAddedInThePetstore() {
        petSteps.the_pet_should_be_available(generatedPet);
    }

    //Find a pet which not exist
    @When("looking for a pet an id (.*) that does not exist results in a status 404 and message Pet not found$")
    public void lookingForANonExistingPet(int id){
        petSteps.the_pet_is_not_available(id);
    }

    //Finds pets by status
    @When("^looking for the pet with status (.*) and name (.*) i expect it to be in de results$")
    public void lookingForPetWithStatus(String status, String name){
        petSteps.the_pet_should_be_available_by_status(status, name);
    }

    //Update an existing pet
    @Given("^an updated pet with id (.*) to name (.*) and status (.*)$")
    public void givenAnUpdatedPet(int id, String name, String status){
        generatedPetUpdating = new Pet(status, name);
        idJson = id;
        jsonPet = "{\"id\": " + idJson + " , \"name\": \""
                + generatedPetUpdating.getName() + "\", \"photoUrls\": [], \"status\": \""
                + generatedPetUpdating.getStatus() + "\"}";

        this.generatedPetUpdating.setId(idJson);
    }

    @When("updating the pet")
    public void updatingTheNameAndStatusOfThePet(){
        rest().given().contentType("application/json")
                 .body(jsonPet).put(Constants.PET_ENDPOINT);
    }

    @Then("has the pet an updated profile")
    public void petHasUpdatedProfile(){
        petSteps.the_pet_should_be_available(generatedPetUpdating);
    }

    //Updates a pet in the store with form data
    @Given("^a pet with id (.*) that needs to be updated by form data with name (.*) and status (.*)$")
    public void updatesPetByFormData(int id, String name, String status){
        petSteps.update_a_pet_by_form_data(id, name, status);
    }


    //Deletes a pet
    @When("^deleting the pet with id (.*)$")
    public void deletingAPet(int id){
        idDeletedPet = id;
        petSteps.delete_the_pet(idDeletedPet);
    }

    @Then("^the pet does not longer exist in the store$")
    public void petDoesNotLongerExist(){
        petSteps.the_pet_is_not_available(idDeletedPet);
    }
}

