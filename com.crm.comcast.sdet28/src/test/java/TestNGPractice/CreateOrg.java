package TestNGPractice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateOrg {
	@BeforeClass
	public void bc() {
		System.out.println("laucnch the browser");
		
	}
	@AfterClass
	public void ac() {
		System.out.println("quit the browser");
	}
	@BeforeMethod
	public void bm() {
		System.out.println("login the application");
		
	}
	@AfterMethod
	public void af() {
		System.out.println("logout the application");
		
	}
	@Test(priority = 1)
	public void org() {
		System.out.println("navigate to organization");
		System.out.println("create organization");
		System.out.println("verify organization");
	}
	@Test(priority = 2)
	public void contact() {
		System.out.println("navigate to contact");
		System.out.println("create contact");
		System.out.println("verify contact");
	}
	@Test(priority = 3)
	public void product() {
		System.out.println("navigate to product");
		System.out.println("create product");
		System.out.println("verify product");
	}

}
