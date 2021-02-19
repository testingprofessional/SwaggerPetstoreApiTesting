package com.freenow.blog.controller;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import com.freenow.blog.models.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class PetController {
    public static String BASE_URL = "http://petstore.swagger.io/v2";
    public static String PET_ENDPOINT = BASE_URL + "/pet";
    private RequestSpecification requestSpecification;

    public PetController() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(BASE_URL);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();
    }

    public Pet addNewPet(Pet pet) {
        return given(requestSpecification)
                .body(pet)
                .post(PET_ENDPOINT).as(Pet.class);
    }

    public List<Pet> getPetsByStatus(Status status) {
        return given(requestSpecification)
                .queryParam("status", Status.available.toString())
                .get(PET_ENDPOINT + "/findByStatus")
                .then().log().all()
                .extract().body()
                .jsonPath().getList("", Pet.class);

    }

    public void deletePet(Pet pet) {
        given(requestSpecification)
                .pathParam("petId", pet.getId())
                .delete(PET_ENDPOINT + "/{petId}");
    }

    public void verifyPetDeleted(Pet pet) {
         given(requestSpecification)
                .pathParam("petId", pet.getId())
                .get(PET_ENDPOINT + "/{petId}")
                .then()
                .body(containsString("Pet not found"));
    }

    public Pet findPet(Pet pet) {
        return given(requestSpecification)
                .pathParam("petId", pet.getId())
                .get(PET_ENDPOINT + "/15").as(Pet.class);
    }

    public Pet updatePet(Pet pet) {
        return given(requestSpecification)
                .body(pet)
                .put(PET_ENDPOINT).as(Pet.class);
    }
}
