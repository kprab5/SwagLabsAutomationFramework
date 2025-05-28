package packageForPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import packageForAppUtilites.AppUtilities;
import packageForHelperUtilities.HelperUtility;

public class CustomerInformationPage extends HelperUtility 
{
	public WebDriver driver;
	
	public CustomerInformationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div/span[contains(@class,'title')]")
	WebElement pageTitleElement;
	
	@FindBy(name = "firstName")
	WebElement firstNameElement;
	
	@FindBy(name = "lastName")
	WebElement lastNameElement;
	
	@FindBy(name = "postalCode")
	WebElement postalCodeElement;
	
	@FindBy(name = "continue")
	WebElement continueBtn;
	
	public String validatePageTitle() 
	{
		String pageTitle = pageTitleElement.getText();
		return pageTitle;
	}
	
	public void setPersonalInfo(String fname, String lname, String pcode)
	{
		firstNameElement.sendKeys(fname);
		lastNameElement.sendKeys(lname);
		postalCodeElement.sendKeys(pcode);
	}
	
	public OrderOverviewPage clickOnContinue() 
	{
		continueBtn.click();
		OrderOverviewPage orderPage= new OrderOverviewPage(driver);
		return orderPage;
	}	
}
