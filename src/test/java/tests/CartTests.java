package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductsPage;


// Тестовый класс для проверки функциональности корзины покупок:
public class CartTests extends BaseTest {

    /*
    Тест проверки пустой корзины (пользователь ничего ранее в корзину не добавлял):
    Шаги:
    1. Открываем страницу логина
    2. Выполняем успешный логин
    3. Открываем корзину
    4. Проверяем, что корзина пуста
     */
    @Test
    public void testEmptyCart() {
        // Инициализация страницы логина
        loginStandardUser();

        // Переход на страницу корзины
        CartPage cartPage = new CartPage(driver);
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
    @Test
    public void testAddItemToCart() {
        // Инициализация страницы логина
        loginStandardUser();

        // Добавление товара в корзину
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCart("Sauce Labs Backpack");

        // Переход и проверки в корзине
        CartPage cartPage = new CartPage(driver);
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
    @Test
    public void testRemoveItemFromCart() {
        // Инициализация страницы логина
        loginStandardUser();

        // Добавление товара в корзину
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCart("Sauce Labs Bike Light");

        // Удаление товара из корзины
        CartPage cartPage = new CartPage(driver);
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
    @Test
    public void testContinueShopping() {
        // Инициализация страницы логина
        loginStandardUser();

        // Проверка работы кнопки продолжения покупок
        CartPage cartPage = new CartPage(driver);
        cartPage.open();
        cartPage.continueShopping();

        // Проверка, что вернулись на страницу продуктов
        ProductsPage productsPage = new ProductsPage(driver);
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
    @Test
    public void testCheckoutNavigation() {
        // Инициализация и выполнение логина
        loginStandardUser();

        // Добавление товара в корзину
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");

        // Переход к оформлению заказа
        CartPage cartPage = new CartPage(driver);
        cartPage.open();
        cartPage.checkout();

        // Проверка URL страницы оформления заказа
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"),
                "Должны перейти на страницу оформления заказа после нажатия Checkout");
    }
}
