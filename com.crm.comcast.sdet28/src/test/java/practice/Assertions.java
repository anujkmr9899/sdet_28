package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Assertions {
	@Test
	public void demo() {
		System.out.println("launch the browser");
		System.out.println("login the application");
		System.out.println("create organization");
		
		int a = 10;
		int b = 20; Assert.assertEquals(a, b);
		
		int [] arr = {1,2,3,4,4};
		int [] arr2 = {1,2,3,4};
		
		Assert.assertEquals(arr, arr2);
		
		Assert.assertEquals(false,true);
		
		String expectedName = "Adarshghhgh";String actualName = "Adarsh";
		Assert.assertEquals(expectedName, actualName);//assertion is failed
		
		System.out.println("Logout from application");
		System.out.println("quit the browser");
	}

}
