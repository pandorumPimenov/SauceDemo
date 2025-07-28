package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class SauceDemoTest extends BaseTest{

    @Test(description = "Проверка корректности добавления товара в корзину",
            testName = "Тест добавления товара в корзину")
    @Description("Проверка что выбранный товар правильно добавляется в корзину - " +
            "сохраняется название и цена, товар отображается в корзине")
    public void testAddToCartAndVerify() {
        // Логинимся стандартным пользователем (метод из BaseTest)
        loginStandardUser();

        // Проверяем что страница продуктов открыта
        softAssert.assertTrue(productsPage.isPageOpened(), "Страница с продуктами, открыта");

        // Получаем название и цену первого товара на странице продуктов
        String expectedItemName = productsPage.getFirstProductName();
        String expectedItemPrice = productsPage.getFirstProductPrice();

        // Добавляем первый товар в корзину
        productsPage.addFirstProductToCart();

        // Переходим в корзину
        productsPage.goToCart();

        // Проверяем что товар добавлен в корзину
        softAssert.assertTrue(cartPage.isItemPresent(), "Cart is empty");

        // Проверяем название и цену товара в корзине
        softAssert.assertEquals(cartPage.getItemName(), expectedItemName,
                "Название товара не совпадает");
        softAssert.assertEquals(cartPage.getItemPrice(), expectedItemPrice,
                "Цена товара не совпадает");

        softAssert.assertAll(); // Завершаем проверки
    }
}
