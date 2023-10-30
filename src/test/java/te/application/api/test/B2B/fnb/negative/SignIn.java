package te.application.api.test.B2B.fnb.negative;

import io.restassured.path.json.JsonPath;
import te.application.api.baseTest.B2BBaseTest;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import te.application.api.test.B2B.fnb.positive.Signup;
import te.application.appConstants.AppConstants;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBodyB2B;

import java.io.IOException;
import java.util.Properties;
import static te.application.api.test.B2B.fnb.positive.Signup.email;

@Slf4j
public class SignIn extends B2BBaseTest {

    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException, InterruptedException {
        Signup.signUpWithRandomEmail();

        RestAssured.basePath = AppConstants.BASE_PATH_SIGNIN;
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {

            String bodyData = generateAPIBodyB2B.signIn(AppConstants.requestAppVersion, AppConstants.requestOSPlatform,
                    AppConstants.requestLocation,
                    AppConstants.requestOSPlatform, email, AppConstants.requestKey, AppConstants.requestKey,
                    AppConstants.requestDeviceModel, 1,
                    "fnb", "true");
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", "Bearer " + AppConstants.BEARER_TOKEN)
                    .contentType("application/json")
                    .body(bodyData)
                    .log().all();
            Thread.sleep(250);
            response = httpRequest.post();
            log.info(response.asString());
            jsonPath = response.jsonPath();
            AppConstants.sessionID = jsonPath.getString("data.user.session_token");
            AppConstants.UserID = jsonPath.getString("data.user.user_id");
            log.info("Session ID : " + AppConstants.sessionID);
            log.info("User ID : " + AppConstants.UserID);
        } else {
            log.error("Unable to read the properties file!!!");
        }
    }

    @Test(priority = 0, description = "Sign In with blank credentials")
    public void signInWithBlankCredentials() {
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNIN;
        String bodyData = generateAPIBodyB2B.signIn(AppConstants.requestAppVersion, AppConstants.requestOSPlatform,
                AppConstants.requestLocation,
                AppConstants.requestOSPlatform, "", AppConstants.requestKey, AppConstants.requestKey,
                AppConstants.requestDeviceModel, 1,
                "fnb", "true");
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer " + AppConstants.BEARER_TOKEN)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(400, statusCode, "Status code should be 400 returned");
    }

}

