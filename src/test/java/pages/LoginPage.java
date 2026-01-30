package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By loginInput = By.id("user-name");
    private final By passwordInput = By.cssSelector(DATA_TEST_PATTERN.formatted("password"));
    private final By loginButton = By.cssSelector(DATA_TEST_PATTERN.formatted("login-button"));
    private final By ErrorMsg = By.cssSelector(DATA_TEST_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(String user, String password) {
        driver.findElement(loginInput).sendKeys(user);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isErrorMsgDisplayed() {
        return driver.findElement(ErrorMsg).isDisplayed();
    }

    public String getErrorMsgText() {
        return driver.findElement(ErrorMsg).getText();
    }
}
