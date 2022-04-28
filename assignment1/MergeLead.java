package week5.day2.assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MergeLead extends ParentClass{
	@BeforeClass
	public void setFileName() {
		excelFileName = "MergeLead";
	}

	@Test(dataProvider = "fetchData")
	public void mergeLead(String fromLeadFirstName, String toLeadFirstName) throws InterruptedException {
		//Click Merge Leads using linktext
		driver.findElement(By.linkText("Merge Leads")).click();
		//click the img icon in From using xpath
		driver.findElement(By.xpath("//img[@alt='Lookup']")).click();
		//Get all the window handles using Set
		Set<String> allWindows = driver.getWindowHandles();
		//Copy the window handles in a ArrayList
		List<String> allhandles = new ArrayList<String>(allWindows);
		//Switch to first window
		driver.switchTo().window(allhandles.get(1));
		//Given the name as dynamic using xpath
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(fromLeadFirstName);
		//Click Find Leads using xpath
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		//Added a sleep of 1 secs
		Thread.sleep(1000);
		//Get the value of leadId using xpath
		String leadID = driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).getText();
		//Click the first resulting Id
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		// Switch to parent window handle
		driver.switchTo().window(allhandles.get(0));
		//click the img icon in TO using xpath
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		//Get all the window handles using Set
		Set<String> allWindows2 = driver.getWindowHandles();
		//Copy the window handles in a ArrayList
		List<String> allhandles2 = new ArrayList<String>(allWindows2);
		//Switch to first window
		driver.switchTo().window(allhandles2.get(1));
		//Given the name as dynamic using xpath
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(toLeadFirstName);
		//Click Find Leads using xpath
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		//Added a sleep of 1 secs
		Thread.sleep(1000);
		//Get the first resulting Id using xpath
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		//Switch to parent window
		driver.switchTo().window(allhandles2.get(0));
		//Click Merge using xpath
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		//Switch to Alerts
		driver.switchTo().alert().accept();
		//Click Find Leads using link text
		driver.findElement(By.linkText("Find Leads")).click();
		//Give the leadId using xpath
		driver.findElement(By.xpath("//input[@name='id']")).sendKeys(leadID);
		//Click Find Leads using xpath
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		//Get the number of records present using xpath
		String text = driver.findElement(By.className("x-paging-info")).getText();
		//Check the merged record using if-else condition
		if (text.equals("No records to display")) {
			System.out.println("Text matched");
		} else {
			System.out.println("Text not matched");
		}
	}

}
