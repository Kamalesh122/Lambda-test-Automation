package Lambda.test.Automation;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MacBrowserAutomation {

	RemoteWebDriver driver;

	@BeforeMethod
	public void setup() throws MalformedURLException {
		String userName = "kamalesh.sakthi";

		String accessKey = "TwkiAiPxTjq6mcModff6mNmVRZh6VLrOuCwI1o3bYpkC5o2Ah1";

		String hub = "@hub.lambdatest.com/wd/hub";

		EdgeOptions browserOptions = new EdgeOptions();

		browserOptions.setPlatformName("MacOS Sierra");
		browserOptions.setBrowserVersion("87.0");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", userName);
		ltOptions.put("accessKey", accessKey);
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("console", true);
		ltOptions.put("build", "Lamdatest-Assignment");
		ltOptions.put("network", true);
		ltOptions.put("project", "Untitled");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
		browserOptions.setCapability("LT:Options", ltOptions);
		driver = new RemoteWebDriver(new URL("https://" + userName + ":" + accessKey + hub), browserOptions);

	}

	@Test
	public void test002() throws InterruptedException, MalformedURLException {

		driver.get("https://www.lambdatest.com");
		String ExpectedUrl = "https://www.lambdatest.com/integrations";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement header = driver.findElement(By.id("header"));
		wait.until(ExpectedConditions.visibilityOf(header));

		WebElement seeAllIntegraion = driver.findElement(By.cssSelector(".clearfix a[href*='integration']"));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[@class='clearfix'] //ul")));

		// seeAllIntegraion.click();
		String intLink = seeAllIntegraion.getAttribute("href");

		driver.switchTo().newWindow(WindowType.TAB);

		ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());

		System.out.println(windows);

		driver.get(intLink);

		WebElement intHeading = driver.findElement(By.xpath("//h1[text()='LambdaTest Integrations']"));
		wait.until(ExpectedConditions.visibilityOf(intHeading));

		String ActualUrl = driver.getCurrentUrl();

		assertEquals(ExpectedUrl, ActualUrl, "Verified the current Url is matched with expected Url");

		System.out.println("Closing the current window...");

		driver.close();

	}

	@AfterMethod
	public void quit() {
		System.out.println("Closing the current window");
		driver.quit();

	}
}
