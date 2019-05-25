package drillmania;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class MainTest {

	@Test
	public void checkChromeDriver() throws InterruptedException {
		ChromeDriverLoader.init();
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com");
		Thread.sleep(1000);
		driver.quit();
	}
}
