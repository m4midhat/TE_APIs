package te.application.api.test.B2C.positive;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2CBaseTest;
import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;


@Slf4j
public class MyAccountTest extends B2CBaseTest {
    @BeforeClass
    public void ValidateMyAccount() throws IOException {
        RestAssured.basePath = endPoints.getProperty("PROFILE_BASE_PATH");

        //Passing bodyData to profile function
        String bodyData = generateAPIBody.Profile(AppConstants.requestLanguage,"25.300579","entertainer",
                AppConstants.requestOSPlatform, AppConstants.requestAppVersion,AppConstants.requestDeviceKey,AppConstants.requestCurrency,
                "55.307709",AppConstants.requestDeviceKey,"1",AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone);

        //Passing Request specifications
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();

        //Storing response
        response = httpRequest.post();
        log.info(response.asString());

        //Extracting Json path from response
        jsonPath = response.jsonPath();

        //Getting session token from jsonPath and storing into AppConstants
        //AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        log.info("Session ID : " + AppConstants.sessionID);

    }

    @Test(priority = 0, description = "Status code check" )
    public void checkStatus(){
        Assert.assertEquals(200, response.getStatusCode(), "Status code validated");
        log.info(String.valueOf(response.getStatusCode()));
    }


    @Test(priority = 1, description = "email check")
    public void checkEmail(){
        String responseEmail = jsonPath.getString("data.user.email");
        Assert.assertEquals(SignInTest.propUserName,responseEmail,"email validated");
        log.info(responseEmail);
        log.info(SignInTest.propUserName);

    }

   /* @Test(priority = 2, description = "image check")
    public void profileImage(){
        String profileImage = jsonPath.getString("data.user.profile_image");
        Assert.assertEquals(SignInTest.profileImage, profileImage,"image validated");
        log.info(profileImage);
        log.info(SignInTest.profileImage);
    }*/

    @Test(priority = 3, description = "first name check")
    public void checkFirstName(){
        String firstName = jsonPath.getString("data.user.first_name");
        Assert.assertEquals(SignInTest.firstName,firstName,"First Name validated");
        log.info(firstName);
        log.info(SignInTest.firstName);
    }

    @Test(priority = 4, description = "last name check")
    public void checkLastName(){
        String lastName = jsonPath.getString("data.user.last_name");
        Assert.assertEquals(SignInTest.lastName,lastName,"Last Name validated");
        log.info(lastName);
        log.info(SignInTest.lastName);
    }

    @Test(priority = 5, description = "date of birth check")
    public void checkDob(){
        String Dob = jsonPath.getString("data.user.date_of_birth");
        Assert.assertEquals(SignInTest.dob,Dob,"Date of Birth validated");
        log.info(Dob);
        log.info(SignInTest.dob);
    }

    @Test(priority = 6, description = "nationality check")
    public void nationality(){
        String nationality = jsonPath.getString("data.user.nationality");
        Assert.assertEquals(SignInTest.nationality,nationality,  "Nationality validated");
        log.info(nationality);
        log.info(SignInTest.nationality);
    }

    @Test(priority = 7, description = "country of residence check")
    public void checkCountryOfResidence() throws IOException, ParseException {
        String country = jsonPath.getString("data.user.country");
        Assert.assertEquals(Utils.countryCode(country),SignInTest.country,"Country validated");
        log.info(country);
        log.info(SignInTest.country);
    }

    @Test(priority = 8, description = "gender check")
    public void checkGender(){
        String gender = jsonPath.getString("data.user.gender");
        Assert.assertEquals(SignInTest.gender,gender,"Gender validated");
        log.info(gender);
        log.info(SignInTest.gender);
    }

    @Test(priority = 9, description = "default currency check")
    public void checkDefaultCurrency(){
        String defaultCurrency = jsonPath.getString("data.user.default_currency");
        Assert.assertEquals(SignInTest.currency,defaultCurrency, "Default Currency validated");
        log.info(defaultCurrency);
        log.info(SignInTest.currency);
    }

}