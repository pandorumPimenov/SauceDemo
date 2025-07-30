package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@Log4j2
public abstract class BasePage {

    protected final String BASE_URL = "https://www.saucedemo.com/";
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Конструктор базовой страницы
    public BasePage(WebDriver driver) {
        log.info("Инициализация базовой страницы с драйвером");
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Абстрактный метод для открытия страницы.
    public abstract BasePage open();

    // Абстрактный метод для проверки открытия страницы.
    public abstract BasePage isPageOpened();

    // Ожидает, когда элемент станет кликабельным
    protected BasePage waitForElementToBeClickable(By locator) {
        log.info("Ожидание кликабельности элемента: {}", locator);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            log.info("Элемент {} теперь кликабелен", locator);
        } catch (Exception e) {
            log.error("Не удалось дождаться кликабельности элемента {}: {}", locator, e.getMessage());
            throw e;
        }
        return this;
    }

    // Ожидает, когда элемент станет видимым на странице
    protected BasePage waitForElementToBeVisible(By locator) {
        log.info("Ожидание видимости элемента: {}", locator);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            log.info("Элемент {} теперь видим на странице", locator);
        } catch (Exception e) {
            log.error("Не удалось дождаться видимости элемента {}: {}", locator, e.getMessage());
            throw e;
        }
        return this;
    }
}
