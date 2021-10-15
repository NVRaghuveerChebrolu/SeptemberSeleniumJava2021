package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	public static WebElement FindElement(String OrepLocator){
		By search=null;
		System.out.println(OrepLocator); 
		String locator = OrepLocator.split("&")[0];
		String value = OrepLocator.split("&")[1];
		System.out.println(locator);
		System.out.println(value);
		if(locator.equals("name")){
			search=By.name(value);
		}else if (locator.equals("id")){
			search=By.id(value);
		}else if (locator.equals("xpath")){
			search=By.xpath(value);
		}else if (locator.equals("tagName")){
			search=By.tagName(value);
		}else if (locator.equals("className")){
			search=By.className(value);
		}else if (locator.equals("partialLinkText")){
			search=By.partialLinkText(value);
		}else if (locator.equals("cssSelector")){
			search=By.cssSelector(value);
		}else if (locator.equals("linkText")){
			search=By.linkText(value);
		}
		return driver.findElement(search);
	}
	
	public static void waitForPageToLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		// explicit wait -> Applicable for one webEllement
		WebDriverWait wait = new WebDriverWait(driver, 60);//60 seconds 
		wait.until(pageLoadCondition);
	}
	
	public static String takescreeshot(WebDriver driver) throws Exception {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		System.out.println(dateName);
		String destination = System.getProperty("user.dir") + "//src//test//resources//scrreenshots//" + dateName
				+ "captured.png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}
}
