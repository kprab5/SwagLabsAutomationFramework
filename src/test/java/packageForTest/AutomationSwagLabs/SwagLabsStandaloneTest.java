package packageForTest.AutomationSwagLabs;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SwagLabsStandaloneTest 
{
	@Test
	public void purchaseSauceLabs() throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
		
		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle, "Swag Labs", "Incorrect Page Loaded");
		
		driver.findElement(By.cssSelector("[name='user-name']")).sendKeys("standard_user");
		driver.findElement(By.cssSelector("[name='password']")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		WebElement cartElement = driver.findElement(By.xpath("//div[contains(@class,'shopping_cart_container')]"));
		Assert.assertTrue(cartElement.isDisplayed(),"User is not logged in");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//		System.out.println("Popup text :" +alert.getText());
//		alert.accept();
//		
		Thread.sleep(4000);
		
		WebElement sortOptions = driver.findElement(By.xpath("//span/select[@class='product_sort_container']"));
		Actions action = new Actions(driver);
		action.moveToElement(sortOptions).build().perform();
		sortOptions.click();
		
		Select select = new Select(sortOptions);
		select.selectByValue("hilo");
		
		
		List<WebElement> shoppingElements = driver.findElements(By.xpath("//div[contains(@class,'inventory_list')]/div[@class='inventory_item']"));
		
		System.out.println(shoppingElements.size());
		
		for(int i=0; i<shoppingElements.size();i++)
		{
			String productName = shoppingElements.get(i).findElement(By.xpath("./descendant::div[(@class='inventory_item_name ')]")).getText();
			if(productName.equalsIgnoreCase("Sauce Labs Bike Light"))
			{
				WebElement productNameElement= shoppingElements.get(i).findElement(By.xpath("./descendant::div[(@class='inventory_item_name ')]"));
				wait.until(ExpectedConditions.visibilityOf(productNameElement));
				productNameElement.click();
				break;
			}
		}
		
		WebElement addToCartBtn=driver.findElement(By.id("add-to-cart"));
		wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
		addToCartBtn.click();
		
		WebElement backToProductsBtn=driver.findElement(By.cssSelector("[name='back-to-products']"));
		backToProductsBtn.click();
		
		try {
			action.moveToElement(sortOptions).build().perform();
			sortOptions.click();
		}catch (StaleElementReferenceException e) {
			WebElement sortOptions2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/select[@class='product_sort_container']")));
			action.moveToElement(sortOptions2).build().perform();
			sortOptions2.click();
			Select select2 = new Select(sortOptions2);
			select2.selectByIndex(1);
		}
		
		
		
		
		List<WebElement> shoppingElements2 = driver.findElements(By.xpath("//div[contains(@class,'inventory_list')]/div[@class='inventory_item']"));
		for(int i=0; i<shoppingElements2.size();i++)
		{
			String productName = shoppingElements2.get(i).findElement(By.xpath("./descendant::div[(@class='inventory_item_name ')]")).getText();
			
			if(productName.equalsIgnoreCase("Sauce Labs Onesie"))
			{
				WebElement productNameElement= shoppingElements2.get(i).findElement(By.xpath("./descendant::div[(@class='inventory_item_name ')]"));
				wait.until(ExpectedConditions.visibilityOf(productNameElement));
				productNameElement.click();
				break;
			}
		}
		
		WebElement addToCartBtn2=driver.findElement(By.id("add-to-cart"));
		wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn2));
		addToCartBtn2.click();
		
		WebElement goToCart = driver.findElement(By.xpath("//div/a[@class='shopping_cart_link']"));
		wait.until(ExpectedConditions.elementToBeClickable(goToCart));
		goToCart.click();
		
//		WebElement cartProductName1 = driver.findElement(By.xpath("(//div[@class='cart_item']//div[@class='inventory_item_name'])[1]"));
//		System.out.println(cartProductName1.getText());

		List<WebElement> cartItems = driver.findElements(By.xpath("//div[@data-test='inventory-item']/descendant::a/div"));
		System.out.println(cartItems.size());
		int counter=0;
		for(int i=0;i<cartItems.size();i++)
		{
			
			String cartProductNames = cartItems.get(i).getText();
				System.out.println(cartProductNames);
				if(cartProductNames.equalsIgnoreCase("Sauce Labs Bike Light") || cartProductNames.equalsIgnoreCase("Sauce Labs Onesie"))
				{
					counter++;
					if (counter==2)
						break;
				}
//			
//			
		}
		Assert.assertEquals(counter, 2);
		
		driver.findElement(By.id("checkout")).click();
		driver.findElement(By.name("firstName")).sendKeys("Tom");
		driver.findElement(By.name("lastName")).sendKeys("Cruise");
		driver.findElement(By.name("postalCode")).sendKeys("400022");
		
		driver.findElement(By.name("continue")).click();
		
		WebElement checkoutOverview = driver.findElement(By.xpath("//div/span[contains(@class,'title')]"));
		Assert.assertTrue(checkoutOverview.isDisplayed());
		
		String subTotal = driver.findElement(By.xpath("//div/div[@class='summary_subtotal_label']")).getText();
		Assert.assertTrue(subTotal.contains("17.98"), "Price is incorrect");
		
		driver.findElement(By.id("finish")).click();
		
		WebElement orderMsgElement = driver.findElement(By.xpath("//div/h2[@class='complete-header']"));
//		wait.until(ExpectedConditions.visibilityOf(orderMsgElement));
		String orderMsg = orderMsgElement.getText();
		Assert.assertTrue(orderMsg.equalsIgnoreCase("thank you for your order!"), "Order is not placed.");
		
		driver.findElement(By.xpath("//div/button[@data-test='back-to-products']")).click();
		
		WebElement productsElement =driver.findElement(By.xpath("//div/span[@class='title']"));
		Assert.assertTrue(productsElement.isDisplayed(),"User is not re-directed to Products Page!");
		
	}

}
