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

public class OrderConfirmationPage extends HelperUtility 
{
	public WebDriver driver;
	
	public OrderConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div/h2[@class='complete-header']")
	WebElement orderMsgElement;
	
	@FindBy(xpath = "//div/button[@data-test='back-to-products']")
	WebElement backHomeBtn;
	
	public String verifyOrderMsg() 
	{
		String orderMsgString = orderMsgElement.getText();
		return orderMsgString;
	}
	
	public void clickOnBackHome()
	{
		backHomeBtn.click();
	}
}
