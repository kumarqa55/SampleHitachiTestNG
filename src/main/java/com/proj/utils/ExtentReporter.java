package com.proj.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	static File reportFilePath;
	static String url = ReadPropertyFile.getConfigProperties().getProperty("url");
	static String browser = ReadPropertyFile.getConfigProperties().getProperty("chromeBrowser");

	public static ExtentReports generateExtenetReports() {
		reportFilePath = new File(System.getProperty("user.dir") + File.separator + " test-output" + File.separator
				+ "Extent_Reports" + File.separator + "Report.html");
		ExtentReports extentReport = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Practice TestNG Framework");
		sparkReporter.config().setDocumentTitle("Sample TestNG Tests");
		sparkReporter.config().setTimeStampFormat("dd-MM-yyyy hh:mm:ss");
		extentReport.attachReporter(sparkReporter);

		extentReport.setSystemInfo("Application URL", url);
		extentReport.setSystemInfo("Test Executing On", browser);
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

		return extentReport;
	}
}
