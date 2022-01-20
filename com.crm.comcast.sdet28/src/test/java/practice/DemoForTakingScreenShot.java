package practice;

import org.testng.Assert;

import genericUitility.BaseClass;

public class DemoForTakingScreenShot extends BaseClass {
	public void sample() {
		String expectedname = "adarsh";
		String actualName = "Tyss";
		
		Assert.assertEquals(expectedname, actualName);
	}

}
