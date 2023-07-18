package testRail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
public class TestRailClass {

    // Create connection with TesRail and return APIClient object
    public APIClient connectTestRail(String url, String userName, String password) {
        APIClient client = new APIClient(url);
        client.setUser(userName);
        client.setPassword(password);
        log.info("Connecting to TestRail...");
        return client;
    }

    // Receive parametres required and create a test run on test rail
    public int createTestRun(APIClient client, int suiteID, int assignedto_id, String[] testCaseIDs, int projectID,
                             String testRail_Description) throws JsonParseException, JsonMappingException, IOException, Exception {
        // Initialize tesRun
        System.out.println("calling createTestRun...");
        int testRunID = -1;

        // create a jSon string
        String jsonString = "{\"suite_id\": " + suiteID + ",\"name\": \"" + testRail_Description
                + "\",\"assignedto_id\": " + assignedto_id + ",\"include_all\": false,\"case_ids\": "
                + Arrays.toString(testCaseIDs) + "}";

        // Create a map and save jSon String in map
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(jsonString, Map.class);
        // Send API request through client object
        JSONObject obj = (JSONObject) client.sendPost("add_run/" + projectID + "", map);
        System.out.println("testRun is created: Value = " + obj.get("id").toString());
        // Get Test Run ID from Jason response and convert it into int
        testRunID = Integer.parseInt(obj.get("id").toString());
        System.out.println("created testRunID..."+testRunID);
        return testRunID;

    }

    // Receive test rail test case id and return its title from test rail in
    // string
    public String getTestCaseTitle(APIClient client, int testCaseID)
            throws MalformedURLException, IOException, APIException {
        JSONObject obj = (JSONObject) client.sendGet("get_case/" + testCaseID + "");

        return obj.get("title").toString();
    }

    // Update test case on test rail as per status and comments received as a
    // parametre
    public void updateTestCaseStatus(APIClient client, int statusID, String Comment, int testRunID, int testCaseID)
            throws MalformedURLException, IOException, APIException {
        // Create a map and put statusid and comment in it
        Map<String, Comparable> data = new HashMap<String, Comparable>();
        data.put("status_id", statusID);
        data.put("comment", Comment);
        // Send JSon request
        JSONObject jsonObj = (JSONObject) client.sendPost("add_result_for_case/" + testRunID + "/" + testCaseID + "",
                data);
    }

    /////////////////// close test Plan /////////////

    public int closeTestPlan(APIClient client, int testPlanID)
            throws MalformedURLException, IOException, APIException {
        // testRunID=14140;
        // Create a map and put statusid and comment in it
        Map<String, Comparable> data = new HashMap<String, Comparable>();

        // Send JSon request
        JSONObject jsonObj = (JSONObject) client.sendPost("close_plan/" + Integer.toString(testPlanID), data);

        return testPlanID;

    }



    ///////////////////////////////////////////


    // Create a Test Plan
    public int createTestPlan(APIClient client, String testPlanName, String testPlanDescription, int assignedto_id,
                              int porjectID) throws MalformedURLException, IOException, APIException {

        // Create a map and put statusid and comment in it
        Map<String, Comparable> data = new HashMap<String, Comparable>();
        data.put("name", testPlanName);
        data.put("description", testPlanDescription);
        data.put("assignedto_id", assignedto_id);
        // Send JSon request
        JSONObject jsonObj = (JSONObject) client.sendPost("add_plan/" + porjectID + "", data);

        int testPlanID = Integer.parseInt(jsonObj.get("id").toString());
        return testPlanID;
    }

    // Add a TestRun in a Test Plan
    public int createTestRunInTestPlan(APIClient client, int suiteID, int assignedto_id, String[] testCaseIDs,
                                       int projectID, String testRunName, String testRunDescription, int testPlanID)
            throws JsonParseException, JsonMappingException, IOException, Exception {

        // Initialize tesRun
        int testRunID = -1;

        // create a jSon string
        String jsonString = "{\"suite_id\": " + suiteID + ",\"name\": \"" + testRunName + "\",\"assignedto_id\": "
                + assignedto_id + ",\"include_all\": false,\"case_ids\": " + Arrays.toString(testCaseIDs) + "}]}";


        // Create a map and save jSon String in map
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(jsonString, Map.class);
        // Send API request through client object
        JSONObject obj = (JSONObject) client.sendPost("add_plan_entry/" + testPlanID + "", map);

        // Get Test Run ID from Jason response and convert it into int
        JSONArray aObj = (JSONArray) obj.get("runs");
        JSONObject jSonobj = (JSONObject) aObj.get(0);
        testRunID = Integer.parseInt(jSonobj.get("id").toString());

        return testRunID;
    }

    // Send Test Rail Report
    public void sendTestRailReport(APIClient client, int reportID)
            throws MalformedURLException, IOException, APIException {
        JSONObject obj = (JSONObject) client.sendGet("run_report/" + reportID + "");
    }

    public static String decodeString(String PAS)
    {

        byte[] decodeString=Base64.decodeBase64(PAS);
        return (new String(decodeString));

    }

}
