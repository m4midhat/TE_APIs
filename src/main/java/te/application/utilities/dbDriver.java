package te.application.utilities;

import te.application.appConstants.AppConstants;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class dbDriver {

    static String IPAddress="", port="", username="", password="", dbName="";
    Connection connection;

    public dbDriver(){
        connection = null;
    }

    private Connection initiateConnection(String envName) throws IOException, SQLException {
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

    private void closeConnection() throws SQLException {
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



}
