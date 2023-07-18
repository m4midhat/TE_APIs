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
import te.application.appConstants.bearerToken;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.MILLIS;
import static org.testng.Assert.*;
import static te.application.utilities.generateAPIBody.merchantDetails;

@Slf4j
public class GetMerchantDetailsTest extends B2CBaseTest {

    private int merchantID;
    private long res;

    public GetMerchantDetailsTest(int merchantID){
        this.merchantID = merchantID;
        //log.info(String.valueOf(this.merchantID));
    }


    @Factory
    public static Object[] factoryMethod()
    {
        return new Object[]
                {
                        new GetMerchantDetailsTest(11133)
                };
    }

    @BeforeClass
    public void testGetMerchantsData()  {
        LocalDateTime requestTime = LocalDateTime.now();
        String bodyData = merchantDetails("andriod", "default", "entertainer", "Travel", 9120877, "none", 0,1, "None",
                "False", 31930199, "en", "json", merchantID, 11133, "None", "USD", "None", "None", "None",
                "None", "3bc5d207fb86dab8", AppConstants.sessionID, "redeemable_reusable", "8.04.02");
        RestAssured.basePath = AppConstants.B2C_BASE_PATH_MERCHANT + merchantID;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", bearerToken.B2C)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        log.info(response.asString());
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        LocalDateTime responseTime = LocalDateTime.now();
        log.info(requestTime+ "||" + responseTime);
        res = MILLIS.between(requestTime, responseTime);
    }

    @Test(description = "Time taken by the API should be less than expected time", priority = 100)
    public void timeTakenByAPI() {
        log.info("Time taken by API : "+ res);
        assertFalse(res > AppConstants.EXPECTED_API_TIME, "(MerchantID : "+merchantID+"). Expected time should be less than "+AppConstants.EXPECTED_API_TIME);
    }

    @Test(description = "Status code should be 200", priority = 101)
    public void statusCodeShouldBe200(){
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 200, "(MerchantID : "+merchantID+"). Status code should be 200");
    }

    @Test(description = "Success should be true", priority = 102)
    public void successShouldBeTrue(){
        String successValue = jsonPath.getString("success");
        log.info(successValue);
        Assert.assertEquals(successValue, "true", "(MerchantID : "+merchantID+"). success attribute should be 'true'");
    }

    @Test(description = "Message should be success", priority = 103)
    public void messageShouldBeSuccess(){
        String messageValue = jsonPath.getString("message");
        log.info(messageValue);
        Assert.assertEquals(messageValue, "success", "(MerchantID : "+merchantID+"). Message should be 'success'");
    }

    @Test(description = "data node should be populated", priority = 104)
    public void dataNodeShouldBePopulated(){
        int size = jsonPath.getInt("data.merchant.size()");
        log.info("size : "+size);
        assertFalse(Optional.of(size).equals(0) , "(MerchantID : "+merchantID+"). Merchant size should not be 0");
    }

    @Test(description = "Merchant id should be populated", priority = 105)
    public void merchantIdShouldBePopulated(){
        int merchantId = jsonPath.getInt("data.merchant.id");
        log.info(String.valueOf(merchantId));
        assertNotNull(Optional.of(merchantId), "(MerchantID : "+merchantID+"). MerchantID should be populated/not null");
    }

    @Test(description = "Merchant pin should be populated", priority = 106)
    public void merchantPinShouldBePopulated(){
        String merchantPin = jsonPath.getString("data.merchant.merchant_pin");
        log.info(merchantPin);
        assertNotNull(merchantPin, "(MerchantID : "+merchantID+"). MerchantPin should be populated/not null");
    }

    @Test(description = "Merchant email address should be populated", priority = 107)
    public void merchantEmailAddressShouldBePopulated(){
        String merchantEmail = jsonPath.getString("data.merchant.email");
        log.info(merchantEmail);
        assertNotNull(merchantEmail, "(MerchantID : "+merchantID+"). MerchantEmail should be populated/not null");
    }

    @Test(description = "Merchant website should be populated", priority = 108)
    public void merchantWebsiteShouldBePopulated(){
        String merchantWebSite = jsonPath.getString("data.merchant.website");
        log.info(merchantWebSite);
        assertNotNull(merchantWebSite, "(MerchantID : "+merchantID+"). MerchantWebSite should be populated/not null");
    }



}
