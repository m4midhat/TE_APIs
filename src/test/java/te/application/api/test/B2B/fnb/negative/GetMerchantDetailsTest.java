package te.application.api.test.B2B.fnb.negative;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2BbaseTest;
import te.application.api.baseTest.B2CBaseTest;
import te.application.appConstants.AppConstants;

import static te.application.utilities.Utils.generateInvalidAuthToken;
import static te.application.utilities.generateAPIBody.merchantDetails;

@Slf4j
public class GetMerchantDetailsTest extends B2CBaseTest {

    private int merchantID;



    @Test(description = "Testing with invalid authentication token")
    public void invalidAuthToken(){
        String bodyData = merchantDetails(AppConstants.requestOSPlatform, "default", "entertainer", "Travel", "none", 0,1, "None",
                "False", 31930199, "en", "json", merchantID, 87237, "None", "USD", "None", "None", "None",
                "None", AppConstants.requestDeviceKey, "redeemable_reusable", AppConstants.requestAppVersion);
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_MERCHANT") + merchantID;
        String invalidToken = generateInvalidAuthToken();
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ invalidToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(401, statusCode, "Invalid status code returned for using invalid authentication token");
    }

}
