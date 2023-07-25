package te.application.Analyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import te.application.appConstants.AppConstants;

public class RetryAnalyzer  implements IRetryAnalyzer {

    public int retryCount =0;


    public int maxRetry = AppConstants.numberOfRetries;

    @Override
    public boolean retry(ITestResult iTestResult) {
        retryCount++;
        if(retryCount<maxRetry){
            return true;
        }
        return false;
    }


}