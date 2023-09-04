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

import static org.testng.Assert.assertEquals;

@Slf4j
public class SignOutTest extends B2CBaseTest{

@BeforeClass
    public void setupForLogout() {
        RestAssured.baseURI = endPoints.getProperty("BASE_URI_B2C");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNOUT");

        String bodyData = generateAPIBody.SignOutTest("en", "25.300579", "entertainer",
                AppConstants.requestOSPlatform, AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey, AppConstants.requestCurrency, "55.307709",
                AppConstants.requestDeviceKey, endPoints.getProperty("BASE_URI_B2C")+endPoints.getProperty("BASE_PATH_SIGNOUT"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN), AppConstants.sessionID, "1", AppConstants.requestOSVersion,
                AppConstants.requestDeviceModel, AppConstants.requestTimeZone, "55.307709", AppConstants.requestOSPlatform,"25.300579");
        log.info(bodyData);
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .header("User-Agent", AppConstants.requestUserAgent)
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        log.info(response.asString());
        jsonPath = response.jsonPath();

        String get_message = jsonPath.getString("message");
        log.info(get_message);

    }

    @Test(priority = 86, description = "Status code check")
    public void checkStatus() {
        String get_message = jsonPath.getString("message");
        Assert.assertNotNull(get_message);
        assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
        if (get_message.isEmpty()) {

            setupForLogout();

        }

    }
}
