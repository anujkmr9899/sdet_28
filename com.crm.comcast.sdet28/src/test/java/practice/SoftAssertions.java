package practice;

import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

public class SoftAssertions {
	public void sample() {
		String expectedName = "Adarshghghgh";
		String actualName = "Adarsh";
		
		Reporter.log("this is soft assert class ",true);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(expectedName,actualName);
		
		sa.assertAll();
	}

}
