package te.application.api.test.B2C.negative;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2CBaseTest;

import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Properties;
import java.util.Random;

@Slf4j
public class SignInTest extends B2CBaseTest {
/*
    String propUserName;
    String propPassword;
    String randomPassword = null;
    String randomEmail = null;
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String DOMAIN = "example.com"; // Replace with your desired domain name

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

    public void setUp() throws IOException {
        RestAssured.basePath = AppConstants.B2C_LOGIN;

        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            propUserName = Utils.decodeString(properties.getProperty("username"));
            propPassword = Utils.decodeString(properties.getProperty("password"));

            String bodyData = generateAPIBody.signIn(0, "en", true,
                    "25.300579", "entertainer", "iOS", "8.04.01",
                    "ios-0C7C873D-8A9B-4D34-948E-3F5C19A6B439", "0", "AED", "0", "55.307709",
                    "ios-0C7C873D-8A9B-4D34-948E-3F5C19A6B439", "1", propUserName,
                    propPassword, "15.0", "iPhone XR", "Karachi/Islamabad",
                    AppConstants.SessionURL, Utils.decodeString(authToken.B2CAUTH_TOKEN), "true");
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                    .contentType("application/json")
                    .body(bodyData)
                    .log().all();
            response = httpRequest.post();
            log.info(response.asString());
            jsonPath = response.jsonPath();
            AppConstants.sessionID = jsonPath.getString("data.user.session_token");
            log.info("Session ID................ : " + AppConstants.sessionID);
        }
    }
    @Test (priority = 0, description = "Sign In with Random EmailId and Valid password")
    public void  signInWithRandomEmailAndValidPassword() throws IOException {
        RestAssured.basePath = AppConstants.B2C_LOGIN;
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            propPassword = Utils.decodeString(properties.getProperty("password"));
            String randomEmail = generateRandomEmail(12);
            String bodyData = generateAPIBody.signIn(0, "en", true,
                    "25.300579", "entertainer", "iOS", "8.04.01",
                    "ios-0C7C873D-8A9B-4D34-948E-3F5C19A6B439", "0", "AED", "0", "55.307709",
                    "ios-0C7C873D-8A9B-4D34-948E-3F5C19A6B439", "1",randomEmail,
                    propPassword, "15.0", "iPhone XR", "Karachi/Islamabad",
                    AppConstants.SessionURL, authToken.B2CAUTH_TOKEN,"true");
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                    .contentType("application/json")
                    .body(bodyData)
                    .log().all();
            response = httpRequest.post();
            jsonPath = response.jsonPath();
            jsonPath = new JsonPath(response.asString());
            int statusCode = response.statusCode();
            log.info(String.valueOf(statusCode));
            Assert.assertEquals(200, statusCode, "Login Validation Failed");
        }
    }
    @Test (priority = 1, description = "Sign In with valid EmailId and Random password")
    public void  signInWithValidEmailAndRandomPassword() throws IOException {
        RestAssured.basePath = AppConstants.B2C_LOGIN;
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            propUserName = Utils.decodeString(properties.getProperty("username"));
            String randomPassword = generateRandomPassword(8);
            String bodyData = generateAPIBody.signIn(0, "en", true,
                    "25.300579", "entertainer", "iOS", "8.04.01",
                    "ios-0C7C873D-8A9B-4D34-948E-3F5C19A6B439", "0", "AED", "0", "55.307709",
                    "ios-0C7C873D-8A9B-4D34-948E-3F5C19A6B439", "1", propUserName, randomPassword, "15.0", "iPhone XR", "Karachi/Islamabad",
                    AppConstants.SessionURL, Utils.decodeString(authToken.B2CAUTH_TOKEN), "true");
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                    .contentType("application/json")
                    .body(bodyData)
                    .log().all();
            response = httpRequest.post();
            jsonPath = response.jsonPath();
            jsonPath = new JsonPath(response.asString());
            int statusCode = response.statusCode();
            log.info(String.valueOf(statusCode));
            Assert.assertEquals(200, statusCode, "Log in failed Please check your email and/or password.");
        }
    }
    @Test (priority = 2, description = "Sign In with blank credentials.")
    public void  signInWithBlankCredentials(){
        RestAssured.basePath = AppConstants.B2C_LOGIN;
        String bodyData = generateAPIBody.signIn(0, "en", true,
                "25.300579", "entertainer", "iOS", "8.04.01",
                "ios-0C7C873D-8A9B-4D34-948E-3F5C19A6B439", "0", "AED", "0", "55.307709",
                "ios-0C7C873D-8A9B-4D34-948E-3F5C19A6B439", "1", "", "", // Both email and password are empty
                "15.0", "iPhone XR", "Karachi/Islamabad",
                AppConstants.SessionURL, Utils.decodeString(authToken.B2CAUTH_TOKEN), "true");
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(200, statusCode, "Status code should be 422 returned");
    }

    @Test (priority = 3, description = "SignIn with Email Only")
    public void  signInWithEmailOnly(){
        RestAssured.basePath = AppConstants.B2C_LOGIN;
        String bodyData = generateAPIBody.signIn(0, "en", true,
                "25.300579", "entertainer", "iOS", "8.04.01",
                "ios-0C7C873D-8A9B-4D34-948E-3F5C19A6B439", "0", "AED", "0", "55.307709",
                "ios-0C7C873D-8A9B-4D34-948E-3F5C19A6B439", "1", propUserName,
                "", "15.0", "iPhone XR", "Karachi/Islamabad",
                AppConstants.SessionURL, Utils.decodeString(authToken.B2CAUTH_TOKEN),"true");
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(200, statusCode, "Status code should be 422 returned");
    }

    @Test (priority = 4, description = "SignIn with Password Only")
    public void  signInWithPasswordOnly(){
        RestAssured.basePath = AppConstants.B2C_LOGIN;
        String bodyData = generateAPIBody.signIn(0, "en", true,
                "25.300579", "entertainer", "iOS", "8.04.01",
                "ios-0C7C873D-8A9B-4D34-948E-3F5C19A6B439", "0", "AED", "0", "55.307709",
                "ios-0C7C873D-8A9B-4D34-948E-3F5C19A6B439", "1", "",
                propPassword, "15.0", "iPhone XR", "Karachi/Islamabad",
                AppConstants.SessionURL, Utils.decodeString(authToken.B2CAUTH_TOKEN),"true");
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(200, statusCode, "Status code should be 422 returned");
    }
*/
}