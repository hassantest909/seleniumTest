package ui.google.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class GoogleTest {
	
	ChromeDriver driver;
	WebDriver driverr;
	
	@BeforeClass
	public void tearUp() {
		//
	}
	@Feature("Google UI")
	@Story("Open Google")
	@Severity(SeverityLevel.MINOR)
	@Test(description="Initiate Chrome Browser")
	public void testFunction() {
		
		 driver = new ChromeDriver();
		 driver.get("https://www.google.com");
		 //driver.findElement(By.id("")).sendKeys(null);
		
	}
	
	@Feature("Google UI")
	@Story("Open Google")
	@Severity(SeverityLevel.MINOR)
	@Test(description="Go to Google search page")
	public void test0Function() {
		
		 driver = new ChromeDriver();
		 driver.get("https://www.google.com");
		 //driver.findElement(By.id("")).sendKeys(null);
		
	}
	
	@Feature("Google UI")
	@Story("Open Google")
	@Severity(SeverityLevel.MINOR)
	@Test(description="Go to Google search page and find Element")
	public void test1Function() {
		
		 driver = new ChromeDriver();
		 driver.get("https://www.google.com");
		 //driver.findElement(By.id("")).sendKeys(null);
		
	}

	@AfterClass
	public void tearDown() {
		//
	}
}
