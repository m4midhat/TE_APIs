package te.application.api.test.B2B.fnb.negative;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2BbaseTest;
import te.application.appConstants.AppConstants;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBodyB2C;

@Slf4j
public class SignUpTest extends B2BbaseTest {

    private Faker faker = new Faker();

    @Test(description = "Signup with blank credentials body")
    public void signUpWithoutCredentials(){
            String bodyData = generateAPIBodyB2C.signUp("","","","","");
            RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
            String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up without any credentials information");
        }

    @Test(description = "Signup with only username", priority = 1)
    public void signUpWithEmailOnly(){
        String randomEmail = faker.internet().emailAddress().toString();// Utils.generateRandomEmail();
        log.info("Random email generated : "+randomEmail);
        String bodyData = generateAPIBodyB2C.signUp(randomEmail,"","","","");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
            Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with email address only");
    }

    @Test(description = "Signup with only firstname", priority = 2)
    public void signUpWithFirstNameOnly(){
        String randomName = faker.name().firstName(); //Utils.getAlphaNumericString(Utils.generateRandomNumber(3, 15));
        log.info("Random first name : "+randomName);
        String bodyData = generateAPIBodyB2C.signUp("",randomName,"","","");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with first name only");
    }


    @Test(description = "Signup with only lastname", priority = 3)
    public void signUpWithLastNameOnly(){
        String randomName = faker.name().lastName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(3, 15));
        log.info("Random last name : "+randomName);
        String bodyData = generateAPIBodyB2C.signUp("","",randomName,"","");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with last name only");
    }

    @Test(description = "Signup with only password", priority = 4)
    public void signUpWithPasswordOnly(){
        String randomPassword = Utils.getAlphaNumericString(Utils.generateRandomNumber(8, 15));
        log.info("Random Password : "+randomPassword);
        String bodyData = generateAPIBodyB2C.signUp("","","",randomPassword,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with password only");
    }

    @Test(description = "Signup with only confirmed password", priority = 5)
    public void signUpWithConfirmedPasswordOnly(){
        String randomPassword = Utils.getAlphaNumericString(Utils.generateRandomNumber(8, 15));
        log.info("Random Password : "+randomPassword);
        String bodyData = generateAPIBodyB2C.signUp("","","","",randomPassword);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with confirmed password only");
    }


    @Test(description = "Signup with only Email & Firstname", priority = 6)
    public void signUpWithEmailAndFirstNameOnly(){
        String randomEmail = faker.internet().emailAddress();// Utils.generateRandomEmail();
        String randomFirstName = faker.name().firstName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(3, 15));
        String bodyData = generateAPIBodyB2C.signUp(randomEmail,randomFirstName,"","","");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with Email address & First name only");
    }


    @Test(description = "Signup with only Email & Lastname", priority = 7)
    public void signUpWithEmailAndLastNameOnly(){
        String randomEmail = faker.internet().emailAddress();// Utils.generateRandomEmail();
        String randomLastName = faker.name().lastName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(3, 15));
        String bodyData = generateAPIBodyB2C.signUp(randomEmail,"",randomLastName,"","");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with Email address & last name only");
    }


    @Test(description = "Signup with only Email & Password", priority = 8)
    public void signUpWithEmailAndPasswordOnly(){
        String randomEmail = faker.internet().emailAddress();// Utils.generateRandomEmail();
        String randomPassword = Utils.getAlphaNumericString(Utils.generateRandomNumber(8, 15));
        String bodyData = generateAPIBodyB2C.signUp(randomEmail,"","",randomPassword,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with Email address & password only");
    }


    @Test(description = "Signup with only Email & Confirmed Password", priority = 9)
    public void signUpWithEmailAndConfirmedPasswordOnly(){
        String randomEmail = faker.internet().emailAddress();// Utils.generateRandomEmail();
        String randomPassword = Utils.getAlphaNumericString(Utils.generateRandomNumber(8, 15));
        String bodyData = generateAPIBodyB2C.signUp(randomEmail,"","","",randomPassword);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with Email address & Confirmed Password only");
    }


    @Test(description = "Signup with only first name & last name", priority = 10)
    public void signUpWithFirstAndLastNameOnly(){
        String firstName = faker.name().firstName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(4, 10));
        String lastName = faker.name().lastName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(4, 10));
        String bodyData = generateAPIBodyB2C.signUp("",firstName,lastName,"","");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with First & Last only");
    }

    @Test(description = "Signup with only first name & password", priority = 11)
    public void signUpWithFirstAndPasswordOnly(){
        String firstName = faker.name().firstName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(4, 10));
        String randomPassword = Utils.getAlphaNumericString(Utils.generateRandomNumber(8, 15));
        String bodyData = generateAPIBodyB2C.signUp("",firstName,"",randomPassword,"");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with First name & password only");
    }


    @Test(description = "Signup with only first name & confirmed password", priority = 12)
    public void signUpWithFirstAndConfirmedPasswordOnly(){
        String firstName = faker.name().firstName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(4, 10));
        String randomPassword = Utils.getAlphaNumericString(Utils.generateRandomNumber(8, 15));
        String bodyData = generateAPIBodyB2C.signUp("",firstName,"","",randomPassword);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with First name & confirmed password only");
    }

    @Test(description = "Signup with only last name & confirmed password", priority = 13)
    public void signUpWithLastNameAndConfirmedPasswordOnly(){
        String lastName = faker.name().lastName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(4, 10));
        String randomPassword = Utils.getAlphaNumericString(Utils.generateRandomNumber(8, 15));
        String bodyData = generateAPIBodyB2C.signUp("","",lastName,"",randomPassword);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with Last name & confirmed password only");
    }


    @Test(description = "Signup with only password & confirmed password", priority = 13)
    public void signUpWithPasswordAndConfirmedPasswordOnly(){
        String randomPassword = Utils.getAlphaNumericString(Utils.generateRandomNumber(8, 15));
        String bodyData = generateAPIBodyB2C.signUp("","","",randomPassword, randomPassword);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with password & confirmed password only");
    }


    @Test(description = "Signup with only email, first & last name", priority = 14)
    public void signUpWithEmailFirstAndLastName(){
        String firstName = faker.name().firstName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(5, 15));
        String lastName = faker.name().lastName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(5, 15));
        String email = faker.internet().emailAddress();// Utils.generateRandomEmail();
        String bodyData = generateAPIBodyB2C.signUp(email,firstName,lastName,"", "");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with email, first & last name only");
    }


    @Test(description = "Signup with only email, first name & password", priority = 16)
    public void signUpWithEmailFirstNameAndPassword(){
        String firstName = faker.name().firstName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(5, 15));
        String password = Utils.getAlphaNumericString(Utils.generateRandomNumber(8, 15));
        String email = faker.internet().emailAddress();// Utils.generateRandomEmail();
        String bodyData = generateAPIBodyB2C.signUp(email,firstName,"",password, "");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with email, first name & password only");
    }


    @Test(description = "Signup with only email, first name & confirmed password", priority = 17)
    public void signUpWithEmailFirstNameAndConfirmedPassword(){
        String firstName = faker.name().firstName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(5, 15));
        String password = Utils.getAlphaNumericString(Utils.generateRandomNumber(8, 15));
        String email = faker.internet().emailAddress();// Utils.generateRandomEmail();
        String bodyData = generateAPIBodyB2C.signUp(email,firstName,"","", password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with email, first name & confirmed password only");
    }

    @Test(description = "Signup with only email, first name & password", priority = 18)
    public void signUpWithEmailLastNameAndPassword(){
        String lastName = faker.name().lastName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(5, 15));
        String password = Utils.getAlphaNumericString(Utils.generateRandomNumber(8, 15));
        String email = faker.internet().emailAddress();// Utils.generateRandomEmail();
        String bodyData = generateAPIBodyB2C.signUp(email,"",lastName,password, "");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with email, last name & password only");
    }


    @Test(description = "Signup with only email, last name & confirmed password", priority = 19)
    public void signUpWithEmailLastNameAndConfirmedPassword(){
        String lastName = faker.name().lastName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(5, 15));
        String password = Utils.getAlphaNumericString(Utils.generateRandomNumber(8, 15));
        String email = faker.internet().emailAddress();// Utils.generateRandomEmail();
        String bodyData = generateAPIBodyB2C.signUp(email,"",lastName,"", password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with email, last name & confirmed password only");
    }


    @Test(description = "Signup with only first name, last name & password", priority = 20)
    public void signUpWithFirstNameLastNameAndPassword(){
        String lastName = faker.name().lastName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(5, 15));
        String firstName = faker.name().firstName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(5, 15));
        String password = Utils.getAlphaNumericString(Utils.generateRandomNumber(8, 15));
        String bodyData = generateAPIBodyB2C.signUp("",firstName,lastName,password, "");
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with first, last name & password only");
    }


    @Test(description = "Signup with only first name, last name & confirmed password", priority = 21)
    public void signUpWithFirstNameLastNameAndConfirmedPassword(){
        String lastName = faker.name().lastName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(5, 15));
        String firstName = faker.name().firstName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(5, 15));
        String password = Utils.getAlphaNumericString(Utils.generateRandomNumber(8, 15));
        String bodyData = generateAPIBodyB2C.signUp("",firstName,lastName,"", password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with first, last name & confirmed password only");
    }


    @Test(description = "Signup with only last name, password & confirmed password", priority = 22)
    public void signUpWithLastNamePasswordAndConfirmedPassword(){
        String lastName = faker.name().lastName(); //Utils.getAlphaNumericString(Utils.generateRandomNumber(5, 15));
        String password = Utils.getAlphaNumericString(Utils.generateRandomNumber(8, 15));
        String bodyData = generateAPIBodyB2C.signUp("","",lastName,password, password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with last name, password & confirmed password only");
    }


    @Test(description = "Signup without confirmed password", priority = 23)
    public void signUpWithoutConfirmedPassword(){
        String lastName = faker.name().lastName();// Utils.getAlphaNumericString(Utils.generateRandomNumber(5, 15));
        String password = Utils.getAlphaNumericString(Utils.generateRandomNumber(8, 15));
        String bodyData = generateAPIBodyB2C.signUp("","",lastName,password, password);
        RestAssured.basePath = AppConstants.BASE_PATH_SIGNUP;
        String authToken = AppConstants.BEARER_TOKEN;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", "Bearer "+ authToken)
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.get();
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        int statusCode = response.statusCode();
        log.info(String.valueOf(statusCode));
        Assert.assertEquals(404, statusCode, "Invalid Status code while signing up with last name, password & confirmed password only");
    }

}
