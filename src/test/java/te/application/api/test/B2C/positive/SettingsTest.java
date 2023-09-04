package te.application.api.test.B2C.positive;
import io.restassured.RestAssured;
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
import te.application.utilities.generateAPIBody;

import java.io.IOException;

@Slf4j
public class SettingsTest extends B2CBaseTest {

    String locationID, languageCode;

    public SettingsTest(String loc, String lang){
        this.languageCode = lang;
        this.locationID = loc;
    }

    @Factory
    public static Object[] factoryMethod() {
        return new Object[]
                {
                        new SettingsTest("1", "en"),
                        new SettingsTest("1", "ar"),
                        new SettingsTest("1", "ru"),
                        new SettingsTest("2", "en"),
                        new SettingsTest("2", "ar"),
                        new SettingsTest("2", "ru"),
                        new SettingsTest("3", "en"),
                        new SettingsTest("3", "ar"),
                        new SettingsTest("3", "ru"),
                        new SettingsTest("6", "en"),
                        new SettingsTest("6", "ar"),
                        new SettingsTest("6", "ru"),
                        new SettingsTest("7", "en"),
                        new SettingsTest("7", "ar"),
                        new SettingsTest("7", "ru"),
                        new SettingsTest("8", "en"),
                        new SettingsTest("8", "ar"),
                        new SettingsTest("8", "ru"),
                        new SettingsTest("9", "en"),
                        new SettingsTest("9", "ar"),
                        new SettingsTest("9", "ru"),
                        new SettingsTest("10", "en"),
                        new SettingsTest("10", "ar"),
                        new SettingsTest("10", "ru"),
                        new SettingsTest("11", "en"),
                        new SettingsTest("11", "ar"),
                        new SettingsTest("11", "ru"),
                        new SettingsTest("18", "en"),
                        new SettingsTest("18", "ar"),
                        new SettingsTest("18", "ru"),
                        new SettingsTest("49", "en"),
                        new SettingsTest("49", "ar"),
                        new SettingsTest("49", "ru")
                };
    }


    @BeforeClass
    public void setUp() throws IOException {
        RestAssured.basePath = endPoints.getProperty("BASE_PATH_SETTINGS");

        String bodyData = generateAPIBody.settings(AppConstants.requestLanguage,"25.300579","entertainer",
                AppConstants.requestOSPlatform,AppConstants.requestAppVersion,AppConstants.requestDeviceKey,AppConstants.requestCurrency,
                "55.307709",AppConstants.requestDeviceKey,"1",
                AppConstants.requestDeviceModel,AppConstants.requestTimeZone,"entertainer",
                true, true,AppConstants.requestOSPlatform);
        RequestSpecification httpRequest = RestAssured.given()
                .header("Authorization", Utils.decodeString(authToken.B2CAUTH_TOKEN))
                .contentType("application/json")
                .header("User-Agent", AppConstants.requestUserAgent)
                .body(bodyData)
                .log().all();
        response = httpRequest.post();
        System.out.println(response.asString());
        jsonPath = response.jsonPath();
        //AppConstants.sessionID = jsonPath.getString("data.user.session_token");
        System.out.println("Session ID : " + AppConstants.sessionID);
    }
    @Test(priority = 100, description = "Status code check" )
    public void CheckStatus(){
        Assert.assertEquals(200, response.getStatusCode(), "Incorrect status code returned, expected value 200");
        System.out.println(response.getStatusCode());
    }
    @Test(priority = 101, description = "Check location title" )
    public void CheckLocationTitle(){
        String locTitle = jsonPath.getString("data.profile[0].section_list[0].title");
        Assert.assertEquals("Location", locTitle,"Location title should be 'Location'");

    }
    @Test(priority = 102, description = "My Account check" )
    public void CheckMyAccountTitle(){
        String AccTitle = jsonPath.getString("data.profile[0].section_list[1].title");
        Assert.assertEquals("My Account", AccTitle,"Account title should be 'My Account'");
    }

    @Test(priority = 103, description = "Wallet check" )
    public void CheckWalletTitle(){
        String walletTitle = jsonPath.getString("data.profile[0].section_list[2].title");
        System.out.println("walletTitle : "+walletTitle);
        Assert.assertEquals("Wallet", walletTitle,"Wallet title should be 'Wallet'");
        String wallet_webURL_Title = jsonPath.getString("data.profile[0].section_list[2].web_url");
        Assert.assertNotNull(wallet_webURL_Title,"Wallet title is null");
    }
    @Test(priority = 104, description = "Wallet check" )
    public void CheckWalletURL(){
        String wallet_webURL_Title = jsonPath.getString("data.profile[0].section_list[2].web_url");
        Assert.assertNotNull(wallet_webURL_Title,"Wallet title is null");
    }
    @Test(priority = 105, description = "My Family check" )
    public void CheckMyFamily(){
        String FamilyTitle = jsonPath.getString("data.profile[1].section_list[0].title");
        Assert.assertNotNull(FamilyTitle,"My Family title is null");
        Assert.assertEquals("My Family", FamilyTitle,"My Family title should be 'My Family'");
    }
    @Test(priority = 106, description = "My Friends check" )
    public void CheckMyFriends(){
        String FriendsTitle = jsonPath.getString("data.profile[1].section_list[1].title");
        System.out.println("FriendsTitle : "+FriendsTitle);
        Assert.assertNotNull(FriendsTitle,"My Friends title is null");
        Assert.assertEquals("My Friends", FriendsTitle,"My Family title should be 'My Friends'");
    }
    @Test(priority = 107, description = "Pings check" )
    public void CheckPings(){
        String PingsTitle = jsonPath.getString("data.profile[2].section_list[0].title");
        System.out.println("PingsTitle : "+PingsTitle);
        Assert.assertNotNull(PingsTitle,"Pings title is null");
        Assert.assertEquals("Pings", PingsTitle,"Pings title should be 'Pings'");
    }

    @Test(priority = 108, description = "Products check" )
    public void CheckProducts(){
        String ProductsTitle = jsonPath.getString("data.profile[2].section_list[1].title");
        System.out.println("ProductsTitle : "+ProductsTitle);
        Assert.assertNotNull(ProductsTitle,"Products title is null");
        Assert.assertEquals("Products", ProductsTitle,"Products title should be 'Products'");
    }
    @Test(priority = 109, description = "Redemptions check" )
    public void CheckRedemptions(){
        String RedemptionsTitle = jsonPath.getString("data.profile[2].section_list[2].title");
        System.out.println("RedemptionsTitle : "+RedemptionsTitle);
        Assert.assertNotNull(RedemptionsTitle,"Redemptions title is null");
        Assert.assertEquals("Redemptions", RedemptionsTitle,"Redemptions title should be 'Redemptions'");
    }
    @Test(priority = 110, description = "Orders check" )
    public void CheckOrders(){
        String OrdersTitle = jsonPath.getString("data.profile[2].section_list[3].title");
        System.out.println("OrdersTitle : "+OrdersTitle);
        Assert.assertNotNull(OrdersTitle,"Orders title is null");
        Assert.assertEquals("Orders", OrdersTitle,"Orders title should be 'Orders'");
    }
    @Test(priority = 111, description = "Reservations check" )
    public void CheckReservations(){
        String ReservationsTitle = jsonPath.getString("data.profile[2].section_list[4].title");
        System.out.println("ReservationsTitle : "+ReservationsTitle);
        Assert.assertNotNull(ReservationsTitle,"Reservations title is null");
        Assert.assertEquals("Reservations", ReservationsTitle,"Reservations title should be 'Reservations'");
    }
    @Test(priority = 112, description = "Push Notifications check" )
    public void CheckPushNotifications(){
        String PushNotificationsTitle = jsonPath.getString("data.profile[3].section_list[0].title");
        System.out.println("PushNotificationsTitle : "+PushNotificationsTitle);
        Assert.assertNotNull(PushNotificationsTitle,"Push Notifications title is null");
        Assert.assertEquals("Push Notifications", PushNotificationsTitle,"Notifications title should be 'Push Notifications'");
    }
    @Test(priority = 113, description = "Email Preferences check" )
    public void CheckEmailPreferences(){
        String EmailPreferencesTitle = jsonPath.getString("data.profile[3].section_list[1].title");
        System.out.println("EmailPreferencesTitle : "+EmailPreferencesTitle);
        Assert.assertNotNull(EmailPreferencesTitle,"Email Preferences title is null");
        Assert.assertEquals("Email Preferences", EmailPreferencesTitle,"Preferences title should be 'Email Preferences'");
    }
    @Test(priority = 114, description = "Onboarding walkthrough check" )
    public void CheckOnboardingwalkthrough(){
        String OnboardingwalkthroughTitle = jsonPath.getString("data.profile[4].section_list[0].title");
        System.out.println("OnboardingwalkthroughTitle : "+OnboardingwalkthroughTitle);
        Assert.assertNotNull(OnboardingwalkthroughTitle,"Onboarding walkthrough title is null");
        Assert.assertEquals("Onboarding walkthrough", OnboardingwalkthroughTitle,"Title should be 'Onboarding walkthrough'");
    }

    @Test(priority = 115, description = "Help and Support check" )
    public void CheckHelpAndSupport(){
        String HelpAndSupportTitle = jsonPath.getString("data.profile[4].section_list[1].title");
        System.out.println("HelpAndSupportTitle : "+HelpAndSupportTitle);
        Assert.assertNotNull(HelpAndSupportTitle,"Help and Support title is null");
        Assert.assertEquals("Help and Support", HelpAndSupportTitle,"Title should be 'Help and Support'");
    }
    @Test(priority = 116, description = "Help and Live Chat check" )
    public void CheckHelpAndLiveChat(){
        String HelpAndLiveChatTitle = jsonPath.getString("data.profile[4].section_list[1].list[0].section_list[0].title");
        System.out.println("HelpAndLiveChatTitle : "+HelpAndLiveChatTitle);
        Assert.assertNotNull(HelpAndLiveChatTitle,"Help and Live Chat title is null");
        Assert.assertEquals("Help and Live Chat", HelpAndLiveChatTitle,"Title should be 'Help and Live Chat'");
    }
    @Test(priority = 117, description = "Rules of Use check" )
    public void CheckRoU(){
        String RoUTitle = jsonPath.getString("data.profile[4].section_list[1].list[0].section_list[1].title");
        System.out.println("RoUTitle : "+RoUTitle);
        Assert.assertNotNull(RoUTitle,"Rules of Use title is null");
        Assert.assertEquals("Rules of Use", RoUTitle,"Title should be 'Rules of Use'");
    }
    @Test(priority = 118, description = "Hotel Rules of Use check" )
    public void CheckHRoU(){
        String HRoUTitle = jsonPath.getString("data.profile[4].section_list[1].list[0].section_list[2].title");
        System.out.println("HRoUTitle : "+HRoUTitle);
        Assert.assertNotNull(HRoUTitle,"Hotel Rules of Use title is null");
        Assert.assertEquals("Hotel Rules of Use", HRoUTitle,"Title should be 'Hotel Rules of Use'");
    }
    @Test(priority = 119, description = "About check" )
    public void CheckAbout(){
        String AboutTitle = jsonPath.getString("data.profile[4].section_list[2].title");
        System.out.println("About_Title : "+AboutTitle);
        Assert.assertNotNull(AboutTitle,"About title is null");
        Assert.assertEquals("About", AboutTitle,"Title should be 'About'");
    }
    @Test(priority = 120, description = "End User License Agreement check" )
    public void CheckEULA(){
        String EULATitle = jsonPath.getString("data.profile[4].section_list[2].list[0].section_list[0].title");
        System.out.println("EULATitle : "+EULATitle);
        Assert.assertNotNull(EULATitle,"End User License Agreement title is null");
        Assert.assertEquals("End User License Agreement", EULATitle,"Title should be 'End User License Agreement'");
    }
    @Test(priority = 121, description = "Give us app feedback" )
    public void CheckFeedback(){
        String FeedbackTitle = jsonPath.getString("data.profile[4].section_list[2].list[0].section_list[1].title");
        System.out.println("FeedbackTitle : "+FeedbackTitle);
        Assert.assertNotNull(FeedbackTitle,"Give us app feedback title is null");
        Assert.assertEquals("Give us app feedback", FeedbackTitle,"Title should be 'Give us app feedback'");
    }
    @Test(priority = 122, description = "Rate our App" )
    public void CheckRateOurApp(){
        String RateAppTitle = jsonPath.getString("data.profile[4].section_list[2].list[0].section_list[2].title");
        System.out.println("RateAppTitle : "+RateAppTitle);
        Assert.assertNotNull(RateAppTitle,"Rate our App title is null");
        Assert.assertEquals("Rate our App", RateAppTitle,"Title should be 'Rate our App'");
    }
    @Test(priority = 123, description = "Sign Out check" )
    public void CheckSignOut(){
        String SignOutTitle = jsonPath.getString("data.profile[5].section_list[0].title");
        System.out.println("SignOutTitle : "+SignOutTitle);
        Assert.assertNotNull(SignOutTitle,"Sign Out title is null");
        Assert.assertEquals("Sign Out", SignOutTitle,"Title should be 'Sign Out'");
    }
}
