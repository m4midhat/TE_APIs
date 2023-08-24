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
import java.util.Properties;

import static java.time.temporal.ChronoUnit.MILLIS;
import static te.application.data.response.locations.loadLocations;

@Slf4j
public class B2CBaseTest {
    protected Response response;
    protected JsonPath jsonPath;
    static JSONObject testData;
    protected static Properties endPoints;

    @BeforeSuite(alwaysRun = true)
    public static void setUpSuite() throws IOException, ParseException {
        AppConstants.START_DATE = LocalDateTime.now();
        endPoints = new Properties();
        endPoints = Utils.initProperties("ProductEndPoints");
        loadLocations();
        testData = Utils.readTestData();
        //AppConstants.testDataLanguage = Utils.getRandomSupportedLanguage(testData);
        AppConstants.requestOSPlatform = Utils.getRandomOS(testData);
        AppConstants.requestOSVersion = Utils.getRandomDeviceOS(testData);
        AppConstants.requestDeviceModel = Utils.getRandomDevice(testData);
        AppConstants.requestTimeZone = Utils.getRandomTimeZone(testData);
        AppConstants.requestCurrency = "USD";//Utils.getRandomCurrency(testData);
        AppConstants.requestAppVersion = Utils.getRandomAppVersion(testData);
        AppConstants.requestDeviceKey = "ios-0C7C873D-8A9B-4D34-948E-3F5C19A6B439";//Utils.getRandomDeviceKey(testData);
        RestAssured.baseURI = endPoints.getProperty("BASE_URI_B2C");
        if(AppConstants.LANG != null) {
            if (AppConstants.LANG.compareToIgnoreCase("en") == 0) {
                log.info("Selected Language : 'English'");
                AppConstants.requestLanguage = "en";
            } else if (AppConstants.LANG.compareToIgnoreCase("ru") == 0) {
                log.info("Selected Language : 'Russian'");
                AppConstants.requestLanguage = "ru";
            } else if (AppConstants.LANG.compareToIgnoreCase("ar") == 0) {
                log.info("Selected Language : 'Arabic'");
                AppConstants.requestLanguage = "ar";
            } else if (AppConstants.LANG.compareToIgnoreCase("all") == 0) {
                log.info("Suite will be executed for all languages");
            } else {
                log.error("Invalid Language Selected");
                AppConstants.requestLanguage = Utils.getRandomSupportedLanguage(testData);
            }
        }
        else
            AppConstants.requestLanguage = Utils.getRandomSupportedLanguage(testData);

        AppConstants.requestLanguage = "en";
    }


    @AfterSuite
    public void tearDownSuite(){
        AppConstants.END_DATE = LocalDateTime.now();
        long res = MILLIS.between(AppConstants.START_DATE, AppConstants.END_DATE);
        log.info("Total time taken by the suite : "+ Math.round(res/1000) +"seconds " + res%1000 + "ms" );
    }

}
