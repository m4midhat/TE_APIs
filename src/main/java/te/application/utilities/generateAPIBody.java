package te.application.utilities;

import te.application.appConstants.AppConstants;

public class generateAPIBody {

    static String body;
    public static String signUp(String email, String firstName, String lastName, String password, String confirmPassword){
        body = "{" +
                "    \"email\": \""+email+"\",\n" +
                "    \"firstname\": \""+firstName+"\",\n" +
                "    \"lastname\": \""+lastName+"\",\n" +
                "    \"password\": \""+password+"\",\n" +
                "    \"confirm_password\": \""+confirmPassword+"\"\n" +
                "}";
        return body;
    }

    public static String merchantDetails(String plateform, String sort, String company, String category, int i, String deviceModel, int offerId, int locationId, String param1,
                                         String instantBooking, int SID, String langCode, String format, int merchantID, int outletID, String timezone, String currency,
                                         String searchType, String limit, String query, String offset, String deviceKey, String sessionToken, String redeemability, String appVersion) {
        body = "{\"__platform\": \""+plateform+"\"," +
                "\"sort\":\""+sort+"\", " +
                "\"company\": \""+company+"\", " +
                "\"platform\": \""+plateform+"\", " +
                "\"__category\": \""+category+"\", " +
                "\"__i\": "+i+", " +
                "\"device_model\": \""+deviceModel+"\", " +
                "\"offer_id\": "+offerId+", " +
                "\"category\": \""+category+"\"," +
                "\"location_id\": "+locationId+", " +
                "\"param1\": \""+param1+"\", " +
                "\"is_hww_instant_booking\": \""+instantBooking+"\", " +
                "\"__sid\": "+SID+", " +
                "\"language\": \""+langCode+"\", " +
                "\"_format\": \""+format+"\", " +
                "\"merchant_id\": "+merchantID+", " +
                "\"outlet_id\": "+outletID+", " +
                "\"timezone\": \""+timezone+"\", " +
                "\"currency\": \""+currency+"\", " +
                "\"search_type\": \""+searchType+"\", " +
                "\"limit\": \""+limit+"\", " +
                "\"query\": \""+query+"\", " +
                "\"offset\": \""+offset+"\", " +
                "\"device_key\": \""+deviceKey+"\", " +
                "\"session_token\": \""+AppConstants.sessionID+"\", " +
                "\"__t\": \""+AppConstants.sessionID+"\", " +
                "\"redeemability\": \""+redeemability+"\", " +
                "\"encryption_disable_key\": \""+ AppConstants.ENCRYPTION_DISABLE_KEY +"\", " +
                "\"app_version\": \""+appVersion+"\"" +
                "}";
        return body;
    }


    public static String signUp(int ice, String language, boolean agreementAccepted, String latitude, String company,
                                String OSPlatform, String appVersion, String deviceKey, String i, String currency,
                                String userID, String longitude, String deviceID, String locationID, String emailAddress,
                                String password, String OSVersion, String deviceModel, String timeZone,
                                String configURL, String configAuthToken
                                               ){
        String bodyData = "{\n" +
                "  \"i_c_e\" : "+ice+",\n" +
                "  \"language\" : \""+language+"\",\n" +
                "  \"is_user_agreement_accepted\" : "+agreementAccepted+",\n" +
                "  \"__lat\" : \""+latitude+"\",\n" +
                "  \"company\" : \""+company+ "\",\n" +
                "  \"__platform\" : \""+OSPlatform+"\",\n" +
                "  \"app_version\" : \""+appVersion+"\",\n" +
                "  \"device_key\" : \""+deviceKey+"\",\n" +
                "  \"__i\" : \"0\",\n" +
                "  \"currency\" : \""+currency+"\",\n" +
                "  \"user_id\" : \""+userID+"\",\n" +
                "  \"lng\" : \""+latitude+"\",\n" +
                "  \"__device_id\" : \""+deviceID+"\",\n" +
                "  \"__configuration\" : {\n" +
                "    \"url\" : \""+configURL+"\",\n" +
                "    \"Authorization\" : \""+ configAuthToken+"\"\n" +
                "  },\n" +
                "  \"session_token\" : \"\",\n" +
                "  \"location_id\" : \""+locationID+"\",\n" +
                "  \"email\" : \""+emailAddress+"\",\n" +
                "  \"os_version\" : \""+OSVersion+"\",\n" +
                "  \"device_model\" : \""+deviceModel+"\",\n" +
                "  \"time_zone\" : \""+timeZone+"\",\n" +
                "  \"__lng\" : \""+longitude+"\",\n" +
                "  \"device_os\" : \""+OSPlatform+"\",\n" +
                "  \"lat\" : \""+latitude+"\",\n" +
                "  \"force_login\" : \"true\",\n" +
                "  \"password\" : \""+password+"\"\n" +
                "}";

        return bodyData;
    }

    public static String home(int epcEnable, String language, String latitude, String company,
                              String OSPlatform, String appVersion, String deviceKey, String i, String currency,
                              String userID, String longitude, String deviceID, String locationID,
                              String OSVersion, String deviceModel, String timeZone,
                              String configURL, String configAuthToken, int freemiumBaseHome){
        String body;
        body = "{\n" +
                "  \"language\" : \""+language+"\",\n" +
                "  \"__lat\" : \""+latitude+"\",\n" +
                "  \"company\" : \""+company+"\",\n" +
                "  \"__platform\" : \""+OSPlatform+"\",\n" +
                "  \"app_version\" : \""+appVersion+"\",\n" +
                "  \"device_key\" : \""+deviceKey+"\",\n" +
                "  \"__i\" : \""+i+"\",\n" +
                "  \"currency\" : \""+currency+"\",\n" +
                "  \"user_id\" : \""+userID+"\",\n" +
                "  \"is_freemium_base_home\" : "+freemiumBaseHome+",\n" +

                "\"encryption_disable_key\": \"0af5d6f0-4dd9-498d-8d2c-acf8c80ad9ba\", " +
                "  \"__device_id\" : \""+deviceID+"\",\n" +
                "  \"lng\" : \""+longitude+"\",\n" +
                "  \"__configuration\" : {\n" +
                "    \"url\" : \""+configURL+"\",\n" +
                "    \"Authorization\" : \""+configAuthToken+"\"\n" +
                "  },\n" +
                "  \"epc_enabled\" : "+epcEnable+",\n" +
                "  \"session_token\" : \""+AppConstants.sessionID+"\",\n" +
                "  \"location_id\" : \""+locationID+"\",\n" +
                "  \"os_version\" : \""+OSVersion+"\",\n" +
                "  \"device_model\" : \""+deviceModel+"\",\n" +
                "  \"time_zone\" : \""+timeZone+"\",\n" +
                "  \"__lng\" : \""+longitude+"\",\n" +
                "  \"device_os\" : \""+OSPlatform+"\",\n" +
                "  \"lat\" : \""+latitude+"\"\n" +
                "}";
        return body;
    }

    public static String signUp(String ConfirmPassword, int ice, String Lastname, String language,
                                int agreementAccepted, String latitude , String company, String OSPlatform,
                                String appVersion, String deviceKey, String i, String currency, String userID, String Firstname,
                                String deviceID, String Birthdate, String longitude, String configURL, String configAuthToken,
                                String Sessiontoken, String Nationality, String emailAddress, String OSVersion, String deviceModel,
                                String timeZone, String password) {
        String bodyData = "{\n" +
                "  \"confirm_password\" : \""+ConfirmPassword+"\",\n" +
                "  \"i_c_e\" : "+ice+",\n" +
                "  \"lastname\" : \""+Lastname+"\",\n" +
                "  \"language\" : \""+language+"\",\n" +
                "  \"is_user_agreement_accepted\" : "+agreementAccepted+",\n" +
                "  \"__lat\" : \""+latitude+"\",\n" +
                "  \"company\" : \""+company+ "\",\n" +
                "  \"__platform\" : \""+OSPlatform+"\",\n" +
                "  \"app_version\" : \""+appVersion+"\",\n" +
                "  \"device_key\" : \""+deviceKey+"\",\n" +
                "  \"__i\" : \""+i+"\",\n" +
                "  \"currency\" : \""+currency+"\",\n" +
                "  \"user_id\" : \""+userID+"\",\n" +
                "  \"firstname\" : \""+Firstname+"\",\n" +
                "  \"__device_id\" : \""+deviceID+"\",\n" +
                "  \"date_of_birth\" : \""+Birthdate+"\",\n" +
                "  \"lng\" : \""+longitude+"\",\n" +
                "  \"__configuration\" : {\n" +
                "    \"url\" : \""+configURL+"\",\n" +
                "    \"Authorization\" : \""+ configAuthToken+"\"\n" +
                "  },\n" +
                "  \"session_token\" : \""+Sessiontoken+"\",\n" +
                "  \"nationality\" : \""+Nationality+"\",\n" +
                //"  \"location_id\" : \""+locationID+"\",\n" +

                "  \"email\" : \""+emailAddress+"\",\n" +
                "  \"os_version\" : \""+OSVersion+"\",\n" +
                "  \"device_model\" : \""+deviceModel+"\",\n" +
                "  \"time_zone\" : \""+timeZone+"\",\n" +
                "  \"__lng\" : \""+longitude+"\",\n" +
                "  \"device_os\" : \""+OSPlatform+"\",\n" +
                "  \"lat\" : \""+latitude+"\",\n" +
                "  \"password\" : \""+ConfirmPassword+"\"\n" +
                "}";

        return bodyData;
    }


    public static String locations(int ice, String language, boolean agreementAccepted, String latitude, String company,
                                   String OSPlatform, String appVersion, String deviceKey, String i, String currency,
                                   String userID, String freemiumBase, String longitude, String deviceID, String locationID,
                                   String password, String OSVersion, String deviceModel, String timeZone,
                                   String epc, String SessionTok, String Longitude, String DevOs, String Lat,
                                   String sessionURL, String b2cauthToken){
        String bodyData = "{\n" +
                "  \"i_c_e\" : "+ice+",\n" +
                "  \"language\" : \""+language+"\",\n" +
                "  \"is_user_agreement_accepted\" : "+agreementAccepted+",\n" +
                "  \"__lat\" : \""+latitude+"\",\n" +
                "  \"company\" : \""+company+ "\",\n" +
                "  \"__platform\" : \""+OSPlatform+"\",\n" +
                "  \"app_version\" : \""+appVersion+"\",\n" +
                "  \"device_key\" : \""+deviceKey+"\",\n" +
                "  \"__i\" : \"0\",\n" +
                "  \"currency\" : \""+currency+"\",\n" +
                "  \"user_id\" : \""+userID+"\",\n" +
                "  \"lng\" : \""+latitude+"\",\n" +
                "  \"__device_id\" : \""+deviceID+"\",\n" +
                "  \"is_freemium_base_home\" : \""+freemiumBase+"\",\n" +
                "  \"session_token\" : \"\",\n" +
                "  \"location_id\" : \""+locationID+"\",\n" +
                "  \"os_version\" : \""+OSVersion+"\",\n" +
                "  \"device_model\" : \""+deviceModel+"\",\n" +
                "  \"time_zone\" : \""+timeZone+"\",\n" +
                "  \"__lng\" : \""+longitude+"\",\n" +
                "  \"device_os\" : \""+OSPlatform+"\",\n" +
                "  \"lat\" : \""+latitude+"\",\n" +
                "  \"force_login\" : \"true\",\n" +
                "  \"password\" : \""+password+"\"\n" +
                "  \"epc_enabled\" : \""+epc+"\"\n" +
                "  \"session_token\" : \"\",\n" +
                "  \"os_version\" : \""+OSVersion+"\"\n" +
                "  \"__lng\" : \""+Longitude+"\"\n" +
                "  \"device_os\" : \""+DevOs+"\"\n" +
                "  \"lat\" : \""+Lat+"\"\n" +

                "}";

        return bodyData;
    }

    public static String settings( String language, String latitude, String company,
                                   String OSPlatform, String appVersion, String deviceKey,  String currency,
                                   String userID, String longitude, String deviceID, String locationID
            , String deviceModel, String timeZone,
                                   String c, boolean promoHistorySection, boolean languageSection
            ,String DevicePlateform, String t, String customerId ) {
        String body;
        body = "{\n" +
                "  \"language\" : \"" + language + "\",\n" +
                "  \"__lat\" : \"" + latitude + "\",\n" +
                "  \"company\" : \"" + company + "\",\n" +
                "  \"__platform\" : \"" + OSPlatform + "\",\n" +
                "  \"app_version\" : \"" + appVersion + "\",\n" +
                "  \"device_key\" : \"" + deviceKey + "\",\n" +
                "  \"currency\" : \"" + currency + "\",\n" +
                "  \"user_id\" : \"" + userID + "\",\n" +
                "  \"__device_id\" : \"" + deviceID + "\",\n" +
                "  \"lng\" : \"" + longitude + "\",\n" +
                "  \"session_token\" : \"" + AppConstants.sessionID + "\",\n" +
                "  \"location_id\" : \"" + locationID + "\",\n" +
                "  \"device_model\" : \"" + deviceModel + "\",\n" +
                "  \"time_zone\" : \"" + timeZone + "\",\n" +
                "  \"__lng\" : \"" + longitude + "\",\n" +
                "  \"device_os\" : \"" + OSPlatform + "\",\n" +
                "  \"lat\" : \"" + latitude + "\",\n" +
                "  \"__c\" : \"" + c + "\",\n" +
                "  \"promo_history_section\" : \"" + promoHistorySection + "\",\n" +
                "  \"language_section\" : \"" + languageSection + "\",\n" +
                "  \"platform\" : \"" + DevicePlateform + "\",\n" +
                "  \"__t\" : \"" + AppConstants.sessionID + "\",\n" +
                "  \"customer_id\" : \"" + customerId + "\",\n" +

                "}";
        return body;
    }


    public static String Profile(String language, String latitude, String company,
                                 String OSPlatform, String appVersion, String deviceKey, String currency,
                                 String userID, String longitude, String deviceID, String locationID,
                                 String deviceModel, String timeZone, String token, String customerId ,String session_token

    ){
        String bodyData = "{\n" +
                "  \"language\" : \""+language+"\",\n" +
                "  \"__lat\" : \""+latitude+"\",\n" +
                "  \"company\" : \""+company+ "\",\n" +
                "  \"__platform\" : \""+OSPlatform+"\",\n" +
                "  \"app_version\" : \""+appVersion+"\",\n" +
                "  \"device_key\" : \""+deviceKey+"\",\n" +
                "  \"currency\" : \""+currency+"\",\n" +
                "  \"user_id\" : \""+userID+"\",\n" +
                "  \"lng\" : \""+latitude+"\",\n" +
                "  \"__device_id\" : \""+deviceID+"\",\n" +
                "  \"session_token\" :  \""+AppConstants.sessionID+"\",\n" +
                "  \"location_id\" : \""+locationID+"\",\n" +
                "  \"device_model\" : \""+deviceModel+"\",\n" +
                "  \"time_zone\" : \""+timeZone+"\",\n" +
                "  \"__lng\" : \""+longitude+"\",\n" +
                "  \"device_os\" : \""+OSPlatform+"\",\n" +
                "  \"lat\" : \""+latitude+"\",\n" +
                "  \"__c\" : \""+company+"\",\n" +
                "  \"__t\" : \""+token+"\",\n" +
                "  \"customer_id\" : \""+customerId+"\",\n" +
                "  \"platform\" : \""+OSPlatform+"\"\n" +
                "}";

        return bodyData;
    }


}
