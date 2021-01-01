package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import utils.BrowserManager;

public class BaseTest {
	static WebDriver driver;
	@BeforeClass
	public void beforeClass() throws IOException {
		FileReader file=new FileReader(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\properties.properties"));
		Properties properties=new Properties();
		properties.load(file);
		driver = BrowserManager.getDriver(properties.getProperty("browser"));
	}
	@AfterClass
	public void afterClass() {
	//	driver.quit();
	}
}

