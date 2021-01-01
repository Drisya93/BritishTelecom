package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import static utils.ScreenShotHelper.takeSnapShot;
public class HomePageQA extends BaseTest {

	@DataProvider(name="testData")
	public Object[][] getDataFromDataprovider(){
		return new Object[][] 
				{
			{ "https://www.bing.com/" ,"Infosys - Bing"},
			{ "https://www.Google.com/","Infosys - Google Search" }
				};
	}
	@Test(dataProvider="testData")
	public void testingServiceNavigation(String url,String title) throws Exception {
		driver.get(url);
		String filePath =System.getProperty("user.dir")+"\\test-output\\Screenshots\\";
		HomePage homePage = new HomePage(driver);
		homePage.setSearch("Infosys");
		Assert.assertEquals(driver.getTitle(), title);	
		Assert.assertTrue(driver.getCurrentUrl().contains("q=Infosys"));
		takeSnapShot(driver, filePath+"Bing.png");
		homePage.validateSearchPage();
		Assert.assertEquals(driver.getTitle(), "Infosys - Consulting | IT Services | Digital Transformation","Title validation success");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.infosys.com/");
		takeSnapShot(driver, filePath+"HomePage.png");
		homePage.clickTesting();
		Assert.assertEquals(driver.getTitle(),"Quality Assurance (QA) Consulting Services | Infosys");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.infosys.com/services/validation-solutions.html");
		takeSnapShot(driver, filePath+"TestingPage.png");
	}

}
