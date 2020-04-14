package utilities;

import helpers.ConfigHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class RequestSpecApi {

    public ConfigHelper configHelper;
    protected RequestSpecification request;

    public void setUpTest() throws IOException {
        configHelper = new ConfigHelper();
        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.setBaseUri(configHelper.getBaseUri());
        builder.setBasePath(configHelper.getBasePath());

        RequestSpecification requestSpec = builder.build();
        request = given().spec(requestSpec).log().all();
    }
}
