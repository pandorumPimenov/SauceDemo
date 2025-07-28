package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    1. Открываем страницу логина
    2. Выполняем успешный логин
    3. Открываем корзину
    4. Проверяем, что корзина пуста
     */
    @Test(description = "Проверка, что корзина пуста после входа в систему",
            testName = "Тест пустой корзины",priority = 1)
    @Description("Проверка состояния корзины после авторизации - корзина должна быть пустой")
    public void testEmptyCart() {
        // Переход на страницу корзины
        cartPage.open();

        // Проверка, что корзина пуста
        Assert.assertFalse(cartPage.isItemPresent(), "Корзина должна быть пустой после входа");
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
        productsPage.addToCart("Sauce Labs Backpack");

        // Переход и проверки в корзине
        cartPage.open();

        // Проверки:
        Assert.assertTrue(cartPage.isItemPresent(), "Товар должен быть в корзине");
        Assert.assertEquals(cartPage.getItemName(), "Sauce Labs Backpack",
                "Название товара не совпадает");
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
        // Добавление товара в корзину
        productsPage.addToCart("Sauce Labs Bike Light");

        // Удаление товара из корзины
        cartPage.open();
        cartPage.removeItem();

        // Проверка, что корзина пуста после удаления
        Assert.assertFalse(cartPage.isItemPresent(),
                "Корзина должна быть пустой после удаления товара");
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
        // Проверка работы кнопки продолжения покупок
        cartPage.open();
        cartPage.continueShopping();

        // Проверка, что вернулись на страницу продуктов
        Assert.assertTrue(productsPage.isPageOpened(),
                "Должны вернуться на страницу продуктов после нажатия Continue Shopping");
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
        // Добавление товара в
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");

        // Переход к оформлению заказа
        cartPage.open();
        cartPage.checkout();

        // Проверка URL страницы оформления заказа
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"),
                "Должны перейти на страницу оформления заказа после нажатия Checkout");
    }
}
