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
import te.application.data.response.filterCategory;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

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
            String bodyData = generateAPIBody.filter(languageCode,"31.527539", "entertainer",
                    AppConstants.requestOSPlatform, AppConstants.requestAppVersion, AppConstants.requestDeviceKey,
                    AppConstants.requestCurrency, 1,
                    AppConstants.requestDeviceKey, "Restaurants and Bars",
                    "74.352919", "c86078f5-5a6c-4831-ba8a-1e9914a1ce33", locationID,
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

    @Test(priority = 1)
    public void verifyFiltersTitle() {
        String title = jsonPath.getString("data.filters[0].options[0].title");
        if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.nearestEnglish, title, "Title Nearest");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.nearestArabic, title, "Title Nearest");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.nearestRussian, title, "Title Nearest");
        }
    }

    @Test(priority = 2)
    public void verifyOption1() {
        String title = jsonPath.getString("data.filters[0].options[1].title");
        if(languageCode.compareToIgnoreCase("en")==0){
            Assert.assertEquals(filterCategory.topRatedEnglish, title, "Title top rated");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0){
            Assert.assertEquals(filterCategory.topRatedArabic, title, "Title top rated");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0){
            Assert.assertEquals(filterCategory.topRatedRussian, title, "Title top rated");
        }
    }

    @Test(priority = 3)
    public void verifyOption2() {
        String title = jsonPath.getString("data.filters[0].options[2].title");
        if(languageCode.compareToIgnoreCase("en")==0){
            Assert.assertEquals(filterCategory.newEnglish, title, "Title New");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0){
            Assert.assertEquals(filterCategory.newArabic, title, "Title New");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0){
            Assert.assertEquals(filterCategory.newRussian, title, "Title New");
        }
    }

    @Test(priority = 4)
    public void verifyOption3() {
        String title = jsonPath.getString("data.filters[1].options[0].title");
        if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.buyOneGetOneEnglish, title, "Title Buy one get one");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.buyOneGetOneArabic, title, "Title Buy one get one");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.buyOneGetOneRussian, title, "Title Buy one get one");
        }
    }

    @Test(priority = 5)
    public void verifyOption4() {
        String title = jsonPath.getString("data.filters[1].options[1].title");
        if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.takeAwayEnglish, title, "Title Takeaway");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.takeAwayArabic, title, "Title Takeaway");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.takeAwayRussian, title, "Title Takeaway");
        }
    }

    @Test(priority = 6)
    public void verifyOption5() {
        String sixth_title = jsonPath.getString("data.filters[1].options[2].title");
        if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.newEnglish, sixth_title, "Title New");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.newArabic, sixth_title, "Title New");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.newRussian, sixth_title, "Title New");
        }
    }

    @Test(priority = 7)
    public void verifyOption6() {
        String seventh_title = jsonPath.getString("data.filters[1].options[3].title");
        if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.dineInEnglish, seventh_title, "Title Dine-in");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.dineInArabic, seventh_title, "Title Dine-in");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.dineInRussian, seventh_title, "Title Dine-in");
        }
    }

    @Test(priority = 8)
    public void verifyOption7() {
        String eighth_title = jsonPath.getString("data.filters[1].options[4].title");
        if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.reservationEnglish, eighth_title, "Title Reservation");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.reservationArabic, eighth_title, "Title Reservation");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.reservationRussian, eighth_title, "Title Reservation");
        }
    }

    @Test(priority = 9)
    public void verifyOption8() {
        String ninth_title = jsonPath.getString("data.filters[1].options[5].title");
        if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.percentOffEnglish, ninth_title, "Title Percentage off");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.percentOffArabic, ninth_title, "Title Percentage off");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.percentOffRussian, ninth_title, "Title Percentage off");
        }
    }

    @Test(priority = 10)
    public void verifyOption9() {
        String tenth_title = jsonPath.getString("data.filters[1].options[6].title");
        if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.deliveryEnglish, tenth_title, "Title Delivery");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.deliveryArabic, tenth_title, "Title Delivery");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.deliveryRussian, tenth_title, "Title Delivery");
        }
    }

    @Test(priority = 11)
    public void verifyOption10() {
        String eleventh_title = jsonPath.getString("data.filters[1].options[7].title");
        if(languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(filterCategory.liteEnglish, eleventh_title, "Title Lite");
        }
        else if(languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(filterCategory.liteArabic, eleventh_title, "Title Lite");
        }
        else if(languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(filterCategory.liteRussian, eleventh_title, "Title Lite");
        }
    }
}