package packageForPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[name='user-name']")
	WebElement usernameElement;
	
	@FindBy(css = "[name='password']")
	WebElement passwordElement;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginBtn;
	
	public String getTitle()
	{
		String pageTitle = driver.getTitle();
		return pageTitle;
	}
	
	public void setLoginCreds()
	{
		usernameElement.sendKeys("standard_user");
		passwordElement.sendKeys("secret_sauce");
	}
	
	public ProductsPage clickOnLogin()
	{
		loginBtn.click();
		ProductsPage productsPage=new ProductsPage(driver);
		return productsPage;
	}
}
