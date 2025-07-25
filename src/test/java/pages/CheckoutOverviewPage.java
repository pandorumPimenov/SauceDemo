package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    // Локаторы элементов страницы
    private final By ITEM_NAME = By.className("inventory_item_name");
    private final By FINISH_BUTTON = By.id("finish");
    private final By CANCEL_BUTTON = By.id("cancel");
    private final By SUBTOTAL_LABEL = By.className("summary_subtotal_label");
    private final By TAX_LABEL = By.className("summary_tax_label");
    private final By TOTAL_LABEL = By.className("summary_total_label");
    private final By COMPLETE_HEADER = By.className("complete-header");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutOverviewPage open() {
        driver.get(BASE_URL + "checkout-step-two.html");
        return this;
    }

    @Override
    public CheckoutOverviewPage isPageOpened() {
        driver.findElement(FINISH_BUTTON).isDisplayed();
        return this;
    }

    @Step("Получить название товара на странице подтверждения")
    public String getItemName() {
        return driver.findElement(ITEM_NAME).getText();
    }

    @Step("Завершить оформление заказа")
    public CheckoutCompletePage finishCheckout() {
        driver.findElement(FINISH_BUTTON).click();
        return new CheckoutCompletePage(driver).isPageOpened();
    }

    @Step("Отменить оформление заказа")
    public ProductsPage cancelCheckout() {
        driver.findElement(CANCEL_BUTTON).click();
        return new ProductsPage(driver).isPageOpened();
    }

    @Step("Получить итоговую сумму")
    public String getTotalAmount() {
        return driver.findElement(TOTAL_LABEL).getText();
    }

    @Step("Проверить наличие сообщения об успешном оформлении")
    public boolean isOrderComplete() {
        return driver.findElement(COMPLETE_HEADER).isDisplayed();
    }
}
