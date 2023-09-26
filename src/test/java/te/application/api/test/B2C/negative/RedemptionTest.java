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
import te.application.data.response.redemption;
import te.application.utilities.Utils;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static te.application.utilities.dbDriver.getRandomOfferFromDB;
import static te.application.utilities.generateAPIBodyB2C.RedemptionsDetails;

@Slf4j
public class RedemptionTest extends B2CBaseTest {


    @Test (priority = 75, description = "invalid merchant pin")
    public void Redemption_with_invalid_merchant_pin() throws IOException {

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
        int outlet_id= Integer.parseInt(dbInfo.get(4));
        int product_id= Integer.parseInt(dbInfo.get(5));
        int offer= Integer.parseInt(dbInfo.get(0));

        String bodyData = RedemptionsDetails("25.300579",AppConstants.requestOSPlatform,"entertainer",AppConstants.requestLanguage,
                AppConstants.requestAppVersion, AppConstants.requestDeviceKey,
                AppConstants.requestCurrency,"ios-277C9450-8B64-4521-9B89-3583B6F788D7",
                AppConstants.requestDeviceKey, offer,"7777",AppConstants.sessionID ,
                "1",1,1,product_id,"0",AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"55.307709",AppConstants.requestOSPlatform,
                AppConstants.requestCurrency,outlet_id,"0");

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
        String msg = jsonPath.getString("message");
        Assert.assertNotNull(msg);
        assertEquals(redemption.invalidPin, msg, "Invalid merchant pin error should be displayed");
        assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 200");
    }

}
