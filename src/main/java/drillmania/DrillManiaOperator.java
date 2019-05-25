package drillmania;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

public class DrillManiaOperator implements AutoCloseable {
	private WebDriver driver;
	final int retryNum = 100;

	public DrillManiaOperator() {
		ChromeDriverLoader.init();
		this.driver = new ChromeDriver();
		driver.get("https://drillmania.work");
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
	public DrillType getDrillType() throws MalformedURLException, InterruptedException {
		String imgSrc;
		int count = 0;
		while (true) {
			try {
				imgSrc = driver.findElement(By.className("black")).getAttribute("src");
				return DrillType.getDrillType(new URL(imgSrc));
			} catch (NoSuchElementException e) {
				count++;
				if (count > retryNum) {
					throw new RuntimeException(e);
				} else {
					Thread.sleep(1);
				}
			}
		}
	}

	/**
	 * Click YES/NO button.
	 * 
	 * @param isDrill
	 */
	public void sendResult(boolean isDrill) {
		String buttonClassName;
		if (isDrill) {
			buttonClassName = "yes";
		} else {
			buttonClassName = "no";
		}
		try {
			driver.findElement(By.className(buttonClassName)).click();
		} catch (WebDriverException e) {
			//do nothing
		}
	}

	enum DrillType {
		REALDRILL("/_nuxt/img/3040568.png"), 
		ANIMEDRILL("/_nuxt/img/678a65b.png"), 
		TAKENOKO("/_nuxt/img/53493b5.png"),
		ASPARAGUS("/_nuxt/img/1a79a5b.png");

		private final String filename;

		private DrillType(final String filename) {
			this.filename = filename;
		}

		public static DrillType getDrillType(URL url) {
			return Stream.of(values()).filter(pictures -> pictures.filename.equals(url.getPath())).findAny()
					.orElse(null);
		}
	}

	@Override
	public void close() throws Exception {
		driver.quit();
	}
}
