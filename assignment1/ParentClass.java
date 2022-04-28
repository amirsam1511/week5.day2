package week5.day2.assignment1;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParentClass {
	//Declare ChromeDriver
	public ChromeDriver driver;
	//Declare global variable excelFileName
	public String excelFileName;
	
	@DataProvider
	public String[][] fetchData() throws IOException {
		return ReadExcel.readData(excelFileName); //Read CreateLead,EditLead,DeleteLead,MergeLead & DuplicateLead
		
	}
	
	//Passed static parameters url,username and password
	@Parameters({"url","username","password"}) 
	@BeforeMethod
	public void preCondition(String url, String Uname, String Pwd) {
		//ChromeDriver Setup
		WebDriverManager.chromedriver().setup();
		//Initialize ChromeDriver
		driver = new ChromeDriver();
		//Pass the url of the application
		driver.get(url);
		//Maximize the window
		driver.manage().window().maximize();
		//Added a Implicit wait of 5 secs
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//Get the username using id and pass the value
		driver.findElement(By.id("username")).sendKeys(Uname);
		//Get the password using id and pass the value
		driver.findElement(By.id("password")).sendKeys(Pwd);
		//Click Submit using className
		driver.findElement(By.className("decorativeSubmit")).click();
		//Click CRMSFA link using linkText
		driver.findElement(By.linkText("CRM/SFA")).click();
		//Click Leads link using linkText
		driver.findElement(By.linkText("Leads")).click();
	}
	@AfterMethod
	public void postCondition() {
		//Close the browser
		driver.close();
	}

}
