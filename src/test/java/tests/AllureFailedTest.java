package tests;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Тесты корзины покупок")
@Feature("Основные функции корзины")
@Log4j2
public class AllureFailedTest extends BaseTest {

    @Test(description = "Проверка соответствия названия товара в корзине (намеренное падение)",enabled = false)
    @Story("Проверка добавления товара в корзину")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Pimenov S.I")
    @Link("https://www.saucedemo.com/")
    @TmsLink("ITM-4")
    @Issue("ITM-4-1")
    public void demoFailedTest() {
        log.info("Начало теста demoFailedTest");
        // 1. Авторизация (использует метод из BaseTest)
        log.info("Шаг 1: Авторизация стандартного пользователя");
        loginStandardUser();

        // 2. Добавление конкретного товара в корзину
        log.info("Шаг 2: Добавление товара 'Sauce Labs Backpack' в корзину");
        productsPage.addToCart("Sauce Labs Backpack");

        // 3. Получение названия товара из корзины
        log.info("Шаг 3: Открытие корзины и получение названия товара");
        String itemName = cartPage
                .open() // Открываем страницу корзины
                .getItemName(); // Получаем название первого товара
        log.info("Получено название товара в корзине: {}", itemName);

        // 4. Намеренно некорректная проверка
        log.info("Шаг 4: Намеренная некорректная проверка");
        Assert.assertEquals(itemName,
                "BLA BLA BLA",
                "Намеренная ошибка");
        log.info("Конец теста demoFailedTest");
    }
}
