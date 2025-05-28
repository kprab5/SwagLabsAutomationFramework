package packageForAppUtilites;

import org.openqa.selenium.WebDriver;

public class AppUtilities 
{
	public WebDriver driver;
	
	public AppUtilities(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public static void appUtil() throws InterruptedException
	{
		Thread.sleep(2000);
	}

}
