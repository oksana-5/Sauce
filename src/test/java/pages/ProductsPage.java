package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    private static final String ADD_TO_CART_PATTERN = "//*[text()='%s']" + "//ancestor::div[@class='inventory_item']//child::button[text()='Add to cart']";
    private final By cartCounter = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));
    private final By cartLink = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-link"));


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем видимость заголовка")
    public boolean isTitleIsDisplayed() {
        return findElement(title).isDisplayed();
    }

    @Step("Добавляем товары в корзину по имени: {goodsName})")
    public void addGoodsToCart(String goodsName) {
        By addToCart = By.xpath(ADD_TO_CART_PATTERN.formatted(goodsName));
        findElement(addToCart).click();
    }

    @Step("Добавляем товары в корзину по индексу: {goodsIndex}")
    public void addGoodsToCart(int goodsIndex) {
        driver.findElements(By.xpath("//*[text()='Add to cart']")).get(goodsIndex).click();
    }

    @Step("Проверяем количество товаров в корзине")
    public String checkCounterValue() {
        return findElement(cartCounter).getText();
    }

    @Step("Проверяем цвет счётчика товаров в коризине")
    public String checkCounterColor() {
        return findElement(cartCounter).getCssValue("background-color");
    }

    @Step("Переключаемся на корзину")
    public void switchToCart() {
       findElement(cartLink).click();
    }
}
