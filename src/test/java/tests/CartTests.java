package tests;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Log4j2
@Epic("Тесты корзины покупок")
@Feature("Основные функции корзины")
public class CartTests extends BaseTest {

    @BeforeMethod
    public void setUpTest() {
        log.info("Подготовка теста: авторизация пользователя");
        loginStandardUser().isPageOpened();
    }

    @Test(description = "Проверка пустой корзины после входа",
            testName = "Тест пустой корзины",
            priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Cart Functionality")
    @Owner("Pimenov S.I")
    @Description("Проверка что корзина пуста после авторизации")
    public void testEmptyCart() {
        log.info("Запуск теста проверки пустой корзины");
        cartPage
                .open()
                .isPageOpened(); // Проверяем только открытие страницы

        log.info("Проверка наличия товаров в корзине");
        Assert.assertFalse(cartPage.isItemPresent(),
                "Корзина должна быть пустой после входа");
    }

    @Test(description = "Проверка добавления товара в корзину",
            testName = "Тест добавления товара",
            priority = 2)
    @Severity(SeverityLevel.BLOCKER)
    @Story("Cart Functionality")
    @Owner("Pimenov S.I")
    @Description("Проверка корректности добавления товара в корзину")
    public void testAddItemToCart() {
        String productName = "Sauce Labs Backpack";
        log.info("Запуск теста добавления товара: {}", productName);

        productsPage
                .addToCart(productName)
                .goToCart()
                .isPageOpened();

        log.info("Проверка наличия товара в корзине");
        Assert.assertTrue(cartPage.isItemPresent(),
                "Товар должен быть в корзине");
        log.info("Проверка соответствия названия товара");
        Assert.assertEquals(cartPage.getItemName(), productName,
                "Название товара не совпадает");
    }

    @Test(description = "Проверка удаления товара из корзины",
            testName = "Тест удаления товара",
            priority = 3)
    @Severity(SeverityLevel.NORMAL)
    @Story("Cart Functionality")
    @Owner("Pimenov S.I")
    @Description("Проверка функционала удаления товара из корзины")
    public void testRemoveItemFromCart() {
        String productName = "Sauce Labs Bike Light";
        log.info("Запуск теста удаления товара: {}", productName);

        productsPage
                .addToCart(productName)
                .goToCart()
                .isPageOpened()
                .removeItem();

        log.info("Проверка отсутствия товаров в корзине после удаления");
        Assert.assertFalse(cartPage.isItemPresent(),
                "Корзина должна быть пустой после удаления");
    }

    @Test(description = "Проверка кнопки 'Continue Shopping'",
            testName = "Тест продолжения покупок",
            priority = 4)
    @Severity(SeverityLevel.NORMAL)
    @Story("Cart Navigation")
    @Owner("Pimenov S.I")
    @Description("Проверка возврата на страницу продуктов")
    public void testContinueShopping() {
        log.info("Запуск теста кнопки 'Continue Shopping'");
        cartPage
                .open()
                .isPageOpened()
                .continueShopping()
                .isPageOpened();

        log.info("Проверка возврата на страницу продуктов");
        Assert.assertTrue(productsPage.isOnProductsPage(),
                "Должны вернуться на страницу продуктов");
    }

    @Test(description = "Проверка перехода к оформлению заказа",
            testName = "Тест оформления заказа",
            priority = 5)
    @Severity(SeverityLevel.BLOCKER)
    @Story("Checkout Process")
    @Owner("Pimenov S.I")
    @Description("Проверка перехода на страницу оформления заказа")
    public void testCheckoutNavigation() {
        String productName = "Sauce Labs Bolt T-Shirt";
        log.info("Запуск теста оформления заказа для товара: {}", productName);

        productsPage
                .addToCart(productName)
                .goToCart()
                .isPageOpened()
                .checkout();

        log.info("Проверка URL страницы оформления заказа");
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"),
                "URL должен содержать checkout-step-one.html");
    }
}
