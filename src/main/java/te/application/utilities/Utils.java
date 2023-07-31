package te.application.utilities;


import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import te.application.appConstants.AppConstants;

import java.io.*;
import java.util.*;

@Slf4j
public class Utils {

    public static Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public static int generateRandomNumber(int min, int max){
        return (int) (Math.random()*(max-min+1)+min);
    }

    public static String generateRandomEmail() {
        String emailAddress = "";
        while (emailAddress.length() < 25) {
            String[] domains = {".com", ".co", ".org", ".pk", ".com.uk", ".com.ae", ".ae"};
            emailAddress += getAlphaNumericString(generateRandomNumber(10,30));
            emailAddress += Integer.valueOf((int) (Math.random() * 99)).toString();
            emailAddress += "@" +getAlphaNumericString(generateRandomNumber(5,10))+domains[generateRandomNumber(0, domains.length-1)];
        }
        return emailAddress.toLowerCase();
    }

    public static String get3CharactersForPassword(){
        String capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smalls = "abcdefghijklmnopqrstuvxyz";
        String characters = "+_)(*&^%$#@!~)`!-=?:><,./:[]";
        String integers = "1234567890";
        char c = capitals.charAt(Utils.generateRandomNumber(0, capitals.length()-1));
        char s = smalls.charAt(Utils.generateRandomNumber(0, smalls.length()-1));
        char sp = characters.charAt(Utils.generateRandomNumber(0, characters.length()-1));
        char i = integers.charAt(Utils.generateRandomNumber(0, integers.length()-1));

        String pwd = new StringBuilder().append(c).append(s).append(sp).append(i).toString();
        System.out.println(pwd);
        return pwd;
    }

     public static String getAlphaNumericString(int n) {

        // choose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }


    //eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb21wYW55IjoiZW50ZXJ0YWluZXIiLCJhcGlfdG9rZW4iOiIyNGZlOThiNC05ZjFjLTRjYjgtOGFhNC0zMWY1YWMwNmMyNmIiLCJzZXNzaW9uX3Rva2VuIjoiNzFjZDczNjgtNmYxMy00YzVlLThjZGQtYjkzMjIxMzdlY2ExIn0.qdinslzvXb79GBZObPSH8rG4cL8utB_jsMFMsQhkSio
    public static String generateInvalidAuthToken(){
        String company, apiToken, session;
        company = getAlphaNumericString(36);
        apiToken = getAlphaNumericString(175);
        session = getAlphaNumericString(43);
        return company+'.'+apiToken+'.'+session;
    }


    public static String decodeString(String str)
    {
        byte[] decodeString= Base64.getDecoder().decode(str);
        return (new String(decodeString));
    }

    public static String encodeString(String str){
        String encodedString = Base64.getEncoder().encodeToString(str.getBytes());
        //System.out.println("Encoded String : "+encodedString);
        return encodedString;
    }

    public static Properties initProperties(String fileName) throws IOException, FileNotFoundException {
        Properties properties;
        FileInputStream inputStream = null;
        if(fileName.compareToIgnoreCase("dbUATResources")==0){
            inputStream = new FileInputStream("./src/main/resources/dbUATResources.properties");
        }
        else
        if(fileName.compareToIgnoreCase("AppAuthentication")==0){
            inputStream = new FileInputStream("./src/main/resources/AppAuthentication.properties");
        }
        properties = new Properties();
        properties.load(inputStream);

        return properties;
    }


    public static String replaceString(String input, String replacement, String previous, String next) {

        String pr;
        String nx;

        int sLength;
        if (previous.length() == 0) {
            pr = input.substring(0, input.indexOf(previous));
        } else {
            sLength = previous.length() + 1;
            pr = input.substring(0, input.indexOf(previous) + sLength);
        }

        if (next.length() == 0) {
            nx = input.substring(input.lastIndexOf(next));
        } else {
            nx = input.substring(input.indexOf(next) - 1);
        }

        log.info("Original String : "+input);
        log.info("After replacing placeholder: " +pr + replacement + nx);
        return pr + replacement + nx;
    }

    public static String countryCode(String countryName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        String countryCode = "";
        File file = new File("./src/test/java/te/application/data/countryCodes.json");
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));
        if(!jsonObject.isEmpty()){
            countryCode = jsonObject.get(countryName).toString();
        }
        return countryCode;
    }

    public static JSONObject readTestData() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        File file = new File("./src/test/java/te/application/data/testData.json");
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));
        //if(!jsonObject.isEmpty()){
            //JSONArray lang = (JSONArray) jsonObject.get("language");
            //System.out.println(lang.get(generateRandomNumber(0, lang.size() - 1)).toString());
        //}

        return jsonObject;
    }

    public static String getRandomSupportedLanguage(JSONObject jsonObject){
        String randomLanguage = "";
        if(!jsonObject.isEmpty()){
            JSONArray lang = (JSONArray) jsonObject.get("language");
            randomLanguage = lang.get(generateRandomNumber(0, lang.size() - 1)).toString();
            System.out.println("Random language selected from file : "+randomLanguage);
        }
        return randomLanguage;
    }

    public static String getRandomOS(JSONObject jsonObject){
        String operatingSystem = "";
        if(!jsonObject.isEmpty()){
            JSONArray os = (JSONArray) jsonObject.get("OS");
            operatingSystem = os.get(generateRandomNumber(0, os.size() - 1)).toString();
            System.out.println("Random operating system selected from file : "+operatingSystem);
        }
        return operatingSystem;
    }

    public static String getRandomDeviceOS(JSONObject jsonObject){
        String operatingSystem = "";
        JSONArray deviceOS;
        if(!jsonObject.isEmpty()){
            if(AppConstants.testDataOSPlatform.compareToIgnoreCase("iOS")==0) {
                deviceOS = (JSONArray) jsonObject.get("iOS");
                operatingSystem = deviceOS.get(generateRandomNumber(0, deviceOS.size() - 1)).toString();
            }
            else if(AppConstants.testDataOSPlatform.compareToIgnoreCase("android")==0) {
                deviceOS = (JSONArray) jsonObject.get("android");
                operatingSystem = deviceOS.get(generateRandomNumber(0, deviceOS.size() - 1)).toString();
            }
            System.out.println("Random device OS selected from file : "+operatingSystem);
        }
        return operatingSystem;
    }

    public static String getRandomDevice(JSONObject jsonObject){
        String device = "";
        JSONArray devices;
        if(!jsonObject.isEmpty()){
            if(AppConstants.testDataOSPlatform.compareToIgnoreCase("iOS")==0) {
                devices = (JSONArray) jsonObject.get("iOS Device");
                device = devices.get(generateRandomNumber(0, devices.size() - 1)).toString();
            }
            else if(AppConstants.testDataOSPlatform.compareToIgnoreCase("android")==0) {
                devices = (JSONArray) jsonObject.get("Android Device");
                device = devices.get(generateRandomNumber(0, devices.size() - 1)).toString();
            }
            System.out.println("Random device OS selected from file : "+device);
        }

        return device;
    }

    public static String getRandomTimeZone(JSONObject jsonObject){
        String timeZoneSelected = "";
        if(!jsonObject.isEmpty()){
            JSONArray timeZone = (JSONArray) jsonObject.get("Time Zone");
            timeZoneSelected = timeZone.get(generateRandomNumber(0, timeZone.size() - 1)).toString();
            System.out.println("Random time zone selected from file : "+timeZoneSelected);
        }
        return timeZoneSelected;
    }

    public static String getRandomCurrency(JSONObject jsonObject){
        String currencySelected = "";
        if(!jsonObject.isEmpty()){
            JSONArray Currency = (JSONArray) jsonObject.get("Currency");
            currencySelected = Currency.get(generateRandomNumber(0, Currency.size() - 1)).toString();
            System.out.println("Random Currency selected from file : "+currencySelected);
        }
        return currencySelected;
    }

    public static String getRandomAppVersion(JSONObject jsonObject){
        String appVersion = "";
        if(!jsonObject.isEmpty()){
            JSONArray versions = (JSONArray) jsonObject.get("AppVersion");
            appVersion = versions.get(generateRandomNumber(0, versions.size() - 1)).toString();
            System.out.println("Random appVersion selected from file : "+appVersion);
        }
        return appVersion;
    }

}
