package com.proj.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {

	public static Object[][] getExcelData(String sheetName) {

		XSSFWorkbook book = null;

		File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "SampleTestData.xlsx");
		try {
			FileInputStream fis = new FileInputStream(file);
			book = new XSSFWorkbook(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = book.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] excelData = new Object[rows][cols];
		for (int i = 0; i < rows; i++) {
			XSSFRow rowData = sheet.getRow(i + 1);
			for (int j = 0; j < cols; j++) {
				XSSFCell cell = rowData.getCell(j);
				CellType cellType = cell.getCellType();
				switch (cellType) {
				case STRING:
					excelData[i][j] = cell.getStringCellValue();
				case NUMERIC:
					excelData[i][j] = String.valueOf((int) cell.getNumericCellValue());
				case BOOLEAN:
					excelData[i][j] = cell.getBooleanCellValue();
				default:
					excelData[i][j] = cell.getStringCellValue();
				}
			}
		}
		return excelData;
	}

	public static String captureScreenshot(WebDriver driver, String testName) {
		String destScreenshot = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator
				+ testName + ".png";
		File dest = new File(destScreenshot);
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destScreenshot;
	}
}
