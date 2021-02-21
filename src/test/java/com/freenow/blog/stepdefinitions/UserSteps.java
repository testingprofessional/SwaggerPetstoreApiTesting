package com.freenow.blog.stepdefinitions;

import org.hamcrest.Matchers;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import net.thucydides.core.annotations.Step;
import static org.hamcrest.Matchers.*;
import java.util.Random;

import com.freenow.blog.generic.Constants;

import static net.serenitybdd.rest.SerenityRest.rest;

public class UserSteps {

    @Step
    public String generateUser(int id, String username, String firstname, String lastname, String email, String password, String phone, int userStatus) {
        return "{\"id\": " + id + 
                " , \"username\": \"" + username + 
                "\", \"firstName\": \"" + firstname + 
                "\", \"lastName\": \"" + lastname + 
                "\", \"email\": \"" + email + 
                "\", \"password\": \"" + password + 
                "\", \"phone\": \"" + phone + 
                "\", \"userStatus\": " + userStatus + "}";
    }

    @Step
    public void addUserToPetStore(String jsonUser) {
        rest().given().contentType("application/json")
            .body(jsonUser).post(Constants.USER_ENDPOINT);
    }

    @Step
    public void theUserShouldBeAvailable(String username, String phone) {
        rest().get(Constants.USER_ENDPOINT + "/{username}", username)
            .then().statusCode(200)
            .and().body("username", equalTo(username))
                  .body("phone", equalTo(phone));
    }

    @Step
    public void updateUserInThePetStore(String jsonUser, String username) {
        rest().given().contentType("application/json")
            .body(jsonUser).put(Constants.USER_ENDPOINT + "/" + username)
            .then().statusCode(200);
    }

    @Step
    public void deleteUser(String username) {
        rest().delete(Constants.USER_ENDPOINT + "/{username}", username)
            .then().statusCode(200);
    }

    @Step
    public void theUserIsNotAvailable(String username) {
        rest().get(Constants.USER_ENDPOINT + "/{username}", username)
            .then().statusCode(404)
            .and().body("message", equalTo("User not found"));
    }
}
