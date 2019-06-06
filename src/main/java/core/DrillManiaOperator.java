package core;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 
 * @author aaaanwz
 *
 */
public class DrillManiaOperator implements AutoCloseable {
  private WebDriver driver;

  /**
   * Launch Chrome and connect to drillmania.
   */
  public DrillManiaOperator() {
    ChromeDriverLoader.init();
    this.driver = new ChromeDriver();
    driver.get(Constants.url);
  }

  /**
   * @return true if the game is started
   */
  public boolean isStarted() {
    return driver
        .findElement(By.className("start-count"))
        .getAttribute("style")
        .equals("display: none;");
  }

  /**
   * @return true if there is no gameplay remaining time
   */
  public boolean isTimeOver() {
    try {
      driver.findElement(By.className("result"));
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  /**
   * Determine the type of silhouette
   * 
   * @return DrillType
   * @throws MalformedURLException failed to parse url of the picture.
   * @throws InterruptedException
   */
  public Boolean isDrill() throws MalformedURLException, InterruptedException {
    return driver
        .findElements(By.className("black"))
        .stream()
        .filter(e -> !e.getAttribute("style").equals("display: none;"))
        .map(e -> {
          try {
            return new URL(e.getAttribute("src")).getPath();
          } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
          }
        })
        .map(e -> Constants.drillPictureMap.get(e))
        .findAny()
        .orElse(null);
  }



  /**
   * Click YES/NO button.
   * 
   * @param isDrill
   */
  public void sendResult(Boolean isDrill) {
    if (isDrill == null) {
      return;
    }
    String buttonClassName;
    if (isDrill) {
      buttonClassName = "yes";
    } else {
      buttonClassName = "no";
    }
    try {
      driver.findElement(By.className(buttonClassName)).click();
    } catch (WebDriverException e) {
      // do nothing
    }
  }

  /**
   * Disconnect from drillmania.
   */
  @Override
  public void close() throws Exception {
    driver.quit();
  }
}
