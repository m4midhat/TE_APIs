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
                "\"session_token\": \""+"0203b680-40e2-4e4e-b2c1-52fe4ec65116"+"\", " +
                "\"__t\": \""+"0203b680-40e2-4e4e-b2c1-52fe4ec65116"+"\", " +
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

}
