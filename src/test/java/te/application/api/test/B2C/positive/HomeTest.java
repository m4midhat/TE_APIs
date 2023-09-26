package te.application.api.test.B2C.positive;

import com.google.gson.*;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import te.application.api.baseTest.B2CBaseTest;
import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBodyB2C;

import java.io.IOException;

@Slf4j
public class HomeTest extends B2CBaseTest{
    String jsonData = "";
    JsonObject dataObject;
    JsonArray homeSectionsArray;
    String locationID, languageCode;

    public HomeTest(String loc, String lang){
        this.languageCode = lang;
        this.locationID = loc;
    }


    @Factory
    public static Object[] factoryMethod() {
        return new Object[]
                {
                        new HomeTest("1", "en"),
                        new HomeTest("1", "ar"),
                        new HomeTest("1", "ru"),
                        new HomeTest("2", "en"),
                        new HomeTest("2", "ar"),
                        new HomeTest("2", "ru"),
                        new HomeTest("3", "en"),
                        new HomeTest("3", "ar"),
                        new HomeTest("3", "ru"),
                        new HomeTest("6", "en"),
                        new HomeTest("6", "ar"),
                        new HomeTest("6", "ru"),
                        new HomeTest("7", "en"),
                        new HomeTest("7", "ar"),
                        new HomeTest("7", "ru"),
                        new HomeTest("8", "en"),
                        new HomeTest("8", "ar"),
                        new HomeTest("8", "ru"),
                        new HomeTest("9", "en"),
                        new HomeTest("9", "ar"),
                        new HomeTest("9", "ru"),
                        new HomeTest("10", "en"),
                        new HomeTest("10", "ar"),
                        new HomeTest("10", "ru"),
                        new HomeTest("11", "en"),
                        new HomeTest("11", "ar"),
                        new HomeTest("11", "ru"),
                        new HomeTest("18", "en"),
                        new HomeTest("18", "ar"),
                        new HomeTest("18", "ru"),
                        new HomeTest("49", "en"),
                        new HomeTest("49", "ar"),
                        new HomeTest("49", "ru")
                };
    }


    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException {
        log.info("Session ID"+AppConstants.sessionID);
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_HOME");
        String bodyData = generateAPIBodyB2C.home(1,languageCode,"26.22876",
                "entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey, AppConstants.UserID ,AppConstants.requestCurrency,
                "50.584381",AppConstants.requestDeviceKey, locationID,
                AppConstants.requestOSVersion,AppConstants.requestDeviceModel,AppConstants.requestTimeZone,
                endPoints.getProperty("BASE_URI_B2C")+endPoints.getProperty("BASE_PATH_HOME"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                1
        );
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

        //..........................................................
        jsonData =response.asString();
        JsonParser parser = new JsonParser();
        JsonElement root;
        root = parser.parse(jsonData);
        JsonObject jsonObject = root.getAsJsonObject();
        dataObject = jsonObject.getAsJsonObject("data");
        homeSectionsArray = dataObject.getAsJsonArray("home_sections");
        log.info(String.valueOf(homeSectionsArray));
    }
    @Test(priority = 50, description = "Status code check" , groups = {"Smoke", "Sanity", "Regression"})
    public void checkStatus(){

        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
        log.info(String.valueOf(response.getStatusCode()));
        String msg = jsonPath.getString("message");
        log.info("Message : "+ msg);
        Assert.assertEquals("success",msg);
    }


    @Test(priority = 51, description = "Test Section Identifiers" , groups = {"Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void SectionIdentifiers() {

        for (int i = 0; i < homeSectionsArray.size(); i++) {

            String homeSectionsIdentifiers = jsonPath.getString("data.home_sections["+i+"].section_identifier");
            log.info("homeSections : "+ i+" "+homeSectionsIdentifiers);
            Assert.assertNotNull(homeSectionsIdentifiers,"homeSections is null");
        }
    }


    @Test(priority = 52, description = "Test Identifiers Tiles_Messages" , groups = {"Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void IdentifiersTilesMessages() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            JsonObject section = homeSectionsArray.get(i).getAsJsonObject();
            JsonArray tilesArray = section.getAsJsonArray("tiles");

            for (int j = 0; j < tilesArray.size(); j++) {
                String messageTest = jsonPath.getString("data.home_sections["+i+"].tiles["+j+"].message");
                if (response.path("data.home_sections["+i+"].tiles["+j+"].message")!=null){
                    log.info("message : "+ i+j+" "+messageTest);
                    softAssert.assertNotNull(messageTest,"message is null");
                }
            }
        }
        softAssert.assertAll();
    }
    @Test(priority = 53, description = "Test Identifiers Tiles Button_Title" , groups = {"Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void IdentifiersTilesButtonTitle() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            JsonObject section = homeSectionsArray.get(i).getAsJsonObject();
            JsonArray tilesArray = section.getAsJsonArray("tiles");

            for (int j = 0; j < tilesArray.size(); j++) {
                String buttonTitleTest = jsonPath.getString("data.home_sections["+i+"].tiles["+j+"].button_title");
                if (response.path("data.home_sections["+i+"].tiles["+j+"].button_title")!=null){
                    log.info("buttonTitleTest : "+ i+j+" "+buttonTitleTest);
                    softAssert.assertNotNull(buttonTitleTest,"buttonTitleTest is null");
                }
            }
        }
        softAssert.assertAll();
    }
    @Test(priority = 54, description = "Test Identifiers Tiles Sub_Title" , groups = {"Regression"}, dependsOnMethods = "checkStatus")
    public void IdentifiersTilesSubTitle() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            JsonObject section = homeSectionsArray.get(i).getAsJsonObject();
            JsonArray tilesArray = section.getAsJsonArray("tiles");

            for (int j = 0; j < tilesArray.size(); j++) {
                String subTitleTest = jsonPath.getString("data.home_sections["+i+"].tiles["+j+"].sub_title");
                if (response.path("data.home_sections["+i+"].tiles["+j+"].sub_title")!=null){
                    log.info("subTitleTest : " + i + j + " " + subTitleTest);
                    softAssert.assertNotNull(subTitleTest, "subTitleTest is null");
                }
            }
        }
        softAssert.assertAll();
    }


    @Test(priority = 55, description = "Test Identifiers Tiles Show_Tile" , groups = {"Regression"}, dependsOnMethods = "checkStatus")
    public void IdentifiersTilesShowTile() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            JsonObject section = homeSectionsArray.get(i).getAsJsonObject();
            JsonArray tilesArray = section.getAsJsonArray("tiles");

            for (int j = 0; j < tilesArray.size(); j++) {
                String showTileTest = jsonPath.getString("data.home_sections["+i+"].tiles["+j+"].show_tile");
                boolean temp=Boolean.parseBoolean(showTileTest);
                if (response.path("data.home_sections["+i+"].tiles["+j+"].show_tile")!=null){
                    log.info("showTileTest : " + i + j + " " + temp);
                    softAssert.assertTrue(temp, "show tile is false at : " + i + j);
                }
            }
        }
        softAssert.assertAll();
    }


    @Test(priority = 56, description = "Test Section Is Overlap", groups = {"Regression"} , dependsOnMethods = "checkStatus")
    public void SectionIsOverlap() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            String IsOverlap = jsonPath.getString("data.home_sections["+i+"].is_overlap");
            boolean temp=Boolean.parseBoolean(IsOverlap);
            if (response.path("data.home_sections["+i+"].is_overlap")!=null){
                log.info("IsOverlap : " + i + " " + temp);
                softAssert.assertFalse(temp, "show tile is true at : " + i);
            }
        }
        softAssert.assertAll();
    }

    @Test(priority = 57, description = "Test Section Title", groups = {"Regression"} , dependsOnMethods = "checkStatus")
    public void SectionTitle() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            String secTitle = jsonPath.getString("data.home_sections["+i+"].title");
            if (response.path("data.home_sections["+i+"].title")!=null){
                log.info("secTitle : " + i + " " + secTitle);
                softAssert.assertNotNull(secTitle, "secTitle is null");
            }
        }
        softAssert.assertAll();
    }
    @Test(priority = 58, description = "Test Tile Analytic Name", groups = {"Regression"} , dependsOnMethods = "checkStatus")
    public void TileAnalyticName() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            JsonObject section = homeSectionsArray.get(i).getAsJsonObject();
            JsonArray tilesArray = section.getAsJsonArray("tiles");

            for (int j = 0; j < tilesArray.size(); j++) {
                String analyticName = jsonPath.getString("data.home_sections["+i+"].tiles["+j+"].tile_analytic_name");
                if (response.path("data.home_sections["+i+"].tiles["+j+"].tile_analytic_name")!=null){
                    log.info("analyticName : "+ i+j+" "+analyticName);
                    softAssert.assertNotNull(analyticName,"secTitle is null");
                }}
        }
        softAssert.assertAll();
    }

    @Test(priority = 59, description = "Test Section Tile Tile_title" , groups = {"Regression"}, dependsOnMethods = "checkStatus")
    public void SecTileTiletitle() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            JsonObject section = homeSectionsArray.get(i).getAsJsonObject();
            JsonArray tilesArray = section.getAsJsonArray("tiles");

            for (int j = 0; j < tilesArray.size(); j++) {
                String tileTitle = jsonPath.getString("data.home_sections["+i+"].tiles["+j+"].tile_title");
                if (response.path("data.home_sections["+i+"].tiles["+j+"].tile_title")!=null){
                    log.info("tileTitle : "+ i+j+" "+tileTitle);
                    softAssert.assertNotNull(tileTitle,"tileTitle is null");
                }}
        }
        softAssert.assertAll();
    }
    @Test(priority = 60, description = "Test Tile Type Analytics" , groups = {"Regression"}, dependsOnMethods = "checkStatus")
    public void TileTypeAnalytics() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < homeSectionsArray.size(); i++) {
            JsonObject section = homeSectionsArray.get(i).getAsJsonObject();
            JsonArray tilesArray = section.getAsJsonArray("tiles");

            for (int j = 0; j < tilesArray.size(); j++) {
                String TypeAnalytics = jsonPath.getString("data.home_sections["+i+"].tiles["+j+"].tile_type_analytics");
                if (response.path("data.home_sections["+i+"].tiles["+j+"].tile_type_analytics")!=null){
                    log.info("TypeAnalytics : "+ i+j+" "+TypeAnalytics);
                    softAssert.assertNotNull(TypeAnalytics,"TypeAnalytics is null");
                }}
        }
        softAssert.assertAll();
    }
    @Test(priority = 61, description = "Test Location" , groups = {"Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void Location() {

        int showLocationSize = jsonPath.getInt("data.location.size()");
        if (response.path("data.location.size()")==null){
            log.info("showLocationSize : " + showLocationSize);
            Assert.assertNotEquals(0, showLocationSize);
        }
    }


    @Test(priority = 62, description = "Test Delivery Tab" , groups = {"Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void DeliveryTab() {

        SoftAssert softAssert = new SoftAssert();
        String deliveryTab = jsonPath.getString("data.location.delivery_tab");
        if (response.path("data.location.delivery_tab")!=null){
            log.info("deliveryTab : "+deliveryTab);
            softAssert.assertNotNull(deliveryTab,"deliveryTab is null");
        }
    }


    @Test(priority = 63, description = "Test users information" , groups = {"Smoke", "Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void Users() {
        int userSize = jsonPath.getInt("data.user.size()");
        if (response.path("data.user.size()")!=null){
            log.info("userSize : "+ userSize);
            Assert.assertNotEquals(0,userSize);
        }
    }

}