package com.freenow.blog.stepdefinitions;

import com.freenow.blog.generic.Constants;

import org.hamcrest.Matchers;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import net.thucydides.core.annotations.Step;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.rest.SerenityRest.rest;

public class StoreSteps {

    @Step
    public String generateOrder(int orderId, int petId, int quantity) {
        return "{\"id\": " + orderId + " , \"petId\": \"" 
                + petId + "\", \"quantity\": \"" 
                + quantity + "\", \"shipDate\": \" 2021-02-20T08:24:19.213Z \", \"status\": \"placed\" , \"complete\": \"true\"}";
    }

    @Step
    public void addOrderToPetStore(String jsonOrder) {
        rest().given().contentType("application/json")
            .body(jsonOrder).post(Constants.STORE_ENDPOINT + "/order");
    }

    @Step
    public void theOrderShouldBeAvailable(int Id) {
        rest().get(Constants.STORE_ENDPOINT + "/order/{id}", Id)
            .then().statusCode(200)
            .and().body("id", equalTo(Id));
    }

    @Step
    public void theInventoryShouldBeAvailable() {
        rest().get(Constants.STORE_ENDPOINT + "/inventory")
            .then().statusCode(200)
            .and().body("$", hasKey("sold"))
                  .body("$", hasKey("ccStatus"))
                  .body("$", hasKey("string"))
                  .body("$", hasKey("pending"))
                  .body("$", hasKey("available"));
    }

    @Step
    public void deleteTheOrder(int idDeletedOrder) {
        rest().delete(Constants.STORE_ENDPOINT + "/order/{id}", idDeletedOrder)
            .then().statusCode(200);
    }

    @Step
    public void deleteTheOrderWithNonExistingId(int idNotExisting) {
        rest().delete(Constants.STORE_ENDPOINT + "/order/{id}", idNotExisting)
            .then().statusCode(404)
            .and().body("message", equalTo("Order Not Found"));
    }

    @Step
    public void theOrderIsNotAvailable(int idNotExistingOrder) {
        rest().get(Constants.STORE_ENDPOINT + "/order/{id}", idNotExistingOrder)
            .then().statusCode(404)
            .and().body("message", equalTo("Order not found"));
    }
}
