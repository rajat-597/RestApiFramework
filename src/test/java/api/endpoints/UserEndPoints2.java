package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.awt.geom.RectangularShape;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.*;

public class UserEndPoints2 {

    // method created to get URL from properties files

    static ResourceBundle getURL(){
       ResourceBundle routes = ResourceBundle.getBundle("routes");  // here routes are name of the properties files
       return routes;
    }
    public static Response post_user(User payload){

       String post_url = getURL().getString("post_url");
        Response response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);

        return response;
    }

    public  static Response get_user(String userName){
        String get_url = getURL().getString("get_url");
        Response response =   given()
                .pathParam("username", userName)
                .when()
                .get(get_url);
        return response;
    }

    public static Response put_user(User payload, String userName){
        String update_url = getURL().getString("update_url");
        Response response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)
                .when()
                .put(update_url);

        return response;
    }


    public  static Response delete_user(String userName){
        String delete_url = getURL().getString("delete_url");
        Response response =   given()
                .pathParam("username", userName)
                .when()
                .delete(delete_url);
        return response;
    }
}
