package utilities;

import io.restassured.response.Response;

import java.io.IOException;

public class StoreApi extends RequestSpecApi {

    public StoreApi() throws IOException {
        setUpTest();
    }

    public Response fetchStoreInventory() {
        return request.get("store/inventory");
    }
}
