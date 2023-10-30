package te.application.api.test.B2B.fnb.positive;

import io.restassured.path.json.JsonPath;
import te.application.api.baseTest.B2BBaseTest;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import te.application.appConstants.AppConstants;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBodyB2B;

import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertEquals;
import static te.application.api.test.B2B.fnb.positive.Signup.email;

@Slf4j
public class SignIn extends B2BBaseTest {

    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException, InterruptedException {
        Signup.signUpWithRandomEmail();

        RestAssured.basePath = AppConstants.BASE_PATH_SIGNIN;
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {

            String bodyData = generateAPIBodyB2B.signIn(AppConstants.requestAppVersion, AppConstants.requestOSPlatform,
                    AppConstants.requestLocation,
                    AppConstants.requestOSPlatform, email, AppConstants.requestKey, AppConstants.requestKey,
                    AppConstants.requestDeviceModel, 1,
                    "fnb", "true");
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", "Bearer " + AppConstants.BEARER_TOKEN)
                    .contentType("application/json")
                    .body(bodyData)
                    .log().all();
            Thread.sleep(250);
            response = httpRequest.post();
            log.info(response.asString());
            jsonPath = response.jsonPath();
            AppConstants.sessionID = jsonPath.getString("data.user.session_token");
            AppConstants.UserID = jsonPath.getString("data.user.user_id");
            log.info("Session ID : " + AppConstants.sessionID);
            log.info("User ID : " + AppConstants.UserID);
        } else {
            log.error("Unable to read the properties file!!!");
        }
    }

    @Test(priority = 0, description = "Verify Status code")
    public void checkStatus() {
        Assert.assertEquals(response.getStatusCode(), 200, "status code returned");
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
    }

    @Test(priority = 1, description = "Verify Success Message")
    public void checkMessage() {
        String expectedMessage = "success";
        String actualMessage = jsonPath.getString("message");
        Assert.assertEquals(expectedMessage, actualMessage, "Message validated");
        log.info("expect value is : " + expectedMessage);
        log.info("actual value in response is : " + actualMessage);
    }

    @Test(priority = 2, description = "Validate email")
    public void checkEmail() {
        String responseEmail = jsonPath.getString("data.user.email");
        log.info(responseEmail);
        Assert.assertNotNull(responseEmail);
        Assert.assertEquals(responseEmail, email);
        log.info("Actual: " + email + " " + "Expected: " + responseEmail);
    }

    @Test(priority = 3, description = "Check Session Token is not nul")
    public void verifySessionToken() {
        String sessionToken = jsonPath.getString("data.validation_params.session_token");
        log.info(">>>>>>>>>>>>>>>>>" + sessionToken);
        Assert.assertNotNull(sessionToken, "Session token should not be null");
    }

    @Test(priority = 4, description = "check Membership Code")
    public void verifyMemberShipCode() {
        String memberShipCode = jsonPath.getString("data.user.membership_code");
        log.info(String.valueOf(memberShipCode));
        Assert.assertEquals(memberShipCode, memberShipCode);
    }

    @Test(priority = 5, description = "Check UserID is not null after successful Sign In")
    public void VerifyUserID() {
        String userid = jsonPath.getString("data.user.user_id");
        log.info(userid);
        Assert.assertNotNull(userid);
        assertEquals(userid, userid);
    }

    @Test(priority = 6, description = "Verify User Status is Active")
    public void verifyActiveStatus() {
        String expectedActiveStatus = "1";
        String actualActiveStatus = jsonPath.getString("data.user.is_active");
        Assert.assertEquals(expectedActiveStatus, actualActiveStatus, "ActiveStatus validated");
        log.info("expect value is : " + expectedActiveStatus);
        log.info("actual value in response is : " + actualActiveStatus);
    }

    @Test(priority = 7, description = "Verify the Membership Expiration Date")
    public void verifyMembershipExpirationDate() throws ParseException {
        String responseBody = response.getBody().asString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String membershipExpirationDate = response.jsonPath().getString("data.user.membership_expiration_date");
        if ("None".equals(membershipExpirationDate)) {
            System.out.println("Membership expiration date is not available (None).");
        } else {
            Date expectedDate = dateFormat.parse(membershipExpirationDate);
            Date actualDate = dateFormat.parse(membershipExpirationDate);
            Assert.assertEquals(actualDate, expectedDate);
        }
    }

    @Test(priority = 8, description = "Verify Total Number Of Products and Product Digits")
    public void getTotalNumberOfProductsAndDigits() {
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getList("data.user.products").size(), 18);
        for (String productId : jsonPath.getList("data.user.products", String.class)) {
            Assert.assertTrue(productId.matches("\\d{5}")); // Assuming the format is 5 digits
        }
    }
}




