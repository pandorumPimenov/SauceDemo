package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Epic("Тесты корзины покупок")
@Feature("Основные функции корзины")
public class CartTests extends BaseTest {

    @BeforeMethod
    public void setUpTest() {
        loginStandardUser().isPageOpened();
    }

    @Test(groups = "cart_operations",
            description = "Проверка пустой корзины после входа",
            testName = "Тест пустой корзины",
            priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Cart Functionality")
    @Owner("Pimenov S.I")
    @Description("Проверка что корзина пуста после авторизации")
    public void testEmptyCart() {
        cartPage
                .open()
                .isPageOpened(); // Проверяем только открытие страницы

        Assert.assertFalse(cartPage.isItemPresent(),
                "Корзина должна быть пустой после входа");
    }

    @Test(groups = "cart_operations",
            description = "Проверка цены товара в корзине",
            priority = 2)
    @Severity(SeverityLevel.BLOCKER)
    @Story("Cart Functionality")
    @Owner("Pimenov S.I")
    public void testItemPriceInCart() {
        String productName = "Sauce Labs Backpack";
        String expectedPrice = "$29.99";

        productsPage
                .addToCart(productName)
                .goToCart()
                .isPageOpened();

        String actualPrice = cartPage.getItemPrice();
        assertEquals(actualPrice, expectedPrice, "Цена товара не совпадает");
    }

    @Test(groups = "cart_operations",
            description = "Проверка добавления товара в корзину",
            testName = "Тест добавления товара",
            priority = 3)
    @Severity(SeverityLevel.BLOCKER)
    @Story("Cart Functionality")
    @Owner("Pimenov S.I")
    @Description("Проверка корректности добавления товара в корзину")
    public void testAddItemToCart() {
        String productName = "Sauce Labs Backpack";

        productsPage
                .addToCart(productName)
                .goToCart()
                .isPageOpened();

        Assert.assertTrue(cartPage.isItemPresent(),
                "Товар должен быть в корзине");
        assertEquals(cartPage.getItemName(), productName,
                "Название товара не совпадает");
    }

    @Test(groups = "cart_operations",
            description = "Проверка удаления товара из корзины",
            testName = "Тест удаления товара",
            priority = 4)
    @Severity(SeverityLevel.NORMAL)
    @Story("Cart Functionality")
    @Owner("Pimenov S.I")
    @Description("Проверка функционала удаления товара из корзины")
    public void testRemoveItemFromCart() {
        String productName = "Sauce Labs Bike Light";

        productsPage
                .addToCart(productName)
                .goToCart()
                .isPageOpened()
                .removeItem();

        Assert.assertFalse(cartPage.isItemPresent(),
                "Корзина должна быть пустой после удаления");
    }

    @Test(groups = "cart_operations",
            description = "Проверка кнопки 'Continue Shopping'",
            testName = "Тест продолжения покупок",
            priority = 5)
    @Severity(SeverityLevel.NORMAL)
    @Story("Cart Navigation")
    @Owner("Pimenov S.I")
    @Description("Проверка возврата на страницу продуктов")
    public void testContinueShopping() {
        cartPage
                .open()
                .isPageOpened()
                .continueShopping()
                .isPageOpened();

        Assert.assertTrue(productsPage.isOnProductsPage(),
                "Должны вернуться на страницу продуктов");
    }

    @Test(groups = "cart_operations",
            description = "Проверка перехода к оформлению заказа",
            testName = "Тест оформления заказа",
            priority = 6)
    @Severity(SeverityLevel.BLOCKER)
    @Story("Checkout Process")
    @Owner("Pimenov S.I")
    @Description("Проверка перехода на страницу оформления заказа")
    public void testCheckoutNavigation() {
        String productName = "Sauce Labs Bolt T-Shirt";

        productsPage
                .addToCart(productName)
                .goToCart()
                .isPageOpened()
                .checkout();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"),
                "URL должен содержать checkout-step-one.html");
    }
}
