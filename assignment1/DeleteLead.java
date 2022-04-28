package week5.day2.assignment1;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteLead extends ParentClass{
	@BeforeClass
	public void setFileName() {
		excelFileName = "DeleteLead";
	}

	@Test(dataProvider = "fetchData")
	public void deleteLead(String phoneNumber) throws InterruptedException {

		//Click Find Leads using linktext
		driver.findElement(By.linkText("Find Leads")).click();
		//Click Phone using xpath
		driver.findElement(By.xpath("//a[contains(@class,'x-tab-right')]/following::span")).click();
		//Given 9 as phone number using name
		driver.findElement(By.name("phoneNumber")).sendKeys(phoneNumber);
		//Click FindLeads using xpath
		driver.findElement(By.xpath("//button[text() = 'Find Leads']")).click();
		//Added a sleep of 3secs
		Thread.sleep(3000);
		//Get the text of First Resulting lead using xpath
		String text = driver.findElement(By.xpath("//td[contains(@class,'first ')]/div/a[1]")).getText();
		//Print the lead
		System.out.println(text);
		//Added a sleep of 3 secs
		Thread.sleep(3000);
		//Click the first resulting lead
		driver.findElement(By.xpath("//td[contains(@class,'first ')]/div/a[1]")).click();
		//Added a sleep of 3 secs
		Thread.sleep(3000);
		//Click Delete using xpath
		driver.findElement(By.xpath("//a[text() = 'Delete']")).click();
		//Click Find Leads using xpath
		driver.findElement(By.xpath("//a[text() = 'Find Leads']")).click();
		//Search the resulting id using name
		driver.findElement(By.name("id")).sendKeys(text);
		//Click Find Leads using xpath
		driver.findElement(By.xpath("//button[text() = 'Find Leads']")).click();
		//Added a sleep of 3 secs
		Thread.sleep(3000);
		//Get the number of records present using xpath
		String recordDeleted = driver.findElement(By.xpath("//div[@class = 'x-paging-info']")).getText();
		//Check if record is deleted using if-else condition
		if(recordDeleted.equals("No records to display")) {
			System.out.println("Record has been deleted");
		}
		else {
			System.out.println("Record has not been deleted");
		}


	}

}
