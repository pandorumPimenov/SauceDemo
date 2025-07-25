package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class SauceDemoTest extends BaseTest{

    @Test(description = "Проверка корректности добавления товара в корзину",
            testName = "Тест добавления товара в корзину")
    @Description("Проверка что выбранный товар правильно добавляется в корзину - " +
            "сохраняется название и цена, товар отображается в корзине")
    public void testAddToCartAndVerify() {
        // 1. Авторизация и получение данных о товаре
        String productName = loginStandardUser() // Авторизуемся
                .getFirstProductName(); // Получаем название первого товара

        String productPrice = productsPage // Получаем цену товара
                .addFirstProductToCart() // Добавляем первый товар в корзину
                .getFirstProductPrice(); // Получаем цену товара

        // 2. Переход в корзину
        cartPage
                .open() // Открываем страницу корзины
                .isPageOpened(); // Проверяем что страница открылась

        softAssert.assertTrue(cartPage.isItemPresent(), "Корзина пуста");
        softAssert.assertEquals(cartPage.getItemName(), productName, "Название не совпадает");
        softAssert.assertEquals(cartPage.getItemPrice(), productPrice, "Цена не совпадает");
        softAssert.assertAll();
    }
}
