package genericUitility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryimplimentationClass implements IRetryAnalyzer {
	int count = 0;
	int retryLimit=5;
	
	public boolean retry(ITestResult result) {
		if(count<retryLimit) {
			count++;
			return true;
		}
		return false;
	}
	

}
