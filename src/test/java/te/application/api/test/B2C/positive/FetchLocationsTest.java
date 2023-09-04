package te.application.api.test.B2C.positive;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2CBaseTest;
import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;
import java.util.Properties;

import static te.application.data.response.locations.*;

@Slf4j
public class FetchLocationsTest extends B2CBaseTest {

    String locationID, languageCode;

    public FetchLocationsTest(String loc, String lang){
        this.languageCode = lang;
        this.locationID = loc;
    }

    @Factory
    public static Object[] factoryMethod() {
        return new Object[]
                {
                        new FetchLocationsTest("1", "en"),
                        new FetchLocationsTest("1", "ar"),
                        new FetchLocationsTest("1", "ru"),
                        new FetchLocationsTest("2", "en"),
                        new FetchLocationsTest("2", "ar"),
                        new FetchLocationsTest("2", "ru"),
                        new FetchLocationsTest("3", "en"),
                        new FetchLocationsTest("3", "ar"),
                        new FetchLocationsTest("3", "ru"),
                        new FetchLocationsTest("6", "en"),
                        new FetchLocationsTest("6", "ar"),
                        new FetchLocationsTest("6", "ru"),
                        new FetchLocationsTest("7", "en"),
                        new FetchLocationsTest("7", "ar"),
                        new FetchLocationsTest("7", "ru"),
                        new FetchLocationsTest("8", "en"),
                        new FetchLocationsTest("8", "ar"),
                        new FetchLocationsTest("8", "ru"),
                        new FetchLocationsTest("9", "en"),
                        new FetchLocationsTest("9", "ar"),
                        new FetchLocationsTest("9", "ru"),
                        new FetchLocationsTest("10", "en"),
                        new FetchLocationsTest("10", "ar"),
                        new FetchLocationsTest("10", "ru"),
                        new FetchLocationsTest("11", "en"),
                        new FetchLocationsTest("11", "ar"),
                        new FetchLocationsTest("11", "ru"),
                        new FetchLocationsTest("18", "en"),
                        new FetchLocationsTest("18", "ar"),
                        new FetchLocationsTest("18", "ru"),
                        new FetchLocationsTest("49", "en"),
                        new FetchLocationsTest("49", "ar"),
                        new FetchLocationsTest("49", "ru")
                };
    }


    @BeforeClass
    public void setUp() throws IOException {

        //Defining api url
        RestAssured.basePath = endPoints.getProperty("B2C_LOCATIONS");
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            String propPassword = Utils.decodeString(properties.getProperty("password"));

            //initializing params
            String bodyData = generateAPIBody.locations(0, languageCode, true, "25.300579", "entertainer",
                    AppConstants.requestOSPlatform, AppConstants.requestAppVersion, AppConstants.requestDeviceKey, "0", AppConstants.requestCurrency,
                    "1", "55.307709", AppConstants.requestDeviceKey, locationID,
                    propPassword, AppConstants.requestOSVersion, AppConstants.requestDeviceModel, AppConstants.requestTimeZone,
                    "1", "55.307709", AppConstants.requestOSPlatform, "25.300579");
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                    .contentType("application/json")
                    .header("User-Agent", AppConstants.requestUserAgent)
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
            if(languageCode.compareToIgnoreCase("en")==0) {
                log.info("Location extracted : " + locationsEng.get(i));
                Assert.assertEquals(jsonPath.getString("data.locations[" + i + "].name"), locationsEng.get(i), "Incorrect location returned");
            }
            else if(languageCode.compareToIgnoreCase("ar")==0) {
                log.info("Location extracted : " + locationsAr.get(i));
                Assert.assertEquals(jsonPath.getString("data.locations[" + i + "].name"), locationsAr.get(i), "Incorrect location returned");
            }
            else if(languageCode.compareToIgnoreCase("ru")==0) {
                log.info("Location extracted : " + locationsRu.get(i));
                Assert.assertEquals(jsonPath.getString("data.locations[" + i + "].name"), locationsRu.get(i), "Incorrect location returned");
            }
        }
    }
}
