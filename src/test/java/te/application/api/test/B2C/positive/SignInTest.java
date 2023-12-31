package te.application.api.test.B2C.positive;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
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
public class SignInTest extends B2CBaseTest {
    public static String firstName;
    public static String lastName;
    public static String dob;
    public static String nationality;
    public static String country;
    public static String gender;
    public static String currency;
    static String propUserName;
    String propPassword;
    public static String userid = null;

    String locationID, languageCode;

    public SignInTest(String loc, String lang){
        this.languageCode = lang;
        this.locationID = loc;
    }

    @Factory
    public static Object[] factoryMethod() {
        return new Object[]
                {
                        new SignInTest("1", "en"),
                        new SignInTest("1", "ar"),
                        new SignInTest("1", "ru"),
                        new SignInTest("2", "en"),
                        new SignInTest("2", "ar"),
                        new SignInTest("2", "ru"),
                        new SignInTest("3", "en"),
                        new SignInTest("3", "ar"),
                        new SignInTest("3", "ru"),
                        new SignInTest("6", "en"),
                        new SignInTest("6", "ar"),
                        new SignInTest("6", "ru"),
                        new SignInTest("7", "en"),
                        new SignInTest("7", "ar"),
                        new SignInTest("7", "ru"),
                        new SignInTest("8", "en"),
                        new SignInTest("8", "ar"),
                        new SignInTest("8", "ru"),
                        new SignInTest("9", "en"),
                        new SignInTest("9", "ar"),
                        new SignInTest("9", "ru"),
                        new SignInTest("10", "en"),
                        new SignInTest("10", "ar"),
                        new SignInTest("10", "ru"),
                        new SignInTest("11", "en"),
                        new SignInTest("11", "ar"),
                        new SignInTest("11", "ru"),
                        new SignInTest("18", "en"),
                        new SignInTest("18", "ar"),
                        new SignInTest("18", "ru"),
                        new SignInTest("49", "en"),
                        new SignInTest("49", "ar"),
                        new SignInTest("49", "ru")
                };
    }


    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException, InterruptedException {
        RestAssured.basePath = endPoints.getProperty("B2C_LOGIN");;
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            propUserName = Utils.decodeString(properties.getProperty("username"));
            propPassword = Utils.decodeString(properties.getProperty("password"));

            String bodyData = generateAPIBodyB2C.signIn(0, AppConstants.requestLanguage, true,
                    "25.300579", "entertainer", AppConstants.requestOSPlatform, AppConstants.requestAppVersion,
                    AppConstants.requestDeviceKey, AppConstants.requestCurrency, "55.307709",
                    AppConstants.requestDeviceKey, locationID, propUserName,
                    propPassword, AppConstants.requestOSVersion, AppConstants.requestDeviceModel, AppConstants.requestTimeZone,
                    endPoints.getProperty("AppConstants.SessionURL"), Utils.decodeString(authToken.B2CAUTH_TOKEN),"true");
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                    .contentType("application/json")
                    .header("User-Agent", AppConstants.requestUserAgent)
                    .body(bodyData)
                    .log().all();
            Thread.sleep(250);
            response = httpRequest.post();
            log.info(response.asString());
            jsonPath = response.jsonPath();
            AppConstants.sessionID = jsonPath.getString("data.user.session_token");
            AppConstants.UserID = jsonPath.getString("data.user.user_id");
            log.info("Session ID : " + AppConstants.sessionID);
            log.info("User ID : "+ AppConstants.UserID);
            firstName = jsonPath.getString("data.user.first_name");
            lastName =jsonPath.getString("data.user.last_name");
            //String profileImage = jsonPath.getString("data.user.profile_image");
            gender = jsonPath.getString("data.user.gender");
            nationality = jsonPath.getString("data.user.nationality");
            currency = jsonPath.getString("data.user.currency");
            country = jsonPath.getString("data.user.country");
            dob = jsonPath.getString("data.user.date_of_birth");
            userid = jsonPath.getString("data.user.user_id");
        }
        else {
            log.error("Unable to read the properties file!!!");
        }
    }

    @Test(priority = 0, description = "Status code check", groups = {"Smoke", "Sanity", "Regression"})
    public void checkStatus() {
        Assert.assertEquals(response.getStatusCode(), 200, "Incorrect status code returned, expected value 200");
    }

    @Test(priority = 1, description = "Status Success Message", groups = {"Smoke", "Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void checkMessage() {
        String message = jsonPath.getString("message");
        Assert.assertEquals("success", message, "Message value should be 'success'");
    }

    @Test(priority = 2, description = "email Validate", groups = {"Smoke", "Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void checkEmail() {
        String responseEmail = jsonPath.getString("data.user.email");
        Assert.assertEquals(propUserName, responseEmail);
        log.info("Actual: " + propUserName + " " + "Expected: " + responseEmail);
    }

    @Test(priority = 3, description = "check New user Value should be false", groups = {"Regression"}, dependsOnMethods = "checkStatus")
    public void verifyNewUser() {
        boolean newUserVal = jsonPath.getBoolean("data.user.new_user");
        //log.info(String.valueOf(newUserVal));
        log.info(String.valueOf(newUserVal));
        //Assert.assertEquals(newUserVal, "true", "New User value should be false");
        Assert.assertFalse(newUserVal);
    }



    @Test(priority = 4, description = "Check Session Token is not null", groups = {"Smoke", "Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void verifySessionToken() {
        String sessionToken = jsonPath.getString("data.validation_params.session_token");
        log.info(">>>>>>>>>>>>>>>>>"+sessionToken);
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertNotNull(sessionToken, "Session token should not be null");
    }


    @Test(priority = 5, description = "Check UserID is not null after successful Sign In", groups = {"Smoke", "Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void VerifyUserID() {
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(200, statusCode, "Status code should be 200 returned");
        String userid = jsonPath.getString("data.user.user_id");
        Assert.assertNotNull(userid, "UserID should not be null");
    }


    @Test(priority = 6, description = "Verify Default currency", groups = {"Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void verifyDefaultCurrency(){
        String defaultCurrency = jsonPath.getString("data.user.currency");
        String expectedDefaultCurrency = AppConstants.requestCurrency;
        Assert.assertEquals(expectedDefaultCurrency, defaultCurrency, "Default currency should be USD");
        log.info(">>>>>>>>>>>>>>>>>"+defaultCurrency);
        log.info(defaultCurrency);

    }

    @Test(priority = 7, description = "onboarding status check", groups = {"Regression"}, dependsOnMethods = "checkStatus")
    public void checkOnBoardingStatus() {
        //String expectedOnboardingStatus = "2";
        String actualOnboardingStatus = jsonPath.getString("data.user.onboarding_status");
        Assert.assertNotNull(actualOnboardingStatus, "Status validated");
        //Assert.assertEquals("2",actualOnboardingStatus, "Status validated");
        log.info("actual value in response is : " + actualOnboardingStatus);
    }

    @Test(priority = 8, description = "Verify user Active status " , groups = {"Regression"}, dependsOnMethods = "checkStatus")
    public void CheckUserActiveStatus(){
        int userActiveStatus = jsonPath.getInt("data.user.is_active");
        log.info(String.valueOf(userActiveStatus));
        Assert.assertEquals(1,userActiveStatus,"Status validated");
    }
    @Test(priority = 9, description = "Sign In with valid Email Id and password on multiple devices.", groups = {"Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void signInWithValidEmailAndPasswordOnMultipleDevices() {
        RestAssured.basePath = endPoints.getProperty("B2C_LOGIN");;
        String bodyData = generateAPIBodyB2C.signIn(0, AppConstants.requestLanguage, true,
                "25.300579", "entertainer", AppConstants.requestOSPlatform, AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey, AppConstants.requestCurrency, "55.307709",
                AppConstants.requestDeviceKey, locationID, propUserName, propPassword,
                AppConstants.requestOSVersion, AppConstants.requestDeviceModel, AppConstants.requestTimeZone,
                endPoints.getProperty("AppConstants.SessionURL"), Utils.decodeString(authToken.B2CAUTH_TOKEN),"");
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .header("User-Agent", AppConstants.requestUserAgent)
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        String msg = jsonPath.getString("message");
        Assert.assertEquals(statusCode, 200, "Status code validation Success");
        //AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //String multipleDevicesMessage = "You will be signed out form all other devices";
        //Assert.assertEquals(msg,msg);
        log.info("Actual: " + msg + " " + "Expected: " );

    }
}


