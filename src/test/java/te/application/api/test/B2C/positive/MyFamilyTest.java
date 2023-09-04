package te.application.api.test.B2C.positive;

import lombok.extern.slf4j.Slf4j;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2CBaseTest;

import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.data.response.myFamily;
import te.application.utilities.Utils;
import te.application.utilities.generateAPIBody;

import java.io.IOException;

@Slf4j
public class MyFamilyTest  extends B2CBaseTest {

    String locationID, languageCode;

    public MyFamilyTest(String loc, String lang){
        this.languageCode = lang;
        this.locationID = loc;
    }


    @Factory
    public static Object[] factoryMethod() {
        return new Object[]
                {
                        new MyFamilyTest("1", "en"),
                        new MyFamilyTest("1", "ar"),
                        new MyFamilyTest("1", "ru"),
                        new MyFamilyTest("2", "en"),
                        new MyFamilyTest("2", "ar"),
                        new MyFamilyTest("2", "ru"),
                        new MyFamilyTest("3", "en"),
                        new MyFamilyTest("3", "ar"),
                        new MyFamilyTest("3", "ru"),
                        new MyFamilyTest("6", "en"),
                        new MyFamilyTest("6", "ar"),
                        new MyFamilyTest("6", "ru"),
                        new MyFamilyTest("7", "en"),
                        new MyFamilyTest("7", "ar"),
                        new MyFamilyTest("7", "ru"),
                        new MyFamilyTest("8", "en"),
                        new MyFamilyTest("8", "ar"),
                        new MyFamilyTest("8", "ru"),
                        new MyFamilyTest("9", "en"),
                        new MyFamilyTest("9", "ar"),
                        new MyFamilyTest("9", "ru"),
                        new MyFamilyTest("10", "en"),
                        new MyFamilyTest("10", "ar"),
                        new MyFamilyTest("10", "ru"),
                        new MyFamilyTest("11", "en"),
                        new MyFamilyTest("11", "ar"),
                        new MyFamilyTest("11", "ru"),
                        new MyFamilyTest("18", "en"),
                        new MyFamilyTest("18", "ar"),
                        new MyFamilyTest("18", "ru"),
                        new MyFamilyTest("49", "en"),
                        new MyFamilyTest("49", "ar"),
                        new MyFamilyTest("49", "ru")
                };
    }

    @BeforeClass
    public void setUp() throws IOException {
        RestAssured.basePath = endPoints.getProperty("B2C_FAMILY");

        String bodyData = generateAPIBody.Family(languageCode, "25.300579", "entertainer",
                AppConstants.requestOSPlatform, AppConstants.requestAppVersion, AppConstants.requestDeviceKey,
                AppConstants.requestCurrency, "55.307709", AppConstants.requestDeviceKey,
                locationID, "1", AppConstants.requestOSVersion, AppConstants.requestDeviceModel,
                AppConstants.requestTimeZone, "55.307709", "25.300579");
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .header("User-Agent", AppConstants.requestUserAgent)
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        log.info(response.asString());
        jsonPath = response.jsonPath();
    }

    @Test(description = "Member information array should be populated")
    public void memberInfoArrayShouldBePopulated(){
        int arraySize = jsonPath.getInt("data.member_info.size()");
        log.info("Member info array size : "+ arraySize);
        Assert.assertNotEquals(arraySize, 0);
    }


    @Test(priority = 1, description = "success should be true")
    public void successShouldBeTrue(){
        String successMsg = jsonPath.getString("success");
        Assert.assertEquals(successMsg, "true");
    }

    @Test(priority = 2, description = "Family List section array should be populated")
    public void familyListSectionArrayShouldBePopulated(){
        int arraySize = jsonPath.getInt("data.family_list_section.size()");
        log.info("Family list section info array size : "+ arraySize);
        Assert.assertNotEquals(arraySize, 0);
    }

    @Test(priority = 3, description = "Purchased product ids should be populated")
    public void purchasedProductIdsShouldBePopulated(){
        int arraySize = jsonPath.getInt("data.member_info.purchased_product_id.size()");
        log.info("Purchased product ids : "+ arraySize);
        Assert.assertNotEquals(arraySize, 0);
    }

    @Test(priority = 4, description = "Verify primary member name")
    public void verifyPrimaryMemberName(){
        String member = jsonPath.getString("data.member_info.primary_member_name").trim();
        log.info(member);
        Assert.assertEquals(member, (SignInTest.firstName+" "+SignInTest.lastName).trim() );
    }

    @Test(priority = 5, description = "Verify Maximum number of family members")
    public void verifyMaxNumberOfMembers(){
        int members = jsonPath.getInt("data.family_list_section.max_member_limit");
        log.info(String.valueOf(members));
        Assert.assertEquals(members, myFamily.FAMILY_MEMBERS_ALLOWED);
    }

    @Test(priority = 6, description = "Verify current number of family members")
    public void verifyNumberOfMembers(){
        int members = jsonPath.getInt("data.family_list_section.total_members");
        log.info(String.valueOf(members));
        Assert.assertTrue(members <= myFamily.FAMILY_MEMBERS_ALLOWED);
    }

}