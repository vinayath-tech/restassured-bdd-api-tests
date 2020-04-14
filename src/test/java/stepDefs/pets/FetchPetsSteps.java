package stepDefs.pets;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import pojos.Pets;
import stepDefs.pets.CreateNewPet;
import utilities.PetApi;

import java.io.IOException;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class FetchPetsSteps {
    public PetApi petApi;
    public CreateNewPet createNewPet;
    public Response resp;

    public FetchPetsSteps() throws IOException {
        petApi = new PetApi();
    }

    @Given("I execute a service to fetch available pets")
    public void i_execute_a_service_to_fetch_available_pets() {
        resp = petApi.fetchPetDetail(createNewPet.petId);
    }

    @Then("the response code should be (\\d+)$")
    public void the_response_code_should_be(int statusCode) {
        assertEquals(resp.getStatusCode(), statusCode);
    }

    //And the response should match expected json schema
    @Then("^the response should match expected json schema$")
    public void the_response_should_match_expected_json_schema() throws Throwable {
       resp.then().assertThat().body(matchesJsonSchemaInClasspath("./../schema/detailPet.json"));

    }

    @Then("^I should see the name as \"([^\"]*)\"$")
    public void i_should_see_the_name_as(String expName) throws Throwable {
        var pets = resp.getBody().as(Pets.class);
        assertThat(pets.getName(), equalTo(expName));
    }
}
