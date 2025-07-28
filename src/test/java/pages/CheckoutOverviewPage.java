package pages;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
// Класс для страницы подтверждения заказа (Checkout Overview)
public class CheckoutOverviewPage extends BasePage {
    // Локатор названия товара на странице подтверждения
    private static final By ITEM_NAME = By.className("inventory_item_name");

    // Локатор блока с итоговой суммой заказа
    private static final By TOTAL_LABEL = By.className("summary_total_label");

    // Локатор кнопки завершения оформления заказа
    private static final By FINISH_BUTTON = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        log.info("Инициализация страницы подтверждения заказа");
    }

    // Открывает страницу подтверждения заказа.
    @Override
    public CheckoutOverviewPage open() {
        log.info("Открываю страницу подтверждения заказа: {}", BASE_URL + "checkout-step-two.html");
        driver.get(BASE_URL + "checkout-step-two.html");
        return this; // Возвращает текущий экземпляр CheckoutOverviewPage
    }

    // Проверяет, что страница подтверждения заказа успешно загружена.
    @Override
    public CheckoutOverviewPage isPageOpened() {
        log.info("Ожидание видимости элемента: {}", FINISH_BUTTON);
        waitForElementToBeVisible(FINISH_BUTTON);
        return this; // Возвращает текущий экземпляр CheckoutOverviewPage
    }

    // Получает название товара на странице подтверждения
    @Step("Получить название товара на странице подтверждения")
    public String getItemName() {
        log.info("Получение названия товара из элемента: {}", ITEM_NAME);
        waitForElementToBeVisible(ITEM_NAME);
        return driver.findElement(ITEM_NAME).getText();
    }

    // Получает итоговую сумму заказа
    @Step("Получить итоговую сумму заказа")
    public String getTotalPrice() {
        log.info("Получение суммы заказа из элемента: {}", TOTAL_LABEL);
        waitForElementToBeVisible(TOTAL_LABEL);
        return driver.findElement(TOTAL_LABEL).getText();
    }

    // Завершает процесс оформления заказа
    public CheckoutCompletePage finishCheckout() {
        log.info("Нажатие кнопки завершения заказа: {}", FINISH_BUTTON);
        driver.findElement(FINISH_BUTTON).click();
        return new CheckoutCompletePage(driver); // Переход на следующую страницу
    }

    @Step("Проверить данные на странице подтверждения: {expectedName}")
    public CheckoutOverviewPage validateCheckoutData(String expectedName) {
        log.info("Начало проверки заказа. Ожидаемый товар: {}", expectedName);
        String totalPrice = getTotalPrice().replaceAll("[^0-9.]", "");
        String actualName = getItemName();

        // Проверка названия товара
        assertEquals(actualName, expectedName,
                "Название товара на странице подтверждения не совпадает. Ожидалось: " + expectedName + ", Фактически: " + actualName);

        // Проверка, что цена положительная
        assertTrue(Double.parseDouble(totalPrice) > 0,
                "Итоговая сумма должна быть положительной. Фактическая сумма: " + totalPrice);
        log.info("Проверка завершена. Товар: {}, Сумма: {}", actualName, totalPrice);
        return this;
    }
}
