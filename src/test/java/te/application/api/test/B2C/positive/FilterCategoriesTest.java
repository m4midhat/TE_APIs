package te.application.api.test.B2C.positive;

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
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBodyB2C;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class FilterCategoriesTest extends B2CBaseTest {

    String locationID, languageCode;

    public FilterCategoriesTest(String loc, String lang){
        this.languageCode = lang;
        this.locationID = loc;
    }


    @Factory
    public static Object[] factoryMethod() {
        return new Object[]
                {
                        new FilterCategoriesTest("1", "en"),
                        new FilterCategoriesTest("1", "ar"),
                        new FilterCategoriesTest("1", "ru"),
                        new FilterCategoriesTest("2", "en"),
                        new FilterCategoriesTest("2", "ar"),
                        new FilterCategoriesTest("2", "ru"),
                        new FilterCategoriesTest("3", "en"),
                        new FilterCategoriesTest("3", "ar"),
                        new FilterCategoriesTest("3", "ru"),
                        new FilterCategoriesTest("6", "en"),
                        new FilterCategoriesTest("6", "ar"),
                        new FilterCategoriesTest("6", "ru"),
                        new FilterCategoriesTest("7", "en"),
                        new FilterCategoriesTest("7", "ar"),
                        new FilterCategoriesTest("7", "ru"),
                        new FilterCategoriesTest("8", "en"),
                        new FilterCategoriesTest("8", "ar"),
                        new FilterCategoriesTest("8", "ru"),
                        new FilterCategoriesTest("9", "en"),
                        new FilterCategoriesTest("9", "ar"),
                        new FilterCategoriesTest("9", "ru"),
                        new FilterCategoriesTest("10", "en"),
                        new FilterCategoriesTest("10", "ar"),
                        new FilterCategoriesTest("10", "ru"),
                        new FilterCategoriesTest("11", "en"),
                        new FilterCategoriesTest("11", "ar"),
                        new FilterCategoriesTest("11", "ru"),
                        new FilterCategoriesTest("18", "en"),
                        new FilterCategoriesTest("18", "ar"),
                        new FilterCategoriesTest("18", "ru"),
                        new FilterCategoriesTest("49", "en"),
                        new FilterCategoriesTest("49", "ar"),
                        new FilterCategoriesTest("49", "ru")
                };
    }

    @BeforeClass
    public void setUp() throws IOException {
        RestAssured.baseURI = endPoints.getProperty("PYTHON_BASE_URI");
        RestAssured.basePath = endPoints.getProperty("B2C_FILTERS");
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            String bodyData = generateAPIBodyB2C.filter(languageCode,"31.527539", "entertainer",
                    AppConstants.requestOSPlatform, AppConstants.requestAppVersion, AppConstants.requestDeviceKey,
                    AppConstants.requestCurrency, 1,
                    AppConstants.requestDeviceKey, "Restaurants and Bars",
                    "74.352919", AppConstants.sessionID, locationID,
                    0, AppConstants.requestOSVersion, AppConstants.requestDeviceModel,
                    AppConstants.requestTimeZone, "74.352919", AppConstants.requestOSPlatform, "31.527539");
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                    .contentType("application/json")
                    .header("User-Agent", AppConstants.requestUserAgent)
                    .body(bodyData)
                    .log().all();
            response = httpRequest.post();
            log.info(response.asString());
            jsonPath = response.jsonPath();
        }
    }

    @Test(description = "Status code check")
    public void checkStatus() {
        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
    }

    @Test(priority = 1, dependsOnMethods = "checkStatus")
    public void verifyFiltersTitle() {
        String title = jsonPath.getString("data.filters[0].options[0].title");
        Assert.assertNotNull(title);
        /*if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.nearestEnglish, title, "Title Nearest");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.nearestArabic, title, "Title Nearest");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.nearestRussian, title, "Title Nearest");
        }*/
    }

    @Test(priority = 2, dependsOnMethods = "checkStatus")
    public void verifyOption1() {
        if(jsonPath.getString("data.filters[0].options[1].title")!=null) {
            String title = jsonPath.getString("data.filters[0].options[1].title");
            Assert.assertNotNull(title);
        }
        /*if(languageCode.compareToIgnoreCase("en")==0){
            Assert.assertEquals(filterCategory.topRatedEnglish, title, "Title top rated");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0){
            Assert.assertEquals(filterCategory.topRatedArabic, title, "Title top rated");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0){
            Assert.assertEquals(filterCategory.topRatedRussian, title, "Title top rated");
        }*/
    }

    @Test(priority = 3, dependsOnMethods = "checkStatus")
    public void verifyOption2() {
        if(jsonPath.getString("data.filters[0].options[2].title")!=null) {
            String title = jsonPath.getString("data.filters[0].options[2].title");
            Assert.assertNotNull(title);
        }
        /*if(languageCode.compareToIgnoreCase("en")==0){
            Assert.assertEquals(filterCategory.newEnglish, title, "Title New");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0){
            Assert.assertEquals(filterCategory.newArabic, title, "Title New");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0){
            Assert.assertEquals(filterCategory.newRussian, title, "Title New");
        }*/
    }

    @Test(priority = 4, dependsOnMethods = "checkStatus")
    public void verifyOption3() {
        if(jsonPath.getString("data.filters[1].options[0].title")!=null) {
            String title = jsonPath.getString("data.filters[1].options[0].title");
            Assert.assertNotNull(title);
        }
        /*if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.buyOneGetOneEnglish, title, "Title Buy one get one");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.buyOneGetOneArabic, title, "Title Buy one get one");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.buyOneGetOneRussian, title, "Title Buy one get one");
        }*/
    }

    @Test(priority = 5, dependsOnMethods = "checkStatus")
    public void verifyOption4() {
        log.info("Language : " + languageCode);
        log.info("LocationID : "+ locationID);
        if(jsonPath.getString("data.filters[1].options[1].title")!=null) {
            String title = jsonPath.getString("data.filters[1].options[1].title");
            log.info(title);
            Assert.assertNotNull(title);
        }
        /*if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.takeAwayEnglish, title, "Title Takeaway");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.takeAwayArabic, title, "Title Takeaway");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.takeAwayRussian, title, "Title Takeaway");
        }*/
    }

    @Test(priority = 6, dependsOnMethods = "checkStatus")
    public void verifyOption5() {
        log.info("Language : " + languageCode);
        log.info("LocationID : "+ locationID);
        if(jsonPath.getString("data.filters[1].options[2].title")!=null) {
            String sixth_title = jsonPath.getString("data.filters[1].options[2].title");
            log.info(sixth_title);
            Assert.assertNotNull(sixth_title);
        }
        /*if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.newEnglish, sixth_title, "Title New");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.newArabic, sixth_title, "Title New");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.newRussian, sixth_title, "Title New");
        }*/
    }

    @Test(priority = 7, dependsOnMethods = "checkStatus")
    public void verifyOption6() {
        log.info("Language : " + languageCode);
        log.info("LocationID : "+ locationID);
        if(jsonPath.getString("data.filters[1].options[3].title")!=null) {
            String seventh_title = jsonPath.getString("data.filters[1].options[3].title");
            log.info(seventh_title);
            Assert.assertNotNull(seventh_title);
        }
        /*if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.dineInEnglish, seventh_title, "Title Dine-in");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.dineInArabic, seventh_title, "Title Dine-in");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.dineInRussian, seventh_title, "Title Dine-in");
        }*/
    }

    @Test(priority = 8, dependsOnMethods = "checkStatus")
    public void verifyOption7() {
        log.info("Language : " + languageCode);
        log.info("LocationID : "+ locationID);
        if(jsonPath.getString("data.filters[1].options[4].title")!=null) {
            String eighth_title = jsonPath.getString("data.filters[1].options[4].title");
            log.info(eighth_title);
            Assert.assertNotNull(eighth_title);
        }
        /*if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.reservationEnglish, eighth_title, "Title Reservation");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.reservationArabic, eighth_title, "Title Reservation");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.reservationRussian, eighth_title, "Title Reservation");
        }*/
    }

    @Test(priority = 9, dependsOnMethods = "checkStatus")
    public void verifyOption8() {
        log.info("Language : " + languageCode);
        log.info("LocationID : "+ locationID);
        if(jsonPath.getString("data.filters[1].options[5].title")!=null) {
            String ninth_title = jsonPath.getString("data.filters[1].options[5].title");
            log.info(ninth_title);
            Assert.assertNotNull(ninth_title);
        }
        /*if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.percentOffEnglish, ninth_title, "Title Percentage off");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.percentOffArabic, ninth_title, "Title Percentage off");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.percentOffRussian, ninth_title, "Title Percentage off");
        }*/
    }

    @Test(priority = 10, dependsOnMethods = "checkStatus")
    public void verifyOption9() {
        log.info("Language : " + languageCode);
        log.info("LocationID : "+ locationID);
        if(jsonPath.getString("data.filters[1].options[6].title")!=null) {
            String tenth_title = jsonPath.getString("data.filters[1].options[6].title");
            log.info(tenth_title);
            Assert.assertNotNull(tenth_title);
        }
        /*if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.deliveryEnglish, tenth_title, "Title Delivery");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.deliveryArabic, tenth_title, "Title Delivery");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.deliveryRussian, tenth_title, "Title Delivery");
        }*/
    }

    @Test(priority = 11, dependsOnMethods = "checkStatus")
    public void verifyOption10() {
        log.info("Language : " + languageCode);
        log.info("LocationID : "+ locationID);
        if(jsonPath.getString("data.filters[1].options[7].title")!=null) {
            String eleventh_title = jsonPath.getString("data.filters[1].options[7].title");
            log.info(eleventh_title);
            Assert.assertNotNull(eleventh_title);
        }
        /*if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.liteEnglish, eleventh_title, "Title Lite");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.liteArabic, eleventh_title, "Title Lite");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.liteRussian, eleventh_title, "Title Lite");
        }*/
    }
}