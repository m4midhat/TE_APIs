package te.application.api.test.B2C.positive;


import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2CBaseTest;

import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class SignInTest extends B2CBaseTest {

    @BeforeClass
    public void setUp() throws IOException {
        RestAssured.basePath = AppConstants.B2C_LOGIN;
        Properties properties = Utils.initProperties("AppAuthentication");
        if(properties!=null) {
            String propUserName = Utils.decodeString(properties.getProperty("username"));
            String propPassword = Utils.decodeString(properties.getProperty("password"));

            String bodyData = generateAPIBody.signUp(0, "en", true,
                    "25.300579", "entertainer", "iOS", "8.04.01",
                    "ios-0C7C873D-8A9B-4D34-948E-3F5C19A6B439", "0", "AED", "0", "55.307709",
                    "ios-0C7C873D-8A9B-4D34-948E-3F5C19A6B439", "1", propUserName,
                    propPassword, "15.0", "iPhone XR", "Karachi/Islamabad",
                    AppConstants.SessionURL, Utils.decodeString(authToken.B2CAUTH_TOKEN));
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                    .contentType("application/json")
                    .body(bodyData)
                    .log().all();
            response = httpRequest.post();
            log.info(response.asString());
            jsonPath = response.jsonPath();
            AppConstants.sessionID = jsonPath.getString("data.user.session_token");
            log.info("Session ID : " + AppConstants.sessionID);
        }
    }

    @Test(priority = 0, description = "Status code check" )
    public void checkStatus(){
        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
    }

    @Test(priority = 1)
    public void checkMessage(){
        String message = jsonPath.getString("message");
        Assert.assertEquals("success", message, "Message value should be 'success'");
    }

    @Test(priority = 2)
    public void verifyNewUser(){
        boolean newUserVal = jsonPath.getBoolean("data.user.new_user");
        //log.info(String.valueOf(newUserVal));
        System.out.println(newUserVal);
        //Assert.assertEquals(newUserVal, "true", "New User value should be false");
        Assert.assertFalse(newUserVal);
    }

}
