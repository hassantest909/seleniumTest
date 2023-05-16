package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleTest {
	
	ChromeDriver driver;
	WebDriver driverr;
	
	@BeforeClass
	public void tearUp() {
		//
	}
	
	@Test
	public void testFunction() {
		
		 driver = new ChromeDriver();
		 driver.get("https://www.google.com");
		 //driver.findElement(By.id("")).sendKeys(null);
		
	}

	@AfterClass
	public void tearDown() {
		//
	}
}
