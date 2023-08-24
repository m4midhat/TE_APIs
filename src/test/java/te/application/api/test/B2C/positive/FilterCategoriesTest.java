package te.application.api.test.B2C.positive;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import org.testng.annotations.BeforeClass;
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

    @BeforeClass
    public void setUp() throws IOException {
        RestAssured.baseURI = endPoints.getProperty("PYTHON_BASE_URI");
        RestAssured.basePath = endPoints.getProperty("B2C_FILTERS");
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            String bodyData = generateAPIBody.filter(AppConstants.requestLanguage,"31.527539", "entertainer",
                    AppConstants.requestOSPlatform, AppConstants.requestAppVersion, AppConstants.requestDeviceKey,
                    AppConstants.requestCurrency, 1,
                    AppConstants.requestDeviceKey, "Restaurants and Bars",
                    "74.352919", "c86078f5-5a6c-4831-ba8a-1e9914a1ce33", "1",
                    0, AppConstants.requestOSVersion, AppConstants.requestDeviceModel,
                    AppConstants.requestTimeZone, "74.352919", AppConstants.requestOSPlatform, "31.527539");
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                    .contentType("application/json")
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
        Assert.assertEquals(filterCategory.nearestEnglish, title, "Title Nearest");
    }

    @Test(priority = 2)
    public void verifyOption1() {
        String title = jsonPath.getString("data.filters[0].options[1].title");
        Assert.assertEquals(filterCategory.topRatedEnglish, title, "Title top rated");
    }

    @Test(priority = 3)
    public void verifyOption2() {
        String title = jsonPath.getString("data.filters[0].options[2].title");
        Assert.assertEquals(filterCategory.newEnglish, title, "Title New");
    }

    @Test(priority = 4)
    public void verifyOption3() {
        String title = jsonPath.getString("data.filters[1].options[0].title");
        Assert.assertEquals(filterCategory.buyOneGetOneEnglish, title, "Title Buy one get one");
    }

    @Test(priority = 5)
    public void verifyOption4() {
        String title = jsonPath.getString("data.filters[1].options[1].title");
        Assert.assertEquals(filterCategory.takeAwayEnglish, title, "Title Takeaway");
    }

    @Test(priority = 6)
    public void verifyOption5() {
        String sixth_title = jsonPath.getString("data.filters[1].options[2].title");
        Assert.assertEquals(filterCategory.newEnglish, sixth_title, "Title New");
    }

    @Test(priority = 7)
    public void verifyOption6() {
        String seventh_title = jsonPath.getString("data.filters[1].options[3].title");
        Assert.assertEquals(filterCategory.dineInEnglish, seventh_title, "Title Dine-in");
    }

    @Test(priority = 8)
    public void verifyOption7() {
        String eighth_title = jsonPath.getString("data.filters[1].options[4].title");
        Assert.assertEquals(filterCategory.reservationEnglish, eighth_title, "Title Reservation");
    }

    @Test(priority = 9)
    public void verifyOption8() {
        String ninth_title = jsonPath.getString("data.filters[1].options[5].title");
        Assert.assertEquals(filterCategory.percentOffEnglish, ninth_title, "Title Percentage off");
    }

    @Test(priority = 10)
    public void verifyOption9() {
        String tenth_title = jsonPath.getString("data.filters[1].options[6].title");
        Assert.assertEquals(filterCategory.deliveryEnglish, tenth_title, "Title Delivery");
    }

    @Test(priority = 11)
    public void verifyOption10() {
        String eleventh_title = jsonPath.getString("data.filters[1].options[7].title");
        Assert.assertEquals(filterCategory.liteEnglish,eleventh_title,"Title Lite");
    }
}