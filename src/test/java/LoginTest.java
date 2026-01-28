import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void correctLogin() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@data-test='login-button']")).click();
        assertTrue(driver.findElement(By.cssSelector("[data-test='title']")).isDisplayed(),
                "Заголовок не отображается");
        assertEquals(driver.findElement(By.cssSelector("[data-test='title']")).getText(), "Products",
                "Неверный текст заголовка");
    }

    @Test
    public void incorrectLogin() {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.xpath("//*[@data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@data-test='login-button']")).click();
        assertTrue(driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed(),
                "Сообщение об ошибке не отображается");
        assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Неверный текст сооющения об ошибке");
    }
}
