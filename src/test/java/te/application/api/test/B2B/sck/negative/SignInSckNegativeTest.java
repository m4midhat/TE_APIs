package te.application.api.test.B2B.sck.negative;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import te.application.api.baseTest.B2BBaseTest;
import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBodyB2B;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

@Slf4j
public class SignInSckNegativeTest extends B2BBaseTest {
    String propUserName;
    String propPassword;
    String randomPassword = null;
    String randomEmail = null;

    private static final Logger log = Logger.getLogger(SignInSckNegativeTest.class);
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String DOMAIN = "example.com"; // Replace with your desired domain name
    String locationID, languageCode;
    public SignInSckNegativeTest(String loc, String lang){
        this.languageCode = lang;
        this.locationID = loc;
    }

    @Factory
    public static Object[] factoryMethod() {
        return new Object[]
                {
                        new SignInSckNegativeTest("1", "en"),

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
    public void  signInWithRandomEmailAndValidPassword() throws IOException, InterruptedException {
        RestAssured.basePath = endPoints.getProperty("B2B_LOGIN");
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            propPassword = Utils.decodeString(properties.getProperty("passwordSCK"));

            String randomEmail = generateRandomEmail(12);
            String bodyData = generateAPIBodyB2B.signIn("en", "SCK", AppConstants.requestOSPlatform, AppConstants.requestOSVersion,"zufi","zufishan","AE", randomEmail,
                    propPassword,propPassword,"female" ,"true",  "galaxy", "true");
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", authToken.B2BAUTH_TOKEN)
                    .contentType("application/json")
                    .header("User-Agent", AppConstants.requestUserAgent)
                    .body(bodyData)
                    .log().all();
            response = httpRequest.post();
            jsonPath = response.jsonPath();
            jsonPath = new JsonPath(response.asString());
            int statusCode = response.statusCode();
            log.info(statusCode);
            Assert.assertEquals(200, statusCode, "user with this email not registered.");

        }
    }
    @Test (priority = 1, description = "Sign In with valid EmailId and Random password", groups = {"Smoke", "Sanity", "Regression"})
    public void  signInWithValidEmailAndRandomPassword() throws IOException, InterruptedException {
        RestAssured.basePath = endPoints.getProperty("B2B_LOGIN");
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            propUserName = Utils.decodeString(properties.getProperty("usernameSCK"));
            String randomPassword = generateRandomPassword(8);
            String bodyData = generateAPIBodyB2B.signIn("en", "SCK", AppConstants.requestOSPlatform, AppConstants.requestOSVersion,"zufi","zufishan","AE",
                    propUserName, randomPassword,randomPassword,"female" ,"true",  "galaxy", "true");
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", authToken.B2BAUTH_TOKEN)
                    .contentType("application/json")
                    .header("User-Agent", AppConstants.requestUserAgent)
                    .body(bodyData)
                    .log().all();
            response = httpRequest.post();
            jsonPath = response.jsonPath();
            jsonPath = new JsonPath(response.asString());
            int statusCode = response.statusCode();
            log.info(statusCode);
            Assert.assertEquals(422, statusCode, "Invalid Password.");
           }
    }
    @Test (priority = 2, description = "Sign In with blank credentials.", groups = {"Smoke", "Sanity", "Regression"})
    public void  signInWithBlankCredentials() throws InterruptedException {
        RestAssured.basePath = endPoints.getProperty("B2B_LOGIN");
        String bodyData = generateAPIBodyB2B.signIn("en", "SCK", AppConstants.requestOSPlatform, AppConstants.requestOSVersion,"zufi","zufishan","AE", "",
                "","","female" ,"true",  "galaxy", "true");
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", authToken.B2BAUTH_TOKEN)
                .contentType("application/json")
                .header("User-Agent", AppConstants.requestUserAgent)
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(statusCode);
        Assert.assertEquals(422, statusCode, "Email must not be empty.");
         }

    @Test (priority = 3, description = "SignIn with Email Only", groups = {"Smoke", "Sanity", "Regression"})
    public void  signInWithEmailOnly() throws InterruptedException {
        RestAssured.basePath = endPoints.getProperty("B2B_LOGIN");
        String bodyData = generateAPIBodyB2B.signIn("en", "SCK", AppConstants.requestOSPlatform, AppConstants.requestOSVersion,"zufi","zufishan","AE",
                propUserName,"","","female" ,"true",  "galaxy", "true");
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", authToken.B2BAUTH_TOKEN)
                .contentType("application/json")
                .header("User-Agent", AppConstants.requestUserAgent)
                .body(bodyData)
                .log().all();
         response = httpRequest.post();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(statusCode);
        Assert.assertEquals(422, statusCode, "Email and password must not be empty.");
       }

    @Test (priority = 4, description = "SignIn with Password Only", groups = {"Smoke", "Sanity", "Regression"})
    public void  signInWithPasswordOnly() throws InterruptedException {
        RestAssured.basePath = endPoints.getProperty("B2B_LOGIN");
        String bodyData = generateAPIBodyB2B.signIn("en", "SCK", AppConstants.requestOSPlatform, AppConstants.requestOSVersion,"zufi","zufishan","AE",
                "", propPassword,propPassword,"female" ,"true", "galaxy", "true");
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", authToken.B2BAUTH_TOKEN)
                .contentType("application/json")
                .header("User-Agent", AppConstants.requestUserAgent)
                .body(bodyData)
                .log().all();
       // Thread.sleep(250);
        response = httpRequest.post();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(statusCode);
        Assert.assertEquals(422, statusCode, "Email must not be empty.");
         }

}
