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
    boolean exists;

    @BeforeClass
    public void setUp() throws IOException {
        RestAssured.baseURI = AppConstants.PYTHON_BASE_URI;
        RestAssured.basePath = AppConstants.BASE_PATH_TRENDING_SEARCH;
        //RestAssured.basePath = AppConstants.BASE_PATE_TRENDING_SEARCH;

        String bodyData = generateAPIBody.trendingSearch(true,"1","All",
                "0","en",3,"31.5273517","74.3528161", AppConstants.testDataTimeZone,
                "AED","9120772","9120772","entertainer",AppConstants.testDataAppVersion,
                AppConstants.testDataOSPlatform,AppConstants.testDataOSVersion,"dc57c128bcfc4155",AppConstants.testDataDeviceModel,"dc57c128bcfc4155");
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());

        jsonPath = response.jsonPath();
        System.out.println("............Session ID : " + AppConstants.sessionID);

        //..........................................................
        jsonData =response.asString();
        JsonParser parser = new JsonParser();
        JsonElement root;
        root = parser.parse(jsonData);
        JsonObject jsonObject = root.getAsJsonObject();
        dataObject = jsonObject.getAsJsonObject("data");
        resultsArray = dataObject.getAsJsonArray("results");
        System.out.println("......................................................");
        System.out.println(resultsArray);
        System.out.println("......................................................");
    }
    @Test(priority = 250, description = "Status code check" )
    public void CheckStatus(){

        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
        System.out.println(response.getStatusCode());
    }

    @Test(priority = 251, description = "Test Section Identifiers" )
    public void SectionIdentifiers() {
        for (int i = 0; i < resultsArray.size(); i++) {

            String resultSectionsIdentifiers = jsonPath.getString("data.results["+i+"].section_identifier");
            System.out.println("resultSectionsIdentifiers : "+ i+" "+resultSectionsIdentifiers);
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

                System.out.println("detailSize : " + i + j + " " + detailSize);
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
                System.out.println("QueryParamsTitle : " + i + j + " " + QueryParamsTitle);
                softAssert.assertNotNull(QueryParamsTitle, "QueryParamsTitle is null");
            }
        }
    }
}
