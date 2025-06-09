package packageForTestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportClass 
{
	public static ExtentReports getExtentReports()
	{
		String path = "C:/Users/aksha/eclipse-workspace/AutomationSwagLabs/src/test/java/packageForTestReport/TestReport.html";
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
		extentSparkReporter.config().setDocumentTitle("SwagLabsReport");
		extentSparkReporter.config().setReportName("TestReportSwagLabs");
		
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("OS", "Windows10");
		extentReports.setSystemInfo("Tester Name", "Ketan Prabhulkar");
		extentReports.setSystemInfo("Environmemt", "Staging");
		return extentReports;
	}

}
