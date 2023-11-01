package te.application.utilities;

import te.application.appConstants.AppConstants;

public class generateAPIBodyB2B {
    public static String signIn( String language, String company,
                                 String OSPlatform, String appVersion,String firstName, String lastName,String countryOfResidence,String emailAddress,
                                 String password, String confirmPassword, String gender,String benc, String deviceModel,  String force_login
    ){
        String bodyData = "{\n" +

                "  \"language\" : \""+language+"\",\n" +
                "  \"wlcompany\" : \""+company+ "\",\n" +
                "  \"__platform\" : \""+OSPlatform+"\",\n" +
                "  \"app_version\" : \""+appVersion+"\",\n" +
                "  \"user_id\" : \""+ AppConstants.UserID+"\",\n" +
                "  \"firstname\" : \""+firstName+"\",\n" +
                "  \"lastname\" : \""+lastName+"\",\n" +
                "  \"session_token\"  : \""+ AppConstants.sessionID+"\",\n" +
                "  \"country_of_residence\" : \""+countryOfResidence+"\",\n" +
                "  \"email\" : \""+emailAddress+"\",\n" +
                "  \"password\" : \""+password+"\",\n" +
                "  \"confirm_password\" : \""+confirmPassword+"\",\n" +
                "  \"gender\" : \""+gender+"\",\n" +
                "  \"benc\" : \""+benc+"\",\n" +
                "  \"device_model\" : \""+deviceModel+"\",\n" +
                "  \"force_login\" : \""+force_login+"\"\n" +
                "}";

        return bodyData;
    }

    public static String signUp(String email, String firstName, String lastName, String deviceOs, String deviceKey, String deviceUId,
                                String deviceModel, String platform, String language, String appVersion, String companyCode, String onlyEmail) {

        String bodyData = "{" +
                "    \"email\": \"" + email + "\",\n" +
                "    \"firstname\": \"" + firstName + "\",\n" +
                "    \"lastname\": \"" + lastName + "\",\n" +
                "    \"device_os\": \"" + deviceOs + "\",\n" +
                "    \"device_key\": \"" + deviceKey + "\",\n" +
                "    \"device_uid\": \"" + deviceUId + "\",\n" +
                "    \"device_model\": \"" + deviceModel + "\",\n" +
                "    \"__platform\": \"" + platform + "\",\n" +
                "    \"language\": \"" + language + "\",\n" +
                "    \"app_version\": \"" + appVersion + "\",\n" +
                "    \"company_code\": \"" + companyCode + "\",\n" +
                "    \"only_email\": \"" + onlyEmail + "\"\n" +
                "}";
        return bodyData;
    }


    public static String signIn(String appVersion, String platform, int locationID, String deviceOs, String emailAddress, String deviceKey, String deviceUid,
                                String deviceModel, int forceLogin, String companyCode, String onlyEmail) {
        String bodyData = "{\n" +
                "  \"app_version\" : \"" + appVersion + "\",\n" +
                "  \"__platform\" : \"" + platform + "\",\n" +
                "  \"location_id\" : \"" + locationID + "\",\n" +
                "  \"device_os\" : \"" + deviceOs + "\",\n" +
                "  \"email\" : \"" + emailAddress + "\",\n" +
                "  \"device_key\" : \"" + deviceKey + "\",\n" +
                "  \"device_uid\" : \"" + deviceUid + "\",\n" +
                "  \"device_model\" : \"" + deviceModel + "\",\n" +
                "  \"force_login\" : \"" + forceLogin + "\",\n" +
                "  \"company_code\" : \"" + companyCode + "\",\n" +
                "    \"only_email\": \"" + onlyEmail + "\"\n" +
                "}";

        return bodyData;
    }


    public static String signUp(String firstName, String lastName, String email, String confirmEmail, String password, String confirmPasswrod,
                                String countryOfResidence,String appVersion, String language, String wlCompany, String deviceModel, String benc,  String OSPlatform) {
        String bodyData = "{\n" +
                "  \"confirm_password\" : \""+confirmPasswrod+"\",\n" +
                " \"lastname\" : \""+lastName+"\",\n" +
                "  \"language\" : \""+language+"\",\n" +
                " \"wlcompany\" : \""+wlCompany+ "\",\n" +
                "  \"app_version\" : \""+appVersion+"\",\n" +
                "  \"firstname\" : \""+firstName+"\",\n" +
                "  \"email\" : \""+email+"\",\n" +
                "  \"confirm_email\" : \""+confirmEmail+"\",\n" +
                "  \"country_of_residence\" : \""+countryOfResidence+"\",\n" +
                "  \"benc\" : \""+benc+"\",\n" +
                "  \"user_id\" : \""+ AppConstants.UserID+"\",\n" +
                "  \"session_token\"  : \""+ AppConstants.sessionID+"\",\n" +
                "  \"device_model\" : \""+deviceModel+"\",\n" +
                "  \"__platform\" : \""+OSPlatform+"\",\n" +
                  "  \"password\" : \""+password+"\"\n" +
                "}";

        return bodyData;
    }

}
