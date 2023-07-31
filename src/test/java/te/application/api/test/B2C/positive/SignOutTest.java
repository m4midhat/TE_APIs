package te.application.api.test.B2C.positive;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2CBaseTest;
import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class SignOutTest extends B2CBaseTest{

@BeforeClass
    public void setupForLogout() {
        RestAssured.baseURI = AppConstants.BASE_URI_B2C;
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNOUT;

        String bodyData = generateAPIBody.SignOutTest("en", "25.300579", "entertainer",
                AppConstants.testDataOSPlatform, AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1", "9120772", "AED", "9120772", "55.307709",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1", AppConstants.BASE_URI_B2C+AppConstants.BASE_PATH_SIGNOUT,
                Utils.decodeString(authToken.B2CAUTH_TOKEN), AppConstants.sessionID, "1", AppConstants.testDataOSVersion,
                AppConstants.testDataDeviceModel, AppConstants.testDataTimeZone, "55.307709", AppConstants.testDataOSPlatform,"25.300579");
        System.out.println(bodyData);
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();

        String get_message = jsonPath.getString("message");
        System.out.println(get_message);

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
