package tests;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

@Log4j2
public class SauceDemoTest extends BaseTest{

    @Test(description = "Оформление и покупка товара с проверками",
            testName = "Тест покупки")
    @Description("Проверка всего процесса покупки: от добавления товара до подтверждения заказа")
    public void testCompletePurchaseWithChaining() {

        // Тестовые данные
        final String PRODUCT_NAME = "Sauce Labs Backpack";
        final String FIRST_NAME = "Sergey";
        final String LAST_NAME = "Pimenov";
        final String ZIP_CODE = "270725";

        log.info("Начало  теста полного цикла оформления покупки");
        log.info("Тестовые данные:");
        log.info("Товар: {}, Имя: {}, Фамилия: {}, Индекс: {}",
                PRODUCT_NAME, FIRST_NAME, LAST_NAME, ZIP_CODE);

        // 1. Цепочка действий и проверок
        log.info("Шаг 1: Авторизация и проверка страницы продуктов");
        String confirmationText = loginStandardUser()
                .isPageOpened()

                // Проверка и получение данных товара
                .validateProductData(PRODUCT_NAME)

                // Работа с корзиной
                .addToCart(PRODUCT_NAME)
                .goToCart()
                .isPageOpened()
                .validateCartItem(PRODUCT_NAME)

                // Оформление заказа
                .checkout()
                .fillInformation(FIRST_NAME, LAST_NAME, ZIP_CODE)
                .isPageOpened()
                .validateCheckoutData(PRODUCT_NAME)

                // Завершение заказа
                .finishCheckout()
                .getConfirmationText();

        log.info("Получено подтверждение заказа: '{}'", confirmationText);

        // 2. Финальная проверка
        assertTrue(confirmationText.contains("Thank you"),
                "Подтверждение заказа должно содержать 'Thank you'. Фактически: " + confirmationText);
        log.info("Тест успешно завершен: покупка товара '{}' подтверждена", PRODUCT_NAME);
    }
}
