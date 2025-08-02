package api.test;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest2 {

    Faker faker;
    User payloadUser;

    public Logger logger;
    @BeforeClass
    public void setUpData(){
        faker = new Faker();
        payloadUser = new User();

        payloadUser.setId(faker.idNumber().hashCode());
        payloadUser.setUsername(faker.name().username());
        payloadUser.setFirstName(faker.name().firstName());
        payloadUser.setLastName(faker.name().lastName());
        payloadUser.setEmail(faker.internet().emailAddress());
        payloadUser.setPassword(faker.internet().password());
        payloadUser.setPhone(faker.phoneNumber().phoneNumber());

        logger = LogManager.getLogger(this.getClass()); // initialize the loggers
    }

    @Test(priority =1)
    public void testPostUser(){
        logger.info("*************** creating user info ****************");
        Response response = UserEndPoints2.post_user(payloadUser);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("***************** User created *************** ");
    }

    @Test(priority = 2)
    public void testGetUser(){
        logger.info("*************** Reading user info ****************");

        Response response =  UserEndPoints2.get_user(this.payloadUser.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("*************** Reading user info completed ****************");

    }

    @Test(priority = 3)
    public void testUpdateUser(){
        logger.info("*************** Updating user info ****************");

        payloadUser.setFirstName(faker.name().firstName());
        payloadUser.setLastName(faker.name().lastName());
        payloadUser.setEmail(faker.internet().emailAddress());

        Response response =  UserEndPoints2.put_user(payloadUser, this.payloadUser.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        // check after update
        Response updatedResponse = UserEndPoints2.post_user(payloadUser);
        updatedResponse.then().log().all();
        Assert.assertEquals(updatedResponse.getStatusCode(),200);
        logger.info("*************** Updated user info ****************");

    }

    @Test(priority = 4)
    public void deleteUser(){
        logger.info("*************** Deleting user info ****************");
        Response response = UserEndPoints2.delete_user(this.payloadUser.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("*************** Deleted user info ****************");

    }
}

