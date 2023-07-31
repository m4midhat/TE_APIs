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
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class FilterCategoriesTest extends B2CBaseTest {

    @BeforeClass
    public void setUp() throws IOException {
        RestAssured.baseURI = AppConstants.PYTHON_BASE_URI;
        RestAssured.basePath = AppConstants.B2C_FILTERS;
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            String propUserName = Utils.decodeString(properties.getProperty("username"));
            String propPassword = Utils.decodeString(properties.getProperty("password"));

            String bodyData = generateAPIBody.filter("en","31.527539", "entertainer",
                    AppConstants.testDataOSPlatform, AppConstants.testDataAppVersion, "ios-C21A0532-B8A5-442D-9204-43805238DE88", "9143933",
                    "AED", AppConstants.UserID, 1, "ios-C21A0532-B8A5-442D-9204-43805238DE88", "Restaurants and Bars",
                    "74.352919", "c86078f5-5a6c-4831-ba8a-1e9914a1ce33", "1", 0, AppConstants.testDataOSVersion, AppConstants.testDataDeviceModel,
                    AppConstants.testDataTimeZone, "74.352919", AppConstants.testDataOSPlatform, "31.527539",
                    AppConstants.SessionURL, Utils.decodeString(authToken.B2CAUTH_TOKEN));
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                    .contentType("application/json")
                    .body(bodyData)
                    .log().all();
            response = httpRequest.post();
            log.info(response.asString());
            jsonPath = response.jsonPath();
            //AppConstants.sessionID = jsonPath.getString("data.user.session_token");
            //log.info("Session ID : " + AppConstants.sessionID);
        }
    }

    @Test(priority = 0, description = "Status code check")
    public void checkStatus() {
        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
    }

    @Test(priority = 1)
    public void verifyFiltersTitle() {
        String first_title = jsonPath.getString("data.filters[0].options[0].title");
        Assert.assertEquals("Nearest", first_title, "Title Nearest");
    }

    @Test(priority = 1)
    public void verifyOption1() {
        String second_title = jsonPath.getString("data.filters[0].options[1].title");
        Assert.assertEquals("Top rated", second_title, "Title top rated");
    }

    @Test(priority = 1)
    public void verifyOption2() {
        String third_title = jsonPath.getString("data.filters[0].options[2].title");
        Assert.assertEquals("New", third_title, "Title New");
    }

    @Test(priority = 1)
    public void verifyOption3() {
        String fourth_title = jsonPath.getString("data.filters[1].options[0].title");
        Assert.assertEquals("Buy one get one", fourth_title, "Title Buy one get one");
    }

    @Test(priority = 1)
    public void verifyOption4() {
        String fifth_title = jsonPath.getString("data.filters[1].options[1].title");
        Assert.assertEquals("Takeaway", fifth_title, "Title Takeaway");
    }

    @Test(priority = 1)
    public void verifyOption5() {
        String sixth_title = jsonPath.getString("data.filters[1].options[2].title");
        Assert.assertEquals("New", sixth_title, "Title New");
    }

    @Test(priority = 1)
    public void verifyOption6() {
        String seventh_title = jsonPath.getString("data.filters[1].options[3].title");
        Assert.assertEquals("Dine-in", seventh_title, "Title Dine-in");
    }

    @Test(priority = 1)
    public void verifyOption7() {
        String eighth_title = jsonPath.getString("data.filters[1].options[4].title");
        Assert.assertEquals("Reservation", eighth_title, "Title Reservation");
    }

    @Test(priority = 1)
    public void verifyOption8() {
        String ninth_title = jsonPath.getString("data.filters[1].options[5].title");
        Assert.assertEquals("Percentage off", ninth_title, "Title Percentage off");
    }

    @Test(priority = 1)
    public void verifyOption9() {
        String tenth_title = jsonPath.getString("data.filters[1].options[6].title");
        Assert.assertEquals("Delivery", tenth_title, "Title Delivery");
    }

    @Test(priority = 1)
    public void verifyOption10() {
        String eleventh_title = jsonPath.getString("data.filters[1].options[7].title");
        Assert.assertEquals("Lite",eleventh_title,"Title Lite");
    }
}