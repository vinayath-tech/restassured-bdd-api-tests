package stepDefs;

import com.fasterxml.jackson.annotation.JsonProperty;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import pojos.Category;
import pojos.Pets;
import pojos.Tag;
import utilities.Log;
import utilities.PetApi;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.testng.Assert.assertEquals;

public class CreateNewPet {

    public PetApi petApi;
    public Response resp, updtResp;
    public static int petId;

    public CreateNewPet() throws FileNotFoundException {
        petApi = new PetApi();
        this.petId = petId;
    }

    @Given("^I execute a service to add a new pet$")
    public void i_execute_a_service_to_add_a_new_pet() throws Throwable {
        Category cat = new Category();
        cat.setId((long) 1017654);
        cat.setName("Gokul-testing");

        Tag tag = new Tag();
        tag.setId(84359843);
        tag.setName("tag-gokul");

        Pets pet = new Pets();
        pet.setId(3453535);
        pet.setCategory(cat);
        pet.setName("Dog-gokul");
        pet.setPhotoUrls(Collections.singletonList("example-photo"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("Available");

        Log.info(this.getClass().getSimpleName() + "Test method started");
        resp = petApi.addANewPet(pet).prettyPeek();
    }

    @Then("^the response code of create endpoint should be (\\d+)$")
    public void the_response_code_of_create_endpoint_should_be(int statusCode) throws Throwable {

        Log.info(this.getClass().getSimpleName() + " test is starting.");
        assertEquals(resp.getStatusCode(), statusCode);
    }

    @Then("^the response should contain expected response values:$")
    public void the_response_should_contain_expected_response_values(DataTable dt) throws Throwable {
        Log.info(this.getClass().getSimpleName() + " test is starting.");

        List<Map<String, String>> list = dt.asMaps(String.class, String.class);
        JsonPath jp = resp.jsonPath();

        petId = jp.get("id");

        String expCatName = list.get(0).get("categoryName");
        String expTagName = list.get(0).get("tagName");
        String expName = list.get(0).get("name");

        assertEquals(expCatName, jp.get("category.name"));
        assertThat(jp.get("tags.name"), contains(expTagName));
        assertEquals(expName, jp.get("name"));
    }


    @Given("^I execute a service to update a pet$")
    public void i_execute_a_service_to_update_a_pet() throws Throwable {

        Log.info(this.getClass().getSimpleName() + " test is starting.");

        Pets pet = new Pets();
        pet.setId(petId);
        pet.setName("updated-test-name");
        pet.setStatus("Available");

        updtResp = petApi.updateAPet(pet).prettyPeek();
    }

    @Then("^the response code of update endpoint should be (\\d+)$")
    public void the_response_code_of_update_endpoint_should_be(int statusCode) throws Throwable {

        Log.info(this.getClass().getSimpleName() + " test is starting.");
        assertEquals(updtResp.getStatusCode(), statusCode);
    }

    @Then("^the response should return the following updated name:$")
    public void the_response_should_return_the_following_updated_name(DataTable dt) throws Throwable {

        Log.info(this.getClass().getSimpleName() + " test is starting.");
        List<String> list = dt.asList(String.class);
        JsonPath jp = updtResp.jsonPath();

        assertEquals(jp.get("name"), list.get(0));
    }
}
