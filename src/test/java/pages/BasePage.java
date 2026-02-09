package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;
import utils.TestListener;

import java.time.Duration;

public abstract class BasePage {
    public static final String DATA_TEST_PATTERN = "[data-test='%s']";
    public final By title = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));
    WebDriver driver;
    WebDriverWait wait;
    public final static String BASE_URL = PropertyReader.getProperty("saucedemo.url");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public WebElement findElement(By locator) {
        WebElement element = driver.findElement(locator);
        highlightElement(element);
        return element;
    }

    private void highlightElement(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].style.border='3px solid red'", element);
        TestListener.takeScreenshot(driver);
    }

    @Step("Проверяем текст заголовка")
    public String checkTitleName() {
        return driver.findElement(title).getText();
    }
}
