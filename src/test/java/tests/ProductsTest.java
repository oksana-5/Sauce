package tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

public class ProductsTest extends BaseTest {
    List<String> goodsList = new ArrayList<>(
            List.of("Sauce Labs Fleece Jacket", "Sauce Labs Backpack", "Sauce Labs Bike Light")
    );

    @Test
    public void checkGoodsAdded() {
        System.out.println("ProductsTest.correct in thread: " + Thread.currentThread().getId());
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.isTitleIsDisplayed());
        assertEquals(productsPage.checkTitleName(), ("Products"));
        for (String goods : goodsList) {
            productsPage.addGoodsToCart(goods);
        }

        productsPage.addGoodsToCart(2);
        assertEquals(productsPage.checkCounterValue(), "4");
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
