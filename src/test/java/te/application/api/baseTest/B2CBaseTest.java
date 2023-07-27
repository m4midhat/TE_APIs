package te.application.api.baseTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import te.application.appConstants.AppConstants;
import te.application.utilities.Utils;

import java.io.IOException;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.MILLIS;

@Slf4j
public class B2CBaseTest {
    protected Response response;
    protected JsonPath jsonPath;
    static JSONObject testData;

    @BeforeSuite
    public static void setUpSuite() throws IOException, ParseException {
        AppConstants.START_DATE = LocalDateTime.now();
        testData = Utils.readTestData();
        AppConstants.testDataLanguage = Utils.getRandomSupportedLanguage(testData);
        AppConstants.testDataOSPlatform = Utils.getRandomOS(testData);
        AppConstants.testDataOSVersion = Utils.getRandomDeviceOS(testData);
        AppConstants.testDataDeviceModel = Utils.getRandomDevice(testData);
        AppConstants.testDataTimeZone = Utils.getRandomTimeZone(testData);
        AppConstants.testDataCurrency = Utils.getRandomCurrency(testData);
        AppConstants.testDataAppVersion = Utils.getRandomAppVersion(testData);
        RestAssured.baseURI = AppConstants.BASE_URI_B2C;
    }


    @AfterSuite
    public void tearDownSuite(){
        AppConstants.END_DATE = LocalDateTime.now();
        long res = MILLIS.between(AppConstants.START_DATE, AppConstants.END_DATE);
        log.info("Total time taken by the suite : "+ Math.round(res/1000) +"seconds " + res%1000 + "ms" );
    }

}
