package te.application.api.test.B2C.negative;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2CBaseTest;
import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.utilities.Utils;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static te.application.utilities.dbDriver.getRandomOfferFromDB;
import static te.application.utilities.generateAPIBody.RedemptionsDetails;

public class RedemptionTest extends B2CBaseTest {


    @Test (priority = 75, description = "invalid merchant pin")
    public void Redemption_with_invalid_merchant_pin() throws IOException {

        List<String > dbInfo = getRandomOfferFromDB();
        System.out.println(dbInfo);
        while (dbInfo.isEmpty()){

            dbInfo= getRandomOfferFromDB();
        }
        System.out.println(dbInfo.get(0));
        System.out.println(dbInfo.get(1));
        System.out.println(dbInfo.get(2));
        System.out.println(dbInfo.get(3));
        System.out.println(dbInfo.get(4));
        System.out.println(dbInfo.get(5));
        int outlet_id= Integer.valueOf(dbInfo.get(4));
        int product_id= Integer.valueOf(dbInfo.get(5));
        int offer= Integer.valueOf(dbInfo.get(0));

        String bodyData = RedemptionsDetails("25.300579","ios","entertainer",AppConstants.requestLanguage,"8.03.01",
                "ios-277C9450-8B64-4521-9B89-3583B6F788D7", dbInfo.get(0),"USD",AppConstants.UserID,"ios-277C9450-8B64-4521-9B89-3583B6F788D7","ios-277C9450-8B64-4521-9B89-3583B6F788D7",
                offer,"7777",AppConstants.sessionID ,"1",1,1,product_id,"0","ios","Unknown Device",
                "Asia/Karachi","55.307709","ios",
                "AED",outlet_id,"0");
        System.out.println(bodyData);

        RestAssured.basePath = AppConstants.BASE_PATH_REDEEM;
        RequestSpecification httpRequest = RestAssured.given()

                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        System.out.println(jsonPath);
        String get_message = jsonPath.getString("message");
        Assert.assertNotNull(get_message);
        assertEquals("Invalid merchant PIN", get_message, "Try again");
        assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 200");
    }

}
