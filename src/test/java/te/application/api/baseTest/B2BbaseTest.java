package te.application.api.baseTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import te.application.appConstants.AppConstants;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.MILLIS;

@Slf4j
public class B2BbaseTest {
    protected Response response;
    protected JsonPath jsonPath;

    @BeforeSuite
    public void setUpSuite(){
        AppConstants.START_DATE = LocalDateTime.now();
        RestAssured.baseURI = "https://apiutb2bmrhsrvrpy.theentertainerme.com/";

    }


    @AfterSuite
    public void tearDownSuite(){
        AppConstants.END_DATE = LocalDateTime.now();
        long res = MILLIS.between(AppConstants.START_DATE, AppConstants.END_DATE);
        log.info("Total time taken by the suite : "+ Math.round(res/1000) +"seconds " + res%1000 + "ms" );
    }

}
