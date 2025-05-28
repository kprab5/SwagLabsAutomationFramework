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

public class OrderOverviewPage extends HelperUtility 
{
	public WebDriver driver;
	
	public OrderOverviewPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div/span[contains(@class,'title')]")
	WebElement pageTitleElement;
	
	@FindBy(xpath = "//div/div[@class='summary_subtotal_label']")
	WebElement subTotalAmt;
	
	@FindBy(id = "finish")
	WebElement finishBtn;
	
	public boolean validatePageTitle() 
	{
		return pageTitleElement.isDisplayed();
	}	
	
	public String validateSubtotalAmt()
	{
		String subTotalValue=subTotalAmt.getText();
		return subTotalValue;
	}
	
	public OrderConfirmationPage clickOnFinishbtn()
	{
		finishBtn.click();
		OrderConfirmationPage confirmationPage = new OrderConfirmationPage(driver);
		return confirmationPage;
	}
}
