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

public class CartPage extends HelperUtility 
{
	public WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//div[@class='cart_item']//div[@class='inventory_item_name'])[1]")
	WebElement cartProductName;
	
	@FindBy(id = "checkout")
	WebElement checkoutBtn;
	
	public String validateProductName()
	{
		String productNameString = cartProductName.getText();
		return productNameString;
	}
	
	public CustomerInformationPage clickOnCheckout()
	{
		checkoutBtn.click();
		CustomerInformationPage custInfoPage = new CustomerInformationPage(driver);
		return custInfoPage;
	}

	//driver.findElement(By.id("checkout")).click();
	
}
