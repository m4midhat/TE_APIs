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
    public  String FN;
    public String LN;
    public String em;
    public String pwd;
    public String na;
    public String dob;

    @Test(priority = 0, description = "Signup with blank credentials body" )
    public void  Signup_with_blank_Credentials() throws IOException {

        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", "" ,"17.0","iPhone 11",
                "Asia/Karachi","");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        jsonPath = response.jsonPath();
        //jsonPath = new JsonPath(response.asString());
        //AppConstants.sessionID = jsonPath.getString()
        //log.info("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 1, description = "Signup with only firstname " )
    public void  Signup_with_only_FirstName() throws IOException {

        //Faker faker = new Faker();
        String FirstName = faker.name().firstName();
        FN=FirstName;
        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FirstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", "" ,"17.0","iPhone 11",
                "Asia/Karachi","");

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
    public void  Signup_with_only_LastName() throws IOException {

        //Faker faker = new Faker();
        String LastName = faker.name().lastName();
        LN=LastName;
        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",LastName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", "" ,"17.0","iPhone 11",
                "Asia/Karachi","");

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
    public void  Signup_with_only_Password() throws IOException {


        //Faker faker = new Faker();
        String password1 = faker.internet().password(8,16, true,true,true);
        pwd= password1;
        String bodyData = generateAPIBody.signUp(password1, 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", "" ,"17.0","iPhone 11",
                "Asia/Karachi",password1);

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
            Signup_with_only_Password();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_only_Password();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_only_Password();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_only_Password();
        }
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message, "Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");

    }
    @Test(priority = 4, description = "Signup with only email & First Name " )
    public void  Signup_with_EmailAndFirstName() throws IOException {

        String email = faker.internet().emailAddress();


        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", email,"17.0","iPhone 11",
                "Asia/Karachi","");

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
    public void  Signup_with_EmailAndLastName() throws IOException {

        String email = faker.internet().emailAddress();
        String bodyData = generateAPIBody.signUp("", 0, LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", email,"17.0","iPhone 11",
                "Asia/Karachi","");

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
    public void  Signup_with_EmailAndPassword() throws IOException {

        String email = faker.internet().emailAddress();
        String bodyData = generateAPIBody.signUp(pwd, 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", email,"17.0","iPhone 11",
                "Asia/Karachi",pwd);

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
            Signup_with_EmailAndPassword();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_EmailAndPassword();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_EmailAndPassword();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_EmailAndPassword();
        }

        assertEquals( "Request parameter firstname is empty",get_message,"Blank Fields");
        assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 7, description = "Signup with only FirstName & LastName" )
    public void  Signup_with_FirstNameAndLastName() throws IOException {

        String bodyData = generateAPIBody.signUp("", 0, LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","","55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", "","17.0","iPhone 11",
                "Asia/Karachi","");
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
    public void  Signup_with_FirstNameAndPassword() throws IOException {

        pwd= faker.internet().password(8,16, true,true,true);
        FN = faker.name().firstName();
        String bodyData = generateAPIBody.signUp(pwd, 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","","55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", "","17.0","iPhone 11",
                "Asia/Karachi",pwd);
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
            Signup_with_FirstNameAndPassword();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_FirstNameAndPassword();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_FirstNameAndPassword();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_FirstNameAndPassword();
        }
        assertEquals( "Invalid email address",get_message,"Blank Fields");
        assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 9, description = "Signup with only email, first & last name " )
    public void  Signup_with_Email_FirstName_LastName() throws IOException {

        //pwd= faker.internet().password(8,16, true,true,true);
        FN = faker.name().firstName();
        em = faker.internet().emailAddress();
        LN = faker.name().lastName();
        String bodyData = generateAPIBody.signUp("", 0, LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","","55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", em,"17.0","iPhone 11",
                "Asia/Karachi","");
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
    public void  Signup_with_Email_FirstName_Password() throws IOException {

        pwd= faker.internet().password(8,16, true,true,true);
        FN = faker.name().firstName();
        em = faker.internet().emailAddress();
        //LN = faker.name().lastName();
        String bodyData = generateAPIBody.signUp(pwd, 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","","55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", em,"17.0","iPhone 11",
                "Asia/Karachi",pwd);
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
            Signup_with_Email_FirstName_Password();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_Email_FirstName_Password();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_Email_FirstName_Password();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_Email_FirstName_Password();
        }
        assertEquals( "Request parameter lastname is empty",get_message,"Blank Fields");
        assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 11, description = "Signup with only first name, last name & password" )
    public void  Signup_with_LastName_FirstName_Password() throws IOException {

        pwd= faker.internet().password(8,16, true,true,true);
        FN = faker.name().firstName();
        //em = faker.internet().emailAddress();
        LN = faker.name().lastName();
        String bodyData = generateAPIBody.signUp(pwd, 0, LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","","55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", "","17.0","iPhone 11",
                "Asia/Karachi",pwd);
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
            Signup_with_LastName_FirstName_Password();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_LastName_FirstName_Password();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_LastName_FirstName_Password();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_LastName_FirstName_Password();
        }
        System.out.println(get_message);
        assertEquals( "Invalid email address",get_message,"Blank Fields");
        assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 12, description = "Signup with only email, last name & password" )
    public void  Signup_with_Email_LastName_Password() throws IOException {

        pwd= faker.internet().password(8,16, true,true,true);
        //FN = faker.name().firstName();
        em = faker.internet().emailAddress();
        LN = faker.name().lastName();
        String bodyData = generateAPIBody.signUp(pwd, 0, LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","","55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", em,"17.0","iPhone 11",
                "Asia/Karachi",pwd);
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
            Signup_with_Email_LastName_Password();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_Email_LastName_Password();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_Email_LastName_Password();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_Email_LastName_Password();
        }
        assertEquals( "Request parameter firstname is empty",get_message,"Blank Fields");
        assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }
    @Test(priority = 13, description = "Signup with only Email,first name,last name & password" )
    public void  Signup_with_Email_FirstName_LastName_Password() throws IOException {

        pwd= faker.internet().password(8,16, true,true,true);
        FN = faker.name().firstName();
        em = faker.internet().emailAddress();
        LN = faker.name().lastName();
        String bodyData = generateAPIBody.signUp(pwd, 0, LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","","55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", em,"17.0","iPhone 11",
                "Asia/Karachi",pwd);
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
            Signup_with_Email_FirstName_LastName_Password();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_Email_FirstName_LastName_Password();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_Email_FirstName_LastName_Password();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_Email_FirstName_LastName_Password();
        }
        System.out.println(get_message);
        assertEquals( "success",get_message,"Blank Fields");
        assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 500");
    }

    @Test(priority = 14, description = "Signup with only Date of Birth " )
    public void  Signup_with_only_Date_of_Birth() throws IOException {
        dob = String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));


        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", "" ,"17.0","iPhone 11",
                "Asia/Karachi","");
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
    public void  Signup_with_only_DateOfBirth_and_FirstName() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        FN = faker.name().firstName();
        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", "" ,"17.0","iPhone 11",
                "Asia/Karachi","");
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
    public void  Signup_with_only_DateOfBirth_and_LastName() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        LN = faker.name().lastName();
        FN = faker.name().firstName();
        String bodyData = generateAPIBody.signUp("", 0, LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", "" ,"17.0","iPhone 11",
                "Asia/Karachi","");
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
    public void  Signup_with_only_DateOfBirth_FirstName_LastName() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        LN = faker.name().lastName();

        String bodyData = generateAPIBody.signUp("", 0, LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", "" ,"17.0","iPhone 11",
                "Asia/Karachi","");
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
    public void  Signup_with_only_Email_DateOfBirth_FirstName_LastName() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        LN = faker.name().lastName();
        FN = faker.name().firstName();
        em = faker.internet().emailAddress();
        String bodyData = generateAPIBody.signUp("", 0, LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", em ,"17.0","iPhone 11",
                "Asia/Karachi","");
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
    public void  Signup_with_only_Email_DateOfBirth_FirstName_LastName_Password() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        LN = faker.name().lastName();
        FN = faker.name().firstName();
        em = faker.internet().emailAddress();
        pwd= faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp(pwd, 0, LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", em ,"17.0","iPhone 11",
                "Asia/Karachi",pwd);
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
            Signup_with_only_Email_DateOfBirth_FirstName_LastName_Password();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_only_Email_DateOfBirth_FirstName_LastName_Password();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_only_Email_DateOfBirth_FirstName_LastName_Password();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_only_Email_DateOfBirth_FirstName_LastName_Password();
        }
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("success", get_message,"Blank Fields");
        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 20, description = "Sign up with Email, Date of Birth & password" )
    public void  Signup_with_only_Email_DateOfBirth_Password() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        //LN = faker.name().lastName();
        //FN = faker.name().firstName();
        em = faker.internet().emailAddress();
        pwd= faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp(pwd, 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", em ,"17.0","iPhone 11",
                "Asia/Karachi",pwd);
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
            Signup_with_only_Email_DateOfBirth_Password();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_only_Email_DateOfBirth_Password();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_only_Email_DateOfBirth_Password();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_only_Email_DateOfBirth_Password();
        }
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Request parameter firstname is empty", get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 21, description = "Signup with only Date of Birth , Email " )
    public void  Signup_with_only_DateOfBirth_Password() throws IOException {
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        //LN = faker.name().lastName();
        //FN = faker.name().firstName();
        em = faker.internet().emailAddress();
        // pwd= faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "","", em ,"17.0","iPhone 11",
                "Asia/Karachi","");
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
            Signup_with_only_DateOfBirth_Password();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_only_DateOfBirth_Password();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_only_DateOfBirth_Password();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_only_DateOfBirth_Password();
        }
        System.out.println(get_message);
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Request parameter firstname is empty", get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 22, description = "Signup with only Nationality" )
    public void  Signup_with_only_Nationality() throws IOException {
        na=faker.nation().nationality();
        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","", "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "",na, "" ,"17.0","iPhone 11",
                "Asia/Karachi","");
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
    public void  Signup_with_only_Nationality_FirstName() throws IOException {
        na=faker.nation().nationality();
        FN=faker.name().firstName();
        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","", "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "",na, "" ,"17.0","iPhone 11",
                "Asia/Karachi","");
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
    public void  Signup_with_only_Nationality_Email() throws IOException {
        em=faker.internet().emailAddress();
        na=faker.nation().nationality();
        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","", "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "",na, em ,"17.0","iPhone 11",
                "Asia/Karachi","");
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
    public void  Signup_with_only_Nationality_Password() throws IOException {
        pwd=faker.internet().password(8,16,true,true,true);
        na=faker.nation().nationality();
        String bodyData = generateAPIBody.signUp(pwd, 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","", "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "",na, "" ,"17.0","iPhone 11",
                "Asia/Karachi",pwd);
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
            Signup_with_only_Nationality_Password();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_only_Nationality_Password();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_only_Nationality_Password();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_only_Nationality_Password();
        }
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }

    @Test(priority = 26, description = "Signup with only Nationality,Date of birth" )
    public void  Signup_with_only_Nationality_Date_of_birth() throws IOException {
        na=faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));

        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "",na, "" ,"17.0","iPhone 11",
                "Asia/Karachi","");
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
    public void  Signup_with_only_Nationality_FirstName_Email() throws IOException {
        na=faker.nation().nationality();
        FN=faker.name().firstName();
        em=faker.internet().emailAddress();

        String bodyData = generateAPIBody.signUp("", 0, "",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","", "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "",na, em ,"17.0","iPhone 11",
                "Asia/Karachi","");
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
    public void  Signup_with_only_Nationality_LastName_Email() throws IOException {
        na=faker.nation().nationality();
        em=faker.internet().emailAddress();
        LN=faker.name().lastName();

        String bodyData = generateAPIBody.signUp("", 0,LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","", "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "",na, em ,"17.0","iPhone 11",
                "Asia/Karachi","");
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
    public void  Signup_with_only_Nationality_FirstName_Password() throws IOException {
        na=faker.nation().nationality();
       /*dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));*/
        //LN = faker.name().lastName();
        FN = faker.name().firstName();
        // em = faker.internet().emailAddress();
        pwd= faker.internet().password(8,16, true,true,true);


        String bodyData = generateAPIBody.signUp(pwd, 0,"",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","", "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "",na, "","17.0","iPhone 11",
                "Asia/Karachi",pwd);
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
            Signup_with_only_Nationality_FirstName_Password();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_only_Nationality_FirstName_Password();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_only_Nationality_FirstName_Password();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_only_Nationality_FirstName_Password();
        }
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 32, description = "Signup with only Nationality, First Name, Date of Birth" )
    public void  Signup_with_only_Nationality_FirstName_Date_of_birth() throws IOException {
        na=faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        //LN = faker.name().lastName();
        FN = faker.name().firstName();
        // em = faker.internet().emailAddress();
        //pwd= faker.internet().password(8,16, true,true,true);


        String bodyData = generateAPIBody.signUp("", 0,"",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "",na, "","17.0","iPhone 11",
                "Asia/Karachi","");
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
    public void  Signup_with_only_Nationality_LastName_Password() throws IOException {
        na=faker.nation().nationality();
       /*dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));*/
        LN = faker.name().lastName();
        //FN = faker.name().firstName();
        // em = faker.internet().emailAddress();
        pwd= faker.internet().password(8,16, true,true,true);


        String bodyData = generateAPIBody.signUp(pwd, 0,LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","", "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "",na, "","17.0","iPhone 11",
                "Asia/Karachi",pwd);
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
            Signup_with_only_Nationality_LastName_Password();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_only_Nationality_LastName_Password();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_only_Nationality_LastName_Password();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_only_Nationality_LastName_Password();
        }
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 33, description = "Signup with only Nationality, Last Name, Date of Birth" )
    public void  Signup_with_only_Nationality_LastName_Date_of_birth() throws IOException {
        na=faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        LN = faker.name().lastName();
        //FN = faker.name().firstName();
        // em = faker.internet().emailAddress();
        //pwd= faker.internet().password(8,16, true,true,true);


        String bodyData = generateAPIBody.signUp("", 0,LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "",na, "","17.0","iPhone 11",
                "Asia/Karachi","");
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
    public void  Signup_with_only_Nationality_Email_password_Date_of_birth() throws IOException {
        na=faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        // LN = faker.name().lastName();
        //FN = faker.name().firstName();
        em = faker.internet().emailAddress();
        pwd= faker.internet().password(8,16, true,true,true);


        String bodyData = generateAPIBody.signUp(pwd, 0,"",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "",na, em,"17.0","iPhone 11",
                "Asia/Karachi",pwd);
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
            Signup_with_only_Nationality_Email_password_Date_of_birth();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_only_Nationality_Email_password_Date_of_birth();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_only_Nationality_Email_password_Date_of_birth();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_only_Nationality_Email_password_Date_of_birth();
        }
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Request parameter firstname is empty", get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 35, description = "Signup with only Nationality, first name, last name, email, Date of Birth" )
    public void  Signup_with_only_Nationality_Email_FirstName_LastName_Date_Of_birth() throws IOException {
        na=faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        LN = faker.name().lastName();
        FN = faker.name().firstName();
        em = faker.internet().emailAddress();
        //pwd= faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp("", 0,LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "",na, em,"17.0","iPhone 11",
                "Asia/Karachi","");
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
    public void  Signup_with_only_Nationality_Date_of_birth_FirstName_LastName_Password() throws IOException {
        na=faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        LN = faker.name().lastName();
        FN = faker.name().firstName();
        //em = faker.internet().emailAddress();
        pwd= faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp(pwd, 0,LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "",na, "","17.0","iPhone 11",
                "Asia/Karachi",pwd);
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
            Signup_with_only_Nationality_Date_of_birth_FirstName_LastName_Password();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_only_Nationality_Date_of_birth_FirstName_LastName_Password();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_only_Nationality_Date_of_birth_FirstName_LastName_Password();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_only_Nationality_Date_of_birth_FirstName_LastName_Password();
        }
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Invalid email address", get_message,"Blank Fields");
        Assert.assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }

    @Test(priority = 38, description = "Signup with only Nationality, first name, last name, Date of birth, Password, Email")
    public void  Signup_with_only_Nationality_Date_of_birth_FirstName_LastName_Password_Email() throws IOException {
        na=faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        LN = faker.name().lastName();
        FN = faker.name().firstName();
        em = faker.internet().emailAddress();
        pwd= faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp(pwd, 0,LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "",na, em,"17.0","iPhone 11",
                "Asia/Karachi",pwd);
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
            Signup_with_only_Nationality_Date_of_birth_FirstName_LastName_Password_Email();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_only_Nationality_Date_of_birth_FirstName_LastName_Password_Email();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_only_Nationality_Date_of_birth_FirstName_LastName_Password_Email();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_only_Nationality_Date_of_birth_FirstName_LastName_Password_Email();
        }
        Assert.assertNotNull(get_message);
        Assert.assertEquals("success", get_message,"Blank Fields");
        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 39, description = "Signup with only first name, email, password, Date of Birth, Nationality")
    public void  Signup_with_only_Nationality_Date_of_birth_FirstName_Password_Email() throws IOException {
        na=faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        //LN = faker.name().lastName();
        FN = faker.name().firstName();
        em = faker.internet().emailAddress();
        pwd= faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp(pwd, 0,"",
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "",na, em,"17.0","iPhone 11",
                "Asia/Karachi",pwd);
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
            Signup_with_only_Nationality_Date_of_birth_FirstName_Password_Email();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_only_Nationality_Date_of_birth_FirstName_Password_Email();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_only_Nationality_Date_of_birth_FirstName_Password_Email();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_only_Nationality_Date_of_birth_FirstName_Password_Email();
        }
        Assert.assertNotNull(get_message);
        Assert.assertEquals("Request parameter lastname is empty" , get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
    @Test(priority = 40, description = "Signup with only last name, email, password, Date of Birth, Nationality")
    public void  Signup_with_only_Nationality_Date_of_birth_LastName_Password_Email() throws IOException {
        na=faker.nation().nationality();
        dob=String.valueOf(Utils.generateRandomNumber(1950,2010))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,12))+"/"+
                String.valueOf(Utils.generateRandomNumber(1,28));
        LN = faker.name().lastName();
        //FN = faker.name().firstName();
        em = faker.internet().emailAddress();
        pwd= faker.internet().password(8,16, true,true,true);
        String bodyData = generateAPIBody.signUp(pwd, 0,LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0","",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1",dob, "55.154117",
                "https://entutapi.theentertainerme.com/et_rs_prd/web/v801/sessions",
                "Basic cWx6ZnFnaHBrZWl3aG16ZzprJFZ9QiooNkRLNXltVE5iSD80PHJqM3VHRjtbfnQ+cQ==",
                "",na, em,"17.0","iPhone 11",
                "Asia/Karachi",pwd);
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
            Signup_with_only_Nationality_Date_of_birth_LastName_Password_Email();
        }
        if (get_message.equalsIgnoreCase("Password should contain 1 small letter")){
            Signup_with_only_Nationality_Date_of_birth_LastName_Password_Email();
        } if (get_message.equalsIgnoreCase("Password should contain 1 capital letter")) {
            Signup_with_only_Nationality_Date_of_birth_LastName_Password_Email();
        } if (get_message.equalsIgnoreCase("Password should contain minimum 8 letter")) {
            Signup_with_only_Nationality_Date_of_birth_LastName_Password_Email();
        }

        Assert.assertNotNull(get_message);
        Assert.assertEquals("Request parameter firstname is empty" , get_message,"Blank Fields");
        Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }
}
