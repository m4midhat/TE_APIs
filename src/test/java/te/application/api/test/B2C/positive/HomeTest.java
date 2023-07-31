package te.application.api.test.B2C.positive;

import com.github.javafaker.App;
import com.google.gson.*;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import te.application.api.baseTest.B2CBaseTest;
import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;

@Slf4j
public class HomeTest extends B2CBaseTest{
    String jsonData = "";
    JsonObject dataObject;
    JsonArray homeSectionsArray;
    boolean exists;

    @BeforeClass
    public void setUp() throws IOException {
        log.info(".............Session ID"+AppConstants.sessionID);
        RestAssured.basePath = AppConstants.BASE_PATH_HOME;
        String bodyData = generateAPIBody.home(1,"ar","26.22876",
                "entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-C72ECFA5-AC6C-4F5D-927A-D6D5E5AA3A62", "9124814","USD",
                "50.584381","ios-C72ECFA5-AC6C-4F5D-927A-D6D5E5AA3A62", "3",
                AppConstants.testDataOSVersion ,AppConstants.testDataDeviceModel,AppConstants.testDataTimeZone,
                AppConstants.BASE_URI_B2C+ AppConstants.BASE_PATH_HOME,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                1
        );
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        log.info(response.asString());

        jsonPath = response.jsonPath();
        log.info("............Session ID : " + AppConstants.sessionID);

        //..........................................................
        jsonData =response.asString();
        JsonParser parser = new JsonParser();
        JsonElement root;
        root = parser.parse(jsonData);
        JsonObject jsonObject = root.getAsJsonObject();
        dataObject = jsonObject.getAsJsonObject("data");
        homeSectionsArray = dataObject.getAsJsonArray("home_sections");
        log.info("......................................................");
        log.info(String.valueOf(homeSectionsArray));
        log.info("......................................................");
    }
    @Test(priority = 50, description = "Status code check" )
    public void CheckStatus(){

        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
        log.info(String.valueOf(response.getStatusCode()));
        String msg = jsonPath.getString("message");
        log.info("Message : "+ msg);
        Assert.assertTrue(jsonData.contains("\"success\":"));
        Assert.assertEquals("success",msg);
    }
    @Test(priority = 51, description = "Test Section Identifiers" )
    public void SectionIdentifiers() {

        for (int i = 0; i < homeSectionsArray.size(); i++) {

            String homeSectionsIdentifiers = jsonPath.getString("data.home_sections["+i+"].section_identifier");
            log.info("homeSections : "+ i+" "+homeSectionsIdentifiers);
            Assert.assertNotNull(homeSectionsIdentifiers,"homeSections is null");
        }
    }
    @Test(priority = 52, description = "Test Identifiers Tiles_Messages" )
    public void IdentifiersTilesMessages() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            JsonObject section = homeSectionsArray.get(i).getAsJsonObject();
            JsonArray tilesArray = section.getAsJsonArray("tiles");

            for (int j = 0; j < tilesArray.size(); j++) {
                String messageTest = jsonPath.getString("data.home_sections["+i+"].tiles["+j+"].message");
                if (response.path("data.home_sections["+i+"].tiles["+j+"].message")==null){
                    log.info(">>>>>>>>>>>>>>>NOT EXIST<<<<<<<<<<<<<<<<<<<");
                }else{

                    log.info("message : "+ i+j+" "+messageTest);
                    softAssert.assertNotNull(messageTest,"message is null");
                }
            }
        }
        softAssert.assertAll();
    }
    @Test(priority = 53, description = "Test Identifiers Tiles Button_Title" )
    public void IdentifiersTilesButtonTitle() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            JsonObject section = homeSectionsArray.get(i).getAsJsonObject();
            JsonArray tilesArray = section.getAsJsonArray("tiles");

            for (int j = 0; j < tilesArray.size(); j++) {
                String buttonTitleTest = jsonPath.getString("data.home_sections["+i+"].tiles["+j+"].button_title");
                if (response.path("data.home_sections["+i+"].tiles["+j+"].button_title")==null){
                    log.info(">>>>>>>>>>>>>>>NOT EXIST<<<<<<<<<<<<<<<<<<<");
                }else{

                    log.info("buttonTitleTest : "+ i+j+" "+buttonTitleTest);
                    softAssert.assertNotNull(buttonTitleTest,"buttonTitleTest is null");
                }
            }
        }
        softAssert.assertAll();
    }
    @Test(priority = 54, description = "Test Identifiers Tiles Sub_Title" )
    public void IdentifiersTilesSubTitle() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            JsonObject section = homeSectionsArray.get(i).getAsJsonObject();
            JsonArray tilesArray = section.getAsJsonArray("tiles");

            for (int j = 0; j < tilesArray.size(); j++) {
                String subTitleTest = jsonPath.getString("data.home_sections["+i+"].tiles["+j+"].sub_title");
                if (response.path("data.home_sections["+i+"].tiles["+j+"].sub_title")==null){
                    log.info(">>>>>>>>>>>>>>>NOT EXIST<<<<<<<<<<<<<<<<<<<");
                }else {
                    log.info("subTitleTest : " + i + j + " " + subTitleTest);
                    softAssert.assertNotNull(subTitleTest, "subTitleTest is null");
                }
            }
        }
        softAssert.assertAll();
    }
    @Test(priority = 55, description = "Test Identifiers Tiles Show_Tile" )
    public void IdentifiersTilesShowTile() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            JsonObject section = homeSectionsArray.get(i).getAsJsonObject();
            JsonArray tilesArray = section.getAsJsonArray("tiles");

            for (int j = 0; j < tilesArray.size(); j++) {
                String showTileTest = jsonPath.getString("data.home_sections["+i+"].tiles["+j+"].show_tile");
                boolean temp=Boolean.parseBoolean(showTileTest);
                if (response.path("data.home_sections["+i+"].tiles["+j+"].show_tile")==null){
                    log.info(">>>>>>>>>>>>>>>NOT EXIST<<<<<<<<<<<<<<<<<<<");
                }else {
                    log.info("showTileTest : " + i + j + " " + temp);
                    softAssert.assertTrue(temp, "show tile is false at : " + i + j);
                }
            }
        }
        softAssert.assertAll();
    }
    @Test(priority = 56, description = "Test Section Is Overlap" )
    public void SectionIsOverlap() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            String IsOverlap = jsonPath.getString("data.home_sections["+i+"].is_overlap");
            boolean temp=Boolean.parseBoolean(IsOverlap);
            if (response.path("data.home_sections["+i+"].is_overlap")==null){
                log.info(">>>>>>>>>>>>>>>NOT EXIST<<<<<<<<<<<<<<<<<<<");
            }else {
                log.info("IsOverlap : " + i + " " + temp);
                softAssert.assertFalse(temp, "show tile is true at : " + i);
            }
        }
        softAssert.assertAll();
    }
    @Test(priority = 57, description = "Test Section Title" )
    public void SectionTitle() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            String secTitle = jsonPath.getString("data.home_sections["+i+"].title");
            if (response.path("data.home_sections["+i+"].title")==null){
                log.info(">>>>>>>>>>>>>>>NOT EXIST<<<<<<<<<<<<<<<<<<<");
            }else {
                log.info("secTitle : " + i + " " + secTitle);
                softAssert.assertNotNull(secTitle, "secTitle is null");
            }
        }
        softAssert.assertAll();
    }
    @Test(priority = 58, description = "Test Tile Analytic Name" )
    public void TileAnalyticName() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            JsonObject section = homeSectionsArray.get(i).getAsJsonObject();
            JsonArray tilesArray = section.getAsJsonArray("tiles");

            for (int j = 0; j < tilesArray.size(); j++) {
                String analyticName = jsonPath.getString("data.home_sections["+i+"].tiles["+j+"].tile_analytic_name");
                if (response.path("data.home_sections["+i+"].tiles["+j+"].tile_analytic_name")==null){
                    log.info(">>>>>>>>>>>>>>>NOT EXIST<<<<<<<<<<<<<<<<<<<");
                }else {
                    log.info("analyticName : "+ i+j+" "+analyticName);
                    softAssert.assertNotNull(analyticName,"secTitle is null");
                }}
        }
        softAssert.assertAll();
    }

    @Test(priority = 59, description = "Test Section Tile Tile_title" )
    public void SecTileTiletitle() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            JsonObject section = homeSectionsArray.get(i).getAsJsonObject();
            JsonArray tilesArray = section.getAsJsonArray("tiles");

            for (int j = 0; j < tilesArray.size(); j++) {
                String tileTitle = jsonPath.getString("data.home_sections["+i+"].tiles["+j+"].tile_title");
                if (response.path("data.home_sections["+i+"].tiles["+j+"].tile_title")==null){
                    log.info(">>>>>>>>>>>>>>>NOT EXIST<<<<<<<<<<<<<<<<<<<");
                }else {
                    log.info("tileTitle : "+ i+j+" "+tileTitle);
                    softAssert.assertNotNull(tileTitle,"tileTitle is null");
                }}
        }
        softAssert.assertAll();
    }
    @Test(priority = 60, description = "Test Tile Type Analytics" )
    public void TileTypeAnalytics() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            JsonObject section = homeSectionsArray.get(i).getAsJsonObject();
            JsonArray tilesArray = section.getAsJsonArray("tiles");

            for (int j = 0; j < tilesArray.size(); j++) {
                String TypeAnalytics = jsonPath.getString("data.home_sections["+i+"].tiles["+j+"].tile_type_analytics");
                if (response.path("data.home_sections["+i+"].tiles["+j+"].tile_type_analytics")==null){
                    log.info(">>>>>>>>>>>>>>>NOT EXIST<<<<<<<<<<<<<<<<<<<");
                }else {
                    log.info("TypeAnalytics : "+ i+j+" "+TypeAnalytics);
                    softAssert.assertNotNull(TypeAnalytics,"TypeAnalytics is null");
                }}
        }
        softAssert.assertAll();
    }
    @Test(priority = 61, description = "Test Location" )
    public void Location() {

        int showLocationSize = jsonPath.getInt("data.location.size()");
        if (response.path("data.location.size()")==null){
            log.info(">>>>>>>>>>>>>>>NOT EXIST<<<<<<<<<<<<<<<<<<<");
        }else {
            log.info("showLocationSize : " + showLocationSize);
            Assert.assertNotEquals(0, showLocationSize);
        }}
    @Test(priority = 62, description = "Test Delivery Tab" )
    public void DeliveryTab() {

        SoftAssert softAssert = new SoftAssert();
        String deliveryTab = jsonPath.getString("data.location.delivery_tab");
        if (response.path("data.location.delivery_tab")==null){
            log.info(">>>>>>>>>>>>>>>NOT EXIST<<<<<<<<<<<<<<<<<<<");
        }else {
            log.info("deliveryTab : "+deliveryTab);
            softAssert.assertNotNull(deliveryTab,"deliveryTab is null");
            softAssert.assertAll();
        }}
    @Test(priority = 63, description = "Test users information" )
    public void Users() {

        int userSize = jsonPath.getInt("data.user.size()");
        if (response.path("data.user.size()")==null){
            log.info(">>>>>>>>>>>>>>>NOT EXIST<<<<<<<<<<<<<<<<<<<");
        }else {
            log.info("userSize : "+ userSize);
            Assert.assertNotEquals(0,userSize);
        }}

}