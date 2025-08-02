package api.endpoints;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserEndPoints {

    public static Response post_user(User payload){

      Response response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);

        return response;
    }

    public  static Response get_user(String userName){
    Response response =   given()
                .pathParam("username", userName)
                .when()
                .get(Routes.get_url);
       return response;
    }

    public static Response put_user(User payload, String userName){

        Response response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)
                .when()
                .put(Routes.put_url);

        return response;
    }

    public  static Response delete_user(String userName){
        Response response =   given()
                .pathParam("username", userName)
                .when()
                .delete(Routes.delete_url);
        return response;
    }
}
