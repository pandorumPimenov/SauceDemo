package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ProductsPage;

// Тестовый класс для проверки функциональности корзины покупок:
public class CartTests extends BaseTest {

    @BeforeMethod
    public void setUpTest() {
        // Инициализация страницы логина
        loginStandardUser();
    }

    /*
    Тест проверки пустой корзины (пользователь ничего ранее в корзину не добавлял):
    Шаги:
    1. Авторизация
    2. Открытие корзины
    3. Проверка отсутствия товаров
     */
    @Test(description = "Проверка, что корзина пуста после входа в систему",
            testName = "Тест пустой корзины",priority = 1)
    @Description("Проверка состояния корзины после авторизации - корзина должна быть пустой")
    public void testEmptyCart() {
        boolean isCartEmpty = cartPage
                .open() // Переход на страницу корзины
                .isPageOpened()
                .isItemPresent();

        // Проверка, что корзина пуста
        Assert.assertFalse(isCartEmpty, "Корзина должна быть пустой после входа");
    }

    /*
    Тест добавления товара в корзину:
    Шаги:
    1. Логин в систему
    2. Добавление конкретного товара в корзину
    3. Переход в корзину
    4. Проверка наличия товара и его названия
     */
    @Test(description = "Проверка добавления товара в корзину",
            testName = "Тест добавления товара в корзину",priority = 2)
    @Description("Проверка корректности добавления товара в корзину: " +
            "товар должен отображаться с правильным названием")
    public void testAddItemToCart() {
        // Добавление товара в корзину
        String productName = "Sauce Labs Backpack";

        productsPage
                .addToCart(productName); // Добавляем товар

        String cartItemName = cartPage
                .open()
                .isPageOpened()
                .getItemName(); // Получаем название товара в корзине

        Assert.assertTrue(cartPage.isItemPresent(), "Товар должен быть в корзине");
        Assert.assertEquals(cartItemName, productName, "Название товара не совпадает");
    }

    /*
    Тест удаления товара из корзины.
   Шаги:
   1. Логин в систему
   2. Добавление товара в корзину
   3. Переход в корзину
   4. Удаление товара
   5. Проверка, что корзина пуста
     */
    @Test(description = "Проверка удаления товара из корзины",
            testName = "Тест удаления товара из корзины",priority = 3)
    @Description("Проверка функционала удаления товара из корзины - " +
            "после удаления корзина должна быть пустой")
    public void testRemoveItemFromCart() {
        String productName = "Sauce Labs Bike Light";

        productsPage
                .addToCart(productName); // Добавляем товар
        boolean isCartEmptyAfterRemoval = cartPage
                .open()
                .isPageOpened()
                .removeItem() // Удаляем товар
                .isItemPresent(); // Проверяем корзину

        Assert.assertFalse(isCartEmptyAfterRemoval, "Корзина должна быть пустой после удаления товара");
    }

    /*
    Тест работы кнопки "Continue Shopping".
    Шаги:
    1. Логин в систему
    2. Переход в корзину
    3. Нажатие кнопки продолжения покупок
    4. Проверка возврата на страницу продуктов
     */
    @Test(description = "Проверка работы кнопки 'Continue Shopping'",
            testName = "Тест кнопки 'Continue Shopping'",priority = 4)
    @Description("Проверка функционала кнопки продолжения покупок - " +
            "должен осуществляться возврат на страницу продуктов")
    public void testContinueShopping() {
        // Переходим в корзину и нажимаем "Continue Shopping"
        ProductsPage productsPage = cartPage
                .open()
                .isPageOpened()
                .continueShopping(); // Нажимаем кнопку продолжения

        // Проверяем что вернулись на страницу продуктов
        Assert.assertTrue(productsPage.isPageOpened() != null,
                "Должны вернуться на страницу продуктов");
    }

    /*
    Тест перехода к оформлению заказа.
    Шаги:
    1. Логин в систему
    2. Добавление товара в корзину
    3. Переход в корзину
    4. Нажатие кнопки оформления заказа
    5. Проверка перехода на страницу оформления
     */
    @Test(description = "Проверка перехода к оформлению заказа",
            testName = "Тест перехода к оформлению заказа",priority = 5)
    @Description("Проверка перехода на страницу оформления заказа при нажатии кнопки Checkout")
    public void testCheckoutNavigation() {
        productsPage
                .addToCart("Sauce Labs Bolt T-Shirt");

        String checkoutUrl = cartPage
                .open()
                .isPageOpened()
                .checkout()
                .getCurrentUrl();

        Assert.assertTrue(checkoutUrl.contains("checkout-step-one.html"),
                "Должны перейти на страницу оформления заказа");
    }
}
