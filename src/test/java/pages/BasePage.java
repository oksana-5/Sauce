package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    public static final String DATA_TEST_PATTERN = "[data-test='%s']";
    WebDriver driver;
    WebDriverWait wait;
    public final static String BASE_URL = "https://www.saucedemo.com/";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
}
