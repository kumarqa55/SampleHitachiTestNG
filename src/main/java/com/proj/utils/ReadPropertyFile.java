package com.proj.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
	static Properties configProp;
	static Properties testDataProp;

	static File srcFilePath = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
			+ File.separator + "resources");

	public static Properties getConfigProperties() {
		configProp = new Properties();
		String configPath = srcFilePath + File.separator + "config.properties";
		try {
			FileInputStream fis = new FileInputStream(configPath);
			configProp.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return configProp;
	}

	public static Properties getTestDataProperties() {
		testDataProp = new Properties();
		FileInputStream data_fis;
		String testDataPath = srcFilePath + File.separator + "TestData.properties";
		try {
			data_fis = new FileInputStream(testDataPath);
			testDataProp.load(data_fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return testDataProp;
	}

}
