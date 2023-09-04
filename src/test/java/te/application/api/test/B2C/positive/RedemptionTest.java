package te.application.api.test.B2C.positive;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
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
import java.util.List;

import static org.testng.Assert.assertEquals;
import static te.application.utilities.dbDriver.getRandomOfferFromDB;

@Slf4j
public class RedemptionTest  extends B2CBaseTest {
    String locationID, languageCode;

    public RedemptionTest(String loc, String lang){
        this.languageCode = lang;
        this.locationID = loc;
    }

    @Factory
    public static Object[] factoryMethod() {
        return new Object[]
                {
                        new RedemptionTest("1", "en"),
                        new RedemptionTest("1", "ar"),
                        new RedemptionTest("1", "ru"),
                        new RedemptionTest("2", "en"),
                        new RedemptionTest("2", "ar"),
                        new RedemptionTest("2", "ru"),
                        new RedemptionTest("3", "en"),
                        new RedemptionTest("3", "ar"),
                        new RedemptionTest("3", "ru"),
                        new RedemptionTest("6", "en"),
                        new RedemptionTest("6", "ar"),
                        new RedemptionTest("6", "ru"),
                        new RedemptionTest("7", "en"),
                        new RedemptionTest("7", "ar"),
                        new RedemptionTest("7", "ru"),
                        new RedemptionTest("8", "en"),
                        new RedemptionTest("8", "ar"),
                        new RedemptionTest("8", "ru"),
                        new RedemptionTest("9", "en"),
                        new RedemptionTest("9", "ar"),
                        new RedemptionTest("9", "ru"),
                        new RedemptionTest("10", "en"),
                        new RedemptionTest("10", "ar"),
                        new RedemptionTest("10", "ru"),
                        new RedemptionTest("11", "en"),
                        new RedemptionTest("11", "ar"),
                        new RedemptionTest("11", "ru"),
                        new RedemptionTest("18", "en"),
                        new RedemptionTest("18", "ar"),
                        new RedemptionTest("18", "ru"),
                        new RedemptionTest("49", "en"),
                        new RedemptionTest("49", "ar"),
                        new RedemptionTest("49", "ru")
                };
    }

    @BeforeClass
    public void testRedemptionsData() throws IOException {

        List<String > dbInfo = getRandomOfferFromDB();
        log.info(dbInfo.toString());
        while (dbInfo.isEmpty()){

            dbInfo= getRandomOfferFromDB();
        }
        log.info(dbInfo.get(0));
        log.info(dbInfo.get(1));
        log.info(dbInfo.get(2));
        log.info(dbInfo.get(3));
        log.info(dbInfo.get(4));
        log.info(dbInfo.get(5));
        int outlet_id= Integer.valueOf(dbInfo.get(4));
        int product_id= Integer.valueOf(dbInfo.get(5));
        int offer= Integer.valueOf(dbInfo.get(0));


        String bodyData = generateAPIBody.RedemptionsDetails("25.300579",AppConstants.requestOSPlatform,"entertainer",
                AppConstants.requestLanguage,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"ios-277C9450-8B64-4521-9B89-3583B6F788D7",
                AppConstants.requestDeviceKey, offer,dbInfo.get(2),AppConstants.sessionID ,locationID,1,
                1,product_id,"0",AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"55.307709",AppConstants.requestOSPlatform,
                AppConstants.requestCurrency,outlet_id,"0");
        log.info(bodyData);

        RestAssured.basePath = endPoints.getProperty("BASE_PATH_REDEEM");
        RequestSpecification httpRequest = RestAssured.given()

                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .header("User-Agent", AppConstants.requestUserAgent)
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        log.info(response.asString());
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        log.info(String.valueOf(jsonPath));
        String get_message = jsonPath.getString("message");
        Assert.assertNotNull(get_message);


    }
    @Test(priority = 72, description = "Verify the Status code" )
    public void check_Status(){
        // "Session has been expired."
        String get_message = jsonPath.getString("message");
        if (get_message.equalsIgnoreCase("Session has been expired.")){
            assertEquals(801, response.getStatusCode(), "Incorrect status code returned, expected value 200");
        }

        if (get_message.equalsIgnoreCase("You are not authorized to access this user")) {
            assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 200");
        }
        if (get_message.equalsIgnoreCase("Internal Server Error") ){
            assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 200");
        }
        if (get_message.equalsIgnoreCase("This offer is no longer available.")){
            assertEquals(422, response.getStatusCode(), "try another offer");
        }

        if (get_message.equalsIgnoreCase("success") ){
            assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
        }
        if (get_message.equalsIgnoreCase("Popular offer alert! We'll add more codes soon- take a look at the other offers we have for this merchant.")){
            assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 200");
        }

    }
}