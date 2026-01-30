package tests;

import org.testng.annotations.DataProvider;
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

    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"Epic sadface: Username and password do not match any user in this service", "secret_sauce",
                        "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "loginData")
    public void incorrectLogin(String user, String password, String errorMsg) {
        loginPage.open();
        loginPage.login(user, password);
        assertTrue(loginPage.isErrorMsgDisplayed(), "Сообщение об ошибке не отображается");
        assertEquals(loginPage.getErrorMsgText(), errorMsg, "Неверный текст сооющения об ошибке");
    }
}
