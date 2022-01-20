package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryClass {
	@Test(retryAnalyzer = genericUitility.RetryimplimentationClass.class)
	
	public void retry() {
		Assert.assertEquals(true,false);
	}

}
