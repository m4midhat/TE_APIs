package te.application.api.test.B2C.positive;

import com.github.javafaker.App;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2CBaseTest;
import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.appConstants.bearerToken;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class SignUpTest extends B2CBaseTest {
    public  String FN;
    public String LN;
    public String em;
    public String pwd;
    private Faker faker = new Faker();
    public String na;
    @BeforeClass
    public void setUp() throws IOException {

        String email = faker.internet().emailAddress();
        String FirstName = faker.name().firstName();
        String LastName = faker.name().lastName();
        //Date Birth_day = faker.date().between("18/07/1919","18/07/1989");
        // Date dt=new Date("2023-12-31");
        //String Birthday = String.valueOf(Birth_day);
        String password = faker.internet().password(8,12,true,true,true);
        String nationality = faker.nation().nationality();
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String bodyData = generateAPIBody.signUp(password, 0, LastName,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FirstName,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(authToken.B2CAUTH_TOKEN),
                "",nationality, email,"17.0","iPhone 11",
                "Asia/Karachi",password);

        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        //AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        System.out.println("Session ID : " + AppConstants.sessionID);
        String get_message = jsonPath.getString("message");

        if (get_message.equalsIgnoreCase("Password should contain 1 numeric letter")){
            Assert.assertEquals(500, response.getStatusCode(), "Incorrect status code returned, expected value 422");
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

    }

    @Test(priority = 0, description = "Status code check" )
    public void checkStatus(){
        assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
    }
    @Test(priority = 1, description = "Verify message" )

    public void checkMessage(){
        String message = jsonPath.getString("message");
        Assert.assertNotNull(message);
        assertEquals("success", message, "A customer with this email address exists.");
            /*if (message.equalsIgnoreCase("A customer with this email address exists.")){
                int StatusCode = response.getStatusCode();

                Assert.assertEquals(422,StatusCode,"try with new email Id" );*/

    }
    @Test(priority = 2, description = "Verify First name" )



    public void  checkFirstName(){
        String FirstName1 = jsonPath.getString("data.user.first_name");
        System.out.println(FirstName1);
        Assert.assertNotNull(FirstName1);
        assertEquals(FirstName1, FN,"name should be like this");

    }
    @Test(priority = 3, description = "Verify last name" )
    public void  checkLastName(){
        String LastName1 = jsonPath.getString("data.user.last_name");
        System.out.println(LastName1 );
        Assert.assertNotNull(LastName1);
        assertEquals(LastName1,LN, "name should be like this");

    }
    @Test(priority = 4, description = "Verify session token" )
    public void  checkSessionToken(){
        String SessionToken = jsonPath.getString("data.user.session_token");
        System.out.println(SessionToken);
        Assert.assertNotNull(SessionToken);

    }
    //data.is_new_registered
    @Test(priority = 5, description = "Verify newly registers" )
    public void  checkNewResister(){
        String is_new_register = jsonPath.getString("data.is_new_registered");
        boolean isVerify=Boolean.valueOf(is_new_register);
        System.out.println(isVerify);
        Assert.assertNotNull(isVerify);
        assertEquals(true,isVerify, "it should be true in new user case" );

    }
    @Test(priority = 6, description = "Verify nationality" )
    public void  checkNationality(){
        String Nationality = jsonPath.getString("data.user.nationality");
        System.out.println(Nationality);
        Assert.assertNotNull(Nationality);

    }
    //data.user.date_of_birth
    @Test(priority = 7, description = "Verify date of birth" )
    public void  checkBirthdate(){
        String Birthdate = jsonPath.getString("data.user.date_of_birth");
        System.out.println(Birthdate);
        Assert.assertNotNull(Birthdate);
        assertEquals("18/07/1989", Birthdate,"It must be greater then 13 years.");

    }

    @Test(priority = 8, description = "Verify already exist customer with this email" )
    public void  checkAlreadyExistEmail() throws IOException {

        String bodyData = generateAPIBody.signUp(pwd, 0, LN,
                "en",1,"25.095395","entertainer","ios","8.18.06",
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","0","AED","0",FN,
                "ios-79C8F176-8478-4AD7-9261-B838FBD269B1","1989/07/18","55.154117",
                AppConstants.BASE_URI_B2C+AppConstants.B2C_LOGIN,
                Utils.decodeString(bearerToken.B2C),
                "",na, em,"17.0","iPhone 11",
                "Asia/Karachi",pwd);

        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(bearerToken.B2C))
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
        assertEquals("A customer with this email address exists.", get_message,"Already customer exist :(");
        assertEquals(422, response.getStatusCode(), "Incorrect status code returned, expected value 422");
    }


}
