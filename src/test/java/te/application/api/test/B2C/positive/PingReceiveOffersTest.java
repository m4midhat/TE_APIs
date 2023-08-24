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
public class PingReceiveOffersTest extends B2CBaseTest {
    JsonArray shareOffersArray;
    String jsonData = "";
    JsonObject dataObject;
    boolean exists;
    @BeforeClass

    public void setUp() throws IOException {

        RestAssured.basePath = endPoints.getProperty("BASE_PATE_PINGS_RECEIVE_OFFERS");
        log.info("\n>>>>>>>>>>>>>>>>>>" + AppConstants.sessionID);

        String bodyData = generateAPIBody.pingReceiveOffers(AppConstants.requestLanguage,"74.3528115","31.527362",
                "entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion, AppConstants.requestDeviceKey,"AED",
                AppConstants.requestDeviceKey,"1",AppConstants.requestDeviceModel,AppConstants.requestTimeZone,"9120772");
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        log.info(response.asString());
        jsonPath = response.jsonPath();
//.............................................................................
        String jsonData =response.asString();
        JsonParser parser = new JsonParser();
        // Parse the response to JsonObject
        JsonElement root = parser.parseString(response.asString());
        JsonObject jsonObject = root.getAsJsonObject();
        dataObject = jsonObject.getAsJsonObject("data");
        shareOffersArray = dataObject.getAsJsonArray("shareOffers");
        log.info(String.valueOf(shareOffersArray));
    }
    @Test(priority = 350, description = "Status code check" )
    public void CheckStatus(){
        log.info("status code: " + response.getStatusCode());
        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
        log.info(String.valueOf(response.getStatusCode()));
    }
    @Test(priority = 351, description = "Test offerMerchantName" )
    public void merchantName() {
        if(shareOffersArray.size()==0){
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        }else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String offerMerchantName = jsonPath.getString("data.shareOffers["+i+"].merchantName");
                log.info("offerMerchantName : "+ i+" "+offerMerchantName);
                Assert.assertNotNull(offerMerchantName,"offerMerchantName is null");
            }}
    }
    @Test(priority = 352, description = "Test offerName" )
    public void offerName() {
        if(shareOffersArray.size()==0){
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        }else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String offerName = jsonPath.getString("data.shareOffers["+i+"].offerName");
                log.info("offerName : "+ i+" "+offerName);
                Assert.assertNotNull(offerName,"offerName is null");
            }}
    }
    @Test(priority = 353, description = "Test offerImageURL" )
    public void offerImageURL() {
        if(shareOffersArray.size()==0){
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        }else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String logoSmallUrl = jsonPath.getString("data.shareOffers["+i+"].logoSmallUrl");
                log.info("logoSmallUrl : "+ i+" "+logoSmallUrl);
                Assert.assertNotNull(logoSmallUrl,"logoSmallUrl is null");
            }}
    }
    @Test(priority = 354, description = "Test offer Ping Is Accepted" )

    public void offerPingIsAccepted() {
        if(shareOffersArray.size()==0){
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        }else {
            for (int i = 0; i < shareOffersArray.size(); i++) {
                int status=jsonPath.getInt("data.shareOffers["+i+"].status");
                boolean pingStatus = jsonPath.getBoolean("data.shareOffers[" + i + "].is_accepted");
                log.info("pingStatus : " + status + " " + pingStatus);
                if(status==0){
                    Assert.assertFalse(pingStatus,"is_accepted");
                }else if(status==1){
                    Assert.assertTrue(pingStatus,"is_accepted");
                }
                else if(status==2){
                    Assert.assertTrue(pingStatus,"is_accepted");
                }
                else {
                    log.info("something is wrong with the status : " + i );
                }
            }}
    }
    @Test(priority = 355, description = "Test offerPingStatus" )
    public void offerPingStatus() {
        if(shareOffersArray.size()==0){
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        }else {
            for (int i = 0; i < shareOffersArray.size(); i++) {
                int status=jsonPath.getInt("data.shareOffers["+i+"].status");
                String pingStatus = jsonPath.getString("data.shareOffers[" + i + "].ping_status");
                log.info("pingStatus : " + status + " " + pingStatus);

                if(status==0){
                    Assert.assertEquals("Sent", pingStatus);
                }else if(status==1){
                    Assert.assertEquals("Accepted", pingStatus);
                }
                else if(status==2){
                    Assert.assertEquals("Rejected", pingStatus);
                }
                else {
                    log.info("something is wrong with the status : " + i );
                }
            }}
    }
    @Test(priority = 356, description = "Test accept_button_text" )
    public void offerPingAcceptButton() {
        if(shareOffersArray.size()==0){
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        }else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String acceptButtonText = jsonPath.getString("data.shareOffers["+i+"].accept_button_text");
                log.info("acceptButtonText : "+ i+" "+acceptButtonText);
                Assert.assertEquals("Accept",acceptButtonText);
            }}
    }
    @Test(priority = 357, description = "Test reject_button_text" )
    public void offerPingRejectButton() {
        if(shareOffersArray.size()==0){
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        }else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String RejectButtonText = jsonPath.getString("data.shareOffers["+i+"].reject_button_text");
                log.info("RejectButtonText : "+ i+" "+RejectButtonText);
                Assert.assertEquals("Reject",RejectButtonText);
            }}
    }
    @Test(priority = 358, description = "Test reject_message" )
    public void offerPingRejectMessage() {
        if(shareOffersArray.size()==0){
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        }else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String rejectMessage = jsonPath.getString("data.shareOffers["+i+"].reject_message");
                log.info("rejectMessage : "+ i+" "+rejectMessage);
                Assert.assertEquals("Are you sure want to reject this ping? The ping will remove from your history.",rejectMessage);
            }}
    }
    @Test(priority = 359, description = "Test reject_title" )
    public void offerPingRejectTitle() {
        if(shareOffersArray.size()==0){
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        }else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String rejectTitle = jsonPath.getString("data.shareOffers["+i+"].reject_title");
                log.info("rejectTitle : "+ i+" "+rejectTitle);
                Assert.assertEquals("Reject Ping",rejectTitle);
            }}
    }
    @Test(priority = 361, description = "Test message" )
    public void offerMessage() {
        String messageTest = jsonPath.getString("data.ping_section.message");
        log.info("messageTest :  "+messageTest);
        Assert.assertNotNull(messageTest,"messageTest is null");
    }
    @Test(priority = 362, description = "Test offerPingInfo" )
    public void offerPingInfo() {
        if(shareOffersArray.size()==0){
            log.info(">>>>>>>>>>>>>>> Array is null <<<<<<<<<<<<<<<<<<\n");
        }else {
            for (int i = 0; i < shareOffersArray.size(); i++) {

                String pingInfoTest = jsonPath.getString("data.shareOffers[" + i + "].ping_info");
                log.info("pingInfoTest : " + i + " " + pingInfoTest);
                Assert.assertNotNull(pingInfoTest, "pingInfo is null");
            }}
    }



}