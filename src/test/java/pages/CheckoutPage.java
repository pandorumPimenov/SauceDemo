package pages;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
// Класс для страницы оформления заказа (первый шаг)
public class CheckoutPage extends BasePage {

    // Локатор поля для ввода имени
    private static final By FIRST_NAME_FIELD = By.id("first-name");

    // Локатор поля для ввода фамилии
    private static final By LAST_NAME_FIELD = By.id("last-name");

    // Локатор поля для ввода почтового индекса
    private static final By POSTAL_CODE_FIELD = By.id("postal-code");

    // Локатор кнопки продолжения оформления
    private static final By CONTINUE_BUTTON = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        super(driver);
        log.info("Инициализация CheckoutPage");
    }

    // Открывает страницу оформления заказа
    @Override
    @Step("Открыть страницу оформления заказа")
    public CheckoutPage open() {
        log.info("Открытие страницы оформления заказа по URL: {}", BASE_URL + "checkout-step-one.html");
        driver.get(BASE_URL + "checkout-step-one.html");
        return this; // Возвращает текущий экземпляр CheckoutPage
    }


    // Проверяет, что страница оформления заказа успешно загружена
    @Override
    @Step("Проверить, что страница оформления заказа открыта")
    public CheckoutPage isPageOpened() {
        log.info("Проверка открытия страницы оформления заказа");
        waitForElementToBeVisible(FIRST_NAME_FIELD);
        return this; // Возвращает текущий экземпляр CheckoutPage
    }

    // Заполняет форму персональных данных и переходит к следующему шагу
    @Step("Заполнить данные для оформления заказа")
    public CheckoutOverviewPage fillInformation(String firstName, String lastName, String postalCode) {
        log.info("Заполнение данных для оформления: Имя={}, Фамилия={}, Индекс={}",
                firstName, lastName, postalCode);
        log.info("Ввод имени в поле: {}", FIRST_NAME_FIELD);
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName); // Заполняем поле имени
        log.info("Ввод фамилии в поле: {}", LAST_NAME_FIELD);
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName); // Заполняем поле фамилии
        log.info("Ввод почтового индекса в поле: {}", POSTAL_CODE_FIELD);
        driver.findElement(POSTAL_CODE_FIELD).sendKeys(postalCode);  // Заполняем поле почтового индекса
        log.info("Нажатие кнопки продолжения: {}", CONTINUE_BUTTON);
        driver.findElement(CONTINUE_BUTTON).click(); // Нажимаем кнопку продолжения
        log.info("Переход на страницу подтверждения заказа");
        return new CheckoutOverviewPage(driver); // Возвращаем новый Page Object для следующей страницы
    }
}
