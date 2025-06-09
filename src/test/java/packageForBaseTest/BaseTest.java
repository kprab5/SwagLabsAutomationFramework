package packageForBaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import packageForAppUtilites.AppUtilities;

public class BaseTest 
{
	public WebDriver driver;
	public Properties props;
	public ChromeOptions chrome_options = new ChromeOptions();	
	
	public void initializeTest() throws Exception
	{
		FileInputStream fis = new FileInputStream("C:/Users/aksha/eclipse-workspace/AutomationSwagLabs/src/test/java/packageforConfigurations/Config.properties");
		props = new Properties();
		props.load(fis);
		
		String browserName=props.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			chrome_options.addArguments("--incognito");
			driver=new ChromeDriver(chrome_options);
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();	
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setup() throws Exception
	{
		initializeTest();
		String url=props.getProperty("url");
		driver.get(url);
	}
	
	@AfterMethod(alwaysRun = true)
	public void close() throws InterruptedException
	{
		AppUtilities.appUtil();
		driver.quit();
	}
	public void getScreenshot(String name) throws IOException
	{
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File screenshot1= screenshot.getScreenshotAs(OutputType.FILE);
		org.openqa.selenium.io.FileHandler.copy(screenshot1, new File("C:/Users/aksha/eclipse-workspace/AutomationSwagLabs/src/test/java/packageForTestReport'"+name+"'.png"));
	}

}
