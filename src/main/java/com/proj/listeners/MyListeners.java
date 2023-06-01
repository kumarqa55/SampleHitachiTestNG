package com.proj.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.proj.Factory.DriverFactory;
import com.proj.utils.ExtentReporter;
import com.proj.utils.Utility;

public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;

	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtenetReports();
	}

	public void onFinish(ITestContext context) {
		extentReport.flush();
		String extentReportPath = System.getProperty("user.dir") + File.separator + " test-output" + File.separator
				+ "Extent_Reports" + File.separator + "Report.html";
		File deskExtentReport = new File(extentReportPath);
		try {
			Desktop.getDesktop().browse(deskExtentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + " Strated Executing");
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName() + "Successfully Executed");
	}

	public void onTestFailure(ITestResult result) {
		String destScreenPath = Utility.captureScreenshot(DriverFactory.tlDriver.get(), result.getName());
		extentTest.addScreenCaptureFromPath(destScreenPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName() + " got Failed");
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName() + " Skipped");
	}

}
