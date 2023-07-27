package te.application.api.test.B2C.negative;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2CBaseTest;
import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class SignUpTest  extends B2CBaseTest {

    private Faker faker = new Faker();
    public  String firstName, lastName, emailAddress, password, nationality, dob;

    @Test(priority = 0, description = "Signup with blank credentials body" )
    public void  signUpWithBlankCredentials() throws IOException {

        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "" ,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        jsonPath = response.jsonPath();
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 1, description = "Signup with only firstname " )
    public void  signUpWithFirstNameOnly() throws IOException {

        //Faker faker = new Faker();
        String FirstName = faker.name().firstName();
        firstName =FirstName;
        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0",FirstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "" ,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");

        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 2, description = "Signup with only Lastname " )
    public void  signUpWithLastNameOnly() throws IOException {

        //Faker faker = new Faker();
        String LastName = faker.name().lastName();
        lastName =LastName;
        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0",LastName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "" ,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");

        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 3, description = "Signup with only password " )
    public void  signUpWithPasswordOnly() throws IOException {


        //Faker faker = new Faker();
        String password1 = faker.internet().password(8,16, true,true,true);
        password = password1;
        String bodyData = generateAPIBody.signUp(password1, 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "" ,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,password1);

        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signUpWithPasswordOnly();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signUpWithPasswordOnly();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signUpWithPasswordOnly();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signUpWithPasswordOnly();
        }
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message, "Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");

    }
    @Test(priority = 4, description = "Signup with only email & First Name " )
    public void signupWithEmailAndFirstName() throws IOException {

        String email = faker.internet().emailAddress();


        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", email,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");

        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        assertEquals( "Request parameter lastname is empty",get_message,"Blank Fields");
        assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 5, description = "Signup with only email & Last Name " )
    public void signupWithEmailAndLastName() throws IOException {

        String email = faker.internet().emailAddress();
        String bodyData = generateAPIBody.signUp("", 0, lastName,
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", email,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");

        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        assertEquals( "Request parameter firstname is empty",get_message,"Blank Fields");
        assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 6, description = "Signup with only email & Password " )
    public void signupWithEmailAndPassword() throws IOException {

        String email = faker.internet().emailAddress();
        String bodyData = generateAPIBody.signUp(password, 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", email,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone, password);

        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);

        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signupWithEmailAndPassword();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signupWithEmailAndPassword();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signupWithEmailAndPassword();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signupWithEmailAndPassword();
        }

        assertEquals( "Request parameter firstname is empty",get_message,"Blank Fields");
        assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 7, description = "Signup with only FirstName & LastName" )
    public void signupWithFirstNameAndLastName() throws IOException {

        String bodyData = generateAPIBody.signUp("", 0, lastName,
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","","55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "",AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        assertEquals( "Invalid email address",get_message,"Blank Fields");
        assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 8, description = "Signup with only first name & password " )
    public void signupWithFirstNameAndPassword() throws IOException {

        password = faker.internet().password(8,16, true,true,true);
        firstName = faker.name().firstName();
        String bodyData = generateAPIBody.signUp(password, 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","","55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "",AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone, password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);

        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signupWithFirstNameAndPassword();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signupWithFirstNameAndPassword();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signupWithFirstNameAndPassword();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signupWithFirstNameAndPassword();
        }
        assertEquals( "Invalid email address",get_message,"Blank Fields");
        assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 9, description = "Signup with only email, first & last name " )
    public void signupWithEmailFirstNameLastName() throws IOException {

        //pwd= faker.internet().password(8,16, true,true,true);
        firstName = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        lastName = faker.name().lastName();
        String bodyData = generateAPIBody.signUp("", 0, lastName,
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","","55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", emailAddress,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        assertEquals( "Request parameter confirm_password is empty",get_message,"Blank Fields");
        assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }

    @Test(priority = 10, description = "Signup with only email, first name & password" )
    public void signupWithEmailFirstNamePassword() throws IOException {

        password = faker.internet().password(8,16, true,true,true);
        firstName = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        //LN = faker.name().lastName();
        String bodyData = generateAPIBody.signUp(password, 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","","55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", emailAddress,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone, password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);

        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signupWithEmailFirstNamePassword();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signupWithEmailFirstNamePassword();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signupWithEmailFirstNamePassword();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signupWithEmailFirstNamePassword();
        }
        assertEquals( "Request parameter lastname is empty",get_message,"Blank Fields");
        assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 11, description = "Signup with only first name, last name & password" )
    public void signupWithLastNameFirstNamePassword() throws IOException {

        password = faker.internet().password(8,16, true,true,true);
        firstName = faker.name().firstName();
        //em = faker.internet().emailAddress();
        lastName = faker.name().lastName();
        String bodyData = generateAPIBody.signUp(password, 0, lastName,
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","","55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "",AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone, password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");

        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signupWithLastNameFirstNamePassword();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signupWithLastNameFirstNamePassword();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signupWithLastNameFirstNamePassword();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signupWithLastNameFirstNamePassword();
        }
        System.out.println(get_message);
        assertEquals( "Invalid email address",get_message,"Blank Fields");
        assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 12, description = "Signup with only email, last name & password" )
    public void signupWithEmailLastNamePassword() throws IOException {

        password = faker.internet().password(8,16, true,true,true);
        //FN = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        lastName = faker.name().lastName();
        String bodyData = generateAPIBody.signUp(password, 0, lastName,
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","","55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", emailAddress,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone, password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);

        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signupWithEmailLastNamePassword();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signupWithEmailLastNamePassword();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signupWithEmailLastNamePassword();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signupWithEmailLastNamePassword();
        }
        assertEquals( "Request parameter firstname is empty",get_message,"Blank Fields");
        assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 13, description = "Signup with only Email,first name,last name & password" )
    public void signupWithEmailFirstNameLastNamePassword() throws IOException {

        password = faker.internet().password(8,16, true,true,true);
        firstName = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        lastName = faker.name().lastName();
        String bodyData = generateAPIBody.signUp(password, 0, lastName,
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","","55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", emailAddress,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone, password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");

        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signupWithEmailFirstNameLastNamePassword();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signupWithEmailFirstNameLastNamePassword();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signupWithEmailFirstNameLastNamePassword();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signupWithEmailFirstNameLastNamePassword();
        }
        System.out.println(get_message);
        assertEquals( "success",get_message,"Blank Fields");
        assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }

    @Test(priority = 14, description = "Signup with only Date of Birth " )
    public void signupWithOnlyDateOfBirth() throws IOException {
        dob = String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));


        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "" ,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }

    @Test(priority = 15, description = "Signup with only Date of Birth, first name" )
    public void signupWithOnlyDateOfBirthAndFirstName() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        firstName = faker.name().firstName();
        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "" ,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 16, description = "Signup with only Date of Birth, last name" )
    public void signupWithOnlyDateOfBirthAndLastName() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();
        firstName = faker.name().firstName();
        String bodyData = generateAPIBody.signUp("", 0, lastName,
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "" ,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 17, description = "Signup with only Date of Birth,first name, last name" )
    public void signupWithOnlyDateOfBirthFirstNameLastName() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();

        String bodyData = generateAPIBody.signUp("", 0, lastName,
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", "" ,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 18, description = "Signup with only Email,Date of Birth,first name, last name" )
    public void signupWithOnlyEmailDateOfBirthFirstNameLastName() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();
        firstName = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        String bodyData = generateAPIBody.signUp("", 0, lastName,
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", emailAddress,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Request parameter confirm_password is empty", get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 19, description = "Signup with only Email,Date of Birth,first name, last name & password" )
    public void signupWithOnlyEmailDateOfBirthFirstNameLastNamePassword() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();
        firstName = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp(password, 0, lastName,
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", emailAddress,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone, password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");

        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signupWithOnlyEmailDateOfBirthFirstNameLastNamePassword();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signupWithOnlyEmailDateOfBirthFirstNameLastNamePassword();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signupWithOnlyEmailDateOfBirthFirstNameLastNamePassword();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signupWithOnlyEmailDateOfBirthFirstNameLastNamePassword();
        }
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("success", get_message,"Blank Fields");
        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 20, description = "Sign up with Email, Date of Birth & password" )
    public void signupWithOnlyEmailDateOfBirthPassword() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        //LN = faker.name().lastName();
        //FN = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp(password, 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", emailAddress,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone, password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signupWithOnlyEmailDateOfBirthPassword();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signupWithOnlyEmailDateOfBirthPassword();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signupWithOnlyEmailDateOfBirthPassword();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signupWithOnlyEmailDateOfBirthPassword();
        }
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Request parameter firstname is empty", get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 21, description = "Signup with only Date of Birth , Email " )
    public void signupWithOnlyDateOfBirthPassword() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        //LN = faker.name().lastName();
        //FN = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        // pwd= faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "","", emailAddress,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signupWithOnlyDateOfBirthPassword();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signupWithOnlyDateOfBirthPassword();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signupWithOnlyDateOfBirthPassword();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signupWithOnlyDateOfBirthPassword();
        }
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Request parameter firstname is empty", get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 22, description = "Signup with only Nationality" )
    public void signupWithOnlyNationality() throws IOException {
        nationality =faker.nation().nationality();
        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","", "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "" ,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 23, description = "Signup with only Nationality, first name" )
    public void signupWithOnlyNationalityFirstName() throws IOException {
        nationality =faker.nation().nationality();
        firstName =faker.name().firstName();
        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","", "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "" ,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 24, description = "Signup with only Nationality, Email" )
    public void signupWithOnlyNationalityEmail() throws IOException {
        emailAddress =faker.internet().emailAddress();
        nationality =faker.nation().nationality();
        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","", "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, emailAddress,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Request parameter firstname is empty", get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }

    @Test(priority = 25, description = "Signup with only Nationality,Password" )
    public void signupWithOnlyNationalityPassword() throws IOException {
        password =faker.internet().password(8,16,true,true,true);
        nationality =faker.nation().nationality();
        String bodyData = generateAPIBody.signUp(password, 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","", "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "" ,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone, password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signupWithOnlyNationalityPassword();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signupWithOnlyNationalityPassword();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signupWithOnlyNationalityPassword();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signupWithOnlyNationalityPassword();
        }
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }

    @Test(priority = 26, description = "Signup with only Nationality,Date of birth" )
    public void signupWithOnlyNationalityDateOfBirth() throws IOException {
        nationality =faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));

        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "" ,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 27, description = "Signup with only Nationality,First Name,Email" )
    public void signupWithOnlyNationalityFirstNameEmail() throws IOException {
        nationality =faker.nation().nationality();
        firstName =faker.name().firstName();
        emailAddress =faker.internet().emailAddress();

        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","", "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, emailAddress,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Request parameter lastname is empty", get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 28, description = "Signup with only Nationality,Last Name, Email" )
    public void signupWithOnlyNationalityLastNameEmail() throws IOException {
        nationality =faker.nation().nationality();
        emailAddress =faker.internet().emailAddress();
        lastName =faker.name().lastName();

        String bodyData = generateAPIBody.signUp("", 0, lastName,
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","", "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, emailAddress,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Request parameter firstname is empty", get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 30, description = "Signup with only Nationality, First Name, Password" )
    public void signupWithOnlyNationalityFirstNamePassword() throws IOException {
        nationality =faker.nation().nationality();
       /*dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));*/
        //LN = faker.name().lastName();
        firstName = faker.name().firstName();
        // em = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);


        String bodyData = generateAPIBody.signUp(password, 0,"",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","", "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "",AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone, password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);

        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signupWithOnlyNationalityFirstNamePassword();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signupWithOnlyNationalityFirstNamePassword();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signupWithOnlyNationalityFirstNamePassword();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signupWithOnlyNationalityFirstNamePassword();
        }
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 32, description = "Signup with only Nationality, First Name, Date of Birth" )
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
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "",AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 30, description = "Signup with only Nationality, Last Name, Password" )
    public void signupWithOnlyNationalityLastNamePassword() throws IOException {
        nationality =faker.nation().nationality();
       /*dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));*/
        lastName = faker.name().lastName();
        //FN = faker.name().firstName();
        // em = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);


        String bodyData = generateAPIBody.signUp(password, 0, lastName,
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","", "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "",AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone, password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);

        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signupWithOnlyNationalityLastNamePassword();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signupWithOnlyNationalityLastNamePassword();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signupWithOnlyNationalityLastNamePassword();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signupWithOnlyNationalityLastNamePassword();
        }
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 33, description = "Signup with only Nationality, Last Name, Date of Birth" )
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
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "",AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 34, description = "Signup with only Nationality,email, password, Date of Birth" )
    public void signupWithOnlyNationalityEmailPasswordDateOfBirth() throws IOException {
        nationality =faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        // LN = faker.name().lastName();
        //FN = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);


        String bodyData = generateAPIBody.signUp(password, 0,"",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, emailAddress,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone, password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);


        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signupWithOnlyNationalityEmailPasswordDateOfBirth();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signupWithOnlyNationalityEmailPasswordDateOfBirth();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signupWithOnlyNationalityEmailPasswordDateOfBirth();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signupWithOnlyNationalityEmailPasswordDateOfBirth();
        }
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Request parameter firstname is empty", get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 35, description = "Signup with only Nationality, first name, last name, email, Date of Birth" )
    public void signupWithOnlyNationalityEmailFirstNameLastNameDateOfBirth() throws IOException {
        nationality =faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();
        firstName = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        //pwd= faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp("", 0, lastName,
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, emailAddress,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Request parameter confirm_password is empty", get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }

    @Test(priority = 37, description = "Signup with only Nationality, first name, last name, Date of birth, Password")
    public void signupWithOnlyNationalityDateOfBirthFirstNameLastNamePassword() throws IOException {
        nationality =faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();
        firstName = faker.name().firstName();
        //em = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp(password, 0, lastName,
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, "",AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone, password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);

        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signupWithOnlyNationalityDateOfBirthFirstNameLastNamePassword();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signupWithOnlyNationalityDateOfBirthFirstNameLastNamePassword();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signupWithOnlyNationalityDateOfBirthFirstNameLastNamePassword();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signupWithOnlyNationalityDateOfBirthFirstNameLastNamePassword();
        }
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }

    @Test(priority = 38, description = "Signup with only Nationality, first name, last name, Date of birth, Password, Email")
    public void signupWithOnlyNationalityDateOfBirthFirstNameLastNamePasswordEmail() throws IOException {
        nationality =faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();
        firstName = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp(password, 0, lastName,
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, emailAddress,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone, password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);

        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signupWithOnlyNationalityDateOfBirthFirstNameLastNamePasswordEmail();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signupWithOnlyNationalityDateOfBirthFirstNameLastNamePasswordEmail();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signupWithOnlyNationalityDateOfBirthFirstNameLastNamePasswordEmail();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signupWithOnlyNationalityDateOfBirthFirstNameLastNamePasswordEmail();
        }
        Assert.assertNotNull(get_message);
        Assert.assertEquals("success", get_message,"Blank Fields");
        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 39, description = "Signup with only first name, email, password, Date of Birth, Nationality")
    public void signupWithOnlyNationalityDateOfBirthFirstNamePasswordEmail() throws IOException {
        nationality =faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        //LN = faker.name().lastName();
        firstName = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp(password, 0,"",
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0", firstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, emailAddress,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone, password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signupWithOnlyNationalityDateOfBirthFirstNamePasswordEmail();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signupWithOnlyNationalityDateOfBirthFirstNamePasswordEmail();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signupWithOnlyNationalityDateOfBirthFirstNamePasswordEmail();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signupWithOnlyNationalityDateOfBirthFirstNamePasswordEmail();
        }
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Request parameter lastname is empty" , get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 40, description = "Signup with only last name, email, password, Date of Birth, Nationality")
    public void signupWithOnlyNationalityDateOfBirthLastNamePasswordEmail() throws IOException {
        nationality =faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        lastName = faker.name().lastName();
        //FN = faker.name().firstName();
        emailAddress = faker.internet().emailAddress();
        password = faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp(password, 0, lastName,
                "en",1,"25.095395","entertainer",AppConstants.testDataOSPlatform,AppConstants.testDataAppVersion,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0",AppConstants.testDataCurrency,"0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "", nationality, emailAddress,AppConstants.testDataOSVersion,AppConstants.testDataDeviceModel,
                AppConstants.testDataTimeZone, password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);

        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            signupWithOnlyNationalityDateOfBirthLastNamePasswordEmail();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            signupWithOnlyNationalityDateOfBirthLastNamePasswordEmail();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            signupWithOnlyNationalityDateOfBirthLastNamePasswordEmail();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            signupWithOnlyNationalityDateOfBirthLastNamePasswordEmail();
        }

        Assert.assertNotNull(get_message);
        Assert.assertEquals("Request parameter firstname is empty" , get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
}
