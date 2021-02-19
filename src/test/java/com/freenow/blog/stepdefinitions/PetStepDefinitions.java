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

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.BeforeClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static net.serenitybdd.rest.SerenityRest.rest;

import static io.restassured.RestAssured.given;

public class PetStepDefinitions {

    private static final String PHOTO_URL = "https://www.tesco.ie/groceries/MarketingContent/Sites/Retail/superstore/Online/P/i/departments/2016/Pets/1BC.jpg";
    PetController petController;
    Pet pet = new Pet.Builder()
            //.withId(RandomStringUtils.randomNumeric(10))
            .withId("15")
            .withName("doggiex")
            .withPhotoUrls(Collections.singletonList("string"))
            .withStatus(Status.available)
            .withTags(Collections.singletonList(new Tag(0, "string")))
            .inCategory(new Category(0, "string")).build();

    @BeforeClass
    public void beforeClass() {
        petController = new PetController();
    }
    
    //Add a new pet to the store
    @Given("^having a new pet defined with id (.*) categoryid (.*) categoryname (.*) name (.*) tagid (.*) tagname (.*) and status (.*)$")
    public void havingANewPetDefined(int id, int categoryId, String categoryName, String name, int tagId, String tagName, String status) {

        given().
            param("username","test").
            param("password","test").
        when().
            get("http://petstore.swagger.io/v2/user/login").
        then().
            assertThat().
            statusCode(200);


            rest().get("http://petstore.swagger.io/v2/pet/15")
            .then().statusCode(200)
            .and().body("name", equalTo(pet.getName()));

        // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! pet: " + pet);
        // Pet petResponse = petController.findPet(pet);
        // assertThat(petResponse, is(samePropertyValuesAs(pet)));

        // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! pet: " + pet);
        // Pet petResponse = petController.addNewPet(pet);
        // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! petResponse: " + petResponse);
        // assertThat(petResponse, is(samePropertyValuesAs(pet)));
    }

    @When("adding the pet to the petstore")
    public void addingPetToPetstore() {
        //TODO
    }

    @Then("expecting the pet to be added in the petstore")
    public void expectThePetToBeAddedInThePetstore() {
        //TODO
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

