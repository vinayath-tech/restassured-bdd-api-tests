package stepDefs.pets;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import stepDefs.pets.CreateNewPet;
import utilities.PetApi;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class DeletePet {

    public PetApi petApi;
    public CreateNewPet createNewPet;
    public Response resp, verifyDelResp;

    public DeletePet() throws IOException {
        petApi = new PetApi();
    }


    @Given("^I execute a service to delete a pet$")
    public void i_execute_a_service_to_delete_a_pet() throws Throwable {

        resp = petApi.deleteAPet(CreateNewPet.petId).prettyPeek();
    }

    @Then("^the delete response code should be (\\d+)$")
    public void the_delete_response_code_should_be(int statusCode) throws Throwable {
        assertEquals(resp.getStatusCode(), statusCode);
    }


    @Then("^the pet should not be available in store$")
    public void the_pet_should_not_be_available_in_store() throws Throwable {
        verifyDelResp = petApi.fetchPetDetail(createNewPet.petId);
         assertEquals(404, verifyDelResp.getStatusCode());
    }
}
