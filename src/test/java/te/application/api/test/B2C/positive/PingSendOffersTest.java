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
import org.testng.annotations.Test;
import te.application.api.baseTest.B2CBaseTest;
import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;

@Slf4j
public class PingSendOffersTest extends B2CBaseTest {
    JsonArray shareOffersArray;
    String jsonData = "";
    JsonObject dataObject;
    boolean exists;

    @BeforeClass

    public void setUp() throws IOException {

        RestAssured.basePath = AppConstants.BASE_PATH_PINGS_SEND_OFFERS;

        String bodyData = generateAPIBody.pingSendOffers(AppConstants.requestLanguage, "74.3528115", "31.527362", "entertainer",
                AppConstants.requestOSPlatform, AppConstants.requestAppVersion, "dc57c128bcfc4155", "AED", "9120772", "dc57c128bcfc4155",
                "1", AppConstants.requestDeviceModel, AppConstants.requestTimeZone, "9120772");
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        System.out.println("Session ID : " + AppConstants.sessionID);
//.............................................................................
        String jsonData = response.asString();
        JsonParser parser = new JsonParser();
        // Parse the response to JsonObject
        JsonElement root = parser.parseString(response.asString());
        JsonObject jsonObject = root.getAsJsonObject();
        dataObject = jsonObject.getAsJsonObject("data");
        shareOffersArray = dataObject.getAsJsonArray("shareOffers");
        System.out.println("......................................................");
        System.out.println(shareOffersArray);
        System.out.println("......................................................");
    }

    @Test(priority = 300, description = "Status code check")
    public void CheckStatus() {
        System.out.println("status code: " + response.getStatusCode());
        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
        System.out.println(response.getStatusCode());
    }

    @Test(priority = 301, description = "Test offerMerchantName")
    public void merchantName() {
        if (shareOffersArray.size() == 0) {
            System.out.println(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String offerMerchantName = jsonPath.getString("data.shareOffers[" + i + "].merchantName");
                System.out.println("offerMerchantName : " + i + " " + offerMerchantName);
                Assert.assertNotNull(offerMerchantName, "offerMerchantName is null");
            }
        }
    }

    @Test(priority = 302, description = "Test offerName")
    public void offerName() {
        if (shareOffersArray.size() == 0) {
            System.out.println(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String offerName = jsonPath.getString("data.shareOffers[" + i + "].offerName");
                System.out.println("offerName : " + i + " " + offerName);
                Assert.assertNotNull(offerName, "offerName is null");
            }
        }
    }

    @Test(priority = 303, description = "Test offerImageURL")
    public void offerImageURL() {
        if (shareOffersArray.size() == 0) {
            System.out.println(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String logoSmallUrl = jsonPath.getString("data.shareOffers[" + i + "].logoSmallUrl");
                System.out.println("logoSmallUrl : " + i + " " + logoSmallUrl);
                Assert.assertNotNull(logoSmallUrl, "logoSmallUrl is null");
            }
        }
    }

    @Test(priority = 304, description = "Test offerisSent")
    public void offerisSent() {
        if (shareOffersArray.size() == 0) {
            System.out.println(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                Boolean OfferIsSentTest = jsonPath.getBoolean("data.shareOffers[" + i + "].is_sent");
                System.out.println("OfferIsSentTest : " + i + " " + OfferIsSentTest);
                Assert.assertTrue(OfferIsSentTest, "offer is not sent");
            }
        }
    }

    @Test(priority = 305, description = "Test offer Ping Is Accepted")

    public void offerPingIsAccepted() {
        if (shareOffersArray.size() == 0) {
            System.out.println(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {
                int status = jsonPath.getInt("data.shareOffers[" + i + "].status");
                boolean pingStatus = jsonPath.getBoolean("data.shareOffers[" + i + "].is_accepted");
                System.out.println("pingStatus : " + status + " " + pingStatus);
                if (status == 0) {
                    Assert.assertFalse(pingStatus, "is_accepted");
                } else if (status == 1) {
                    Assert.assertTrue(pingStatus, "is_accepted");
                } else if (status == 3) {
                    Assert.assertFalse(pingStatus, "is not accepted");
                } else {
                    System.out.println("something is wrong with the status : " + i);
                }
            }
        }
    }

    @Test(priority = 306, description = "Test offerPingStatus")
    public void offerPingStatus() {
        if (shareOffersArray.size() == 0) {
            System.out.println(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {
                int status = jsonPath.getInt("data.shareOffers[" + i + "].status");
                String pingStatus = jsonPath.getString("data.shareOffers[" + i + "].ping_status");
                System.out.println("pingStatus : " + status + " " + pingStatus);

                if (status == 0) {
                    Assert.assertEquals("Sent", pingStatus);
                } else if (status == 1) {
                    Assert.assertEquals("Accepted", pingStatus);
                } else if (status == 3) {
                    Assert.assertEquals("Recalled", pingStatus);
                } else {
                    System.out.println("something is wrong with the status : " + i);
                }
            }
        }
    }

    @Test(priority = 305, description = "Test recall_button_text")
    public void offerPingRecallButton() {
        if (shareOffersArray.size() == 0) {
            System.out.println(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String recallButtonText = jsonPath.getString("data.shareOffers[" + i + "].recall_button_text");
                System.out.println("recallButtonText : " + i + " " + recallButtonText);
                Assert.assertEquals("Recall", recallButtonText);
            }
        }
    }

    @Test(priority = 306, description = "Test recall_message")
    public void offerPingRecallMessage() {
        if (shareOffersArray.size() == 0) {
            System.out.println(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String recallMessage = jsonPath.getString("data.shareOffers[" + i + "].recall_message");
                System.out.println("recallMessage : " + i + " " + recallMessage);
                Assert.assertEquals("Are you sure want to recall this ping?", recallMessage);
            }
        }
    }

    @Test(priority = 306, description = "Test recall_title")
    public void offerPingRecallTitle() {
        if (shareOffersArray.size() == 0) {
            System.out.println(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String recallTitle = jsonPath.getString("data.shareOffers[" + i + "].recall_title");
                System.out.println("recallTitle : " + i + " " + recallTitle);
                Assert.assertEquals("Recall Ping", recallTitle);
            }
        }
    }

    @Test(priority = 307, description = "Test message")
    public void offerMessage() {
        String messageTest = jsonPath.getString("data.ping_section.message");
        System.out.println("messageTest :  " + messageTest);
        Assert.assertNotNull(messageTest, "messageTest is null");
    }

    @Test(priority = 311, description = "Test offerPingInfo")
    public void offerPingInfo() {
        if (shareOffersArray.size() == 0) {
            System.out.println(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String pingInfoTest = jsonPath.getString("data.shareOffers[" + i + "].ping_info");
                System.out.println("pingInfoTest : " + i + " " + pingInfoTest);
                Assert.assertNotNull(pingInfoTest, "pingInfo is null");
            }
        }
    }

    @Test(priority = 312, description = "Test offerPingIsCancellable")
    public void offerPingIsCancellable() {
        if (shareOffersArray.size() == 0) {
            System.out.println(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        } else {
            for (int i = 0; i < shareOffersArray.size(); i++) {
                Integer status = jsonPath.getInt("data.shareOffers[" + i + "].status");
                Boolean pingCancellableStatus = jsonPath.getBoolean("data.shareOffers[" + i + "].is_cancellable");
                System.out.println("pingCancellableStatus : " + status + " " + pingCancellableStatus);
                if (status == 0) {
                    Assert.assertTrue(pingCancellableStatus, "not cancellable");

                } else if (status == 1) {
                    Assert.assertFalse(pingCancellableStatus, "is cancellable");
                } else if (status == 3) {

                    Assert.assertFalse(pingCancellableStatus, "is cancellable");
                } else {
                    System.out.println("something is wrong with the status : " + i);
                }

            }
        }
    }
}