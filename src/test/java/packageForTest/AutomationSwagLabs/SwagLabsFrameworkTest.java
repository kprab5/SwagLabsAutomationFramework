package packageForTest.AutomationSwagLabs;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.logging.FileHandler;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import packageForAppUtilites.AppUtilities;
import packageForBaseTest.BaseTest;
import packageForPages.CartPage;
import packageForPages.CustomerInformationPage;
import packageForPages.HomePage;
import packageForPages.OrderConfirmationPage;
import packageForPages.OrderOverviewPage;
import packageForPages.ProductsPage;

public class SwagLabsFrameworkTest extends BaseTest
{
	@Test(groups = "Smoketest")
	public void purchaseSauceLabs()  throws Exception
	{
		HomePage homePage = new HomePage(driver);
		
		String Title = homePage.getTitle();
		Assert.assertEquals(Title, "Swag Labs", "Incorrect Page Loaded");
		homePage.setLoginCreds();
		ProductsPage productsPage = homePage.clickOnLogin();
		
		Assert.assertTrue(productsPage.isCartDisplayed(),"User is not logged in");
		AppUtilities.appUtil();
		productsPage.applySortUsingValue("hilo");
		productsPage.clickOnProductName("Sauce Labs Bike Light");
		productsPage.clickOnAddToCardBtn();
		productsPage.clickOnBackToProducts();
		productsPage.applySortUsingIndex(1);
		productsPage.clickOnProductName("Sauce Labs Onesie");
		productsPage.clickOnAddToCardBtn();
		
		CartPage cartPage = productsPage.clickOnGoToCart();
		System.out.println(cartPage.validateProductName());
		Assert.assertEquals(cartPage.validateProductName(), "Sauce Labs Bike Light", "Products do not Match");
		CustomerInformationPage custInfoPage = cartPage.clickOnCheckout();
		
		Assert.assertEquals(custInfoPage.validatePageTitle(),"Checkout: Your Information", "Invalid Page");
		custInfoPage.setPersonalInfo("Tom", "Cruise", "400022");
		OrderOverviewPage orderPage = custInfoPage.clickOnContinue();
		
		Assert.assertTrue(orderPage.validatePageTitle(), "Overview Page not displayed");
		Assert.assertEquals(orderPage.validateSubtotalAmt(), "Item total: $17.98", "Incorrect Subtotal Amount");
		OrderConfirmationPage confirmationPage = orderPage.clickOnFinishbtn();
		
		Assert.assertEquals(confirmationPage.verifyOrderMsg(), "Thank you for your order!", "Order not placed successfully");
		getScreenshot("Success Screenshot");
		confirmationPage.clickOnBackHome();
		
		Assert.assertTrue(productsPage.verifyProductsElement(),"User is not re-directed to Products Page!");		
	}
	
	@Test(groups = "Smoketest")
	public void checkLogin()
	{
		HomePage homepage = new HomePage(driver);
		homepage.setLoginCreds();
		homepage.clickOnLogin();
	}
	
	@Test(groups = "Smoketest")
	public void regressionTest() throws InterruptedException
	{
		HomePage homePage = new HomePage(driver);
		homePage.setLoginCreds();
		homePage.clickOnLogin();
		
		ProductsPage productsPage= new ProductsPage(driver);
		Assert.assertTrue(productsPage.isCartDisplayed(), "Login Failed");
		productsPage.clickOnLogout();
	}

}
