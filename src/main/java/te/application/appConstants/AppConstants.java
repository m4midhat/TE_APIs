package te.application.appConstants;

import te.application.utilities.Utils;

import java.time.LocalDateTime;

public class AppConstants {

    public static final String BASE_URI_B2C = "https://entutapi.theentertainerme.com/";
    public static final int EXPECTED_API_TIME = 3000;
    public static final String BEARER_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb21wYW55IjoiZW50ZXJ0YWluZXIiLCJhcGlfdG9rZW4iOiIyNGZlOThiNC05ZjFjLTRjYjgtOGFhNC0zMWY1YWMwNmMyNmIiLCJzZXNzaW9uX3Rva2VuIjoiNzFjZDczNjgtNmYxMy00YzVlLThjZGQtYjkzMjIxMzdlY2ExIn0.qdinslzvXb79GBZObPSH8rG4cL8utB_jsMFMsQhkSio";

    public static final String BASE_PATH_MERCHANT = "api_ets/v3/merchants/";
    public static final String B2C_BASE_PATH_MERCHANT = "et_rs_prd/web/v801/merchants/";
    public static final String B2C_LOGIN = "et_rs_prd/web/v801/sessions";
    public static final String BASE_PATH_SIGNUP = "et_rs_prd/web/v801/users";
    public static final String BASE_PATH_OUTLETS = "api_ets/v3/outlets";
    public static final String BASE_PATH_REDEEM = "et_rs_prd/web/v801/redemptions";
    public static final String BASE_PATH_SETTINGS = "et_rs_prd/web/v801/profile/settings";
    public static final String B2C_LOCATIONS = "et_rs_prd/web/v801/locations";
    public static final String PROFILE_BASE_PATH = "et_rs_prd/web/v801/user/get/profile";
    public static final String BASE_PATH_HOME = "et_rs_prd/web/v801/home";
    public static final String B2C_FILTERS = "pysearch/v2/filter-list";
    public static final String BASE_PATH_PINGS_SEND_OFFERS = "et_rs_prd/web/v801/sharing/sendoffers";
    public static final String BASE_PATE_PINGS_RECEIVE_OFFERS = "et_rs_prd/web/v801/sharing/receivedoffers";
    public static LocalDateTime START_DATE, END_DATE;
    public static String ENV = System.getProperty("Env");
    public static String LANG = System.getProperty("Language");
    public static String SessionURL = "https://rcapi.theentertainerme.com/et_rs_prd/web/v801/sessions";
    public static String sessionID;
    public static String ENCRYPTION_DISABLE_KEY = "0af5d6f0-4dd9-498d-8d2c-acf8c80ad9ba";
    public static int numberOfRetries = 3;
    public static final  String BASE_PATH_Mega_AUTO_SEARCH= "pysearch/v2/mega-auto-suggestions";
    public static final String BASE_PATH_TRENDING_SEARCH="pysearch/v2/trending-search";
    public static final String PYTHON_BASE_URI = "https://uatpysch.theentertainerme.com/";
    public static final String B2C_FORGOT_PASSWORD = "et_rs_prd/web/v801/passwords";
    public static final String BASE_PATH_SIGNOUT = "et_rs_prd/web/v801/session/logout";

    public static String requestLanguage,
            requestOSPlatform,
            requestOSVersion,
            requestDeviceModel,
            requestTimeZone,
            requestCurrency,
            requestAppVersion;

    public static String UserID;
    public static String B2C_FAMILY = "et_rs_prd/web/v801/family/familyinfo";
}
