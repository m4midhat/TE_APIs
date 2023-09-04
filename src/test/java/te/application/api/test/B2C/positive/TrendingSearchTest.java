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
import org.testng.asserts.SoftAssert;
import te.application.api.baseTest.B2CBaseTest;
import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;

@Slf4j
public class TrendingSearchTest extends B2CBaseTest {
    String jsonData = "";
    JsonObject dataObject;
    JsonArray resultsArray;

    String locationID, languageCode;

    public TrendingSearchTest(String loc, String lang){
        this.languageCode = lang;
        this.locationID = loc;
    }


    @Factory
    public static Object[] factoryMethod() {
        return new Object[]
                {
                        new TrendingSearchTest("1", "en"),
                        new TrendingSearchTest("1", "ar"),
                        new TrendingSearchTest("1", "ru"),
                        new TrendingSearchTest("2", "en"),
                        new TrendingSearchTest("2", "ar"),
                        new TrendingSearchTest("2", "ru"),
                        new TrendingSearchTest("3", "en"),
                        new TrendingSearchTest("3", "ar"),
                        new TrendingSearchTest("3", "ru"),
                        new TrendingSearchTest("6", "en"),
                        new TrendingSearchTest("6", "ar"),
                        new TrendingSearchTest("6", "ru"),
                        new TrendingSearchTest("7", "en"),
                        new TrendingSearchTest("7", "ar"),
                        new TrendingSearchTest("7", "ru"),
                        new TrendingSearchTest("8", "en"),
                        new TrendingSearchTest("8", "ar"),
                        new TrendingSearchTest("8", "ru"),
                        new TrendingSearchTest("9", "en"),
                        new TrendingSearchTest("9", "ar"),
                        new TrendingSearchTest("9", "ru"),
                        new TrendingSearchTest("10", "en"),
                        new TrendingSearchTest("10", "ar"),
                        new TrendingSearchTest("10", "ru"),
                        new TrendingSearchTest("11", "en"),
                        new TrendingSearchTest("11", "ar"),
                        new TrendingSearchTest("11", "ru"),
                        new TrendingSearchTest("18", "en"),
                        new TrendingSearchTest("18", "ar"),
                        new TrendingSearchTest("18", "ru"),
                        new TrendingSearchTest("49", "en"),
                        new TrendingSearchTest("49", "ar"),
                        new TrendingSearchTest("49", "ru")
                };
    }

    @BeforeClass
    public void setUp() throws IOException {
        RestAssured.baseURI = endPoints.getProperty("PYTHON_BASE_URI");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_TRENDING_SEARCH");
        //RestAssured.basePath = AppConstants.BASE_PATE_TRENDING_SEARCH;

        String bodyData = generateAPIBody.trendingSearch(true,locationID,"All",
                "0",languageCode,3,"31.5273517","74.3528161", AppConstants.requestTimeZone,
                AppConstants.requestCurrency,"entertainer",AppConstants.requestAppVersion,
                AppConstants.requestOSPlatform,AppConstants.requestOSVersion,AppConstants.requestDeviceKey,AppConstants.requestDeviceModel,AppConstants.requestDeviceKey);
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .header("User-Agent", AppConstants.requestUserAgent)
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
        resultsArray = dataObject.getAsJsonArray("results");
        log.info(String.valueOf(resultsArray));
    }
    @Test(priority = 250, description = "Status code check" )
    public void CheckStatus(){

        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
        log.info(String.valueOf(response.getStatusCode()));
    }

    @Test(priority = 251, description = "Test Section Identifiers" )
    public void SectionIdentifiers() {
        for (int i = 0; i < resultsArray.size(); i++) {

            String resultSectionsIdentifiers = jsonPath.getString("data.results["+i+"].section_identifier");
            log.info("resultSectionsIdentifiers : "+ i+" "+resultSectionsIdentifiers);
            Assert.assertNotNull(resultSectionsIdentifiers,"resultSectionsIdentifiers is null");
        }
    }

    @Test(priority = 252, description = "Test Result Details" )
    public void resultDetails() {

        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < resultsArray.size(); i++) {
            JsonObject section = resultsArray.get(i).getAsJsonObject();
            JsonArray detailArray = section.getAsJsonArray("details");

            for (int j = 0; j < detailArray.size(); j++) {
                Integer detailSize = jsonPath.getInt("data.results[" + i + "].details[" + j + "].size()");
                log.info("detailSize : " + i + j + " " + detailSize);
                Assert.assertNotEquals(0, detailSize);
            }
        }
    }
    @Test(priority = 253, description = "Test Trending Search Query params" )
    public void resultDetailsQueryTitle() {

        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < resultsArray.size(); i++) {
            JsonObject section = resultsArray.get(i).getAsJsonObject();
            JsonArray detailArray = section.getAsJsonArray("details");

            for (int j = 0; j < detailArray.size(); j++) {
                String QueryParamsTitle = jsonPath.getString("data.results[" + i + "].details[" + j + "].title");
                log.info("QueryParamsTitle : " + i + j + " " + QueryParamsTitle);
                softAssert.assertNotNull(QueryParamsTitle, "QueryParamsTitle is null");
            }
        }
    }
}
