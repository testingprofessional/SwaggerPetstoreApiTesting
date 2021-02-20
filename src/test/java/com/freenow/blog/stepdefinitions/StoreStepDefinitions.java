package com.freenow.blog.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class StoreStepDefinitions {

    @Steps
    StoreSteps storeSteps;

    String jsonOrder;
    int orderIdStore;
    int purchaseId;

    //Place an order for a pet
    @Given("^an order with orderid (.*) for pet with id (.*) and quantity (.*)$")
    public void generateOrderForPet(int orderId, int petId, int quantity) {
        orderIdStore = orderId;
        jsonOrder = storeSteps.generateOrder(orderId, petId, quantity);
    }

    @When("I place the order")
    public void placeOrderForPet() {
        storeSteps.addOrderToPetStore(jsonOrder);
    } 

    //Find purchase order by ID 
    @Then("the order is placed in the petstore")
    public void checkOrderInPetStore() {
        storeSteps.theOrderShouldBeAvailable(orderIdStore);
    }

    //Delete purchase order by ID
    @When("^I delete a purchase order with id (.*)")
    public void deletePurchaseOrder(int id) {
        purchaseId = id;
        storeSteps.deleteTheOrder(purchaseId);
    }

    @Then("the order with id 5 is not longer in the petstore")
    public void orderIsnotLongerInPetstore() {
        storeSteps.theOrderIsNotAvailable(purchaseId);
    }

    //Delete purchase order by not existing ID
    @When("deleting a purchase order with a non existing id (.*) the message Order Not Found is expected")
    public void deletePurchaseORderWitNonExistingId(int nonExistingId) {
        storeSteps.deleteTheOrderWithNonExistingId(nonExistingId);
    }

    //Returns pet inventories by status
    @When("asking the inventory of the petstore i expect a map with quantities")
    public void getPetInventoriesByStatus() {
        storeSteps.theInventoryShouldBeAvailable();
    } 
}
