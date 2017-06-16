package com.agiletestware.bumblebee.dummytest;

import java.net.URL;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.agiletestware.bumblebee.annotations.Bumblebee;

@Bumblebee(testplan = "Subject\\appium", testlab = "Root\\appium", testset = "appium test")
public class AppiumTest {
	private static WebDriver driver;

	@BeforeClass
	public static void setUp() throws Exception {
		// define capabilities
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("avd", "Nexus_5X_API_24");
		capabilities.setCapability("deviceName", "Android");
		capabilities.setCapability("appPackage", "com.agiletestware.bumblebeetest");
		capabilities.setCapability("appActivity", ".MainActivity");
		// use local Appium server
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	@Test
	public void testClickOnSendButton() {
		driver.findElement(By.id("com.agiletestware.bumblebeetest:id/fab")).click();
		Assert.assertEquals("something wrong", driver.findElement(By.id("com.agiletestware.bumblebeetest:id/snackbar_text")).getText());
	}

	@AfterClass
	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
