package com.testNgTestCases;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNgClass2 {
	WebDriver driver;
  @Test(priority=-1)
  public void validateGMOonlineLoadedSuccessfully() {
	  System.out.println("inside validateGMOonlineLoadedSuccessfully");
	  String Actualtitle=driver.getTitle();
	  String expectedtitle ="Welcome to Green Mountain Outpost";
	  System.out.println(Actualtitle);
	  Assert.assertEquals(Actualtitle, expectedtitle);
  }
  
  @Test(priority=0)
  public void ValidateOnLineCatalogLoadedSuccessfully(){
	  System.out.println("inside ValidateOnLineCatalogLoadedSuccessfully");
	  driver.findElement(By.name("bSubmit")).click();
	  driver.findElement(By.xpath("//input[@type='text' and @name='QTY_BACKPACKS']")).sendKeys("4");
	  //table/tbody/tr[3]/td[3]
	
	  String qtyPriceFrameBackpack = driver.findElement(By.xpath("//*[contains(text(),'$ 179.95')]")).getText();
	  System.out.println(qtyPriceFrameBackpack);
  }
  
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("inside beforeMethod");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("inside afterMethod");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("inside beforeClass");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("inside afterClass");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("inside beforeTest");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("inside afterTest");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("inside beforeSuite");
	  WebDriverManager.chromedriver().setup();
	  driver= new ChromeDriver();
	  driver.get("http://demo.borland.com/gmopost/");
	  driver.manage().window().maximize();
	  //implicit wait
	  //explicit wait 
	  //fluent wait
	  //implicit wait
	  driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	  //driver.findElement(By.name("bSubmit")).click();
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("inside afterSuite");
  }

}
