package utilities;

import io.restassured.response.Response;

import java.io.FileNotFoundException;

public class PetApi extends RequestSpecApi {

    public PetApi() throws FileNotFoundException {
        setUpTest();
    }

    public Response fetchPetDetail(int petId){
        return request.get("/pet/"+petId);
    }

    public Response fetchPetByStatus(String status) { return request.get("/pet/findByStatus?status="+status); }

    public Response invalidPetByStatus(String status) { return request.get("/findByStatus?status="+status); }

    public Response addANewPet(Object payload) { return request.contentType("application/json").body(payload).post("/pet"); }

    public Response updateAPet(Object payload) { return request.contentType("application/json").body(payload).put("/pet"); }

    public Response deleteAPet(int petId) { return request.accept("application/json").delete("/pet/"+petId); }
}
