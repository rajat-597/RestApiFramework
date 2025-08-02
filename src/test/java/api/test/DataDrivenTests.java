package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTests {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUsers(String userId, String userName, String fName, String lName, String email, String password, String phone){

        User payloadUser = new User();
        payloadUser.setId(Integer.parseInt(userId));
        payloadUser.setUsername(userName);
        payloadUser.setFirstName(fName);
        payloadUser.setLastName(lName);
        payloadUser.setEmail(email);
        payloadUser.setPassword(password);
        payloadUser.setPhone(phone);

       Response response = UserEndPoints.post_user(payloadUser);
       response.then().statusCode(200);
    }

 /*   @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testGetUsers(String userName){
        Response response =  UserEndPoints.get_user(userName);
        response.then().log().all();
    } */

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUsers(String userName){
        Response response = UserEndPoints.delete_user(userName);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

}
