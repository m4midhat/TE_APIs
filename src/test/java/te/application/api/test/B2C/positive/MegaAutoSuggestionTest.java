package te.application.api.test.B2C.positive;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
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
public class MegaAutoSuggestionTest extends B2CBaseTest {
    //String searchQuary = generateRandomString(2)
    JsonArray resultsArray;
    @BeforeClass

    public void setUp() throws IOException {
        RestAssured.baseURI = AppConstants.PYTHON_BASE_URI;
        RestAssured.basePath = AppConstants.BASE_PATH_Mega_AUTO_SEARCH;
        String generatedString = RandomStringUtils.randomAlphabetic(2).toLowerCase();
        log.info(generatedString);


        String bodyData = generateAPIBody.autoMegaSearch("All","0","true",
                "1","true",generatedString,AppConstants.requestLanguage,"true",3,
                "25.300579","55.307709",10,AppConstants.requestTimeZone,
                AppConstants.sessionID,"AED","9120772","9120772",
                "entertainer",AppConstants.requestAppVersion,"android",AppConstants.requestOSVersion,
                "dc57c128bcfc4155", AppConstants.requestDeviceModel,"dc57c128bcfc4155");
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        log.info(response.asString());
        jsonPath = response.jsonPath();
        log.info("Session ID : " + AppConstants.sessionID);
//.............................................................................
        String jsonData =response.asString();
        JsonParser parser = new JsonParser();
        // Parse the response to JsonObject
        JsonElement root = JsonParser.parseString(response.asString());
        JsonObject jsonObject = root.getAsJsonObject();
        // Extract 'data' object from the JsonObject
        JsonObject dataObject = jsonObject.getAsJsonObject("data");
        // Extract 'home_sections' array from the 'data' object
        resultsArray = dataObject.getAsJsonArray("results");
        // Use the extracted data as needed
        // For example, to print the entire 'home_sections' array:
        log.info("resultsArray: " + resultsArray);
        //log.info("7>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    }
    @Test(priority = 200, description = "Status code check" )
    public void CheckStatus(){
        log.info("status code: " + response.getStatusCode());
        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
        log.info(String.valueOf(response.getStatusCode()));
    }
    @Test(priority = 201, description = "Test Section Identifiers" )
    public void SectionIdentifiers() {

        for (int i = 0; i < resultsArray.size(); i++) {

            String resultSectionsIdentifiers = jsonPath.getString("data.results["+i+"].section_identifier");
            log.info("resultSectionsIdentifiers : "+ i+" "+resultSectionsIdentifiers);
            Assert.assertNotNull(resultSectionsIdentifiers,"resultSectionsIdentifiers is null");
        }
    }

    @Test(priority = 202, description = "Test Result Details" )
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
    @Test(priority = 203, description = "Test Result Details title" )
    public void resultDetailsTitle() {

        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < resultsArray.size(); i++) {
            JsonObject section = resultsArray.get(i).getAsJsonObject();
            JsonArray detailArray = section.getAsJsonArray("details");

            for (int j = 0; j < detailArray.size(); j++) {
                String detailTitle = jsonPath.getString("data.results[" + i + "].details[" + j + "].title");
                if (response.path("data.results[" + i + "].details[" + j + "].title")==null){
                    log.info(">>>>>>>>>>>>>>>NOT EXIST AT " +i+j+"<<<<<<<<<<<<<<<<<<<");
                }else {
                    log.info("detailTitle : " + i + j + " " + detailTitle);
                    softAssert.assertNotNull(detailTitle, "detailTitle is null");
                } }
        }
    }
    @Test(priority = 204, description = "Test Result Details sub_title" )
    public void resultDetailsSubTitle() {

        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < resultsArray.size(); i++) {
            JsonObject section = resultsArray.get(i).getAsJsonObject();
            JsonArray detailArray = section.getAsJsonArray("details");

            for (int j = 0; j < detailArray.size(); j++) {
                String detailSubTitle = jsonPath.getString("data.results[" + i + "].details[" + j + "].sub_title");
                if (response.path("data.results[" + i + "].details[" + j + "].sub_title")==null){
                    log.info(">>>>>>>>>>>>>>>NOT EXIST AT " +i+j+"<<<<<<<<<<<<<<<<<<<");
                }else {
                    log.info("detailSubTitle : " + i + j + " " + detailSubTitle);
                    softAssert.assertNotNull(detailSubTitle, "detailSubTitle is null");
                } }
        }
    }
    @Test(priority = 205, description = "Test Result Details Image URL" )
    public void resultDetailsImageURL() {

        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < resultsArray.size(); i++) {
            JsonObject section = resultsArray.get(i).getAsJsonObject();
            JsonArray detailArray = section.getAsJsonArray("details");

            for (int j = 0; j < detailArray.size(); j++) {
                String detailImageURL = jsonPath.getString("data.results[" + i + "].details[" + j + "].image_url");
                if (response.path("data.results[" + i + "].details[" + j + "].image_url")==null){
                    log.info(">>>>>>>>>>>>>>>NOT EXIST AT " +i+j+"<<<<<<<<<<<<<<<<<<<");
                }else {
                    log.info("detailImageURL : " + i + j + " " + detailImageURL);
                    softAssert.assertNotNull(detailImageURL, "detailImageURL is null");
                } }
        }
    }

    @Test(priority = 206, description = "Test Result Details Merchant Tags abbreviated_text" )
    public void resultDetailMerchantTagsAbbreviatedText() {

        SoftAssert softAssert = new SoftAssert();

        for (int i = 1; i < resultsArray.size(); i++) {
            JsonObject array2 = resultsArray.get(i).getAsJsonObject();
            JsonArray detailArray = array2.getAsJsonArray("details");

            for (int j = 1; j < detailArray.size(); j++) {
                JsonObject array3 = detailArray.get(j).getAsJsonObject();
                JsonArray merchantArray = array3.getAsJsonArray("merchant_tags");


                for (int k = 0; k < merchantArray.size(); k++) {

                    if (response.path("data.results["+i+"].details["+j+"].merchant_tags["+k+"].abbreviated_text")==null){
                        log.info(">>>>>>>>>>>>>>>NOT EXIST AT " +i+j+k+"<<<<<<<<<<<<<<<<<<<");
                    }else{
                        String abbreviatedText = jsonPath.getString("data.results["+i+"].details["+j+"].merchant_tags["+k+"].abbreviated_text");
                        log.info("abbreviatedText : " + i + j + k+" " + abbreviatedText);
                        softAssert.assertNotNull(abbreviatedText, "abbreviatedText is null");
                    }
                }
            }

        }
    }
    @Test(priority = 206, description = "Test Result Details Merchant Tags distance" )
    public void resultDetailMerchantTagsDistance() {

        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < resultsArray.size(); i++) {
            JsonObject array2 = resultsArray.get(i).getAsJsonObject();
            JsonArray detailArray = array2.getAsJsonArray("details");

            for (int j = 0; j < detailArray.size(); j++) {
                if (response.path("data.results["+i+"].details["+j+"].distance")==null){
                    log.info(">>>>>>>>>>>>>>>NOT EXIST AT " +i+j+"<<<<<<<<<<<<<<<<<<<");
                }else{
                    String distanceTest = jsonPath.getString("data.results["+i+"].details["+j+"].distance");
                    log.info("distanceTest : " + i + j +" " + distanceTest);
                    softAssert.assertNotNull(distanceTest, "distanceTest is null");

                }
            }

        }
    }
}

