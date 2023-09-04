package te.application.api.test.B2C.positive;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2CBaseTest;
import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.data.response.forgotPassword;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

@Slf4j
public class ForgetPasswordTest extends B2CBaseTest {
    String propUserName;
    String locationID, languageCode;


    public ForgetPasswordTest(String loc, String lang){
        this.languageCode = lang;
        this.locationID = loc;
    }

    @Factory
    public static Object[] factoryMethod()
    {
        return new Object[]
                {
                        new ForgetPasswordTest("1", "en"),
                        new ForgetPasswordTest("1", "ar"),
                        new ForgetPasswordTest("1", "ru"),
                        new ForgetPasswordTest("2", "en"),
                        new ForgetPasswordTest("2", "ar"),
                        new ForgetPasswordTest("2", "ru"),
                        new ForgetPasswordTest("3", "en"),
                        new ForgetPasswordTest("3", "ar"),
                        new ForgetPasswordTest("3", "ru"),
                        new ForgetPasswordTest("6", "en"),
                        new ForgetPasswordTest("6", "ar"),
                        new ForgetPasswordTest("6", "ru"),
                        new ForgetPasswordTest("7", "en"),
                        new ForgetPasswordTest("7", "ar"),
                        new ForgetPasswordTest("7", "ru"),
                        new ForgetPasswordTest("8", "en"),
                        new ForgetPasswordTest("8", "ar"),
                        new ForgetPasswordTest("8", "ru"),
                        new ForgetPasswordTest("9", "en"),
                        new ForgetPasswordTest("9", "ar"),
                        new ForgetPasswordTest("9", "ru"),
                        new ForgetPasswordTest("10", "en"),
                        new ForgetPasswordTest("10", "ar"),
                        new ForgetPasswordTest("10", "ru"),
                        new ForgetPasswordTest("11", "en"),
                        new ForgetPasswordTest("11", "ar"),
                        new ForgetPasswordTest("11", "ru"),
                        new ForgetPasswordTest("18", "en"),
                        new ForgetPasswordTest("18", "ar"),
                        new ForgetPasswordTest("18", "ru"),
                        new ForgetPasswordTest("49", "en"),
                        new ForgetPasswordTest("49", "ar"),
                        new ForgetPasswordTest("49", "ru"),
                };
    }

    @BeforeClass(alwaysRun = true)
    public void ValidateForgetPassword() throws IOException {
        RestAssured.basePath = endPoints.getProperty("B2C_FORGOT_PASSWORD");
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            propUserName = Utils.decodeString(properties.getProperty("username"));
            String bodyData = generateAPIBody.forgotPassword(AppConstants.requestDeviceModel, AppConstants.requestCurrency,
                    AppConstants.requestDeviceKey, AppConstants.requestAppVersion, "entertainer",
                    AppConstants.UserID, endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("B2C_FORGOT_PASSWORD"), locationID,
                    "25.300579", "55.307709", languageCode, AppConstants.requestDeviceKey,
                    "9143773", AppConstants.requestOSPlatform, AppConstants.requestOSVersion, propUserName,
                    AppConstants.requestOSPlatform, AppConstants.requestTimeZone);
            RequestSpecification httpRequest = RestAssured.given()
                    .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                    .header("User-Agent", AppConstants.requestUserAgent)
                    .contentType("application/json")
                    .body(bodyData)
                    .log().all();
            response = httpRequest.post();
            log.info(response.asString());
            //Extracting Json path from response
            jsonPath = response.jsonPath();
        }
        else
            log.error("Incorrect file name!!!");
    }

    @Test(description = "Status code check", groups = {"Smoke", "Sanity", "Regression"})
    public void checkStatus() {
        log.info(this.locationID+ " "+ this.languageCode);
        int expectedStatus = 201;
        int actualStatus = jsonPath.getInt("http_response");
        Assert.assertEquals(expectedStatus, actualStatus, "Status validated");
    }

    @Test(priority = 1, description = "Status Success Message", groups = {"Smoke", "Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void checkMessage() {
        log.info(this.locationID+ " "+ this.languageCode);
        String message = jsonPath.getString("message");
        Assert.assertEquals("success", message, "Message value should be 'success'");
    }

    @Test(priority = 2, description = "Verify Success Message is true", groups = {"Smoke", "Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void successMessage() {
        log.info(this.locationID+ " "+ this.languageCode);
        String message = jsonPath.getString("success");
        Assert.assertEquals("true", message, "Message value should be 'true'");
    }

    @Test(priority = 3, description = "Check Email sent is true", groups = {"Sanity", "Regression"}, dependsOnMethods = "checkStatus")
    public void checkSentStatus() {
        log.info(this.locationID+ " "+ this.languageCode);
        String actualKey = jsonPath.getString("data.is_sent");
        Assert.assertTrue(Boolean.parseBoolean(actualKey), "Sent Key validated");
    }

    @Test(priority = 4, description = "Verify ResetPassword Message", groups = {"Regression"}, dependsOnMethods = "checkStatus")
    public void checkResetPasswordMessage() {
        log.info(this.locationID+ " "+ this.languageCode);
        String actualMessage = jsonPath.getString("data.message");
        if(this.languageCode.compareToIgnoreCase("en")==0) {
            Assert.assertEquals(actualMessage, forgotPassword.msgEnglish, "Link should be sent on registered email");
        }
        else if(this.languageCode.compareToIgnoreCase("ar")==0) {
            Assert.assertEquals(actualMessage, forgotPassword.msgArabic, "Link should be sent on registered email");
        }
        else if(this.languageCode.compareToIgnoreCase("ru")==0) {
            Assert.assertEquals(actualMessage, forgotPassword.msgRussian, "Link should be sent on registered email");
        }
    }

}