package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class library {
	public static Properties objprop = new Properties();
	public static WebDriver driver;
	
	public static void ReadFeatureFile() throws FileNotFoundException{
		System.out.println(System.getProperty("user.dir"));
		File Obj = new File(System.getProperty("user.dir")+"//src//test//resources//configurationProperty.properties");
		FileInputStream objFileinputStream= new FileInputStream(Obj);
		try {
			objprop.load(objFileinputStream);
			objprop.get("GmoOnloneURL_SIT");
			System.out.println(objprop.get("GmoOnloneURL_SIT"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void LaunchBrowser(){
		String browserFromPropertyFile= (String) objprop.get("browser");
		switch (browserFromPropertyFile){
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions objChromeOptions = new ChromeOptions();
			objChromeOptions.setAcceptInsecureCerts(true);
			driver= new ChromeDriver(objChromeOptions);
			Map<String,Object> chromePrefs = new HashMap<String,Object>();
			//chromePrefs.put("profile.default_content_settings.popups", 0);
			//chromePrefs.put("download.prompt_for_download", false);
			//chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
			objChromeOptions.setExperimentalOption("prefs", chromePrefs);
			driver = new ChromeDriver(objChromeOptions);
			DesiredCapabilities ObjDesiredCap = DesiredCapabilities.chrome();
			ObjDesiredCap.setCapability(ChromeOptions.CAPABILITY, objChromeOptions);
			ObjDesiredCap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			//Creating an object of the FirefoxOptions Class
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			//Using the setAcceptInsecureCerts() method to pass parameter as False
			firefoxOptions.setAcceptInsecureCerts(true);
			driver= new FirefoxDriver(firefoxOptions);
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			//Creating an object of EdgeOptions class
			//EdgeOptions edgeOptions = new EdgeOptions();
					
			//Accepting the Insecure certificates through boolean parameter
			//edgeOptions.setAcceptInsecureCerts(true);
							
			//Creating instance of Edge driver by passing reference of EdgeOptions object
	                // Assuming EdgeDriver path has been set in system properties
			//driver = new EdgeDriver(edgeOptions);
			driver=new EdgeDriver();
			
			break;
		case "IE":
			WebDriverManager.iedriver().setup();
			driver= new InternetExplorerDriver();
			break;
		case "opera":
			WebDriverManager.operadriver().setup();
			driver= new OperaDriver();
			break;
		}
		driver.get(objprop.getProperty("GmoOnloneURL_SIT"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
	}

}
