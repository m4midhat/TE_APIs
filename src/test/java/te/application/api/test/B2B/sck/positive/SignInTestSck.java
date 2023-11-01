package te.application.api.test.B2B.sck.positive;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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
@Slf4j
public class SignInTestSck extends B2BBaseTest {
    static String propUserName;
    String propPassword;
    private static final Logger log = Logger.getLogger(SignInTestSck.class);

    String locationID, languageCode;

    public SignInTestSck(String loc, String lang) {
        this.languageCode = lang;
        this.locationID = loc;
    }

    @Factory
    public static Object[] factoryMethod() {
        return new Object[]
                {
                        new SignInTestSck("1", "en"),

                };
    }


    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException, InterruptedException {
        RestAssured.basePath = endPoints.getProperty("B2B_LOGIN");
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            propUserName = Utils.decodeString(properties.getProperty("usernameSCK"));
            propPassword = Utils.decodeString(properties.getProperty("passwordSCK"));
            String headerToken = "Basic " + Utils.decodeString(authToken.B2BAUTH_TOKEN_ENC);

            String bodyData = generateAPIBodyB2B.signIn("en", "SCK", AppConstants.requestOSPlatform, AppConstants.requestOSVersion,"zufi","zufishan","AE",
                    propUserName, propPassword,propPassword,"female" ,"true", AppConstants.requestDeviceModel, "true");
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", headerToken)
                    .contentType("application/json")
                    .header("User-Agent", AppConstants.requestUserAgent)
                    .body(bodyData)
                    .log().all();
            response = httpRequest.post();

            log.info(response.asString());
            jsonPath = response.jsonPath();
            AppConstants.sessionID = jsonPath.getString("data.session_token");
            AppConstants.UserID = jsonPath.getString("data.user_id");
            log.info("Session ID : " + AppConstants.sessionID);
            log.info("User ID : " + AppConstants.UserID);

        } else {
            log.info("Unable to read the properties file!!!");
        }
    }

    @Test(priority = 0, description = "Status code check", groups = {"Smoke", "Sanity", "Regression"})
    public void checkStatus() {
        Assert.assertEquals(response.getStatusCode(), 200, "Incorrect status code returned, expected value 200");
    }

    @Test(priority = 1, description = "Status Success Message", groups = {"Smoke", "Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void checkMessage() {
        String message = jsonPath.getString("message");
        Assert.assertEquals("Success", message, "Message value should be 'Success'");
    }
    @Test(priority = 4, description = "Check Session Token is not null", groups = {"Smoke", "Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void verifySessionToken() {
        String sessionToken = jsonPath.getString("data.validation_params.session_token");
        System.out.println(">>>>>>>>>>>>>>>>>"+sessionToken);
        Assert.assertNotNull(sessionToken, "Session token should not be null");
    }
    @Test(priority = 5, description = "Check UserID is not null after successful Sign In", groups = {"Smoke", "Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void VerifyUserID() {
       String userid = jsonPath.getString("data.user_id");
        Assert.assertNotNull(userid, "UserID should not be null");
    }
    @Test(priority = 6, description = "Check UserID is not null after successful Sign In", groups = {"Smoke", "Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void VerifyNewUser() {
        boolean newUser = jsonPath.getBoolean("data.new_user");
        Assert.assertFalse(newUser);
    }
}
