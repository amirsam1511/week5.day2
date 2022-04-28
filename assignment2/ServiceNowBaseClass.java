package week5.day2.assignment2;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNowBaseClass {
	//Declare ChromeDriver
	public ChromeDriver driver;

	@DataProvider//Call the excelfile readdata
	public String[][] fetchData() throws IOException {
		return ReadExcelFile.readData(); 

	}
	@Parameters({"url","username","password"}) 
	@BeforeMethod
	public void preCondtion(String url, String Uname, String Pwd) {

		//Chrome Driver setup
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		//Get the url and maximize the screen
		driver.get(url);
		driver.manage().window().maximize();
		// Added Implicit Wait of 15 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Added Username and password using id locator
		driver.findElement(By.id("user_name")).sendKeys(Uname);
		driver.findElement(By.id("user_password")).sendKeys(Pwd);
		//Click Submit
		driver.findElement(By.id("sysverb_login")).click();
	}
	@AfterMethod
	public void postCondition() throws InterruptedException {
		Thread.sleep(10000);
		//Close the browser
		driver.close();
	}
}
