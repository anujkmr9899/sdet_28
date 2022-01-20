package organisationTestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
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

public class CreateOrganizationWithMembersOfAndClearTest {
	public static void main(String[] args) throws IOException, InterruptedException    {
		//get the java representation object of the physical file
		FileInputStream fis = new FileInputStream("./src/test/resources/data.properties");
		//create an object to property class to load all the keys
		Properties property = new Properties();
		property.load(fis);
		
		//read the value from get property("key")\
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
		
		//handle child browser 
		String firstwindow = driver.getWindowHandle();
		Thread.sleep(3000);
		
		String title="Accounts";
		driver.findElement(By.xpath("//img[@title='Select']")).click();
		
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		
		while(itr.hasNext()) {
			 String window = itr.next();
			 driver.switchTo().window(window);
			 String currenttitle=driver.getTitle();
			 if(currenttitle.contains(title))
			 {
				 break;
			 }
		}
		
		driver.findElement(By.id("search_txt")).sendKeys("Testyantra1");
		driver.findElement(By.name("search")).click();
		
		driver.findElement(By.linkText("Testyantra1")).click();
		driver.switchTo().alert().accept();
		Set<String> windows1 = driver.getWindowHandles();
		Iterator<String> itr1 = windows1.iterator();
		
		
		String back="EditView";
		while(itr1.hasNext()) {
			 String window = itr1.next();
			 driver.switchTo().window(window);
			 String currenttitle=driver.getTitle();
			 if(currenttitle.contains(back))
			 {
				 System.out.println("editView");
				 break;
				
			 }
			
		}
		
		
		
		//organization verification
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@title='Clear']")).click();
		
		  driver.findElement(By.name("button")).click();
		  WebElement profileImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		  Actions action = new Actions(driver);
		  action.moveToElement(profileImg).perform();
		  driver.findElement(By.xpath("//a[@class='drop_down_usersettings']")).click();
		  driver.quit();
		
		
		
	}

}
