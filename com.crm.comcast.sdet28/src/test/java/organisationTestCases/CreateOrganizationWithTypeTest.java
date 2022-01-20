package organisationTestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithTypeTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		// java object representation physical property file
		FileInputStream fis = new FileInputStream("./src/test/resources/data.properties");
		// create the object from property class
		Properties property = new Properties();
		//load the key and value from property object
		property.load(fis);
		//fetch the value from respective keys
		String browser = property.getProperty("browser");
		String url = property.getProperty("url");
		String username = property.getProperty("username");
		String password = property.getProperty("password");
		
		FileInputStream fis_e = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis_e);
		Sheet sh1 = wb.getSheet("sheet1");
		Row row = sh1.getRow(1);
		Cell cell = row.getCell(2);
		String orgName = cell.getStringCellValue(); 
		Row row2 = sh1.getRow(4);
		Cell cell2 = row2.getCell(2);
		String type = cell2.getStringCellValue();
		
		WebDriver driver = null;
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","./src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else {
			System.out.println("browser is not supported");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("(//img[@alt='Create Organization...'])")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		WebElement ele1 = driver.findElement(By.name("accounttype"));
		
		Select s = new Select(ele1);
		s.selectByVisibleText(type);
		driver.findElement(By.name("button")).click();
		
		Thread.sleep(3000);
		//organization verification
		String actualorganizationName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (actualorganizationName.contains("TestYantraSoftware1")){
			System.out.println("Organization is created");
		}
		else {
			System.out.println("organization is not created");
		
		}
		  WebElement profileImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		  Actions action = new Actions(driver);
		  action.moveToElement(profileImg).perform();
		  driver.findElement(By.linkText("Sign Out")).click();
		  driver.quit();
		  

	}

}
