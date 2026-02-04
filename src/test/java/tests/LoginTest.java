package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

public class LoginTest extends BaseTest {

    @Test
    public void correctLogin() {
        System.out.println("LoginTest.correct in thread: " + Thread.currentThread().getId());
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.isTitleIsDisplayed(), "Заголовок не отображается");
        assertEquals(productsPage.checkTitleName(), "Products",
                "Неверный текст заголовка");
    }

    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                {withLockedPermission(), "Epic sadface: Sorry, this user has been locked out."},
                {withEmptyEmail(), "Epic sadface: Username is required"},
                {withEmptyPassword(), "Epic sadface: Password is required"},
                {withWrongEmail(), "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "loginData")
    public void incorrectLogin(User user, String errorMsg) {
        System.out.println("LoginTest.incorrect in thread: " + Thread.currentThread().getId());
        loginPage.open();
        loginPage.login(user);
        assertTrue(loginPage.isErrorMsgDisplayed(), "Сообщение об ошибке не отображается");
        assertEquals(loginPage.getErrorMsgText(), errorMsg, "Неверный текст сооющения об ошибке");
    }
}
