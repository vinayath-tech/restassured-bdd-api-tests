package stepDefs.pets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.http.Header;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.response.Response;
import pojos.PetAvailability;
import utilities.PetApi;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class FetchPetByStatus {

    public PetApi petApi;
    public Response resp, invalidResp;

    public FetchPetByStatus() throws IOException {
        petApi = new PetApi();
    }

    @Given("^I execute a service to fetch pets by \"([^\"]*)\" status$")
    public void i_execute_a_service_to_fetch_pets_by_status(String status) throws Throwable {
       resp = petApi.fetchPetByStatus(status);
    }

    @Then("^the response code of pet availability endpoint should be (\\d+)$")
    public void the_response_code_of_pet_availability_endpoint_should_be(int statusCode) throws Throwable {
        assertEquals(resp.getStatusCode(), statusCode);
    }

    @Then("^the response should contain \"([^\"]*)\" status in body$")
    public void the_response_should_contain_status_in_body(String statusData) throws Throwable {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(resp.getBody().asString());
        if (jsonNode.isArray()) {
            for (JsonNode node : jsonNode) {
                String nodeContent = mapper.writeValueAsString(node);
                PetAvailability availability = mapper.readValue(nodeContent, PetAvailability.class);

                assertEquals(statusData, availability.getStatus());
            }
        }
    }

    @Then("^the response returns the following headers:$")
    public void the_response_returns_the_following_headers(List<String> expHeaders) throws Throwable {

        for(Header header: resp.getHeaders()){
            assertThat(expHeaders, hasItem(header.getName()));
        }
    }

    @Then("^the response body should be empty$")
    public void the_response_body_should_be_empty() throws Throwable {
        resp.then().assertThat().body("size()", is(0));
    }

    @Given("^I execute an invalid service to fetch pets by status$")
    public void i_execute_an_invalid_service_to_fetch_pets_by_status() throws Throwable {
         invalidResp = petApi.invalidPetByStatus("sold");
    }

    @Then("^the response should return invalid response code (\\d+)$")
    public void the_response_should_return_invalid_response_code(int statusCode) throws Throwable {
        assertEquals(invalidResp.getStatusCode(), statusCode);
    }

}
