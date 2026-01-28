package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void correctLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.isTitleIsDisplayed(), "Заголовок не отображается");
        assertEquals(productsPage.getTitleText(), "Products",
                "Неверный текст заголовка");
    }

    @Test
    public void incorrectLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        assertTrue(loginPage.isErrorMsgDisplayed(), "Сообщение об ошибке не отображается");
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Sorry, this user has been locked out.",
                "Неверный текст сооющения об ошибке");
    }

    @Test
    public void emptyUsername() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertTrue(loginPage.isErrorMsgDisplayed(), "Сообщение об ошибке не отображается");
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Username is required",
                "Неверный текст сооющения об ошибке");
    }

    @Test
    public void emptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertTrue(loginPage.isErrorMsgDisplayed(), "Сообщение об ошибке не отображается");
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Password is required",
                "Неверный текст сооющения об ошибке");
    }

    @Test
    public void incorrectUsername() {
        loginPage.open();
        loginPage.login("Epic sadface: Username and password do not match any user in this service", "secret_sauce");
        assertTrue(loginPage.isErrorMsgDisplayed(), "Сообщение об ошибке не отображается");
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Username and password do not match any user in this service",
                "Неверный текст сооющения об ошибке");
    }

}
