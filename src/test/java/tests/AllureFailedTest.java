package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Тесты корзины покупок")
@Feature("Основные функции корзины")
public class AllureFailedTest extends BaseTest {

    @Test(description = "Проверка соответствия названия товара в корзине (намеренное падение)")
    @Story("Проверка добавления товара в корзину")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Pimenov S.I")
    @Link("https://www.saucedemo.com/")
    @TmsLink("ITM-4")
    @Issue("ITM-4-1")
    public void demoFailedTest() {
        // Шаг 1: Логин стандартного пользователя
        loginStandardUser();

        // Шаг 2: Проверка, что страница продуктов открыта
        Assert.assertTrue(productsPage.isPageOpened(),
                "Страница продуктов не открылась после логина");

        // Шаг 3: Добавляем товар в корзину
        productsPage.addToCart("Sauce Labs Backpack");

        // Шаг 4: Переходим в корзину
        cartPage.open();

        // Шаг 5: Проверяем товар в корзине (намеренная ошибка)
        Assert.assertEquals(cartPage.getItemName(),
                "BLA BLA BLA", // Намеренно неправильное ожидаемое значение
                "Название товара в корзине не совпадает");
    }
}