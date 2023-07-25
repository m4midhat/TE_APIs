package te.application.utilities;


import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

}
