package te.application.api.test.B2C.negative;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2CBaseTest;

import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBodyB2C;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

@Slf4j
public class SignInTest extends B2CBaseTest {

    String propUserName;
    String propPassword;
    String randomPassword = null;
    String randomEmail = null;
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String DOMAIN = "example.com"; // Replace with your desired domain name
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

    public static String generateRandomEmail(int length) {


        StringBuilder email = new StringBuilder(length);
        Random random = new Random();

        // Generate random characters for the local part of the email
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            email.append(ALLOWED_CHARACTERS.charAt(randomIndex));
        }

        email.append('@').append(DOMAIN);
        return email.toString();
    }
    public static String generateRandomPassword(int length) {
        String upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";
        String numberChars = "0123456789";
        String specialChars = "!@#$%^&*()_-+=<>?/{}~|";

        String allChars = upperCaseChars + lowerCaseChars + numberChars + specialChars;
        Random random = new Random();

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allChars.length());
            password.append(allChars.charAt(randomIndex));
        }
        return password.toString();
    }

    @Test (priority = 0, description = "Sign In with Random EmailId and Valid password", groups = {"Smoke", "Sanity", "Regression"})
    public void  signInWithRandomEmailAndValidPassword() throws IOException {
        RestAssured.basePath = endPoints.getProperty("B2C_LOGIN");
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            propPassword = Utils.decodeString(properties.getProperty("password"));
            String randomEmail = generateRandomEmail(12);
            String bodyData = generateAPIBodyB2C.signIn(0, languageCode, true,
                    "25.300579", "entertainer", AppConstants.requestOSPlatform, AppConstants.requestAppVersion,
                    AppConstants.requestDeviceKey, AppConstants.requestCurrency, "55.307709",
                    AppConstants.requestDeviceKey, locationID,randomEmail,
                    propPassword, AppConstants.requestOSVersion, AppConstants.requestDeviceModel, AppConstants.requestTimeZone,
                    endPoints.getProperty("SessionURL"), Utils.decodeString(authToken.B2CAUTH_TOKEN),"true");
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
            Assert.assertEquals(422, statusCode, "Login Validation Failed");
        }
    }
    @Test (priority = 1, description = "Sign In with valid EmailId and Random password", groups = {"Smoke", "Sanity", "Regression"})
    public void  signInWithValidEmailAndRandomPassword() throws IOException {
        RestAssured.basePath = endPoints.getProperty("B2C_LOGIN");
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            propUserName = Utils.decodeString(properties.getProperty("username"));
            String randomPassword = generateRandomPassword(8);
            String bodyData = generateAPIBodyB2C.signIn(0, languageCode, true,
                    "25.300579", "entertainer", AppConstants.requestOSPlatform, AppConstants.requestAppVersion,
                    AppConstants.requestDeviceKey, AppConstants.requestCurrency, "55.307709",
                    AppConstants.requestDeviceKey, locationID, propUserName, randomPassword, AppConstants.requestOSVersion, AppConstants.requestDeviceModel, AppConstants.requestTimeZone,
                    endPoints.getProperty("AppConstants.SessionURL"), Utils.decodeString(authToken.B2CAUTH_TOKEN),"true");
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
            Assert.assertEquals(422, statusCode, "Log in failed Please check your email and/or password.");
        }
    }
    @Test (priority = 2, description = "Sign In with blank credentials.", groups = {"Smoke", "Sanity", "Regression"})
    public void  signInWithBlankCredentials(){
        RestAssured.basePath = endPoints.getProperty("B2C_LOGIN");
        String bodyData = generateAPIBodyB2C.signIn(0, languageCode, true,
                "25.300579", "entertainer", AppConstants.requestOSPlatform, AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey, AppConstants.requestCurrency, "55.307709",
                AppConstants.requestDeviceKey, locationID, "", "", // Both email and password are empty
                AppConstants.requestOSVersion, AppConstants.requestDeviceModel, AppConstants.requestTimeZone,
                endPoints.getProperty("AppConstants.SessionURL"), Utils.decodeString(authToken.B2CAUTH_TOKEN),"true");
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
        Assert.assertEquals(422, statusCode, "Status code should be 422 returned");
    }

    @Test (priority = 3, description = "SignIn with Email Only", groups = {"Smoke", "Sanity", "Regression"})
    public void  signInWithEmailOnly(){
        RestAssured.basePath = endPoints.getProperty("B2C_LOGIN");
        String bodyData = generateAPIBodyB2C.signIn(0, languageCode, true,
                "25.300579", "entertainer", AppConstants.requestOSPlatform, AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey, AppConstants.requestCurrency, "55.307709",
                AppConstants.requestDeviceKey, locationID, propUserName,
                "", AppConstants.requestOSVersion, AppConstants.requestDeviceModel, AppConstants.requestTimeZone,
                endPoints.getProperty("AppConstants.SessionURL"), Utils.decodeString(authToken.B2CAUTH_TOKEN),"true");
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
        Assert.assertEquals(500, statusCode, "Status code should be 500 returned");
    }

    @Test (priority = 4, description = "SignIn with Password Only", groups = {"Smoke", "Sanity", "Regression"})
    public void  signInWithPasswordOnly(){
        RestAssured.basePath = endPoints.getProperty("B2C_LOGIN");
        String bodyData = generateAPIBodyB2C.signIn(0, languageCode, true,
                "25.300579", "entertainer", AppConstants.requestOSPlatform, AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey, AppConstants.requestCurrency, "55.307709",
                AppConstants.requestDeviceKey, locationID, "",
                propPassword, AppConstants.requestOSVersion, AppConstants.requestDeviceModel, AppConstants.requestTimeZone,
                endPoints.getProperty("AppConstants.SessionURL"), Utils.decodeString(authToken.B2CAUTH_TOKEN),"true");
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
        Assert.assertEquals(422, statusCode, "Status code should be 422 returned");
    }

}