package com.freenow.blog.stepdefinitions;

import com.freenow.blog.models.Category;
import com.freenow.blog.models.Pet;
import com.freenow.blog.models.Status;
import com.freenow.blog.models.Tag;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import com.freenow.blog.generic.*;

import net.thucydides.core.annotations.Step;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.rest.SerenityRest.rest;

public class PetSteps {

    Constants constants;

    @Step
    public void the_pet_should_be_available(Pet generatedPet) {
        rest().get(Constants.PET_ENDPOINT + "/{id}", generatedPet.getId())
            .then().statusCode(200)
            .and().body("name", equalTo(generatedPet.getName()));
    }

    @Step
    public void the_pet_is_not_available(int idNotExistingPet) {
        rest().get(Constants.PET_ENDPOINT + "/{id}", idNotExistingPet)
            .then().statusCode(404)
            .and().body("message", equalTo("Pet not found"));
    }

    @Step
    public void the_pet_should_be_available_by_status(String status, String name) {
        rest().get(Constants.PET_ENDPOINT_FIND_BY_STATUS + "{status}", status)
            .then().statusCode(200)
            .and().assertThat().body("name", Matchers.hasItem(name));
    }

    @Step
    public void delete_the_pet(int idDeletedPet) {
        rest().delete(Constants.PET_ENDPOINT + "/{id}", idDeletedPet)
            .then().statusCode(200);
    }

    @Step
    public void update_a_pet_by_form_data(int idUpdatePet, String name, String status) {
        given().urlEncodingEnabled(true)
            .param("name", name)
            .param("status", status)
            .header("Accept", ContentType.JSON.getAcceptHeader())
            .post(Constants.PET_ENDPOINT + "/" + idUpdatePet)
            .then().statusCode(200);
    }
}
