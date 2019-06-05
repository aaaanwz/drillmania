package drillmania;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import core.ChromeDriverLoader;
import core.Constants;

class MainTest {

  @Test
  public void checkChromeDriver() throws InterruptedException {
    ChromeDriverLoader.init();
    WebDriver driver = new ChromeDriver();
    driver.get(Constants.url);
    Thread.sleep(1000);
    driver.quit();
  }
}
