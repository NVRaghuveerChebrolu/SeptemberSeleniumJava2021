package com.testNgTestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.utility.Orep;
import com.utility.constants;
import com.utility.library;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.awt.Window;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNgClass4 extends library {
	// WebDriver driver;

	@Test(priority = -1)
	public void validateGMOonlineLoadedSuccessfully() {
		System.out.println("inside validateGMOonlineLoadedSuccessfully");
		waitForPageToLoad();
		String Actualtitle = driver.getTitle();
		String expectedtitle = "Welcome to Green Mountain Outpost";
		System.out.println(Actualtitle);
		Assert.assertEquals(Actualtitle, expectedtitle);
	}

	@Parameters
	@Test(priority = 0, dependsOnMethods = { "validateGMOonlineLoadedSuccessfully" })
	public void ValidateOnLineCatalogLoadedSuccessfully() {
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
		driver.navigate().to(objprop.getProperty("mouseOperationDragAndDrop"));
		waitForPageToLoad();
		WebElement frameElement=library.FindElement(Orep.Drag_And_Drop_Frame);
		driver.switchTo().frame(frameElement);
		Actions obj = new Actions(driver);
		WebElement source = library.FindElement(Orep.draggable);
		WebElement target =	library.FindElement(Orep.draggable)	;
		
		obj.dragAndDrop(source, target).build().perform();
		
		
		/*obj.clickAndHold(target);
		obj.moveToElement(target);
		obj.release(target);*/
		
		String ActualDroppedtext= library.FindElement(Orep.dropText).getText();
		Assert.assertEquals(ActualDroppedtext, "Dropped");
		driver.switchTo().defaultContent();
		
		

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
		library.LaunchBrowser();

	}

	@AfterTest
	public void afterTest() {
		System.out.println("inside afterTest");
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
