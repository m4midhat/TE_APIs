package te.application.api.test.B2C.negative;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2CBaseTest;
import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.data.b2cDataProvider;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;
import java.util.Properties;

import static te.application.api.test.B2C.negative.SignInTest.generateRandomEmail;

@Slf4j
public class ForgetPasswordTest extends B2CBaseTest {

    @Test(priority = 5, description = "Reset password with blank Email Id", dataProviderClass = b2cDataProvider.class, dataProvider = "dataForB2C")
    public void  resetPasswordWithBlankEmail(String locationId, String languageCode){
        RestAssured.basePath = endPoints.getProperty("B2C_FORGOT_PASSWORD");
        String bodyData = generateAPIBody.forgotPassword(AppConstants.requestDeviceModel, AppConstants.requestCurrency,
                AppConstants.requestDeviceKey, AppConstants.requestAppVersion, "entertainer",
                "9143773", endPoints.getProperty("BASE_URI_B2C")+endPoints.getProperty("B2C_FORGOT_PASSWORD"), locationId,
                "25.300579", "55.307709", languageCode, AppConstants.requestDeviceKey,
                "9143773", AppConstants.requestOSPlatform, AppConstants.requestOSVersion, "",
                AppConstants.requestOSPlatform, AppConstants.requestTimeZone);
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
        Assert.assertEquals(422, statusCode, "Status code should be 422 returned");
    }
    @Test (priority = 6, description = "Reset password with invalid Email Id", dataProviderClass = b2cDataProvider.class, dataProvider = "dataForB2C")
    public void  resetPasswordWithRandomEmail(String locationId, String languageCode) throws IOException {
        RestAssured.basePath = endPoints.getProperty("B2C_FORGOT_PASSWORD");
        Properties properties = Utils.initProperties("AppAuthentication");
        if (properties != null) {
            String randomEmail = generateRandomEmail(12);
            String bodyData = generateAPIBody.forgotPassword(AppConstants.requestDeviceModel, AppConstants.requestCurrency,
                    AppConstants.requestDeviceKey, AppConstants.requestAppVersion,
                    "entertainer", "9143773", endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("B2C_FORGOT_PASSWORD"),
                    locationId, "25.300579", "55.307709", languageCode,
                    AppConstants.requestDeviceKey, "9143773", AppConstants.requestOSPlatform,
                    AppConstants.requestOSVersion, randomEmail, AppConstants.requestOSPlatform, AppConstants.requestTimeZone);
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
            Assert.assertEquals(400, statusCode, "Status Code Validated");
        }
    }
}
