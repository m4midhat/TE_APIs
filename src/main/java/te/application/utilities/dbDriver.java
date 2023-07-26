package te.application.utilities;

import lombok.extern.slf4j.Slf4j;
import te.application.appConstants.AppConstants;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public class dbDriver {

    static String IPAddress="", port="", username="", password="", dbName="";
    static Connection connection;

    public dbDriver(){
        connection = null;
    }

    private static Connection initiateConnection(String envName) throws IOException, SQLException {
        Properties properties = null;
            if (envName.compareToIgnoreCase("uat") == 0) {
                properties = Utils.initProperties("dbUATResources");
                if(properties!=null){
                    IPAddress = Utils.decodeString(properties.getProperty("ipAddress"));
                    port = Utils.decodeString(properties.getProperty("dbPort"));
                    dbName = Utils.decodeString(properties.getProperty("dbName"));
                    username = Utils.decodeString(properties.getProperty("dbUser"));
                    password = Utils.decodeString(properties.getProperty("dbPwd"));
                    String hostURL = "jdbc:mysql://"+IPAddress+":"+port+"/"+dbName+"?useSSL=false";
                    connection = DriverManager.getConnection(hostURL, username, password);
                    System.out.println("Connection successful via ip address");
                }
            }
        return connection;
    }

    private static void closeConnection() throws SQLException {
        if(!connection.isClosed()){
            connection.close();
        }
    }

    public void merchantCategories() throws SQLException, IOException {
        String env = AppConstants.ENV;
        if(env==null){
            env = "uat";
        }
        try {
            connection = initiateConnection(env);
            String query = "SELECT distinct(category) Category FROM entertainer_web.merchant;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String Category = rs.getString("Category");
                System.out.println(Category);
            }
            st.close();
            closeConnection();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public static String getCustomerProductPurchased() throws IOException {
        String productIDs="";
        String env = AppConstants.ENV;
        if(env==null){
            env = "uat";
        }
        String propUserName="";
        Properties properties = Utils.initProperties("AppAuthentication");
        if(properties!=null) {
            propUserName = Utils.decodeString(properties.getProperty("username"));


            try {
                connection = initiateConnection(env);
                String query = "SELECT product_ids FROM consolidation.ent_customer_profile \n" +
                        "inner join entertainer_web.session on entertainer_web.session.customer_id = consolidation.ent_customer_profile.user_id\n" +
                        "where email = \'" + propUserName + "\'\n" +
                        "and session_token = \'"+AppConstants.sessionID+"\';";
                System.out.println("Executing : " + query);
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    productIDs = rs.getString(1);
                }
                st.close();
                closeConnection();
            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        }
        return productIDs;
    }

    public static List<String > getRandomOfferFromDB(){
        List<String> Results = new ArrayList<>();
        String env = AppConstants.ENV;
        if(env==null){
            env = "uat";
        }
        try {
            connection = initiateConnection(env);
            String query = Utils.decodeString("U0VMRUNUIGRpc3RpbmN0ICBmLmlkIE9mZmVySUQgLG10Lm5hbWUgTWVyY2hhbnROYW1lLCBtLnBpbiBNZXJjaGFudFBJTiwgby5sb2NhdGlvbl9pZCBMb2NhdGlvbklELCBvZi5vdXRsZXRfaWQgT3V0bGV0SUQsIHAuaWQgUHJvZHVjdElEIEZST00gZW50ZXJ0YWluZXJfd2ViLm9mZmVyX2VudF9hY3RpdmUgQVMgZgpJTk5FUiBKT0lOIGVudGVydGFpbmVyX3dlYi5vZmZlcl90cmFuc2xhdGlvbl9lbnRfYWN0aXZlIEFTIG9mcl90IE9OIGYuaWQgPSBvZnJfdC5vZmZlcl9pZApJTk5FUiBKT0lOIGVudGVydGFpbmVyX3dlYi5tZXJjaGFudCBBUyBtIE9OIGYubWVyY2hhbnRfaWQgPSBtLmlkCklOTkVSIEpPSU4gZW50ZXJ0YWluZXJfd2ViLm1lcmNoYW50X3RyYW5zbGF0aW9uIEFTIG10IE9OIG10Lm1lcmNoYW50X2lkID0gbS5pZApJTk5FUiBKT0lOIGVudGVydGFpbmVyX3dlYi5vdXRsZXRfb2ZmZXIgQVMgb2YgT04gb2Yub2ZmZXJfaWQgPSBmLmlkCklOTkVSIEpPSU4gZW50ZXJ0YWluZXJfd2ViLm91dGxldCBBUyBvIE9OIG9mLm91dGxldF9pZCA9IG8uaWQKSU5ORVIgSk9JTiBlbnRlcnRhaW5lcl93ZWIub3V0bGV0X3RyYW5zbGF0aW9uIEFTIG90IE9OIG9mLm91dGxldF9pZCA9IG90Lm91dGxldF9pZApJTk5FUiBKT0lOIGVudGVydGFpbmVyX3dlYi5wcm9kdWN0X29mZmVyX2VudF9hY3RpdmUgQVMgcG8gT04gcG8ub2ZmZXJfaWQgPSBmLmlkCklOTkVSIEpPSU4gZW50ZXJ0YWluZXJfd2ViLnByb2R1Y3RfZW50X2FjdGl2ZSBBUyBwIE9OIHAuaWQgPSBwby5wcm9kdWN0X2lkCklOTkVSIEpPSU4gZW50ZXJ0YWluZXJfd2ViLnByb2R1Y3RfdHJhbnNsYXRpb25fZW50X2FjdGl2ZSBvbiBwLmlkPXByb2R1Y3RfdHJhbnNsYXRpb25fZW50X2FjdGl2ZS5wcm9kdWN0X2lkCkxFRlQgSk9JTiBlbnRlcnRhaW5lcl93ZWIub2ZmZXJfYXR0cmlidXRlcyBBUyBvYSBPTiBmLmlkID0gb2Eub2ZmZXJfaWQKV0hFUkUgby5hY3RpdmUgPSAxIEFORCBwLmlzX2VudCA9IDEgQU5EIHAuaXNfZHVtbXlfcHJvZHVjdCA9IDAgQU5EIHAuc2hvd19vZmZlcnNfaWZfcHVyY2hhc2VkID0gMApBTkQgcC5pc19mcmVlbWl1bSA9IDAgQU5EIG9mcl90LmxvY2FsZSA9ICdlbicgQU5EIG10LmxvY2FsZSA9ICdlbicgQU5EIG90LmxvY2FsZSA9ICdlbicKYW5kIHAubG9jYXRpb25faWQ9MSBMaW1pdCAxNTs");
            System.out.println("Executing : "+query);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int randomRowNumber = Utils.generateRandomNumber(0,15);
            System.out.println("Random Row Number : " + randomRowNumber);
            int row = 0;
            while (rs.next()) {
                if(row == randomRowNumber) {
                    Results.add(String.valueOf(rs.getInt("OfferID")));
                    Results.add(rs.getString("MerchantName"));
                    Results.add(rs.getString("MerchantPIN"));
                    Results.add(rs.getString("LocationID"));
                    Results.add(rs.getString("OutletID"));
                    Results.add(rs.getString("ProductID"));
                }
                row++;
            }

            st.close();
            closeConnection();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return Results;
    }


}
