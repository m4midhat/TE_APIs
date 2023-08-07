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
import te.application.data.response.forgotPassword;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

@Slf4j
public class ForgetPasswordTest extends B2CBaseTest {
    String propUserName;
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String DOMAIN = "example.com"; // Replace with your desired domain name

    public static String generateRandomEmail(int length) {


        StringBuilder email = new StringBuilder(length);
        Random random = new Random();

        // Generate random characters for the local part of the email
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            email.append(ALLOWED_CHARACTERS.charAt(randomIndex));
        }

        email.append('@').append(DOMAIN);
        return email.toString();
    }


    @BeforeClass
    public void ValidateForgetPassword() throws IOException {
        RestAssured.basePath = AppConstants.B2C_FORGOT_PASSWORD;
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            propUserName = Utils.decodeString(properties.getProperty("username"));
            String bodyData = generateAPIBody.forgotPassword(AppConstants.requestDeviceModel, "AED",
                    "ios-48195B02-3646-4EDF-A46B-67646935624B", AppConstants.requestAppVersion, "entertainer",
                    AppConstants.UserID, AppConstants.BASE_URI_B2C+AppConstants.B2C_FORGOT_PASSWORD, "1",
                    "25.300579", "55.307709", AppConstants.requestLanguage, "ios-48195B02-3646-4EDF-A46B-67646935624B",
                    "9143773", AppConstants.requestOSPlatform, AppConstants.requestOSVersion, propUserName,
                    AppConstants.requestOSPlatform, AppConstants.requestTimeZone);
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                    .contentType("application/json")
                    .body(bodyData)
                    .log().all();
            response = httpRequest.post();
            log.info(response.asString());
            //Extracting Json path from response
            jsonPath = response.jsonPath();
        }
    }

    @Test(priority = 0, description = "Status code check")
    public void checkStatus() {
        int expectedStatus = 201;
        int actualStatus = jsonPath.getInt("http_response");
        Assert.assertEquals(expectedStatus, actualStatus, "Status validated");
        log.info("Expected value is : " + expectedStatus);
        log.info("Actual value in response is : " + actualStatus);
    }

    @Test(priority = 1, description = "Status Success Message")
    public void checkMessage() {
        String message = jsonPath.getString("message");
        Assert.assertEquals("success", message, "Message value should be 'success'");
    }

    @Test(priority = 2, description = "Verify Success Message is true")
    public void successMessage() {
        String message = jsonPath.getString("success");
        Assert.assertEquals("true", message, "Message value should be 'true'");
    }

    @Test(priority = 3, description = "Check Email sent is true")
    public void checkSentStatus() {
        String actualkey = jsonPath.getString("data.is_sent");
        Assert.assertTrue(Boolean.parseBoolean(actualkey), "Sent Key validated");
    }

    @Test(priority = 4, description = "Verify ResetPassword Message")
    public void checkResetPasswordMessage() {
        String actualMessage = jsonPath.getString("data.message");
        Assert.assertEquals(actualMessage, forgotPassword.msgEnglish, "Link should be sent on registered email");
    }

}