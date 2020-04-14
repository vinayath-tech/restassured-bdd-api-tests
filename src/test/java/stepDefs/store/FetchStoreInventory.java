package stepDefs.store;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;
import pojos.Store;
import utilities.StoreApi;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FetchStoreInventory {

    StoreApi storeApi;
    Response resp;

    public FetchStoreInventory() throws IOException {
        storeApi = new StoreApi();
    }

    @Given("^I execute a service to fetch store inventories$")
    public void i_execute_a_service_to_fetch_store_inventories() throws Throwable {
        resp = storeApi.fetchStoreInventory();
    }

    @Then("^the service should return (\\d+) response code$")
    public void the_service_should_return_response_code(int statusCode) throws Throwable {
        Assert.assertEquals(statusCode, resp.getStatusCode());
    }

    @Then("^I should see the following data in response:$")
    public void i_should_see_the_following_data_in_response(DataTable dt) throws Throwable {
        List<Map<String, Integer>> list = dt.asMaps(String.class, Integer.class);
        var store = resp.getBody().as(Store.class);

        Assert.assertEquals(store.getSold(), list.get(0).get("sold"));
        Assert.assertEquals(store.getPending(), list.get(0).get("pending"));
    }


}
