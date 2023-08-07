package te.application.api.test.B2C.positive;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
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
import java.util.List;

import static org.testng.Assert.assertEquals;
import static te.application.utilities.dbDriver.getRandomOfferFromDB;

public class RedemptionTest  extends B2CBaseTest {

    @BeforeClass
    public void testRedemptionsData() throws IOException {

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


        String bodyData = generateAPIBody.RedemptionsDetails("25.300579","ios","entertainer",AppConstants.requestLanguage,"8.03.01",
                "ios-277C9450-8B64-4521-9B89-3583B6F788D7", dbInfo.get(0),"USD", "9142525","ios-277C9450-8B64-4521-9B89-3583B6F788D7","ios-277C9450-8B64-4521-9B89-3583B6F788D7",
                offer,dbInfo.get(2),AppConstants.sessionID ,"1",1,1,product_id,"0","ios","Unknown Device",
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