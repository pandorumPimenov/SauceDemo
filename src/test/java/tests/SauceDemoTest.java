package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class SauceDemoTest extends BaseTest{

    @Test(groups = "checkout",
            description = "Оформление и покупка товара с проверками",
            testName = "Тест покупки")
    @Description("Проверка всего процесса покупки: от добавления товара до подтверждения заказа")
    public void testCompletePurchaseWithChaining() {

        // Тестовые данные
        final String PRODUCT_NAME = "Sauce Labs Backpack";
        final String FIRST_NAME = "Sergey";
        final String LAST_NAME = "Pimenov";
        final String ZIP_CODE = "270725";

        // 1. Цепочка действий и проверок
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

        // 2. Финальная проверка
        assertTrue(confirmationText.contains("Thank you"),
                "Подтверждение заказа должно содержать 'Thank you'. Фактически: " + confirmationText);
    }
}
