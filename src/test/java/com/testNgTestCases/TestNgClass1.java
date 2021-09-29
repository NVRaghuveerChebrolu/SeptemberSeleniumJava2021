package com.testNgTestCases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNgClass1 {
  @Test(priority=-1)
  public void testcase1() {
	  System.out.println("inside tescase1");
  }
  
  @Test(priority=0)
  public void Testcase2() {
	  System.out.println("inside tescase2");
  }
  
  @Test(priority =0)
  public void Testcase3() {
	  System.out.println("inside tescase3");
  }
  
  @Test (priority=-6)
  public void Testcase4() {
	  System.out.println("inside tescase4");
  }
  
  @Test (priority=4,invocationCount=10)
  public void Testcase5() {
	  System.out.println("inside tescase5");
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
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("inside afterSuite");
  }

}
