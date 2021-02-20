package com.freenow.blog.stepdefinitions;

import com.freenow.blog.models.Pet;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class PetStepDefinitions {

    @Steps
    PetSteps petSteps;

    Pet generatedPet;
    Pet generatedPetUpdating;
    int idJson;
    String jsonPet;
    String jsonPetUpdating;
    int idDeletedPet;
    String statusUpdatedByFormData;
    String nameUpdatedByFormData;

    //Add a new pet to the store
    @Given("^having a new pet defined with id (.*) name (.*) and status (.*)$")
    public void havingANewPetDefined(int id, String name, String status) {
        generatedPet = new Pet(status, name, id);
        jsonPet = petSteps.generateJsonPet(generatedPet.getName(), generatedPet.getStatus(), generatedPet.getId()); 
    }

    @When("adding the pet to the petstore")
    public void addingPetToPetstore() {
        petSteps.addPetToPetStore(jsonPet);
    }

    @Then("expecting the pet to be added in the petstore")
    public void expectThePetToBeAddedInThePetstore() {
        petSteps.thePetShouldBeAvailable(generatedPet);
    }

    //Find a pet which not exist
    @When("looking for a pet an id (.*) that does not exist results in a status 404 and message Pet not found$")
    public void lookingForANonExistingPet(int id){
        petSteps.thePetIsNotAvailable(id);
    }

    //Finds pets by status
    @When("^looking for the pet with status (.*) and name (.*) i expect it to be in de results$")
    public void lookingForPetWithStatus(String status, String name){
        petSteps.thePetShouldBeAvailableByStatus(status, name);
    }

    //Update an existing pet
    @Given("^an updated pet with id (.*) to name (.*) and status (.*)$")
    public void givenAnUpdatedPet(int id, String name, String status){
        generatedPetUpdating = new Pet(status, name, id);
        jsonPetUpdating = petSteps.generateJsonPet(generatedPetUpdating.getName(), generatedPetUpdating.getStatus(), generatedPetUpdating.getId());
    }

    @When("updating the pet")
    public void updatingTheNameAndStatusOfThePet(){
        petSteps.updateThePetInThePetStore(jsonPetUpdating);
    }

    @Then("has the pet an updated profile")
    public void petHasUpdatedProfile(){
        petSteps.thePetShouldBeAvailable(generatedPetUpdating);
    }

    //Updates a pet in the store with form data
    @Given("^a pet with id (.*) that needs to be updated by form data with name (.*) and status (.*)$")
    public void updatesPetByFormData(int id, String name, String status){
        nameUpdatedByFormData = name;
        statusUpdatedByFormData = status;
        petSteps.updateAPetByFormData(id, nameUpdatedByFormData, statusUpdatedByFormData);
    }

    @Then("the pet should be updated")
    public void petShouldBeUpdatedByFormData(){
        petSteps.thePetShouldBeAvailableByStatus(statusUpdatedByFormData, nameUpdatedByFormData);
    }

    //Deletes a pet
    @When("^deleting the pet with id (.*)$")
    public void deletingAPet(int id){
        idDeletedPet = id;
        petSteps.deleteThePet(idDeletedPet);
    }

    @Then("^the pet does not longer exist in the store$")
    public void petDoesNotLongerExist(){
        petSteps.thePetIsNotAvailable(idDeletedPet);
    }
}

