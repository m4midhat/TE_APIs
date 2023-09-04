package te.application.api.test.B2C.positive;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import te.application.data.response.pingSendOffers;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;

@Slf4j
public class PingSendOffersTest extends B2CBaseTest {
    JsonArray shareOffersArray;
    String jsonData = "";
    JsonObject dataObject;
    String locationID, languageCode;

    public PingSendOffersTest(String loc, String lang){
        this.languageCode = lang;
        this.locationID = loc;
    }


    @Factory
    public static Object[] factoryMethod() {
        return new Object[]
                {
                        new PingSendOffersTest("1", "en"),
                        new PingSendOffersTest("1", "ar"),
                        new PingSendOffersTest("1", "ru"),
                        new PingSendOffersTest("2", "en"),
                        new PingSendOffersTest("2", "ar"),
                        new PingSendOffersTest("2", "ru"),
                        new PingSendOffersTest("3", "en"),
                        new PingSendOffersTest("3", "ar"),
                        new PingSendOffersTest("3", "ru"),
                        new PingSendOffersTest("6", "en"),
                        new PingSendOffersTest("6", "ar"),
                        new PingSendOffersTest("6", "ru"),
                        new PingSendOffersTest("7", "en"),
                        new PingSendOffersTest("7", "ar"),
                        new PingSendOffersTest("7", "ru"),
                        new PingSendOffersTest("8", "en"),
                        new PingSendOffersTest("8", "ar"),
                        new PingSendOffersTest("8", "ru"),
                        new PingSendOffersTest("9", "en"),
                        new PingSendOffersTest("9", "ar"),
                        new PingSendOffersTest("9", "ru"),
                        new PingSendOffersTest("10", "en"),
                        new PingSendOffersTest("10", "ar"),
                        new PingSendOffersTest("10", "ru"),
                        new PingSendOffersTest("11", "en"),
                        new PingSendOffersTest("11", "ar"),
                        new PingSendOffersTest("11", "ru"),
                        new PingSendOffersTest("18", "en"),
                        new PingSendOffersTest("18", "ar"),
                        new PingSendOffersTest("18", "ru"),
                        new PingSendOffersTest("49", "en"),
                        new PingSendOffersTest("49", "ar"),
                        new PingSendOffersTest("49", "ru")
                };
    }

    @BeforeClass

    public void setUp() throws IOException {

        RestAssured.basePath = endPoints.getProperty("BASE_PATH_PINGS_SEND_OFFERS");

        String bodyData = generateAPIBody.pingSendOffers(languageCode, "74.3528115",
                "31.527362", "entertainer", AppConstants.requestOSPlatform, AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey, AppConstants.requestCurrency, AppConstants.requestDeviceKey, locationID,
                AppConstants.requestDeviceModel, AppConstants.requestTimeZone, AppConstants.UserID);
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .header("User-Agent", AppConstants.requestUserAgent)
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        log.info(response.asString());
        jsonPath = response.jsonPath();
        log.info("Session ID : " + AppConstants.sessionID);
//.............................................................................
        String jsonData = response.asString();
        JsonParser parser = new JsonParser();
        // Parse the response to JsonObject
        JsonElement root = parser.parseString(response.asString());
        JsonObject jsonObject = root.getAsJsonObject();
        dataObject = jsonObject.getAsJsonObject("data");
        shareOffersArray = dataObject.getAsJsonArray("shareOffers");
        log.info("......................................................");
        log.info(String.valueOf(shareOffersArray));
        log.info("......................................................");
    }

    @Test(priority = 300, description = "Status code check")
    public void checkStatus() {
        log.info("status code: " + response.getStatusCode());
        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
        log.info(String.valueOf(response.getStatusCode()));
    }

    @Test(priority = 301, description = "Test offerMerchantName", dependsOnMethods = "checkStatus")
    public void merchantName() {
        if (shareOffersArray.size() == 0) {
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String offerMerchantName = jsonPath.getString("data.shareOffers[" + i + "].merchantName");
                log.info("offerMerchantName : " + i + " " + offerMerchantName);
                Assert.assertNotNull(offerMerchantName, "offerMerchantName is null");
            }
        }
    }

    @Test(priority = 302, description = "Test offerName", dependsOnMethods = "checkStatus")
    public void offerName() {
        if (shareOffersArray.size() == 0) {
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String offerName = jsonPath.getString("data.shareOffers[" + i + "].offerName");
                log.info("offerName : " + i + " " + offerName);
                Assert.assertNotNull(offerName, "offerName is null");
            }
        }
    }

    @Test(priority = 303, description = "Test offerImageURL", dependsOnMethods = "checkStatus")
    public void offerImageURL() {
        if (shareOffersArray.size() == 0) {
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String logoSmallUrl = jsonPath.getString("data.shareOffers[" + i + "].logoSmallUrl");
                log.info("logoSmallUrl : " + i + " " + logoSmallUrl);
                Assert.assertNotNull(logoSmallUrl, "logoSmallUrl is null");
            }
        }
    }

    @Test(priority = 304, description = "Test offerIsSent", dependsOnMethods = "checkStatus")
    public void offerisSent() {
        if (shareOffersArray.size() == 0) {
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                Boolean OfferIsSentTest = jsonPath.getBoolean("data.shareOffers[" + i + "].is_sent");
                log.info("OfferIsSentTest : " + i + " " + OfferIsSentTest);
                Assert.assertTrue(OfferIsSentTest, "offer is not sent");
            }
        }
    }

    @Test(priority = 305, description = "Test offer Ping Is Accepted", dependsOnMethods = "checkStatus")

    public void offerPingIsAccepted() {
        if (shareOffersArray.size() == 0) {
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {
                int status = jsonPath.getInt("data.shareOffers[" + i + "].status");
                boolean pingStatus = jsonPath.getBoolean("data.shareOffers[" + i + "].is_accepted");
                log.info("pingStatus : " + status + " " + pingStatus);
                if (status == 0) {
                    Assert.assertFalse(pingStatus, "is_accepted");
                } else if (status == 1) {
                    Assert.assertTrue(pingStatus, "is_accepted");
                } else if (status == 3) {
                    Assert.assertFalse(pingStatus, "is not accepted");
                } else {
                    log.info("something is wrong with the status : " + i);
                }
            }
        }
    }

    @Test(priority = 306, description = "Test offerPingStatus", dependsOnMethods = "checkStatus")
    public void offerPingStatus() {
        if (shareOffersArray.size() == 0) {
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {
                int status = jsonPath.getInt("data.shareOffers[" + i + "].status");
                String pingStatus = jsonPath.getString("data.shareOffers[" + i + "].ping_status");
                log.info("pingStatus : " + status + " " + pingStatus);

                if (status == 0) {
                    Assert.assertEquals("Sent", pingStatus);
                } else if (status == 1) {
                    if(languageCode.compareToIgnoreCase("en")==0){
                        Assert.assertEquals(pingSendOffers.PING_ACCEPTED_EN, pingStatus);
                    }
                    else if(languageCode.compareToIgnoreCase("ar")==0){
                        Assert.assertEquals(pingSendOffers.PING_ACCEPTED_AR, pingStatus);
                    }
                    else if(languageCode.compareToIgnoreCase("ru")==0){
                        Assert.assertEquals(pingSendOffers.PING_ACCEPTED_RU, pingStatus);
                    }

                } else if (status == 3) {
                    Assert.assertEquals("Recalled", pingStatus);
                } else {
                    log.info("something is wrong with the status : " + i);
                }
            }
        }
    }

    @Test(priority = 305, description = "Test recall_button_text", dependsOnMethods = "checkStatus")
    public void offerPingRecallButton() {
        if (shareOffersArray.size() == 0) {
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {
                String recallButtonText = jsonPath.getString("data.shareOffers[" + i + "].recall_button_text");
                log.info("recallButtonText : " + i + " " + recallButtonText);
                if(languageCode.compareToIgnoreCase("en")==0) {
                    Assert.assertEquals(pingSendOffers.RECALL_EN, recallButtonText);
                }
                else if(languageCode.compareToIgnoreCase("ar")==0) {
                    Assert.assertEquals(pingSendOffers.RECALL_AR, recallButtonText);
                }
                else if(languageCode.compareToIgnoreCase("ru")==0) {
                    Assert.assertEquals(pingSendOffers.RECALL_RU, recallButtonText);
                }
            }
        }
    }

    @Test(priority = 306, description = "Test recall_message", dependsOnMethods = "checkStatus")
    public void offerPingRecallMessage() {
        if (shareOffersArray.size() == 0) {
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String recallMessage = jsonPath.getString("data.shareOffers[" + i + "].recall_message");
                log.info("recallMessage : " + i + " " + recallMessage);
                if(languageCode.compareToIgnoreCase("en")==0){
                    Assert.assertEquals(pingSendOffers.PING_RECALL_MSG_ENG, recallMessage);
                }
                else if(languageCode.compareToIgnoreCase("ar")==0){
                    Assert.assertEquals(pingSendOffers.PING_RECALL_MSG_AR, recallMessage);
                }
                else if(languageCode.compareToIgnoreCase("ru")==0){
                    Assert.assertEquals(pingSendOffers.PING_RECALL_MSG_RU, recallMessage);
                }
            }
        }
    }

    @Test(priority = 306, description = "Test recall_title", dependsOnMethods = "checkStatus")
    public void offerPingRecallTitle() {
        if (shareOffersArray.size() == 0) {
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {
                String recallTitle = jsonPath.getString("data.shareOffers[" + i + "].recall_title");
                log.info("recallTitle : " + i + " " + recallTitle);
                if(languageCode.compareToIgnoreCase("en")==0) {
                    Assert.assertEquals(pingSendOffers.RECALL_PING_EN, recallTitle);
                }
                else if(languageCode.compareToIgnoreCase("ar")==0) {
                    Assert.assertEquals(pingSendOffers.RECALL_PING_AR, recallTitle);
                }
                else if(languageCode.compareToIgnoreCase("ru")==0) {
                    Assert.assertEquals(pingSendOffers.RECALL_PING_RU, recallTitle);
                }
            }
        }
    }

    @Test(priority = 307, description = "Test message", dependsOnMethods = "checkStatus")
    public void offerMessage() {
        String messageTest = jsonPath.getString("data.ping_section.message");
        log.info("messageTest :  " + messageTest);
        Assert.assertNotNull(messageTest, "messageTest is null");
    }

    @Test(priority = 311, description = "Test offerPingInfo", dependsOnMethods = "checkStatus")
    public void offerPingInfo() {
        if (shareOffersArray.size() == 0) {
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String pingInfoTest = jsonPath.getString("data.shareOffers[" + i + "].ping_info");
                log.info("pingInfoTest : " + i + " " + pingInfoTest);
                Assert.assertNotNull(pingInfoTest, "pingInfo is null");
            }
        }
    }

    @Test(priority = 312, description = "Test offerPingIsCancellable", dependsOnMethods = "checkStatus")
    public void offerPingIsCancellable() {
        if (shareOffersArray.size() == 0) {
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {
                Integer status = jsonPath.getInt("data.shareOffers[" + i + "].status");
                Boolean pingCancellableStatus = jsonPath.getBoolean("data.shareOffers[" + i + "].is_cancellable");
                log.info("pingCancellableStatus : " + status + " " + pingCancellableStatus);
                if (status == 0) {
                    Assert.assertTrue(pingCancellableStatus, "not cancellable");

                } else if (status == 1) {
                    Assert.assertFalse(pingCancellableStatus, "is cancellable");
                } else if (status == 3) {

                    Assert.assertFalse(pingCancellableStatus, "is cancellable");
                } else {
                    log.info("something is wrong with the status : " + i);
                }

            }
        }
    }
}