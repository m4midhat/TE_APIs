package te.application.api.test.B2C.negative;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2CBaseTest;
import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.data.response.signUp;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertEquals;

@Slf4j
public class SignUpTest  extends B2CBaseTest {

    private Faker faker = new Faker();
    public  String firstName, lastName, emailAddress, password, nationality, dob;


    @Test(description = "Signup with blank credentials body" , groups = {"Smoke", "Sanity", "Regression"})
    public void  signUpWithBlankCredentials() throws IOException {

        String bodyData = generateAPIBody.signUp("", 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",
                AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,"1989/07/18","55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "" ,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .header("User-Agent", AppConstants.requestUserAgent)
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        jsonPath = response.jsonPath();
        String get_message = jsonPath.getString("message");
        log.info(get_message);
        
        Assert.assertEquals(signUp.INVALID_EMAIL_ADDRESS, get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }
    @Test(priority = 1, description = "Signup with only firstname " , groups = {"Smoke", "Sanity", "Regression"})
    public void  signUpWithFirstNameOnly() throws IOException {

        //Faker faker = new Faker();
        String FirstName = faker.name().firstName();
        firstName = FirstName;
        String bodyData = generateAPIBody.signUp("", 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,FirstName,
                AppConstants.requestDeviceKey,"1989/07/18","55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "" ,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");

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
        
        Assert.assertEquals(signUp.INVALID_EMAIL_ADDRESS, get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }
    @Test(priority = 2, description = "Signup with only Lastname", groups = {"Smoke", "Sanity", "Regression"} )
    public void  signUpWithLastNameOnly() throws IOException {

        //Faker faker = new Faker();
        String LastName = faker.name().lastName();
        lastName =LastName;
        String bodyData = generateAPIBody.signUp("", 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,LastName,
                AppConstants.requestDeviceKey,"1989/07/18","55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "" ,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");

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
        
        Assert.assertEquals(signUp.INVALID_EMAIL_ADDRESS, get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }
    @Test(priority = 3, description = "Signup with only password " , groups = {"Smoke", "Sanity", "Regression"})
    public void  signUpWithPasswordOnly() throws IOException {


        //Faker faker = new Faker();
        String password1 = faker.internet().password(8,16, true,true,true);
        password = password1+Utils.get3RequiredCharactersForPassword();
        String bodyData = generateAPIBody.signUp(password1, 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,"1989/07/18","55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "" ,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,password1);

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
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signUpWithPasswordOnly();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signUpWithPasswordOnly();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signUpWithPasswordOnly();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signUpWithPasswordOnly();
        }
        
        Assert.assertEquals(signUp.INVALID_EMAIL_ADDRESS, get_message, "Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);

    }
    @Test(priority = 4, description = "Signup with only email & First Name ", groups = {"Smoke", "Sanity", "Regression"} )
    public void signupWithEmailAndFirstName() throws IOException {

        String email = faker.internet().emailAddress();


        String bodyData = generateAPIBody.signUp("", 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency, firstName,
                AppConstants.requestDeviceKey,"1989/07/18","55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", email,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");

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
        assertEquals( signUp.LAST_NAME_EMPTY,get_message,"Blank Fields");
        assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 5, description = "Signup with only email & Last Name " , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithEmailAndLastName() throws IOException {

        String email = faker.internet().emailAddress();
        String bodyData = generateAPIBody.signUp("", 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,"1989/07/18","55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", email,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");

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
        assertEquals( signUp.FIRST_NAME_EMPTY,get_message,"Blank Fields");
        assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 6, description = "Signup with only email & Password " , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithEmailAndPassword() throws IOException {

        String email = faker.internet().emailAddress();
        String bodyData = generateAPIBody.signUp(password, 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,"1989/07/18","55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", email,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone, password);

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

        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signupWithEmailAndPassword();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signupWithEmailAndPassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signupWithEmailAndPassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signupWithEmailAndPassword();
        }

        assertEquals( signUp.FIRST_NAME_EMPTY,get_message,"Blank Fields");
        assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 7, description = "Signup with only FirstName & LastName" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithFirstNameAndLastName() throws IOException {

        String bodyData = generateAPIBody.signUp("", 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency, firstName,
                AppConstants.requestDeviceKey,"","55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "",AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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
        assertEquals( signUp.INVALID_EMAIL_ADDRESS,get_message,"Blank Fields");
        assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 8, description = "Signup with only first name & password " , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithFirstNameAndPassword() throws IOException {

        password = faker.internet().password(8,16, true,true,true);
        firstName = faker.name().firstName();
        password += Utils.get3RequiredCharactersForPassword();
        String bodyData = generateAPIBody.signUp(password, 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency, firstName,
                AppConstants.requestDeviceKey,"","55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "",AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone, password);
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signupWithFirstNameAndPassword();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signupWithFirstNameAndPassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signupWithFirstNameAndPassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signupWithFirstNameAndPassword();
        }
        assertEquals( signUp.INVALID_EMAIL_ADDRESS,get_message,"Blank Fields");
        assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 9, description = "Signup with only email, first & last name " , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithEmailFirstNameLastName() throws IOException {

        //pwd= faker.internet().password(8,16, true,true,true);
        firstName = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        lastName = faker.name().lastName();
        String bodyData = generateAPIBody.signUp("", 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency, firstName,
                AppConstants.requestDeviceKey,"","55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", emailAddress,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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
        assertEquals( signUp.CONFIRM_PASSWORD_EMPTY,get_message,"Blank Fields");
        assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }

    @Test(priority = 10, description = "Signup with only email, first name & password" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithEmailFirstNamePassword() throws IOException {

        password = faker.internet().password(8,16, true,true,true);
        password += Utils.get3RequiredCharactersForPassword();
        firstName = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        //LN = faker.name().lastName();
        String bodyData = generateAPIBody.signUp(password, 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,firstName,
                AppConstants.requestDeviceKey,"","55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", emailAddress,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone, password);
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signupWithEmailFirstNamePassword();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signupWithEmailFirstNamePassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signupWithEmailFirstNamePassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signupWithEmailFirstNamePassword();
        }
        assertEquals( signUp.LAST_NAME_EMPTY,get_message,"Blank Fields");
        assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 11, description = "Signup with only first name, last name & password" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithLastNameFirstNamePassword() throws IOException {

        password = faker.internet().password(8,16, true,true,true);
        password += Utils.get3RequiredCharactersForPassword();
        firstName = faker.name().firstName();
        //em = faker.internet().emailAddress();
        lastName = faker.name().lastName();
        String bodyData = generateAPIBody.signUp(password, 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,firstName,
                AppConstants.requestDeviceKey,"","55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "",AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone, password);
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signupWithLastNameFirstNamePassword();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signupWithLastNameFirstNamePassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signupWithLastNameFirstNamePassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signupWithLastNameFirstNamePassword();
        }
        log.info(get_message);
        assertEquals( signUp.INVALID_EMAIL_ADDRESS,get_message,"Blank Fields");
        assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }


    @Test(priority = 12, description = "Signup with only email, last name & password" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithEmailLastNamePassword() throws IOException {

        password = faker.internet().password(8,16, true,true,true);
        password += Utils.get3RequiredCharactersForPassword();
        emailAddress = faker.internet().emailAddress();
        lastName = faker.name().lastName();
        String bodyData = generateAPIBody.signUp(password, 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,"","55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", emailAddress,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone, password);
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signupWithEmailLastNamePassword();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signupWithEmailLastNamePassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signupWithEmailLastNamePassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signupWithEmailLastNamePassword();
        }
        assertEquals( signUp.FIRST_NAME_EMPTY,get_message,"Blank Fields");
        assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }


    @Test(priority = 13, description = "Signup with only Email,first name,last name & password" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithEmailFirstNameLastNamePassword() throws IOException {

        password = faker.internet().password(8,16, true,true,true);
        password+=Utils.get3RequiredCharactersForPassword();
        firstName = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        lastName = faker.name().lastName();
        String bodyData = generateAPIBody.signUp(password, 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency, firstName,
                AppConstants.requestDeviceKey,"","55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", emailAddress,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone, password);
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signupWithEmailFirstNameLastNamePassword();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signupWithEmailFirstNameLastNamePassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signupWithEmailFirstNameLastNamePassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signupWithEmailFirstNameLastNamePassword();
        }
        log.info(get_message);
        assertEquals( "success",get_message,"Blank Fields");
        assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }


    @Test(priority = 14, description = "Signup with only Date of Birth " , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyDateOfBirth() throws IOException {
        dob = String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));


        String bodyData = generateAPIBody.signUp("", 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "" ,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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
        
        Assert.assertEquals(signUp.INVALID_EMAIL_ADDRESS, get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 15, description = "Signup with only Date of Birth, first name" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyDateOfBirthAndFirstName() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        firstName = faker.name().firstName();
        String bodyData = generateAPIBody.signUp("", 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,firstName,
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "" ,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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
        
        Assert.assertEquals(signUp.INVALID_EMAIL_ADDRESS, get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 16, description = "Signup with only Date of Birth, last name" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyDateOfBirthAndLastName() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();
        firstName = faker.name().firstName();
        String bodyData = generateAPIBody.signUp("", 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "" ,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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
        
        Assert.assertEquals(signUp.INVALID_EMAIL_ADDRESS, get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 17, description = "Signup with only Date of Birth,first name, last name" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyDateOfBirthFirstNameLastName() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();

        String bodyData = generateAPIBody.signUp("", 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,firstName,
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "" ,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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
        
        Assert.assertEquals(signUp.INVALID_EMAIL_ADDRESS, get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 18, description = "Signup with only Email,Date of Birth,first name, last name" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyEmailDateOfBirthFirstNameLastName() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();
        firstName = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        String bodyData = generateAPIBody.signUp("", 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,firstName,
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", emailAddress,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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
        
        Assert.assertEquals(signUp.CONFIRM_PASSWORD_EMPTY, get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 19, description = "Signup with only Email,Date of Birth,first name, last name & password" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyEmailDateOfBirthFirstNameLastNamePassword() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();
        firstName = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);
        password += Utils.get3RequiredCharactersForPassword();
        String bodyData = generateAPIBody.signUp(password, 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,firstName,
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", emailAddress,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone, password);
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signupWithOnlyEmailDateOfBirthFirstNameLastNamePassword();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signupWithOnlyEmailDateOfBirthFirstNameLastNamePassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signupWithOnlyEmailDateOfBirthFirstNameLastNamePassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signupWithOnlyEmailDateOfBirthFirstNameLastNamePassword();
        }
        log.info(get_message);

        Assert.assertEquals("success", get_message,"Blank Fields");
        Assert.assertEquals(200, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 20, description = "Sign up with Email, Date of Birth & password" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyEmailDateOfBirthPassword() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        //LN = faker.name().lastName();
        //FN = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);
        password += Utils.get3RequiredCharactersForPassword();
        String bodyData = generateAPIBody.signUp(password, 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", emailAddress,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone, password);
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signupWithOnlyEmailDateOfBirthPassword();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signupWithOnlyEmailDateOfBirthPassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signupWithOnlyEmailDateOfBirthPassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signupWithOnlyEmailDateOfBirthPassword();
        }
        log.info(get_message);

        Assert.assertEquals(signUp.FIRST_NAME_EMPTY, get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 21, description = "Signup with only Date of Birth , Email ", groups = {"Smoke", "Sanity", "Regression"} )
    public void signupWithOnlyDateOfBirthPassword() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        //LN = faker.name().lastName();
        //FN = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        // pwd= faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp("", 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", emailAddress,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signupWithOnlyDateOfBirthPassword();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signupWithOnlyDateOfBirthPassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signupWithOnlyDateOfBirthPassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signupWithOnlyDateOfBirthPassword();
        }
        log.info(get_message);

        Assert.assertEquals(signUp.FIRST_NAME_EMPTY, get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 22, description = "Signup with only Nationality" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyNationality() throws IOException {
        nationality =faker.nation().nationality();
        String bodyData = generateAPIBody.signUp("", 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,"", "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "" ,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        Assert.assertEquals(signUp.INVALID_EMAIL_ADDRESS, get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 23, description = "Signup with only Nationality, first name" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyNationalityFirstName() throws IOException {
        nationality =faker.nation().nationality();
        firstName =faker.name().firstName();
        String bodyData = generateAPIBody.signUp("", 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,firstName,
                AppConstants.requestDeviceKey,"", "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "" ,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        Assert.assertEquals(signUp.INVALID_EMAIL_ADDRESS, get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 24, description = "Signup with only Nationality, Email" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyNationalityEmail() throws IOException {
        emailAddress =faker.internet().emailAddress();
        nationality =faker.nation().nationality();
        String bodyData = generateAPIBody.signUp("", 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,"", "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, emailAddress,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        Assert.assertEquals(signUp.FIRST_NAME_EMPTY, get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 25, description = "Signup with only Nationality,Password" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyNationalityPassword() throws IOException {
        password =faker.internet().password(8,16,true,true,true);
        password += Utils.get3RequiredCharactersForPassword();
        nationality =faker.nation().nationality();
        String bodyData = generateAPIBody.signUp(password, 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,"", "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "" ,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone, password);
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signupWithOnlyNationalityPassword();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signupWithOnlyNationalityPassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signupWithOnlyNationalityPassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signupWithOnlyNationalityPassword();
        }

        Assert.assertEquals(signUp.INVALID_EMAIL_ADDRESS, get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 26, description = "Signup with only Nationality,Date of birth", groups = {"Smoke", "Sanity", "Regression"} )
    public void signupWithOnlyNationalityDateOfBirth() throws IOException {
        nationality =faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));

        String bodyData = generateAPIBody.signUp("", 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "" ,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        Assert.assertEquals(signUp.INVALID_EMAIL_ADDRESS, get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 27, description = "Signup with only Nationality,First Name,Email" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyNationalityFirstNameEmail() throws IOException {
        nationality =faker.nation().nationality();
        firstName =faker.name().firstName();
        emailAddress =faker.internet().emailAddress();

        String bodyData = generateAPIBody.signUp("", 0, "",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,firstName,
                AppConstants.requestDeviceKey,"", "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, emailAddress,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        Assert.assertEquals(signUp.LAST_NAME_EMPTY, get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 28, description = "Signup with only Nationality,Last Name, Email" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyNationalityLastNameEmail() throws IOException {
        nationality =faker.nation().nationality();
        emailAddress =faker.internet().emailAddress();
        lastName =faker.name().lastName();

        String bodyData = generateAPIBody.signUp("", 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,"", "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, emailAddress,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        Assert.assertEquals(signUp.FIRST_NAME_EMPTY, get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 30, description = "Signup with only Nationality, First Name, Password" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyNationalityFirstNamePassword() throws IOException {
        nationality =faker.nation().nationality();
       /*dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));*/
        //LN = faker.name().lastName();
        firstName = faker.name().firstName();
        // em = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);
        password += Utils.get3RequiredCharactersForPassword();

        String bodyData = generateAPIBody.signUp(password, 0,"",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,firstName,
                AppConstants.requestDeviceKey,"", "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "",AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone, password);
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signupWithOnlyNationalityFirstNamePassword();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signupWithOnlyNationalityFirstNamePassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signupWithOnlyNationalityFirstNamePassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signupWithOnlyNationalityFirstNamePassword();
        }

        Assert.assertEquals(signUp.INVALID_EMAIL_ADDRESS, get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 32, description = "Signup with only Nationality, First Name, Date of Birth" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyNationalityFirstNameDateOfBirth() throws IOException {
        nationality =faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        //LN = faker.name().lastName();
        firstName = faker.name().firstName();
        // em = faker.internet().emailAddress();
        //pwd= faker.internet().password(8,16, true,true,true);


        String bodyData = generateAPIBody.signUp("", 0,"",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,firstName,
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "",AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        Assert.assertEquals(signUp.INVALID_EMAIL_ADDRESS, get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 30, description = "Signup with only Nationality, Last Name, Password" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyNationalityLastNamePassword() throws IOException {
        nationality =faker.nation().nationality();
       /*dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));*/
        lastName = faker.name().lastName();
        //FN = faker.name().firstName();
        // em = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);
        password += Utils.get3RequiredCharactersForPassword();

        String bodyData = generateAPIBody.signUp(password, 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,"", "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "",AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone, password);
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signupWithOnlyNationalityLastNamePassword();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signupWithOnlyNationalityLastNamePassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signupWithOnlyNationalityLastNamePassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signupWithOnlyNationalityLastNamePassword();
        }

        Assert.assertEquals(signUp.INVALID_EMAIL_ADDRESS, get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 33, description = "Signup with only Nationality, Last Name, Date of Birth" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyNationalityLastNameDateOfBirth() throws IOException {
        nationality =faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();
        //FN = faker.name().firstName();
        // em = faker.internet().emailAddress();
        //pwd= faker.internet().password(8,16, true,true,true);


        String bodyData = generateAPIBody.signUp("", 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "",AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        Assert.assertEquals(signUp.INVALID_EMAIL_ADDRESS, get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 34, description = "Signup with only Nationality,email, password, Date of Birth" , groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyNationalityEmailPasswordDateOfBirth() throws IOException {
        nationality =faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        // LN = faker.name().lastName();
        //FN = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);
        password += Utils.get3RequiredCharactersForPassword();

        String bodyData = generateAPIBody.signUp(password, 0,"",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, emailAddress,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone, password);
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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


        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signupWithOnlyNationalityEmailPasswordDateOfBirth();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signupWithOnlyNationalityEmailPasswordDateOfBirth();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signupWithOnlyNationalityEmailPasswordDateOfBirth();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signupWithOnlyNationalityEmailPasswordDateOfBirth();
        }

        Assert.assertEquals(signUp.FIRST_NAME_EMPTY, get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 35, description = "Signup with only Nationality, first name, last name, email, Date of Birth", groups = {"Smoke", "Sanity", "Regression"} )
    public void signupWithOnlyNationalityEmailFirstNameLastNameDateOfBirth() throws IOException {
        nationality =faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2008))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();
        firstName = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        //pwd= faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp("", 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,firstName,
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, emailAddress,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        Assert.assertEquals(signUp.CONFIRM_PASSWORD_EMPTY, get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 37, description = "Signup with only Nationality, first name, last name, Date of birth, Password", groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyNationalityDateOfBirthFirstNameLastNamePassword() throws IOException {
        nationality =faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();
        firstName = faker.name().firstName();
        //em = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);
        password += Utils.get3RequiredCharactersForPassword();

        String bodyData = generateAPIBody.signUp(password, 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,firstName,
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "",AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone, password);
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signupWithOnlyNationalityDateOfBirthFirstNameLastNamePassword();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signupWithOnlyNationalityDateOfBirthFirstNameLastNamePassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signupWithOnlyNationalityDateOfBirthFirstNameLastNamePassword();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signupWithOnlyNationalityDateOfBirthFirstNameLastNamePassword();
        }

        Assert.assertEquals(signUp.INVALID_EMAIL_ADDRESS, get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 38, description = "Signup with only Nationality, first name, last name, Date of birth, Password, Email", groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyNationalityDateOfBirthFirstNameLastNamePasswordEmail() throws IOException {
        nationality =faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();
        firstName = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);
        password += Utils.get3RequiredCharactersForPassword();

        String bodyData = generateAPIBody.signUp(password, 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,firstName,
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, emailAddress,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone, password);
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signupWithOnlyNationalityDateOfBirthFirstNameLastNamePasswordEmail();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signupWithOnlyNationalityDateOfBirthFirstNameLastNamePasswordEmail();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signupWithOnlyNationalityDateOfBirthFirstNameLastNamePasswordEmail();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signupWithOnlyNationalityDateOfBirthFirstNameLastNamePasswordEmail();
        }

        Assert.assertEquals("success", get_message,"Blank Fields");
        Assert.assertEquals(200, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 39, description = "Signup with only first name, email, password, Date of Birth, Nationality", groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyNationalityDateOfBirthFirstNamePasswordEmail() throws IOException {
        nationality =faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        //LN = faker.name().lastName();
        firstName = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);
        password += Utils.get3RequiredCharactersForPassword();

        String bodyData = generateAPIBody.signUp(password, 0,"",
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,firstName,
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, emailAddress,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone, password);
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signupWithOnlyNationalityDateOfBirthFirstNamePasswordEmail();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signupWithOnlyNationalityDateOfBirthFirstNamePasswordEmail();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signupWithOnlyNationalityDateOfBirthFirstNamePasswordEmail();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signupWithOnlyNationalityDateOfBirthFirstNamePasswordEmail();
        }

        Assert.assertEquals(signUp.LAST_NAME_EMPTY , get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 40, description = "Signup with only last name, email, password, Date of Birth, Nationality", groups = {"Smoke", "Sanity", "Regression"})
    public void signupWithOnlyNationalityDateOfBirthLastNamePasswordEmail() throws IOException {
        nationality =faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();
        //FN = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);
        password += Utils.get3RequiredCharactersForPassword();
        String bodyData = generateAPIBody.signUp(password, 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,"",
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP") ,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, emailAddress,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone, password);
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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

        if (get_message.equalsIgnoreCase(signUp.PASSWORD_NUMERIC)){
            signupWithOnlyNationalityDateOfBirthLastNamePasswordEmail();
        }
        if (get_message.equalsIgnoreCase(signUp.PASSWORD_SMALL_CHAR)){
            signupWithOnlyNationalityDateOfBirthLastNamePasswordEmail();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_CAPITAL_CHAR)) {
            signupWithOnlyNationalityDateOfBirthLastNamePasswordEmail();
        } if (get_message.equalsIgnoreCase(signUp.PASSWORD_8CHAR)) {
            signupWithOnlyNationalityDateOfBirthLastNamePasswordEmail();
        }


        Assert.assertEquals(signUp.FIRST_NAME_EMPTY , get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }


    @Test(priority = 41, description = "Signup age under 14 years", groups = {"Smoke", "Sanity", "Regression"} )
    public void ageLimitVerificationOnSignUp() throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy");
        int last14Years = Integer.parseInt(simpleDateFormat.format(new Date()))-11;
        nationality =faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(last14Years, Integer.parseInt(simpleDateFormat.format(new Date()))))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();
        firstName = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        String bodyData = generateAPIBody.signUp("", 0, lastName,
                AppConstants.requestLanguage,1,"25.095395","entertainer",AppConstants.requestOSPlatform,AppConstants.requestAppVersion,
                AppConstants.requestDeviceKey,AppConstants.requestCurrency,firstName,
                AppConstants.requestDeviceKey,dob, "55.154117",
                endPoints.getProperty("BASE_URI_B2C") + endPoints.getProperty("BASE_PATH_SIGNUP"),
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, emailAddress,AppConstants.requestOSVersion,AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone,"");
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SIGNUP");
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
        String get_message = jsonPath.getString("message");
        Assert.assertEquals(signUp.AGE_LIMIT_14YEARS, get_message,"Age limit while signing up");
        Assert.assertEquals(422, response.getStatusCode(), signUp.INCORRECT_STATUS_EXP422);
    }
}
