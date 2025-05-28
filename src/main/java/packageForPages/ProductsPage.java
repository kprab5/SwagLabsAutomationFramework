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

import net.bytebuddy.asm.MemberSubstitution.FieldValue;
import packageForAppUtilites.AppUtilities;
import packageForHelperUtilities.HelperUtility;

public class ProductsPage extends HelperUtility 
{
	public WebDriver driver;
	
	public ProductsPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[contains(@class,'shopping_cart_container')]")
	WebElement cartElement;
	
	@FindBy(xpath = "//span/select[@class='product_sort_container']")
	WebElement sortOptions;
	
	@FindBy(xpath = "//div[contains(@class,'inventory_list')]/div[@class='inventory_item']")
	List<WebElement> shoppingElements;
	
	By productNameBy = By.xpath("./descendant::div[(@class='inventory_item_name ')]");
	
	@FindBy(id = "add-to-cart")
	WebElement addToCartBtn;
	
	@FindBy(css = "[name='back-to-products']")
	WebElement backToProductsBtn;
	
	@FindBy(xpath = "//div/a[@class='shopping_cart_link']")
	WebElement goToCart;
	
	@FindBy(xpath = "//div/span[@class='title']")
	WebElement productElement;

	public boolean isCartDisplayed() throws InterruptedException
	{
		return cartElement.isDisplayed();
	}
	
	public void applySortUsingValue(String option)
	{
		moveToElement(sortOptions);
		sortOptions.click();
		selectUsingValue(sortOptions, option);
	}
	
	public void applySortUsingIndex(int value)
	{
		moveToElement(sortOptions);
		sortOptions.click();
		selectUsingIndex(sortOptions, value);
	}
	
	public void clickOnProductName(String prodName)
	{
		for(int i=0; i<shoppingElements.size();i++)
			{
				String productName = shoppingElements.get(i).findElement(productNameBy).getText();
				if(productName.equalsIgnoreCase(prodName))
					{
						WebElement productNameElement= shoppingElements.get(i).findElement(productNameBy);
						//wait.until(ExpectedConditions.visibilityOf(productNameElement));
						productNameElement.click();
						break;
					}
			}
	}
	
	public void clickOnAddToCardBtn()
	{
		addToCartBtn.click();
	}
	
	public void clickOnBackToProducts()
	{
		backToProductsBtn.click();
	}
	
	public CartPage clickOnGoToCart()
	{
		goToCart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public boolean verifyProductsElement()
	{
		return productElement.isDisplayed();
	}
	
	@FindBy(xpath = "//div/button[@id='react-burger-menu-btn']")
	WebElement menuBtn;
	
	@FindBy(xpath = "//div/nav/a[@id='logout_sidebar_link']")
	WebElement logoutBtn;
	
	public void clickOnLogout()
	{
		menuBtn.click();
		logoutBtn.click();
	}
}
