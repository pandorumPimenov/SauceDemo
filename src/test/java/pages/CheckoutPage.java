package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By FIRST_NAME_FIELD = By.id("first-name");
    private final By LAST_NAME_FIELD = By.id("last-name");
    private final By POSTAL_CODE_FIELD = By.id("postal-code");
    private final By CONTINUE_BUTTON = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Открыть страницу оформления заказа")
    public CheckoutPage open() {
        driver.get(BASE_URL + "checkout-step-one.html");
        return this;
    }

    @Override
    @Step("Проверить, что страница оформления заказа открыта")
    public CheckoutPage isPageOpened() {
        driver.findElement(FIRST_NAME_FIELD).isDisplayed();
        return this;
    }

    @Step("Заполнить данные для оформления заказа")
    public CheckoutOverviewPage fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
        driver.findElement(POSTAL_CODE_FIELD).sendKeys(postalCode);
        driver.findElement(CONTINUE_BUTTON).click();
        return new CheckoutOverviewPage(driver).isPageOpened();
    }
}
