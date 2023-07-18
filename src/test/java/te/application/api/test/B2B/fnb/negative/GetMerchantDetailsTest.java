package te.application.api.test.B2B.fnb.negative;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2BbaseTest;
import te.application.appConstants.AppConstants;

import static te.application.utilities.Utils.generateInvalidAuthToken;
import static te.application.utilities.generateAPIBody.merchantDetails;

@Slf4j
public class GetMerchantDetailsTest extends B2BbaseTest {

    private int merchantID;



    @Test(description = "Testing with invalid authentication token")
    public void invalidAuthToken(){
        String bodyData = merchantDetails("andriod", "default", "entertainer", "Travel", 9120877, "none", 0,1, "None",
                "False", 31930199, "en", "json", merchantID, 87237, "None", "USD", "None", "None", "None",
                "None", "3bc5d207fb86dab8", "4e42a64d-89b3-484c-80a7-cb4a67c30dbe", "redeemable_reusable", "8.04.02");
        RestAssured.basePath = AppConstants.BASE_PATH_MERCHANT + merchantID;
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
