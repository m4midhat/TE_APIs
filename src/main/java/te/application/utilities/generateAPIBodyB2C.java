package te.application.utilities;

import te.application.appConstants.AppConstants;

public class generateAPIBodyB2C {

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

    public static String merchantDetails(String plateform, String sort, String company, String category, String deviceModel, int offerId, int locationId, String param1,
                                         String instantBooking, int SID, String langCode, String format, int merchantID, int outletID, String timezone, String currency,
                                         String searchType, String limit, String query, String offset, String deviceKey, String redeemability, String appVersion) {
        body = "{\"__platform\": \""+plateform+"\"," +
                "\"sort\":\""+sort+"\", " +
                "\"company\": \""+company+"\", " +
                "\"platform\": \""+plateform+"\", " +
                "\"__category\": \""+category+"\", " +
                "\"__i\": "+AppConstants.UserID+", " +
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
                //"\"outlet_id\": "+outletID+", " + // default outlet id
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
                                String OSPlatform, String appVersion, String deviceKey, String currency,
                                String longitude, String deviceID, String locationID, String emailAddress,
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
                "  \"__i\" : \""+AppConstants.UserID+"\",\n" +
                "  \"currency\" : \""+currency+"\",\n" +
                "  \"user_id\" : \""+AppConstants.UserID+"\",\n" +
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
                               String longitude, String deviceID, String locationID,
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
                "  \"__i\" : \""+AppConstants.UserID+"\",\n" +
                "  \"currency\" : \""+currency+"\",\n" +
                "  \"user_id\" : \""+AppConstants.UserID+"\",\n" +
                "  \"is_freemium_base_home\" : "+freemiumBaseHome+",\n" +

                "\"encryption_disable_key\": \""+AppConstants.ENCRYPTION_DISABLE_KEY+"\", \n" +
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
                                String appVersion, String deviceKey, String currency, String Firstname,
                                String deviceID, String Birthdate, String longitude, String configURL, String configAuthToken,
                                String sessionToken, String Nationality, String emailAddress, String OSVersion, String deviceModel,
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
                "  \"__i\" : \""+AppConstants.UserID+"\",\n" +
                "  \"currency\" : \""+currency+"\",\n" +
                "  \"user_id\" : \""+AppConstants.UserID+"\",\n" +
                "  \"firstname\" : \""+Firstname+"\",\n" +
                "  \"__device_id\" : \""+deviceID+"\",\n" +
                "  \"date_of_birth\" : \""+Birthdate+"\",\n" +
                "  \"lng\" : \""+longitude+"\",\n" +
                "  \"__configuration\" : {\n" +
                "    \"url\" : \""+configURL+"\",\n" +
                "    \"Authorization\" : \""+ configAuthToken+"\"\n" +
                "  },\n" +
                "  \"session_token\" : \""+sessionToken+"\",\n" +
                "  \"nationality\" : \""+Nationality+"\",\n" +
                //"  \"location_id\" : \""+locationID+"\",\n" +

                "  \"email\" : \""+emailAddress+"\",\n" +
                "  \"os_version\" : \""+OSVersion+"\",\n" +
                "  \"device_model\" : \""+deviceModel+"\",\n" +
                "  \"time_zone\" : \""+timeZone+"\",\n" +
                "  \"__lng\" : \""+longitude+"\",\n" +
                "  \"device_os\" : \""+OSPlatform+"\",\n" +
                "  \"lat\" : \""+latitude+"\",\n" +
                "  \"password\" : \""+password+"\"\n" +
                "}";

        return bodyData;
    }


    public static String locations(int ice, String language, boolean agreementAccepted, String latitude, String company,
                                   String OSPlatform, String appVersion, String deviceKey, String i, String currency,
                                   String freemiumBase, String longitude, String deviceID, String locationID,
                                   String password, String OSVersion, String deviceModel, String timeZone,
                                   String epc, String Longitude, String DevOs, String Lat){
        String bodyData = "{\n" +
                "  \"i_c_e\" : "+ice+",\n" +
                "  \"language\" : \""+language+"\",\n" +
                "  \"is_user_agreement_accepted\" : "+agreementAccepted+",\n" +
                "  \"__lat\" : \""+latitude+"\",\n" +
                "  \"company\" : \""+company+ "\",\n" +
                "  \"__platform\" : \""+OSPlatform+"\",\n" +
                "  \"app_version\" : \""+appVersion+"\",\n" +
                "  \"device_key\" : \""+deviceKey+"\",\n" +
                "  \"__i\" : \""+AppConstants.UserID+"\",\n" +
                "  \"currency\" : \""+currency+"\",\n" +
                "  \"user_id\" : \""+AppConstants.UserID+"\",\n" +
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
                "  \"password\" : \""+password+"\",\n" +
                "  \"epc_enabled\" : \""+epc+"\",\n" +
                "  \"session_token\" : \"\",\n" +
                "  \"os_version\" : \""+OSVersion+"\",\n" +
                "  \"__lng\" : \""+Longitude+"\",\n" +
                "  \"device_os\" : \""+DevOs+"\",\n" +
                "  \"lat\" : \""+Lat+"\"\n" +

                "}";

        return bodyData;
    }

    public static String settings( String language, String latitude, String company,
                                   String OSPlatform, String appVersion, String deviceKey,  String currency,
                                    String longitude, String deviceID, String locationID,
                                   String deviceModel, String timeZone,
                                   String c, boolean promoHistorySection, boolean languageSection,
                                   String DevicePlateform) {
        String body;
        body = "{\n" +
                "  \"language\" : \"" + language + "\",\n" +
                "  \"__lat\" : \"" + latitude + "\",\n" +
                "  \"company\" : \"" + company + "\",\n" +
                "  \"__platform\" : \"" + OSPlatform + "\",\n" +
                "  \"app_version\" : \"" + appVersion + "\",\n" +
                "  \"device_key\" : \"" + deviceKey + "\",\n" +
                "  \"currency\" : \"" + currency + "\",\n" +
                "  \"user_id\" : \"" + AppConstants.UserID + "\",\n" +
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
                "  \"customer_id\" : \"" + AppConstants.UserID + "\",\n" +

                "}";
        return body;
    }

    public static String signIn(int ice, String language, boolean agreementAccepted, String latitude, String company,
                                String OSPlatform, String appVersion, String deviceKey, String currency,
                                String longitude, String deviceID, String locationID, String emailAddress,
                                String password, String OSVersion, String deviceModel, String timeZone,
                                String configURL, String configAuthToken, String force_login
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
                "  \"user_id\" : \""+AppConstants.UserID+"\",\n" +
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
                "  \"force_login\" : \""+force_login+"\",\n" +
                "  \"password\" : \""+password+"\"\n" +
                "}";

        return bodyData;
    }


    public static String Profile(String language, String latitude, String company,
                                 String OSPlatform, String appVersion, String deviceKey, String currency,
                                 String longitude, String deviceID, String locationID,
                                 String deviceModel, String timeZone

    ){
        String bodyData = "{\n" +
                "  \"language\" : \""+language+"\",\n" +
                "  \"__lat\" : \""+latitude+"\",\n" +
                "  \"company\" : \""+company+ "\",\n" +
                "  \"__platform\" : \""+OSPlatform+"\",\n" +
                "  \"app_version\" : \""+appVersion+"\",\n" +
                "  \"device_key\" : \""+deviceKey+"\",\n" +
                "  \"currency\" : \""+currency+"\",\n" +
                "  \"user_id\" : \""+AppConstants.UserID+"\",\n" +
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
                "  \"__t\" : \""+AppConstants.sessionID+"\",\n" +
                "  \"customer_id\" : \""+AppConstants.UserID+"\",\n" +
                "  \"platform\" : \""+OSPlatform+"\"\n" +
                "}";

        return bodyData;
    }

    public static String autoMegaSearch(String category,String categoryID, String includeTravelOutlets,
                                        String locationID, String fromSearch, String searchQuery, String language,
                                        String autoRedeemibility ,int CSAOlSize, String latitude,String longitude,
                                        int outletSize,  String timeZone,String currency,
                                        String company,
                                        String appVersion,  String platform,String deviceOS,
                                        String deviceID, String deviceModel, String deviceKey){
        String body;
        body = "{\n" +
                "  \"language\" : \""+language+"\",\n" +
                "  \"__outlets_size\" : \""+outletSize+"\",\n" +
                "  \"__lat\" : \""+latitude+"\",\n" +
                "  \"company\" : \""+company+"\",\n" +
                "  \"__platform\" : \""+platform+"\",\n" +
                "  \"app_version\" : \""+appVersion+"\",\n" +
                "  \"device_key\" : \""+deviceKey+"\",\n" +
                "  \"__l\" : \""+language+"\",\n" +
                "  \"currency\" : \""+currency+"\",\n" +
                "  \"user_id\" : \""+AppConstants.UserID+"\",\n" +
                "  \"query\" : \""+searchQuery+"\",\n" +
                "\"__device_id\" : \""+deviceID+"\",\n" +
                "\"category\" : \""+category+"\",\n" +
                "  \"lng\" : \""+longitude+"\",\n" +
                "\"customer_id\" : \""+AppConstants.UserID+"\",\n" +
                "  \"device_os\" : \""+deviceOS+"\",\n" +
                "  \"session_token\" : \""+AppConstants.sessionID+"\",\n" +
                "  \"location_id\" : \""+locationID+"\",\n" +
                "  \"include_travel_outlets\" : \""+includeTravelOutlets+"\",\n" +
                "  \"from_search\" : "+fromSearch+",\n" +
                "  \"device_model\" : \""+deviceModel+"\",\n" +
                "  \"time_zone\" : \""+timeZone+"\",\n" +
                "  \"__lng\" : \""+longitude+"\",\n" +
                "  \"__c_s_a_ol_size\" : \""+CSAOlSize+"\",\n" +
                "  \"auto_redeembility\" : "+autoRedeemibility+",\n" +
                "  \"category_id\" : \""+categoryID+"\",\n" +
                "  \"encryption_disable_key\" : \""+AppConstants.ENCRYPTION_DISABLE_KEY+"\"" +
                "}";
        return body;
    }

    public static String trendingSearch(Boolean includeTravelOutlets, String locationID, String category,String categoryID,
                                        String language,int CSAOlSize, String latitude,String longitude,
                                        String timeZone,String currency, String company,
                                        String appVersion,  String platform,String deviceOS, String deviceID, String deviceModel, String deviceKey)
    {
        String body;
        body = "{\n" +
                "  \"language\" : \""+language+"\",\n" +
                "  \"__lat\" : \""+latitude+"\",\n" +
                "  \"company\" : \""+company+"\",\n" +
                "  \"__platform\" : \""+platform+"\",\n" +
                "  \"app_version\" : \""+appVersion+"\",\n" +
                "  \"device_key\" : \""+deviceKey+"\",\n" +
                "  \"__l\" : \""+language+"\",\n" +
                "  \"currency\" : \""+currency+"\",\n" +
                "  \"user_id\" : \""+AppConstants.UserID+"\",\n" +
                "  \"__device_id\" : \""+deviceID+"\",\n" +
                "  \"category\" : \""+category+"\",\n" +
                "  \"lng\" : \""+longitude+"\",\n" +
                "  \"customer_id\" : \""+AppConstants.UserID+"\",\n" +
                "  \"device_os\" : \""+deviceOS+"\",\n" +
                "  \"session_token\" : \""+AppConstants.sessionID+"\",\n" +
                "  \"location_id\" : \""+locationID+"\",\n" +
                "  \"include_travel_outlets\" : \""+includeTravelOutlets+"\",\n" +
                "  \"device_model\" : \""+deviceModel+"\",\n" +
                "  \"time_zone\" : \""+timeZone+"\",\n" +
                "  \"__lng\" : \""+longitude+"\",\n" +
                "  \"__c_s_a_ol_size\" : \""+CSAOlSize+"\",\n" +
                "  \"category_id\" : \""+categoryID+"\",\n" +
                "  \"encryption_disable_key\" : \""+AppConstants.ENCRYPTION_DISABLE_KEY+"\"" +
                "}";
        return body;
    }

    public static String filter (String language, String __lat, String company, String __platform, String app_version, String device_Key,
                                 String currency, int coming_from_normal_outlet_listing, String __device_id,
                                 String category, String lng, String session_token, String location_id, int include_travel_outlets,
                                 String os_version, String device_model, String time_zone, String __lng, String device_os,
                                 String lat) {
        {
            String bodyData = "{\n" +
                    "  \"language\" : \""+language+"\",\n" +
                    "  \"__lat\" : \""+__lat +"\",\n" +
                    "  \"company\" : \""+company+ "\",\n" +
                    "  \"__platform\" : \""+__platform+"\",\n" +
                    "  \"app_version\" : \""+app_version+"\",\n" +
                    "  \"device_key\" : \""+device_Key+"\",\n" +
                    "  \"__i\" : \""+AppConstants.UserID+"\",\n" +
                    "  \"currency\" : \""+currency+"\",\n" +
                    "  \"user_id\" : \""+AppConstants.UserID+"\",\n" +
                    "  \"coming_from_normal_outlet_listing\" : "+coming_from_normal_outlet_listing+",\n" +
                    "  \"__device_id\" : \""+__device_id+"\",\n"+
                    "  \"category\" : \""+category+"\",\n"+
                    "  \"lng\" : \""+lng+"\",\n" +
                    "  \"session_token\" : \""+session_token+"\",\n" +
                    "  \"location_id\" : \""+location_id+"\",\n" +
                    "  \"include_travel_outlets\" : \""+include_travel_outlets+"\",\n"+
                    "  \"os_version\" : \""+os_version+"\",\n" +
                    "  \"device_model\" : \""+device_model+"\",\n" +
                    "  \"time_zone\" : \""+time_zone+"\",\n" +
                    "  \"__lng\" : \""+__lng+"\",\n" +
                    "  \"device_os\" : \""+device_os+"\",\n" +
                    "  \"lat\" : \""+lat+"\",\n" +
                    "  \"encryption_disable_key\" : \""+AppConstants.ENCRYPTION_DISABLE_KEY+"\"" +
                    "}";

            return bodyData;
        }
    }


    public static String pingSendOffers( String language, String longitude,  String latitude, String company,
                                         String platform, String appVersion, String deviceKey,  String currency,
                                         String deviceID, String locationID
            , String deviceModel, String timeZone,String customerId
    ) {
        String body;
        body = "{\n" +
                "  \"language\" : \"" + language + "\",\n" +
                "  \"__lat\" : \"" + latitude + "\",\n" +
                "  \"company\" : \"" + company + "\",\n" +
                "  \"__platform\" : \"" + platform + "\",\n" +
                "  \"app_version\" : \"" + appVersion + "\",\n" +
                "  \"device_key\" : \"" + deviceKey + "\",\n" +
                "  \"currency\" : \"" + currency + "\",\n" +
                "  \"user_id\" : \"" + AppConstants.UserID + "\",\n" +
                "  \"__device_id\" : \"" + deviceID + "\",\n" +
                "  \"lng\" : \"" + longitude + "\",\n" +
                "  \"session_token\" : \"" + AppConstants.sessionID + "\",\n" +
                "  \"location_id\" : \"" + locationID + "\",\n" +
                "  \"device_model\" : \"" + deviceModel + "\",\n" +
                "  \"time_zone\" : \"" + timeZone + "\",\n" +
                "  \"__lng\" : \"" + longitude + "\",\n" +
                "  \"device_os\" : \"" + platform + "\",\n" +
                "  \"lat\" : \"" + latitude + "\",\n" +
                "  \"__c\" : \"" + company + "\",\n" +
                "  \"platform\" : \"" + platform + "\",\n" +
                "  \"__t\" : \"" + AppConstants.sessionID + "\",\n" +
                "  \"encryption_disable_key\": \""+AppConstants.ENCRYPTION_DISABLE_KEY+"\", " +
                "  \"customer_id\" : \"" + customerId + "\"\n" +

                "}";
        return body;
    }


    public static String forgotPassword(String deviceModel, String currency, String deviceKey,String appVersion, String company, String userID, String title, String locationID,
                                        String latitude, String longitude,String language, String deviceID,String i,String deviceOs,
                                        String OSVersion,  String emailAddress, String platform,  String timeZone){

        String bodyData = "{\n" +
                "  \"device_model\" : \""+deviceModel+"\",\n" +
                "  \"currency\" : \""+currency+"\",\n" +
                "  \"device_key\" : \""+deviceKey+"\",\n" +
                "  \"app_version\" : \""+appVersion+"\",\n" +
                "  \"session_token\" : \"\",\n" +
                "  \"company\" : \""+company+ "\",\n" +
                "  \"user_id\" : \""+AppConstants.UserID+"\",\n" +
                "  \"title\" : \""+title+"\",\n" +
                "  \"location_id\" : \""+locationID+"\",\n" +
                "  \"__lat\" : \""+latitude+"\",\n" +
                "  \"__lng\" : \""+longitude+"\",\n" +
                "  \"language\" : \""+language+"\",\n" +
                "  \"__i\" : \""+AppConstants.UserID+"\",\n" +
                "  \"__device_id\" : \""+deviceID+"\",\n" +
                "  \"device_os\" : \""+deviceOs+"\",\n" +
                "  \"os_version\" : \""+OSVersion+"\",\n" +
                "  \"email\" : \""+emailAddress+"\",\n" +
                "  \"__platform\" : \""+platform+"\",\n" +
                "  \"time_zone\" : \""+timeZone+"\",\n" +
                "  \"encryption_disable_key\" : \""+AppConstants.ENCRYPTION_DISABLE_KEY+"\"" +
                "}";

        return bodyData;
    }


    public static String SignOutTest(String language, String _latitude, String company, String OSPlatform, String appVersion,
                                     String deviceKey, String currency, String longitude1,
                                     String deviceID, String configURL,
                                     String configAuthToken,String sessionID, String locationID, String OSVersion, String deviceModel, String timeZone,
                                     String longitude, String deviceOS,String __lat)
    {
        String bodyData = "{\n" +
                "  \"language\" : \""+language+"\",\n" +
                "  \"__lat\" : \""+_latitude+"\",\n" +
                "  \"company\" : \""+company+ "\",\n" +
                "  \"__platform\" : \""+OSPlatform+"\",\n" +
                "  \"app_version\" : \""+appVersion+"\",\n" +
                "  \"device_key\" : \""+deviceKey+"\",\n" +
                "  \"__i\" : \""+AppConstants.UserID+"\",\n" +
                "  \"currency\" : \""+currency+"\",\n" +
                "  \"user_id\" : \""+AppConstants.UserID+"\",\n" +
                "  \"lng\" : \""+longitude1+"\",\n" +
                "  \"__device_id\" : \""+deviceID+"\",\n" +
                "  \"__configuration\" : {\n" +
                "    \"url\" : \""+configURL+"\",\n" +
                "    \"Authorization\" : \""+ configAuthToken+"\"\n" +
                "  },\n" +
                "  \"session_token\" : \""+sessionID+"\",\n" +
                "  \"location_id\" : \""+locationID+"\",\n" +
                "  \"os_version\" : \""+OSVersion+"\",\n" +
                "  \"device_model\" : \""+deviceModel+"\",\n" +
                "  \"time_zone\" : \""+timeZone+"\",\n" +
                "  \"__lng\" : \""+longitude+"\",\n" +
                "  \"device_os\" : \""+deviceOS+"\",\n" +
                "\"encryption_disable_key\": \""+AppConstants.ENCRYPTION_DISABLE_KEY+"\", " +
                "  \"lat\" : \""+__lat+"\"\n" +
                "}";

        return bodyData;
    }


    public static String pingReceiveOffers( String language, String longitude,  String latitude, String company,
                                            String platform, String appVersion, String deviceKey,  String currency,
                                            String deviceID, String locationID
            , String deviceModel, String timeZone,String customerId) {
        String body;
        body = "{\n" +
                "  \"language\" : \"" + language + "\",\n" +
                "  \"__lat\" : \"" + latitude + "\",\n" +
                "  \"company\" : \"" + company + "\",\n" +
                "  \"__platform\" : \"" + platform + "\",\n" +
                "  \"app_version\" : \"" + appVersion + "\",\n" +
                "  \"device_key\" : \"" + deviceKey + "\",\n" +
                "  \"currency\" : \"" + currency + "\",\n" +
                "  \"user_id\" : \"" + AppConstants.UserID + "\",\n" +
                "  \"__device_id\" : \"" + deviceID + "\",\n" +
                "  \"lng\" : \"" + longitude + "\",\n" +
                "  \"session_token\" : \"" + AppConstants.sessionID + "\",\n" +
                "  \"location_id\" : \"" + locationID + "\",\n" +
                "  \"device_model\" : \"" + deviceModel + "\",\n" +
                "  \"time_zone\" : \"" + timeZone + "\",\n" +
                "  \"__lng\" : \"" + longitude + "\",\n" +
                "  \"device_os\" : \"" + platform + "\",\n" +
                "  \"lat\" : \"" + latitude + "\",\n" +
                "  \"__c\" : \"" + company + "\",\n" +
                "  \"platform\" : \"" + platform + "\",\n" +
                "  \"__t\" : \"" + AppConstants.sessionID + "\",\n" +
                "\"encryption_disable_key\": \"" + AppConstants.ENCRYPTION_DISABLE_KEY + "\",\n" +
                "  \"customer_id\" : \"" + customerId + "\"\n" +

                "}";
        return body;
    }


    public static String RedemptionsDetails(String lat,String platform,String company,String language,String app_version,
                                            String device_key,String currency,String transaction_id,
                                            String __device_id,int offer_id,String merchant_pin,String session_token,String location_id,
                                            int quantity,int is_reattempt,int product_id,String is_shared,String os_version,
                                            String device_model,String time_zone,String __lng,String device_os,String outlet_currency,
                                            int outlet_id,String is_family_offer) {
        body = "{\"__lat\": \""+lat+"\"," +
                "\"__platform\":\""+platform+"\", " +
                "\"company\": \""+company+"\", " +
                "\"language\": \""+language+"\", " +
                "\"app_version\": \""+app_version+"\", " +
                "\"device_key\":\" "+device_key+"\", " +
                "\"__i\": \""+AppConstants.UserID+"\",\n" +
                "\"currency\": \""+currency+"\", " +
                "\"user_id\": \""+AppConstants.UserID+"\"," +
                "\"transaction_id\": \""+transaction_id+"\", " +
                "\"__device_id\": \""+__device_id+"\", " +
                "\"offer_id\": "+offer_id+", " +
                "\"merchant_pin\": \""+merchant_pin+"\", " +
                "\"session_token\": \""+session_token+"\", " +
                "\"location_id\": \""+location_id+"\", " +
                "\"quantity\": "+quantity+", " +
                "\"is_reattempt\": "+is_reattempt+", " +
                "\"product_id\": "+product_id+", " +
                "\"is_shared\": \""+is_shared+"\", " +
                "\"os_version\": \""+os_version+"\", " +
                "\"device_model\": \""+device_model+"\", " +
                "\"time_zone\": \""+time_zone+"\", " +
                "\"__lng\": \""+__lng+"\", " +
                "\"device_os\": \""+device_os+"\", " +
                "\"outlet_currency\": \""+outlet_currency+"\", " +
                "\"outlet_id\": "+outlet_id+", " +
                "\"encryption_disable_key\": \""+ AppConstants.ENCRYPTION_DISABLE_KEY +"\", " +
                "\"is_family_offer\": \""+is_family_offer+"\"" +
                "}";
        return body;
    }

    public static String Family(String language, String __lat, String Company, String OsPlatform, String appVersion,
                                String deviceKey, String Currency, String lng, String deviceID,
                                String LocationId, String FamilyLimit,
                                String OsVer, String DeviceModel, String TimeZone, String __lng,
                                String lat){
        String bodyData = "{\n" +
                "  \"language\" : \""+language+"\",\n" +
                "  \"__lat\" : \""+__lat+"\",\n" +
                "  \"company\" : \""+Company+"\",\n" +
                "  \"__platform\" : \""+OsPlatform+"\",\n" +
                "  \"app_version\" : \""+appVersion+"\",\n" +
                "  \"device_key\" : \""+deviceKey+"\",\n" +
                "  \"currency\" : \""+Currency+"\",\n" +
                "  \"user_id\" : \""+AppConstants.UserID+"\",\n" +
                "  \"lng\" : \""+lng+"\",\n" +
                "  \"__device_id\" : \""+deviceID+ "\",\n" +
                "  \"session_token\" : \""+AppConstants.sessionID+ "\",\n" +
                "  \"location_id\" : \""+LocationId+"\",\n" +
                "  \"family_limit\" : \""+FamilyLimit+"\",\n" +
                "  \"os_version\" : \""+OsVer+"\",\n" +
                "  \"device_model\" : \""+DeviceModel+"\",\n" +
                "  \"time_zone\" : \""+TimeZone+"\",\n" +
                "  \"__lng\" : \""+__lng+"\",\n" +
                "  \"device_os\" : \""+OsPlatform+"\",\n" +
                "  \"lat\" : \""+lat+"\",\n" +
                "  \"encryption_disable_key\" : \"0af5d6f0-4dd9-498d-8d2c-acf8c80ad9ba\"" +
                "}";

        return bodyData;
    }
}
