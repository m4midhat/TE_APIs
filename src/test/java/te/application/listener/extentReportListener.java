package te.application.listener;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


@Slf4j
public class extentReportListener  implements ITestListener {

    private static final String OUTPUT_FOLDER = "./TestReport/";
    private static String FILE_NAME = "TestExecutionReport";

    private static final ExtentReports extent = init();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ExtentReports extentReports;


    private static ExtentReports init() {

        Path path = Paths.get(OUTPUT_FOLDER);
        // if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                // fail to create directory
                e.printStackTrace();
            }
        }

        String dateTime = LocalDateTime.now().toString();
        log.info(dateTime);
        /*log.info(dateTime.substring(0,4));
        log.info(dateTime.substring(5,7));
        log.info(dateTime.substring(8,10));
        log.info(dateTime.substring(11,13));
        log.info(dateTime.substring(14,16));
        log.info(dateTime.substring(17,19));*/
        FILE_NAME += dateTime.substring(0,4);
        FILE_NAME += dateTime.substring(5,7);
        FILE_NAME += dateTime.substring(8,10);
        FILE_NAME += dateTime.substring(11,13);
        FILE_NAME += dateTime.substring(14,16);
        FILE_NAME += dateTime.substring(17,19);

        extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME+ ".html");
        reporter.config().setDocumentTitle("theEntertainer API Suite");

        reporter.config().setReportName("theEntertainer API Automation Test Results");
        reporter.config().setTheme(Theme.STANDARD);
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("System", "Windows / Mac");
        extentReports.setSystemInfo("Architect", "Midhat Rosull Chughtai");
        extentReports.setSystemInfo("Build#", "1.0");
        extentReports.setSystemInfo("Team", "theEntertainer QA");
        extentReports.setSystemInfo("Project", "B2C APIs");

        return extentReports;
    }

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Test Suite started!");

    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println(("Test Suite is ending!"));
        extent.flush();
        test.remove();
        System.out.println("Review the test report : "+ System.getProperty("user.dir")+ OUTPUT_FOLDER + FILE_NAME+ ".html");
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        log.info(methodName + " started!");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());

        extentTest.assignCategory(result.getTestContext().getSuite().getName());
        /*
         * methodName = StringUtils.capitalize(StringUtils.join(StringUtils.
         * splitByCharacterTypeCamelCase(methodName), StringUtils.SPACE));
         */
        extentTest.assignCategory(className);
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }

    public synchronized void onTestSuccess(ITestResult result) {
        log.info((result.getMethod().getDescription() + " >>> PASSED!"));
        //  MediaEntityBuilder screenshot = MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot());
        test.get().pass("Test passed");
        // test.get().pass(result.getThrowable(), screenshot.build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestFailure(ITestResult result) {
        log.warn("********** "+(result.getMethod().getDescription() + " FAILED! **********"));
        test.get().fail("Test Failed");
        //test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestSkipped(ITestResult result) {
        log.warn("********** "+(result.getMethod().getDescription() + " SKIPPED! ********** "));
        //test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

}