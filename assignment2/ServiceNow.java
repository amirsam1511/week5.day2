package week5.day2.assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.sukgu.Shadow;

public class ServiceNow extends ServiceNowBaseClass{
	
	
	@Test(dataProvider = "fetchData")
	public void serviceNow(String description) throws InterruptedException {
		//Added a sleep of 10secs
		Thread.sleep(10000);
		//Create object for shadow as the dom has shadow root
		Shadow shadow = new Shadow(driver);
		//Click All Filter menu
		WebElement selectFilter = shadow.findElementByXPath("//div[text() = 'All']");
		//Create a object for actions class
		Actions builder = new Actions(driver);
		//Click Search Filter navigation
		builder.moveToElement(selectFilter).click().perform();
		//Added a sleep of 10secs
		Thread.sleep(10000);
		//Send Value as incident in Filter navigation
		shadow.findElementByXPath("//input[@id = 'filter']").sendKeys("incident");
		//Added a sleep of 10secs
		Thread.sleep(10000);
		//Select the Filter
		selectFilter.click();
		//Click All button again
		WebElement selectAll = shadow.findElementByXPath("//span[text() = 'All']");
		//Selected All
		builder.moveToElement(selectAll).click().perform();
		//Added a sleep of 3secs
		Thread.sleep(3000);
		//Get the Frame using shadow xpath
		WebElement frame = shadow.findElementByXPath("//iframe[@id='gsft_main']");
		//Switch to next frame using shadow
		driver.switchTo().frame(frame);
		//Added a sleep of 3secs
		Thread.sleep(3000);
		//Click New Button
		driver.findElement(By.id("sysverb_new")).click();
		//Added a sleep of 3secs
		Thread.sleep(3000);
		//Click the search icon of Caller
		driver.findElement(By.xpath("//span[contains(@class,'icon-search')]")).click();
		//Get the window handles using Set
		Set<String> windowHandles = driver.getWindowHandles();
		//Assign it to Array List
		List<String> listHandles = new ArrayList<String>(windowHandles);
		//Switch to next window handle
		driver.switchTo().window(listHandles.get(1));
		//Select the first resulting id
		driver.findElement(By.xpath("//table[@id='sys_user_table']//tr[1]/td[3]/a")).click();
		//Switch back to main window
		driver.switchTo().window(listHandles.get(0));
		//Again Switch to Frame
		driver.switchTo().frame(frame);
		//Added a sleep of 10secs
		Thread.sleep(3000);
		//Entered values into short description
		driver.findElement(By.id("incident.short_description")).sendKeys(description);
		//Get the value of incident No
		String incidentNo = driver.findElement(By.id("incident.number")).getAttribute("value");
		//Print it for reference
		System.out.println("Incident No is : " + incidentNo);
		//Select the Submit button
		driver.findElement(By.id("sysverb_insert")).click();
		//Get the value of Search using xpath
		WebElement searchIncident = driver.findElement(By.xpath("//input[@placeholder = 'Search']"));
		//Send values as incident no which we got while creating
		searchIncident.sendKeys(incidentNo);
		//Click Enter
		searchIncident.sendKeys(Keys.ENTER);
		//Get the resulting Incident No
		String resultIncident = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		//Print it for reference
		System.out.println(resultIncident);
		//Verify if incident is created successfully by comparing the incident No
		if(incidentNo.equals(resultIncident)) {
			System.out.println("Incident has been created");
		}
		else {
			System.out.println("Incident has not been created");
		}
	}

}
