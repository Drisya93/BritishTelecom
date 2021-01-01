package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserManager {
	static WebDriver driver;
	public static WebDriver getDriver(String type) {
		
		if(type.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();	
		}
		else if(type.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
		    driver  = new FirefoxDriver();
		}
		else {
			Assert.assertTrue(false,"No browsertype found");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

}
