package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {
    private final By loginInput = By.id("user-name");
    private final By passwordInput = By.cssSelector(DATA_TEST_PATTERN.formatted("password"));
    private final By loginButton = By.cssSelector(DATA_TEST_PATTERN.formatted("login-button"));
    private final By ErrorMsg = By.cssSelector(DATA_TEST_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие браузера")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Логинимся под кредами пользователя")
    public void login(User user) {
        fillLoginField(user.getEmail());
        fillPasswordField(user.getPassword());
        findElement(loginButton).click();
    }

    @Step("Вводим логин {user}")
    public void fillLoginField(String user) {
        findElement(loginInput).sendKeys(user);
    }

    @Step("Вводим пароль {password}")
    public void fillPasswordField(String password) {
        findElement(passwordInput).sendKeys(password);
    }

    @Step("Проверяем видимость сообщения об ошибке")
    public boolean isErrorMsgDisplayed() {
        return findElement(ErrorMsg).isDisplayed();
    }

    @Step("Проверяем текст сообщения об ошибке")
    public String getErrorMsgText() {
        return findElement(ErrorMsg).getText();
    }
}
