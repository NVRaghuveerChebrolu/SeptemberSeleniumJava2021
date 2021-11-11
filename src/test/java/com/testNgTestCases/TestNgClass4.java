package com.testNgTestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.utility.Orep;
import com.utility.constants;
import com.utility.library;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNgClass4 extends library {
	// WebDriver driver;
	 HashMap<String,String> testData=new HashMap<String,String>();//Creating HashMap    

	@Test(priority = -1)
	public void validateGMOonlineLoadedSuccessfully() {
		System.out.println("inside validateGMOonlineLoadedSuccessfully");
		extenttest = extentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		waitForPageToLoad();
		String Actualtitle = driver.getTitle();
		String expectedtitle = "Welcome to Green Mountain Outpost";
		System.out.println(Actualtitle);
		Assert.assertEquals(Actualtitle, expectedtitle);
	}

	@Parameters
	@Test(priority = 0, dependsOnMethods = { "validateGMOonlineLoadedSuccessfully" })
	public void ValidateOnLineCatalogLoadedSuccessfully() {
		extenttest = extentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		System.out.println("inside ValidateOnLineCatalogLoadedSuccessfully");
		// driver.findElement(By.name("bSubmit")).click();
		library.FindElement(Orep.EnterGmoONline).click();
		waitForPageToLoad();
		// driver.findElement(By.xpath("//input[@type='text' and
		// @name='QTY_BACKPACKS']")).sendKeys(constants.FrameBackpackQty);
		library.FindElement(Orep.QTY_BACKPACKS).sendKeys(constants.FrameBackpackQty);
		// table/tbody/tr[3]/td[3]
		// String qtyPriceFrameBackpack =
		// driver.findElement(By.xpath("//*[contains(text(),'$
		// 179.95')]")).getText();
		String qtyPriceFrameBackpack = library.FindElement(Orep.qtyPriceFrameBackpack).getText();
		System.out.println(qtyPriceFrameBackpack);
		// driver.findElement(By.xpath("//input[@value='Place An
		// Order']")).click();
		library.FindElement(Orep.PlaceAnOrder).click();
	}

	@Test(priority = 1, dependsOnMethods = { "ValidateOnLineCatalogLoadedSuccessfully" })
	public void ValidatePriceCalculationInPlaceorderPage() throws Exception {
		System.out.println("inside ValidatePriceCalculationInPlaceorderPage");
		extenttest = extentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		String ActualTitle = driver.findElement(By.xpath("//h1[contains(text(),'Place Order')]")).getText();
		String ExpectedTile = "Place Orde";
		// Assert.assertEquals(ActualTitle, ExpectedTile);
		SoftAssert sAssert = new SoftAssert();
		// library.takescreeshot(driver);
		sAssert.assertEquals(ActualTitle, ExpectedTile);
		String UnitPriceBackPack = driver.findElement(By.xpath("//table/tbody/tr[2]/td[4]")).getText();
		String PriceQtyBackPack = UnitPriceBackPack.substring(2).trim();
		System.out.println(PriceQtyBackPack);
		Float ExpectedTotalPriceBackPack = Float.parseFloat(PriceQtyBackPack) * 4;
		System.out.println("ExpectedTotalPriceBackPack:" + ExpectedTotalPriceBackPack);
		String AcutalTotalPriceBackPack = driver.findElement(By.xpath("//table/tbody/tr[2]/td[5]")).getText()
				.substring(2).trim();
		Float ActualPrice = Float.parseFloat(AcutalTotalPriceBackPack);
		System.out.println("ActualPrice:" + ActualPrice);
		Assert.assertEquals(ActualPrice, ExpectedTotalPriceBackPack);
		sAssert.assertAll();// this should be declared at the last line of the
							// testcase
	}

	@Test(priority = 2)
	public void ValidateHandlingAlerts() {
		System.out.println("inside ValidateHandlingAlerts");
		extenttest = extentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.get(objprop.getProperty("AlertURL"));
		waitForPageToLoad();
		driver.findElement(By.id("alertButton")).click();
		Alert objalertButton = driver.switchTo().alert();
		String alertButtonText = objalertButton.getText();
		System.out.println(alertButtonText);
		Assert.assertEquals(alertButtonText, "You clicked a button");
		objalertButton.accept();

		driver.findElement(By.id("timerAlertButton")).click();
		// Explict wait : applicable for one webElement
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());

		Alert objTimerAlertbutton = driver.switchTo().alert();
		String TimerAlertText = objTimerAlertbutton.getText();
		System.out.println("TimerAlertText: " + TimerAlertText);
		Assert.assertEquals(TimerAlertText, "This alert appeared after 5 seconds");
		objTimerAlertbutton.accept();

		driver.findElement(By.id("confirmButton")).click();
		Alert ConfirmAction = driver.switchTo().alert();
		ConfirmAction.dismiss();
		String ConfirmActionText = driver.findElement(By.xpath("//span[@id='confirmResult']")).getText();
		System.out.println("ConfirmActionText: " + ConfirmActionText);
		Assert.assertEquals(ConfirmActionText, "You selected Cancel");

		driver.findElement(By.id("promtButton")).click();
		Alert promtButton = driver.switchTo().alert();
		promtButton.sendKeys("HI Typing inside alert box");
		promtButton.accept();
		String promtResult = driver.findElement(By.xpath("//span[@id='promptResult']")).getText();
		System.out.println("promtResult: " + promtResult);
		Assert.assertEquals(promtResult, "You entered HI Typing inside alert box");

	}

	@Test(priority = 3)
	public void ValidtateHandlingOfFrames() {
		System.out.println("inside ValidtateHandlingOfFrames");
		extenttest = extentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(objprop.getProperty("FramesURL"));
		waitForPageToLoad();
		driver.switchTo().frame("singleframe");
		library.FindElement(Orep.SingleFrameTextBox).sendKeys("inside single frame");
		driver.switchTo().defaultContent();
		library.FindElement(Orep.IframewithInIFrame).click();
		WebElement element = library.FindElement(Orep.MultipleFrames);
		driver.switchTo().frame(element);
		WebElement SingleFrameElement = library.FindElement(Orep.SngleFrameWithInMultiple);
		driver.switchTo().frame(SingleFrameElement);
		library.FindElement(Orep.SingleFrameTextBox).sendKeys("iframeWithINiframe");
		driver.switchTo().defaultContent();

	}

	@Test(priority = 4)
	public void ValidateHandlingWindows() {
		System.out.println("inside ValidateHandlingWinwods");
		extenttest = extentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(objprop.getProperty("WindowsURL"));
		waitForPageToLoad();
		Set<String> AllWindows = driver.getWindowHandles();
		String ParentWidnow = driver.getWindowHandle();
		int count = 0;
		for (String Individualwindow : AllWindows) {
			driver.switchTo().window(Individualwindow);
			count++;
			String title = driver.getTitle();
			System.out.println("title: " + title);
			if ((count == 2) && title.equals("Tech Mahindra")) {
				driver.manage().window().maximize();
			}
			if ((count == 3) && title.equals("Tech Mahindra")) {
				driver.close(); // close the current window
			}
			// driver.quit(); to close all windows
		}
	}

	@Test(priority = 5)
	public void ValidateHandlingOfWebTable() {
		System.out.println("inside ValidateHandlingWinwods");
		extenttest = extentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(objprop.getProperty("WebTableURL"));
		waitForPageToLoad();

		List<WebElement> AllLastNames = library.FindElements(Orep.listOfLastNamesInWebTable);
		// System.out.println(AllLastNames);
		for (int i = 0; i <= AllLastNames.size() - 1; i++) {
			String lastname = AllLastNames.get(i).getText();
			System.out.println(lastname);
			if (lastname.equals("Kelly")) {
				int rowvalue = i + 1;
				String Salary = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + rowvalue + "]/td[7]"))
						.getText();
				System.out.println(Salary);
				String StateDate = driver
						.findElement(By.xpath("//table[@id='example']/tbody/tr[" + rowvalue + "]/td[6]")).getText();
				System.out.println(StateDate);
			}
		}

		// table[@id='example']
	}

	@Test(priority = 6)
	public void ValidateMouseOperationRightClick() {
		System.out.println("inside ValidateMouseOperationRightClick");
		extenttest = extentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(objprop.getProperty("mouseOpeartionRightClick"));
		waitForPageToLoad();
		Actions obj = new Actions(driver);
		WebElement RightClick = library.FindElement(Orep.MouseOpearationRightClick);
		obj.contextClick(RightClick).build().perform();
		library.FindElement(Orep.copy).click();
		Alert AcceptingAlert = driver.switchTo().alert();
		String textOfAlert = AcceptingAlert.getText();
		System.out.println(textOfAlert);
		Assert.assertEquals(textOfAlert, "clicked: copy");
		AcceptingAlert.accept();
	}

	@Test(priority = 6)
	public void ValidatemouseOpeartionDoubleClick() {
		System.out.println("inside ValidatemouseOpeartionDoubleClick");
		extenttest = extentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(objprop.getProperty("mouseOpeartionDoubleClick"));
		waitForPageToLoad();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0,1000)");//To scroll vertically
		// Down by 1000 pixels
		// js.executeScript("window.scrollBy(0,-500)");//To scroll vertically Up
		// by 500 pixels
		// js.executeScript("window.scrollBy(500,0)");//To scroll horizontally
		// right by 500 pixels
		// js.executeScript("window.scrollBy(-500,0)");//To scroll horizontally
		// left by 500 pixels
		// Color loginButtonColour =
		// Color.fromString(driver.findElement(By.id("login")).getCssValue("color"));

		WebElement element = library.FindElement(Orep.MouseOpearationDemoSection);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.switchTo().frame(0);
		Actions obj = new Actions(driver);
		WebElement DoubleCLick = library.FindElement(Orep.MouseOpearationdoubleClick);
		obj.doubleClick(DoubleCLick).build().perform();
		Color BackGroundColor = Color.fromString(
				driver.findElement(By.xpath("//span[text()='Double click the block']/preceding-sibling::div"))
						.getCssValue("background-color"));
		System.out.println("BackGroundColor:" + BackGroundColor);
		String ActualBackGroundColor = BackGroundColor.asRgba();
		System.out.println("ActualBackGroundColor:" + ActualBackGroundColor);
		Assert.assertEquals(ActualBackGroundColor, "rgba(255, 255, 0, 1)");
		driver.switchTo().defaultContent();

	}

	@Test(priority = 7)
	public void ValidateMouseOperationDragAndDrop() {
		System.out.println("inside ValidatemouseOpeartionDoubleClick");
		extenttest = extentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(objprop.getProperty("mouseOperationDragAndDrop"));
		waitForPageToLoad();
		WebElement frameElement=library.FindElement(Orep.Drag_And_Drop_Frame);
		driver.switchTo().frame(frameElement);
		Actions obj = new Actions(driver);
		WebElement source = library.FindElement(Orep.draggable);
		WebElement target =	library.FindElement(Orep.droppable)	;
		//obj.dragAndDrop(source, target).build().perform();
		obj.clickAndHold(source);
		obj.moveToElement(target);
		obj.release(target).build().perform();
		String ActualDroppedtext= library.FindElement(Orep.dropText).getText();
		Assert.assertEquals(ActualDroppedtext, "Dropped");
		driver.switchTo().defaultContent();
	}

	@Test(priority = 8)
	public void ValidateFileUpload() throws InterruptedException, AWTException {
		System.out.println("inside ValidateFileUpload");
		extenttest = extentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(objprop.getProperty("FileUpload"));
		waitForPageToLoad();
		library.FindElement(Orep.FileUpload).click();
		//WebElement element = library.findElementByLocator(ORep.FileUpload);
		//library.javascriptExecutorScroolIntoViewAndClick(element);
		// File Obj=new
		// File(System.getProperty("user.dir"+"//src/test//resources/Sample.jpg"));

		StringSelection objStringSelection = new StringSelection(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Sample.jpg");
		Clipboard objClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		objClipboard.setContents(objStringSelection, null);
		try {
			Transferable objTransferable = objClipboard.getContents(null);
			if (objTransferable.isDataFlavorSupported(DataFlavor.stringFlavor))
				System.out.println(objTransferable.getTransferData(DataFlavor.stringFlavor));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Robot objRobot = new Robot();
		objRobot.delay(250);
		objRobot.keyPress(KeyEvent.VK_ENTER);
		objRobot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		objRobot.keyPress(KeyEvent.VK_CONTROL);
		objRobot.keyPress(KeyEvent.VK_V);
		Thread.sleep(2000);
		objRobot.keyRelease(KeyEvent.VK_V);
		objRobot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		objRobot.keyPress(KeyEvent.VK_ENTER);
		objRobot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);

	}

	@Test(priority = 12)
	public void ValidateFileDownload() throws InterruptedException {
		System.out.println("inside ValidateFileDownload");
		extenttest = extentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(objprop.getProperty("FileDownload"));
		waitForPageToLoad();
		library.FindElement(Orep.FileDownload).click();
		Thread.sleep(8000);
		File objFile = new File(System.getProperty("user.dir"));
		File[] listOfFiles = objFile.listFiles();
		boolean fileFound = false;
		File obj_File = null;
		for (File IndividualFile : listOfFiles) {
			String FileName = IndividualFile.getName();
			System.out.println(FileName);
			if (FileName.contains("file-sample")) {
				fileFound = true;
				obj_File = new File(FileName);
			}
		}
		Assert.assertTrue(fileFound, "File Downloaded Not Found");
		obj_File.deleteOnExit();

	}

	@Test
	public void ValidateDataDrivenTesting() throws Exception{
		System.out.println("inside ValidateDataDrivenTesting");
		driver.navigate().to("http://demo.automationtesting.in/Register.html");
		try {
			FileInputStream objFileinput = new FileInputStream (new File (System.getProperty("user.dir")+"//src//test//resources//AutomationDemoSite.xlsx"));
			XSSFWorkbook ObjWorkBook = new XSSFWorkbook(objFileinput);
			XSSFSheet ObjSheet = ObjWorkBook.getSheet("TestData");
			int NumberOfRows = ObjSheet.getLastRowNum();
			
			System.out.println("NumberOfRows: "+NumberOfRows);
			for (int rowNumber =1 ;rowNumber<=NumberOfRows;rowNumber++){
				testData = ReadTestData(rowNumber,ObjSheet);
				library.FindElement(Orep.DataDrivenFirstName).clear();
				library.FindElement(Orep.DataDrivenFirstName).sendKeys(testData.get("Firstname"));
				library.FindElement(Orep.DataDrivenLastName).clear();
				library.FindElement(Orep.DataDrivenLastName).sendKeys(testData.get("LastName"));
				library.FindElement(Orep.DataDrivenAddress).clear();
				library.FindElement(Orep.DataDrivenAddress).sendKeys(testData.get("Address"));
				library.FindElement(Orep.DataDrivenEmail_address).clear();
				library.FindElement(Orep.DataDrivenEmail_address).sendKeys(testData.get("Email_address"));
				library.FindElement(Orep.DataDrivenPhoneNum).clear();
				library.FindElement(Orep.DataDrivenPhoneNum).sendKeys(testData.get("PhoneNumber"));
				String Gender = testData.get("Gender");
				if(Gender.equals("Male")){
					library.FindElement(Orep.DataDrivenGenderMale).click();
				}else{
					library.FindElement(Orep.DataDrivenGenderFemale).click();
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private HashMap<String, String> ReadTestData(int rowNumber, XSSFSheet objSheet) {
		
		DataFormatter Format = new DataFormatter();					

		testData.put("RunMode", objSheet.getRow(rowNumber).getCell(0).getStringCellValue());
		testData.put("TestCaseName", objSheet.getRow(rowNumber).getCell(1).getStringCellValue());
		testData.put("Firstname", objSheet.getRow(rowNumber).getCell(2).getStringCellValue());
		testData.put("LastName", objSheet.getRow(rowNumber).getCell(3).getStringCellValue());
		testData.put("Address", objSheet.getRow(rowNumber).getCell(4).getStringCellValue());
		testData.put("Email_address", objSheet.getRow(rowNumber).getCell(5).getStringCellValue());
		
		String PhoneNumber = Format.formatCellValue(objSheet.getRow(rowNumber).getCell(6));
		testData.put("PhoneNumber", PhoneNumber);
		
		testData.put("Gender", objSheet.getRow(rowNumber).getCell(7).getStringCellValue());
		testData.put("Hobbies", objSheet.getRow(rowNumber).getCell(8).getStringCellValue());
		testData.put("Languages", objSheet.getRow(rowNumber).getCell(9).getStringCellValue());
		testData.put("Skills", objSheet.getRow(rowNumber).getCell(10).getStringCellValue());
		testData.put("Country", objSheet.getRow(rowNumber).getCell(11).getStringCellValue());
		testData.put("SelectCntry", objSheet.getRow(rowNumber).getCell(12).getStringCellValue());
		
		String DOB_YY = Format.formatCellValue(objSheet.getRow(rowNumber).getCell(13));
		testData.put("DOB_YY", DOB_YY);
		
		testData.put("DOB_MM", objSheet.getRow(rowNumber).getCell(14).getStringCellValue());
		
		String DOB_DD = Format.formatCellValue(objSheet.getRow(rowNumber).getCell(15));
		testData.put("DOB_DD", DOB_DD);
	
		testData.put("Password", objSheet.getRow(rowNumber).getCell(16).getStringCellValue());
		testData.put("confirmPasspwd", objSheet.getRow(rowNumber).getCell(17).getStringCellValue());
		testData.put("Result", objSheet.getRow(rowNumber).getCell(18).getStringCellValue());
		
		System.out.println("-----------------------------------");
		System.out.println(testData.get("RunMode"));
		System.out.println(testData.get("TestCaseName"));
		System.out.println(testData.get("Firstname"));
		System.out.println(testData.get("LastName"));
		System.out.println(testData.get("Address"));
		System.out.println(testData.get("Email_address"));
		System.out.println(testData.get("PhoneNumber"));
		System.out.println(testData.get("Gender"));
		System.out.println(testData.get("Hobbies"));
		System.out.println(testData.get("Languages"));
		System.out.println(testData.get("Skills"));
		System.out.println(testData.get("Country"));
		System.out.println(testData.get("SelectCntry"));
		System.out.println(testData.get("DOB_YY"));
		System.out.println(testData.get("DOB_MM"));
		System.out.println(testData.get("DOB_DD"));
		System.out.println(testData.get("Password"));
		System.out.println(testData.get("confirmPasspwd"));
		System.out.println("-----------------------------------");
		return testData;
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("inside beforeMethod");
		
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
		extenttest.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
		extenttest.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report
		String screenshotPath = library.takescreeshot(driver, result.getName());
		extenttest.addScreenCaptureFromPath(screenshotPath);// adding screen shot to extent report
		} else if (result.getStatus() == ITestResult.SKIP) {
			extenttest.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if (result.getStatus() == ITestResult.SUCCESS) {
			extenttest.log(Status.PASS, "Test Case PASSED IS " + result.getName());
		}
		//driver.close();//close the current window which is currently open
		//driver.quit();//Quits this driver, closing every associated window.
		}

	@BeforeClass
	public void beforeClass() {
		System.out.println("inside beforeClass");
		library.startReport();
	}

	@AfterClass
	public void afterClass() {
		System.out.println("inside afterClass");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("inside beforeTest");
		library.LaunchBrowser();

	}

	@AfterTest
	public void afterTest() {
		System.out.println("inside afterTest");
		extentReport.flush();//if this is not given report will not get generated
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("inside beforeSuite");
		try {
			library.ReadFeatureFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");
	}

}
