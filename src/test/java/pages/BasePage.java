package pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected final String BASE_URL = "https://www.saucedemo.com/";
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // Абстрактный метод для открытия страницы.
    public abstract BasePage open();

    // Абстрактный метод для проверки открытия страницы.
    public abstract BasePage isPageOpened();

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
