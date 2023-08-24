package te.application.api.test.B2C.positive;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import te.application.api.baseTest.B2CBaseTest;
import te.application.appConstants.AppConstants;
import te.application.appConstants.authToken;
import te.application.utilities.Utils;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.MILLIS;
import static org.testng.Assert.*;
import static te.application.utilities.generateAPIBody.merchantDetails;

@Slf4j
public class GetMerchantDetailsTest extends B2CBaseTest {

    private final int merchantID, locationID;
    private final String languageCode, category;
    private long res;

    public GetMerchantDetailsTest(int merchantID, String languageCode, int locationID, String category){
        this.merchantID = merchantID;
        this.locationID = locationID;
        this.languageCode = languageCode;
        //log.info(String.valueOf(this.merchantID));
        this.category = category;
    }


    @Factory
    public static Object[] factoryMethod()
    {
        return new Object[]
                {
                        //new GetMerchantDetailsTest(52604, "en", 1),
                        new GetMerchantDetailsTest(19257,"en", 1, "Restaurants and Bars")
                        //new GetMerchantDetailsTest(19337, "en", 1)

                };
    }

    @BeforeClass
    public void getMerchantsData()  {
        LocalDateTime requestTime = LocalDateTime.now();
        String bodyData = merchantDetails("andriod", "default", "entertainer", category,
                AppConstants.requestDeviceModel, 0,locationID, "None", "False",
                31930199, languageCode, "json", merchantID, -1, AppConstants.requestTimeZone,
                AppConstants.requestCurrency, "None", "None", "None", "None", AppConstants.requestDeviceKey,
                "redeemable_reusable", AppConstants.requestAppVersion);
        RestAssured.basePath = endPoints.getProperty("B2C_BASE_PATH_MERCHANT") + merchantID;
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        log.info(response.asString());
        jsonPath = response.jsonPath();
        jsonPath = new JsonPath(response.asString());
        LocalDateTime responseTime = LocalDateTime.now();
        log.info(requestTime+ "||" + responseTime);
        res = MILLIS.between(requestTime, responseTime);
    }

    @Test(description = "Status code should be 200", priority = 10)
    public void statusCodeShouldBe200(){
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 200, "(MerchantID : "+merchantID+"). Status code should be 200");
    }


    @Test(description = "Time taken by the API should be less than expected time", priority = 11, dependsOnMethods = "statusCodeShouldBe200")
    public void timeTakenByAPI() {
        log.info("Time taken by API : "+ res);
        assertFalse(res > Integer.parseInt(endPoints.getProperty("EXPECTED_API_TIME")) , "(MerchantID : "+merchantID+"). Expected time should be less than "+Integer.parseInt(endPoints.getProperty("EXPECTED_API_TIME")));
    }



    @Test(description = "Success should be true", priority = 12, dependsOnMethods = "statusCodeShouldBe200")
    public void successShouldBeTrue(){
        String successValue = jsonPath.getString("success");
        log.info(successValue);
        Assert.assertEquals(successValue, "true", "(MerchantID : "+merchantID+"). success attribute should be 'true'");
    }

    @Test(description = "Message should be success", priority = 13, dependsOnMethods = "statusCodeShouldBe200")
    public void messageShouldBeSuccess(){
        String messageValue = jsonPath.getString("message");
        log.info(messageValue);
        Assert.assertEquals(messageValue, "success", "(MerchantID : "+merchantID+"). message should be 'success'");
    }

    @Test(description = "data node should be populated", priority = 14, dependsOnMethods = "statusCodeShouldBe200")
    public void dataNodeShouldBePopulated(){
        int size = jsonPath.getInt("data.merchant.size()");
        log.info("size : "+size);
        assertFalse(Optional.of(size).equals(0) , "(MerchantID : "+merchantID+"). Merchant size should not be 0");
    }

    @Test(description = "Merchant id should be populated", priority = 15, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantIdShouldBePopulated(){
        int merchantId = jsonPath.getInt("data.merchant.id");
        log.info(String.valueOf(merchantId));
        assertNotNull(Optional.of(merchantId), "(MerchantID : "+merchantID+"). id should be populated/not null");
    }

    @Test(description = "Merchant pin should be populated", priority = 16, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantPinShouldBePopulated(){
        String merchantPin = jsonPath.getString("data.merchant.merchant_pin");
        log.info(merchantPin);
        assertNotNull(merchantPin, "(MerchantID : "+merchantID+"). merchant_pin should be populated/not null");
    }

    @Test(description = "Merchant email address should be populated", priority = 17, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantEmailAddressShouldBePopulated(){
        String merchantEmail = jsonPath.getString("data.merchant.email");
        log.info(merchantEmail);
        //log.info(String.valueOf(merchantEmail.length()));
        assertNotNull(merchantEmail, "(MerchantID : "+merchantID+"). email should be populated/not null");
    }

    @Test(description = "Merchant website should be populated", priority = 18, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantWebsiteShouldBePopulated(){
        String merchantWebSite = jsonPath.getString("data.merchant.website");
        log.info(merchantWebSite);
        assertNotNull(merchantWebSite, "(MerchantID : "+merchantID+"). website should be populated/not null");
    }

    @Test(description = "Merchant name should be populated", priority = 19, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantNameShouldBePopulated(){
        String merchantName = jsonPath.getString("data.merchant.name");
        log.info(merchantName);
        assertNotNull(merchantName, "(MerchantID : "+merchantID+"). name should be populated/not null");
    }


    @Test(description = "Merchant description should be populated", priority = 20, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantDescriptionShouldBePopulated(){
        String merchantDescription = jsonPath.getString("data.merchant.description");
        log.info(merchantDescription);
        assertNotNull(merchantDescription, "(MerchantID : "+merchantID+"). description should be populated/not null");
    }

    @Test(description = "Merchant is_pingable should be populated", priority = 21, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantIsPingableShouldBePopulated(){
        String isPingable = jsonPath.getString("data.merchant.is_pingable");
        log.info(isPingable);
        assertNotNull(isPingable, "(MerchantID : "+merchantID+"). is_pingable should be populated/not null");
    }

    @Test(description = "Merchant is_pingable should be populated", priority = 22, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantIsForMembersOnlyShouldBePopulated(){
        String membersOnly = jsonPath.getString("data.merchant.is_for_members_only");
        log.info(membersOnly);
        assertNotNull(membersOnly, "(MerchantID : "+merchantID+"). Merchant is_for_members_only should be populated/not null");
    }

    @Test(description = "Merchant delivery_contact_number should be populated", priority = 23, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantDeliveryContactNumberShouldBePopulated(){
        String deliveryContactNumber = jsonPath.getString("data.merchant.delivery_contact_no");
        log.info(deliveryContactNumber);
        assertNotNull(deliveryContactNumber, "(MerchantID : "+merchantID+"). Merchant delivery_contact_no should be populated/not null");
    }

    @Test(description = "Merchant has_promo_code_offers should be populated", priority = 24, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantHasPromoCodeOffersShouldBePopulated(){
        String hasPromoCodeOffers = jsonPath.getString("data.merchant.has_promo_code_offers");
        log.info(hasPromoCodeOffers);
        assertNotNull(hasPromoCodeOffers, "(MerchantID : "+merchantID+"). Merchant has_promo_code_offers should be populated/not null");
    }

    @Test(description = "Merchant category should be populated", priority = 25, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantCategoryShouldBePopulated(){
        String category = jsonPath.getString("data.merchant.category");
        log.info(category);
        assertNotNull(category, "(MerchantID : "+merchantID+"). Merchant category should be populated/not null");
    }

    @Test(description = "Merchant offer_categories should be populated", priority = 26, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantOfferCategoryShouldBePopulated(){
        int offerCategories = jsonPath.getInt("data.merchant.offer_categories.size()");
        log.info(String.valueOf(offerCategories));
        assertNotEquals(offerCategories,0, "(MerchantID : "+merchantID+"). Merchant offer_categories size should not be 0");
    }

    @Test(description = "Merchant sub_categories should be populated", priority = 27, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantSubCategoryShouldBePopulated(){
        int subCategories = jsonPath.getInt("data.merchant.sub_categories.size()");
        log.info(String.valueOf(subCategories));
        assertNotEquals(subCategories,0, "(MerchantID : "+merchantID+"). Merchant sub_categories size should not be 0");
    }

    @Test(description = "Merchant cuisines should be populated", priority = 28, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantCuisinesShouldBePopulated(){
        int cuisines = jsonPath.getInt("data.merchant.cuisines.size()");
        log.info(String.valueOf(cuisines));
        assertNotEquals(cuisines,0, "(MerchantID : "+merchantID+"). Merchant cuisines size should not be 0");
    }

    @Test(description = "Merchant cuisines_sub_categories should be populated", priority = 29, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantCuisineSubCategoriesShouldBePopulated(){
        int cuisineSubCategories = jsonPath.getInt("data.merchant.cuisines_sub_categories.size()");
        log.info(String.valueOf(cuisineSubCategories));
        assertNotEquals(cuisineSubCategories,0, "(MerchantID : "+merchantID+"). Merchant cuisines_sub_categories size should not be 0");
    }

    @Test(description = "Merchant merchant_sf_id should be populated", priority = 30, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantSFIDShouldBePopulated(){
        String sf_id = jsonPath.getString("data.merchant.merchant_sf_id");
        log.info(sf_id);
        assertNotNull(sf_id, "(MerchantID : "+merchantID+"). Merchant merchant_sf_id should be populated/not null");
    }

    @Test(description = "Merchant is_payless_enabled should be populated", priority = 31, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantPaylessEnabledShouldBePopulated(){
        String is_payless_enabled = jsonPath.getString("data.merchant.is_payless_enabled");
        log.info(is_payless_enabled);
        assertNotNull(is_payless_enabled, "(MerchantID : "+merchantID+"). Merchant is_payless_enabled should be populated/not null");
    }

    @Test(description = "Merchant is_barcode_enabled should be populated", priority = 32, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantBarcodeEnabledShouldBePopulated(){
        int is_barcode_enabled = jsonPath.getInt("data.merchant.is_barcode_enabled");
        log.info(String.valueOf(is_barcode_enabled));
        assertNotNull(is_barcode_enabled, "(MerchantID : "+merchantID+"). Merchant is_barcode_enabled should be populated/not null");
    }

    @Test(description = "Merchant is_barcode_enabled should be populated", priority = 33, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantIsOpenShouldBePopulated(){
        int is_open=0;
        if(category.compareToIgnoreCase("Restaurants and Bars")!=0){
            is_open = jsonPath.getInt("data.merchant.is_open");
            log.info(String.valueOf(is_open));
            assertNotNull(is_open, "(MerchantID : "+merchantID+"). Merchant is_open should be populated/not null");
        }

    }

    @Test(description = "Merchant show_view_dine_offer_section should be populated", priority = 34, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantViewDineOfferSectionShouldBePopulated(){
        String show_view_dine_offer_section = jsonPath.getString("data.merchant.show_view_dine_offer_section");
        log.info(show_view_dine_offer_section);
        assertNotNull(show_view_dine_offer_section, "(MerchantID : "+merchantID+"). Merchant show_view_dine_offer_section should be populated/not null");
    }

    @Test(description = "Merchant offer array size should not be 0", priority = 35, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantOffersArrayShouldBePopulated(){
        int offersArraySize = jsonPath.getInt("data.merchant.sections[3].offers.size()");
        log.info(String.valueOf(offersArraySize));
        assertNotEquals(offersArraySize, 0, "(MerchantID : "+merchantID+"). Merchant offers array should be populated/size should not be 0");
    }

    @Test(description = "Merchant offers_to_display array size should not be 0", priority = 36, dependsOnMethods = "statusCodeShouldBe200")
    public void merchantOffersToDisplayArrayShouldBePopulated(){
        int offersArraySize = jsonPath.getInt("data.merchant.sections[3].offers[0].offers_to_display.size()");
        log.info(String.valueOf(offersArraySize));
        assertNotEquals(offersArraySize, 0, "(MerchantID : "+merchantID+"). Merchant offers to display array should be populated/size should not be 0");
    }


}
