package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Тесты корзины покупок")
@Feature("Основные функции корзины")
public class AllureFailedTest extends BaseTest {

    @Test(description = "Проверка соответствия названия товара в корзине (намеренное падение)",enabled = false)
    @Story("Проверка добавления товара в корзину")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Pimenov S.I")
    @Link("https://www.saucedemo.com/")
    @TmsLink("ITM-4")
    @Issue("ITM-4-1")
    public void demoFailedTest() {
        // 1. Авторизация (использует метод из BaseTest)
        loginStandardUser();

        // 2. Добавление конкретного товара в корзину
        productsPage.addToCart("Sauce Labs Backpack");

        // 3. Получение названия товара из корзины
        String itemName = cartPage
                .open() // Открываем страницу корзины
                .getItemName(); // Получаем название первого товара

        // 4. Намеренно некорректная проверка
        Assert.assertEquals(itemName,
                "BLA BLA BLA",
                "Намеренная ошибка");
    }
}
