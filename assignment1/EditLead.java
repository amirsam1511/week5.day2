package week5.day2.assignment1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EditLead extends ParentClass{
	@BeforeClass
	public void setFileName() {
		excelFileName = "EditLead";//Created setFileName dynamically using excel
	}

	@Test(dataProvider = "fetchData")
	public void editLead(String phoneNumber, String companyName) throws InterruptedException {
		//Click Find Leads using linktext
		driver.findElement(By.linkText("Find Leads")).click();
		//Click Phone using xpath
		driver.findElement(By.xpath("//span[text()='Phone']")).click();
		//Given dynamic phone number using xpath
		driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys(phoneNumber);
		//Click Find Leads using xpath
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		//Added a sleep of 2 secs
		Thread.sleep(2000);
		//Click the First Resulting Id
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		//Click Edit using linkText
		driver.findElement(By.linkText("Edit")).click();
		//Get the company Name using Id locator
		WebElement updateCompanyName = driver.findElement(By.id("updateLeadForm_companyName"));
		//Clear the existing value
		updateCompanyName.clear();
		//Given value as Updated Company Name
		updateCompanyName.sendKeys(companyName);
		//Click submit button using name
		driver.findElement(By.name("submitButton")).click();

	}
}
