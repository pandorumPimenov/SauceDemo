package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private final By COMPLETE_HEADER = By.className("complete-header");
    private final By BACK_HOME_BUTTON = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutCompletePage open() {
        driver.get(BASE_URL + "checkout-complete.html");
        return this;
    }

    @Override
    public CheckoutCompletePage isPageOpened() {
        driver.findElement(COMPLETE_HEADER).isDisplayed();
        return this;
    }

    @Step("Получить текст сообщения об успешном заказе")
    public String getCompleteText() {
        return driver.findElement(COMPLETE_HEADER).getText();
    }

    @Step("Вернуться на страницу продуктов")
    public ProductsPage backToProducts() {
        driver.findElement(BACK_HOME_BUTTON).click();
        return new ProductsPage(driver).isPageOpened();
    }
}
