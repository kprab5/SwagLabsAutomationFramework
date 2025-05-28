package packageForHelperUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.ui.Select;

public class HelperUtility
{
	WebDriver driver;
	Actions action;
	Select select;
	
	public HelperUtility(WebDriver driver)
	{
		this.driver=driver;
		action = new Actions(driver);
	}
	
	public void moveToElement(WebElement element)
	{
		action.moveToElement(element).build().perform();
	}
	
	public void selectUsingValue(WebElement element, String value)
	{
		select = new Select(element);
		select.selectByValue(value);
	}
	
	public void selectUsingIndex(WebElement element, int index)
	{
		select = new Select(element);
		select.selectByIndex(index);
	}
	
	

}
