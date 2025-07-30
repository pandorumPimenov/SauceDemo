package pages;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
// Класс для страницы авторизации
public class LoginPage extends BasePage {
    // Локатор поля для ввода имени пользователя
    private static final By LOGIN_FIELD = By.id("user-name");

    // Локатор поля для ввода пароля
    private static final By PASSWORD_FIELD = By.id("password");

    // Локатор кнопки входа
    private static final By LOGIN_BUTTON = By.id("login-button");

    // Локатор сообщения об ошибке
    private static final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");

    public LoginPage(WebDriver driver) {
        super(driver);
        log.info("Инициализация страницы авторизации");
    }

    // Открывает страницу авторизации
    @Override
    @Step("Открытие страницы логина")
    public LoginPage open() {
        log.info("Открытие страницы авторизации по URL: {}", BASE_URL);
        driver.get(BASE_URL);
        return this; // Возвращает текущий экземпляр LoginPage
    }


    // Проверяет, что страница авторизации успешно загружена
    @Override
    @Step("Проверка открытия страницы логина")
    public LoginPage isPageOpened() {
        log.info("Проверка видимости полей авторизации");
        waitForElementToBeVisible(LOGIN_FIELD)
                .waitForElementToBeVisible(PASSWORD_FIELD);
        return this; // Возвращает текущий экземпляр LoginPage
    }

    // Выполняет вход в систему с указанными учетными данными
    @Step("Вход в систему с именем пользователя: {user} и паролем: {password}")
    public ProductsPage login(String user, String password) {
        log.info("Попытка авторизации пользователя: {},{}", user,password);
        isPageOpened();
        driver.findElement(LOGIN_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver); // Возвращает экземпляр ProductsPage (страница после успешного входа)
    }

    // Для проверки ошибок
    @Step("Попытка входа с невалидными данными: login = '{user}', password = '{password}'")
    public LoginPage attemptLogin(String user, String password) {
        log.info("Попытка входа с невалидными данными. Логин: {},{}", user,password);
        driver.findElement(LOGIN_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return this; // Возвращает текущий экземпляр LoginPage
    }

    // Получает текст сообщения об ошибке авторизации
    @Step("Получение текста ошибки при неудачном входе")
    public String getErrorMessage() {
        log.info("Получение текста ошибки авторизации");
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
