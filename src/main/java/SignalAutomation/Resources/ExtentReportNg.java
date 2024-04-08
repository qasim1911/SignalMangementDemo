package SignalAutomation.Resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public  class ExtentReportNg {

	public static ExtentReports getExtentReport() {

		ExtentSparkReporter reporter = new ExtentSparkReporter(
				new File(System.getProperty("user.dir") + "//Report//index.html"));
		reporter.config().setDocumentTitle("3 Analytics");
		reporter.config().setReportName("Signal Management");
		
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("TESTED BY", "SYED MOHD");
		return extentReports;
	}
}
