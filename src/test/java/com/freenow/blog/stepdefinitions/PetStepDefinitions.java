package com.freenow.blog.stepdefinitions;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.Matchers.*;

import java.util.Collections;

import com.freenow.blog.controller.PetController;
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
import net.thucydides.core.annotations.Steps;

import static net.serenitybdd.rest.SerenityRest.rest;

import static io.restassured.RestAssured.given;

public class PetStepDefinitions {

    @Steps
    PetSteps petSteps;

    Pet generatedPet;
    int idJson;
    String jsonPet;
    Constants constants;

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
        petSteps.the_pets_should_be_available(generatedPet);
    }

    // //Uploads an image
    // @Given("^having an image (.*) that i want to upload to the pet with id (.*)$")
    // public void haveAnImage(String image, int id) {
    //     //TODO
    // }

    // @When ("the image is uploaded")
    // public void imageUploaded(){
    //     //TODO
    // }

    // @Then ("the pet has an image in its profile")
    // public void checkUploadedImageInProfile(){
    //     //TODO
    // }
}

