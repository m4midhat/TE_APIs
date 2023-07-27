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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public class FetchLocationsTest extends B2CBaseTest {

    List<String> locations = new ArrayList<>();

    String LocationsName="";
    @BeforeClass
    public void setUp() throws IOException {

        locations.add("Dubai & N. Emirates");
        locations.add("Abu Dhabi & Al Ain");
        locations.add("Bahrain");
        locations.add("Cape Town");
        locations.add("Kuwait");
        locations.add("Oman");
        locations.add("Qatar");
        locations.add("Riyadh");
        locations.add("Singapore");
        locations.add("Johannesburg & Pretoria");
        locations.add("United Kingdom");
        locations.add("Jeddah");
        locations.add("Durban");
        locations.add("Athens");
        locations.add("Eastern Province");
        locations.add("Egypt");


        //Defining api url
        RestAssured.basePath = AppConstants.B2C_LOCATIONS;
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            String propUserName = Utils.decodeString(properties.getProperty("username"));
            String propPassword = Utils.decodeString(properties.getProperty("password"));

            //initalizing params
            String bodyData = generateAPIBody.locations(0, "en", true, "25.300579", "entertainer",
                    AppConstants.testDataOSPlatform, "8.04.01", "ios-0C7C873D-8A9B-4D34-948E-3F5C19A6B439", "0", "AED",
                    "0", "1", "55.307709", "ios-0C7C873D-8A9B-4D34-948E-3F5C19A6B439", "1",
                    propPassword, AppConstants.testDataOSVersion, AppConstants.testDataDeviceModel, "Karachi/Islamabad",
                    "1", "8070037d-eea1-4499-882a-b3de8f12d63c", "55.307709", AppConstants.testDataOSPlatform, "25.300579",
                    AppConstants.SessionURL, authToken.B2CAUTH_TOKEN);
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
    }

    @Test(priority = 0, description = "Status code check")
    public void checkStatus() {
        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
    }

    @Test(priority = 1, description = "Verify locations array size")
    public void location_print() {
        int size = jsonPath.getInt("data.locations.size()");
        Assert.assertNotEquals(size,0);
    }

    @Test(priority = 2, description = "asserting all locations at each index")
    public void assert_locations(){

        int size = jsonPath.getInt("data.locations.size()");
        for (int i = 0; i < size; i++) {
            log.info("Location extracted : "+locations.get(i));
            Assert.assertEquals(jsonPath.getString("data.locations["+i+"].name"),locations.get(i), "Incorrect location returned");
        }

    }
}
