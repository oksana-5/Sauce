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
    public LoginPage open() {
        driver.get(BASE_URL);

        return this;
    }

    @Step("Логинимся под кредами пользователя")
    public LoginPage login(User user) {
        fillLoginField(user.getEmail());
        fillPasswordField(user.getPassword());
        findElement(loginButton).click();

        return this;
    }

    @Step("Вводим логин {user}")
    public LoginPage fillLoginField(String user) {
        findElement(loginInput).sendKeys(user);

        return this;
    }

    @Step("Вводим пароль {password}")
    public LoginPage fillPasswordField(String password) {
        findElement(passwordInput).sendKeys(password);

        return this;
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
