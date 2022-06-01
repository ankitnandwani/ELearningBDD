package com.ui.cucumber.StepDefs;

import com.backend.entities.response.ListUsers;
import com.backend.entities.response.User;
import com.google.gson.Gson;
import com.ui.cucumber.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class Api {
    String endpoint;
    Response response;

    Hooks hooks = new Hooks();
    Gson gson = new Gson();

    @Given("^endpoint (.*)$")
    public void endpoint(String endpoint) {
        this.endpoint=endpoint;
    }

    @When("^I send (.*) request$")
    public void iSendRequest(String requestType) {
        response = hooks.sendRequest(requestType, endpoint);
    }

    @Then("I should get {int} response code")
    public void iShouldGetResponseCode(int responseCode) {
        Assert.assertEquals(response.getStatusCode(), responseCode);
    }

    @And("^List of users should be returned$")
    public void listOfUsersShouldBeReturned() {
        String responseString = response.getBody().asString();
        ListUsers users = gson.fromJson(responseString, ListUsers.class);
        Assert.assertNotNull(users.getData().stream().map(User::getFirst_name));
    }
}
