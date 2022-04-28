package week5.day2.assignment1;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CreateLead extends ParentClass{
	@BeforeClass
	public void setFileName() {
		excelFileName = "CreateLead";//Created setFileName dynamically using excel
	}
	
	@Test(dataProvider = "fetchData")
	public void createLead(String cName,String fName,String lName){
		//Click Create using xpath
		driver.findElement(By.xpath("//a[contains(text() ,'Create')]")).click();
		//Given CompanyName as dynamic using xpath
		driver.findElement(By.xpath("//input[contains(@id,'company') ]")).sendKeys(cName);
		//Given firstName as dynamic using xpath
		driver.findElement(By.xpath("//input[contains(@id,'firstName') ]")).sendKeys(fName);
		//Given lastName as dynamic using xpath
		driver.findElement(By.xpath("//input[contains(@id,'lastName') ]")).sendKeys(lName);
		//Click Submit
		driver.findElement(By.xpath("//input[contains(@class,'Submit') ]")).click();

	}
}