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

import static te.application.data.response.locations.locations;

@Slf4j
public class FetchLocationsTest extends B2CBaseTest {

    @BeforeClass
    public void setUp() throws IOException {

        //Defining api url
        RestAssured.basePath = endPoints.getProperty("B2C_LOCATIONS");
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            String propPassword = Utils.decodeString(properties.getProperty("password"));

            //initializing params
            String bodyData = generateAPIBody.locations(0, AppConstants.requestLanguage, true, "25.300579", "entertainer",
                    AppConstants.requestOSPlatform, AppConstants.requestAppVersion, AppConstants.requestDeviceKey, "0", AppConstants.requestCurrency,
                    "1", "55.307709", AppConstants.requestDeviceKey, "1",
                    propPassword, AppConstants.requestOSVersion, AppConstants.requestDeviceModel, AppConstants.requestTimeZone,
                    "1", "55.307709", AppConstants.requestOSPlatform, "25.300579");
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                    .contentType("application/json")
                    .body(bodyData)
                    .log().all();
            response = httpRequest.post();
            log.info(response.asString());
            jsonPath = response.jsonPath();
            //AppConstants.sessionID = jsonPath.getString("data.user.session_token");
            log.info("Session ID : " + AppConstants.sessionID);
        }
        else {
            log.error("Unable to read the file");
        }
    }

    @Test(priority = 0, description = "Status code check")
    public void checkStatus() {
        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
    }

    @Test(priority = 1, description = "Verify locations array size")
    public void verifyLocationArraySize() {
        int size = jsonPath.getInt("data.locations.size()");
        Assert.assertNotEquals(size,0);
    }

    @Test(priority = 2, description = "asserting all locations at each index")
    public void verifyLocationsList(){

        int size = jsonPath.getInt("data.locations.size()");
        for (int i = 0; i < size; i++) {
            log.info("Location extracted : "+ locations.get(i));
            Assert.assertEquals(jsonPath.getString("data.locations["+i+"].name"),locations.get(i), "Incorrect location returned");
        }
    }
}
