package te.application.api.test.B2C.negative;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2CBaseTest;
import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;
import java.util.Properties;

import static te.application.api.test.B2C.negative.SignInTest.generateRandomEmail;

@Slf4j
public class ForgetPasswordTest extends B2CBaseTest {

    @Test(priority = 5, description = "Reset password with blank Email Id")
    public void  resetPasswordWithBlankEmail(){
        RestAssured.basePath = AppConstants.B2C_FORGOT_PASSWORD;
        String bodyData = generateAPIBody.forgotPassword(AppConstants.testDataDeviceModel, "AED",
                "ios-48195B02-3646-4EDF-A46B-67646935624B", AppConstants.testDataAppVersion, "entertainer",
                "9143773", AppConstants.BASE_URI_B2C+AppConstants.B2C_FORGOT_PASSWORD, "1",
                "25.300579", "55.307709", "en", "ios-48195B02-3646-4EDF-A46B-67646935624B",
                "9143773", AppConstants.testDataOSPlatform, AppConstants.testDataOSVersion, "", AppConstants.testDataOSPlatform, AppConstants.testDataTimeZone);
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(422, statusCode, "Status code should be 422 returned");
    }
    @Test (priority = 6, description = "Reset password with invalid Email Id")
    public void  resetPasswordWithRandomEmail() throws IOException {
        RestAssured.basePath = AppConstants.B2C_FORGOT_PASSWORD;
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            String randomEmail = generateRandomEmail(12);
            String bodyData = generateAPIBody.forgotPassword(AppConstants.testDataDeviceModel, "AED",
                    "ios-48195B02-3646-4EDF-A46B-67646935624B", AppConstants.testDataAppVersion,
                    "entertainer", "9143773", AppConstants.BASE_URI_B2C+AppConstants.B2C_FORGOT_PASSWORD,
                    "1", "25.300579", "55.307709", "en",
                    "ios-48195B02-3646-4EDF-A46B-67646935624B", "9143773", AppConstants.testDataOSPlatform,
                    AppConstants.testDataOSVersion, randomEmail, AppConstants.testDataOSPlatform, AppConstants.testDataTimeZone);
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                    .contentType("application/json")
                    .body(bodyData)
                    .log().all();
            response = httpRequest.post();
            jsonPath = response.jsonPath();
            jsonPath = new JsonPath(response.asString());
            int statusCode = response.statusCode();
            log.info(String.valueOf(statusCode));
            Assert.assertEquals(400, statusCode, "Status Code Validated");
        }
    }
}
