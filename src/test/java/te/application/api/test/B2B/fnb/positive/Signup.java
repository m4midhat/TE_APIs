package te.application.api.test.B2B.fnb.positive;

import com.github.javafaker.App;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2BBaseTest;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import te.application.appConstants.AppConstants;
import te.application.utilities.generateAPIBodyB2B;


import static org.testng.Assert.assertEquals;

@Slf4j
public class Signup extends B2BBaseTest {

    private static Faker faker = new Faker();
    public static String email = faker.internet().emailAddress();
    public static String FirstName = faker.name().firstName();
    public static String LastName = faker.name().lastName();

    @Test(priority = 1, description = "verify with Signup Random Email")
    public static void signUpWithRandomEmail() {
        String bodyData = generateAPIBodyB2B.signUp(email, FirstName, LastName, AppConstants.requestOSPlatform, AppConstants.requestDeviceKey, AppConstants.requestDeviceKey,
                AppConstants.requestDeviceModel, AppConstants.requestOSPlatform, AppConstants.requestLanguage, AppConstants.requestAppVersion, "fnb", "true");


        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer " + authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(200, statusCode, "success");
    }

    @Test(priority = 2, description = "Verify user email")
    public void checkEmail() {
        String responseEmail = jsonPath.getString("data.resend_invite_section.email");
        log.info(responseEmail);
        Assert.assertNotNull(responseEmail);
        assertEquals(responseEmail, email, "userID should be like this");
    }

    @Test(priority = 3, description = "Verify the message")
    public void checkMessage() {
        String responseMessage = jsonPath.getString("data.resend_invite_section.message");
        log.info(responseMessage);
        Assert.assertNotNull(responseMessage);
        String msg = "Please verify your email address to continue using your account. If left unverified, your account will be purged in the next 90 days from the date of registration.";
        assertEquals(responseMessage, msg, "message should be like this");
    }

    @Test(priority = 4, description = "Verify the member Type")
    public void checkMemberType() {
        String responseMessage = jsonPath.getString("data.member_type");
        log.info(responseMessage);
        Assert.assertNotNull(responseMessage);
        String memberType = "3";
        assertEquals(responseMessage, memberType, "memberType should be like this");

    }
}

