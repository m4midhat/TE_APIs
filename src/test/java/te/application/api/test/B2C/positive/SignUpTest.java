package te.application.api.test.B2C.positive;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2CBaseTest;
import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

@Slf4j
public class SignUpTest extends B2CBaseTest {
    public String FN;
    public String LN;
    public String em;
    public String pwd;
    private Faker faker = new Faker();
    public String na;
    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException {

        String email = faker.internet().emailAddress();
        String FirstName = faker.name().firstName();
        String LastName = faker.name().lastName();

        String password = faker.internet().password(8,12,true,true,true);
        password+=Utils.get3RequiredCharactersForPassword();
        log.info("Final Password : "+password);
        String nationality = faker.nation().nationality();
        RestAssured.baseURI = endPoints.getProperty("BASE_URI_B2C");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
        String bodyData = generateAPIBody.signUp(password, 0, LastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","USD",FirstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                endPoints.getProperty("BASE_URI_B2C")+endPoints.getProperty("B2C_LOGIN"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "",nationality, email, AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,password);

        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .header("User-Agent", AppConstants.requestUserAgent)
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        log.info(response.asString());
        jsonPath = response.jsonPath();
        String get_message = jsonPath.getString("message");

        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
            setUp();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
            setUp();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
            setUp();

        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
            setUp();
        }

        FN=FirstName;
        LN=LastName;
        pwd = password;
        na=nationality;
        em= email;
        log.info("STATUS CODE "+response.getStatusCode());
        log.info("Data Sent : "+ FN+ " "+ LN+" "+em);

    }

    @Test(priority = 0, description = "Status code check", groups = {"Smoke", "Sanity", "Regression"} )
    public void checkStatus(){
        assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
    }
    @Test(priority = 1, description = "Verify message" , groups = {"Smoke", "Sanity", "Regression"})

    public void checkMessage(){
        String message = jsonPath.getString("message");
        Assert.assertNotNull(message);
        assertEquals("success", message, "A customer with this email address exists.");
            /*if (message.equalsIgnoreCase("A customer with this email address exists.")){
                int StatusCode = response.getStatusCode();

                Assert.assertEquals(422,StatusCode,"try with new email Id" );*/

    }
    @Test(priority = 2, description = "Verify First name" , groups = {"Smoke", "Sanity", "Regression"})
    public void  checkFirstName(){
        String FirstName1 = jsonPath.getString("data.user.first_name");
        log.info(FirstName1);
        Assert.assertNotNull(FirstName1);
        assertEquals(FirstName1, FN,"name should be like this");

    }
    @Test(priority = 3, description = "Verify last name" , groups = {"Smoke", "Sanity", "Regression"})
    public void  checkLastName(){
        String LastName1 = jsonPath.getString("data.user.last_name");
        log.info(LastName1 );
        Assert.assertNotNull(LastName1);
        assertEquals(LastName1,LN, "name should be like this");

    }
    @Test(priority = 4, description = "Verify session token" , groups = {"Smoke", "Sanity", "Regression"})
    public void  checkSessionToken(){
        String SessionToken = jsonPath.getString("data.user.session_token");
        log.info(SessionToken);
        Assert.assertNotNull(SessionToken);

    }
    //data.is_new_registered
    @Test(priority = 5, description = "Verify newly registers" , groups = {"Sanity", "Regression"})
    public void  checkNewResister(){
        String is_new_register = jsonPath.getString("data.is_new_registered");
        boolean isVerify=Boolean.valueOf(is_new_register);
        log.info(String.valueOf(isVerify));
        Assert.assertNotNull(isVerify);
        assertEquals(true,isVerify, "it should be true in new user case" );

    }
    @Test(priority = 6, description = "Verify nationality" , groups = {"Regression"})
    public void  checkNationality(){
        String Nationality = jsonPath.getString("data.user.nationality");
        log.info(Nationality);
        Assert.assertNotNull(Nationality);

    }
    //data.user.date_of_birth
    @Test(priority = 7, description = "Verify date of birth" , groups = {"Regression"})
    public void  checkBirthdate(){
        String Birthdate = jsonPath.getString("data.user.date_of_birth");
        log.info(Birthdate);
        Assert.assertNotNull(Birthdate);
        assertEquals("18/07/1989", Birthdate,"It must be greater then 13 years.");

    }

    @Test(priority = 8, description = "Verify already exist customer with this email" , groups = {"Sanity", "Regression"})
    public void  checkAlreadyExistEmail() throws IOException {

        String bodyData = generateAPIBody.signUp(pwd, 0, LN,
                AppConstants.requestLanguage,1,"25.095395","entertainer", AppConstants.requestOSPlatform,"8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",AppConstants.requestCurrency,FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                endPoints.getProperty("BASE_URI_B2C")+endPoints.getProperty("B2C_LOGIN"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "",na, em,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,pwd);

        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .header("User-Agent", AppConstants.requestUserAgent)
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        log.info(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        log.info(get_message);
        Assert.assertNotNull(get_message);
        assertEquals("A customer with this email address exists.", get_message,"Already customer exist :(");
        assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }


}
