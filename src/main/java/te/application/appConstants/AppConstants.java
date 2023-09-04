package te.application.appConstants;

import te.application.utilities.Utils;

import java.time.LocalDateTime;

public class AppConstants {

    public static final String BASE_PATH_SIGNUP = "et_rs_prd/web/v801/users"; //B2B
    public static final String BEARER_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb21wYW55IjoiZW50ZXJ0YWluZXIiLCJhcGlfdG9rZW4iOiIyNGZlOThiNC05ZjFjLTRjYjgtOGFhNC0zMWY1YWMwNmMyNmIiLCJzZXNzaW9uX3Rva2VuIjoiNzFjZDczNjgtNmYxMy00YzVlLThjZGQtYjkzMjIxMzdlY2ExIn0.qdinslzvXb79GBZObPSH8rG4cL8utB_jsMFMsQhkSio";

    public static LocalDateTime START_DATE, END_DATE;
    public static String ENV = System.getProperty("Env");
    public static String LANG = System.getProperty("Language");
    public static String sessionID;
    public static String ENCRYPTION_DISABLE_KEY = "0af5d6f0-4dd9-498d-8d2c-acf8c80ad9ba";
    public static int numberOfRetries = 3;

    public static String requestLanguage,
            requestOSPlatform,
            requestOSVersion,
            requestDeviceModel,
            requestTimeZone,
            requestCurrency,
            requestAppVersion,
            requestDeviceKey,
            requestUserAgent;

    public static String UserID;
}
