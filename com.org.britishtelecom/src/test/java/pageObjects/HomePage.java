package pageObjects;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(how=How.ID,using = "sb_form_q")
	private WebElement txtbox_searchBing;

	@FindBy(how=How.NAME, using="q")
	private WebElement txtbox_searchGoogle;

	@FindBy(how=How.XPATH,using="*//li/h2/a")
	private List<WebElement> resultsBing;

	@FindBy(how=How.XPATH,using="//div[@class='yuRUbf']/a") 
	private List<WebElement> resultsGoogle;

	@FindBy(how=How.XPATH, using ="//*[@title='Accept Cookies']")
	private WebElement acceptCookies;

	@FindBy(how=How.XPATH, using="//div[@class='burger']")
	WebElement clickBurger;

	@FindBy(how=How.XPATH, using="//a[@class='services']")
	WebElement serviceLink;

	@FindBy(how=How.XPATH,using="//a[@title='Testing']")
	WebElement testingOption;

	public HomePage setSearch(String arg) throws InterruptedException {
		if(driver.getCurrentUrl().contains("bing")) {
			txtbox_searchBing.sendKeys(arg +Keys.ENTER);
		}
		else {
			txtbox_searchGoogle.sendKeys(arg +Keys.ENTER);
		}
		waitForPageLoad();
		Thread.sleep(2000);
		return this;
	}
	public HomePage selectResult() {
		if(driver.getCurrentUrl().contains("bing")) {
			for(WebElement result:resultsBing) {
				if(result.getAttribute("href").equalsIgnoreCase("https://www.infosys.com/")) {
					result.click();
					break;
				}
			}

		}
		else {
			for(WebElement result:resultsGoogle) {
				if(result.getAttribute("href").equalsIgnoreCase("https://www.infosys.com/")) {
					result.click();
					break;
				}

			}
		}
		return this;
	}
	public HomePage acceptCookies() throws Exception
	{
		waitForPageLoad();
		try{
				acceptCookies.click();
		}
		catch(Exception e){
			
		}
			
		return this;
	}
	private void waitForPageLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));
	}
	public HomePage validateSearchPage() throws Exception {
		selectResult();
		acceptCookies();
		return this;
	}
	public HomePage clickTesting() throws Exception {
		waitForPageLoad();
		clickBurger.click();
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveToElement(serviceLink).build().perform();
		testingOption.click();
		return this;
	}
}