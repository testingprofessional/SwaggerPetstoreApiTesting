package com.freenow.blog.stepdefinitions;

import com.freenow.blog.models.Category;
import com.freenow.blog.models.Pet;
import com.freenow.blog.models.Status;
import com.freenow.blog.models.Tag;

import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.*;

import static net.serenitybdd.rest.SerenityRest.rest;

public class PetSteps {

    @Step
    public void the_pets_should_be_available(Pet generatedPet) {
        rest().get("https://petstore.swagger.io/v2/pet/{id}", generatedPet.getId())
            .then().statusCode(200)
            .and().body("name", equalTo(generatedPet.getName()));
    }
}
