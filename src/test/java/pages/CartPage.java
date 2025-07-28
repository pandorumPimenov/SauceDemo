package pages;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


/*
 Класс страницы корзины покупок.
 Предоставляет методы для взаимодействия с элементами корзины:
 - Проверка наличия товаров
 - Удаление товаров
 - Навигация (продолжить покупки/оформление заказа)
 - Получение информации о товарах
 */
@Log4j2
public class CartPage extends BasePage {

    // Локатор контейнера товара в корзине
    private static final By CART_ITEM = By.className("cart_item");

    // Локатор кнопки удаления товара
    private static final By REMOVE_BUTTON = By.cssSelector("button[class*='btn_secondary']");

    // Локатор кнопки "Continue Shopping"
    private static final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");

    // Локатор кнопки "Checkout"
    private static final By CHECKOUT_BUTTON = By.id("checkout");

    // Локатор названия товара в корзине
    private static final By ITEM_NAME = By.className("inventory_item_name");

    // Локатор цены товара в корзине
    private static final By ITEM_PRICE = By.cssSelector(".inventory_item_price");

    // Локатор заголовка страницы корзины
    private static final By CART_TITLE = By.className("title");

    // Конструктор класса CartPage
    public CartPage(WebDriver driver) {
        super(driver);
        log.info("Инициализация страницы корзины");
    }

    // Открывает страницу корзины
    @Override
    @Step("Открыть страницу корзины")
    public CartPage open() {
        log.info("Открытие страницы корзины по URL: {}", BASE_URL + "cart.html");
        driver.get(BASE_URL + "cart.html");
        return this;
    }

    // Открывает страницу корзины
    @Override
    @Step("Проверить, что страница корзины открыта")
    public CartPage isPageOpened() {
        log.info("Проверка видимости заголовка корзины");
        waitForElementToBeVisible(CART_TITLE);
        log.info("Страница корзины успешно открыта");
        return this; // Возвращает текущий экземпляр CartPage
    }

    // Проверяет наличие товаров в корзине
    // true если есть хотя бы один товар, false если корзина пуста
    @Step("Проверить наличие товаров в корзине")
    public boolean isItemPresent() {
        log.info("Проверка наличия товаров в корзине");
        // findElements возвращает пустой список, если элементы не найдены
        return !driver.findElements(CART_ITEM).isEmpty();
    }

    // Удаляет товар из корзины
    // Удаляет первый найденный товар (если в корзине несколько товаров)
    @Step("Удалить товар из корзины")
    public CartPage removeItem() {
        log.info("Удаление товара из корзины");
        waitForElementToBeClickable(REMOVE_BUTTON);
        driver.findElement(REMOVE_BUTTON).click();
        return this; // Возвращает текущий экземпляр CartPage
    }

    // Нажимает кнопку "Continue Shopping"
    // Возвращает пользователя на страницу продуктов
    @Step("Нажать кнопку 'Continue Shopping'")
    public ProductsPage continueShopping() {
        log.info("Нажатие кнопки 'Continue Shopping'");
        waitForElementToBeClickable(CONTINUE_SHOPPING_BUTTON);
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
        return new ProductsPage(driver); // Возвращает экземпляр ProductsPage (страница с товарами)
    }

    // Нажимает кнопку "Checkout"
    // Переводит пользователя на страницу оформления заказа
    @Step("Нажать кнопку 'Checkout'")
    public CheckoutPage checkout() {
        log.info("Нажатие кнопки 'Checkout'");
        waitForElementToBeClickable(CHECKOUT_BUTTON);
        driver.findElement(CHECKOUT_BUTTON).click();
        return new CheckoutPage(driver); // Возвращает экземпляр CheckoutPage (страница оформления заказа)
    }

    // Получает название товара в корзине
    // и возвращает текст названия первого товара в корзине
    @Step("Получить название товара в корзине")
    public String getItemName() {
        log.info("Получение названия товара в корзине");
        return driver.findElement(ITEM_NAME).getText();
    }

    // Получает цену товара в корзине
    // и возвращает текст цены первого товара в корзине
    @Step("Получить цену товара в корзине")
    public String getItemPrice() {
        log.info("Получение цены товара в корзине");
        return driver.findElement(ITEM_PRICE).getText();
    }

    @Step("Проверить товар в корзине: {expectedName}")
    public CartPage validateCartItem(String expectedName) {
        log.info("Проверка товара в корзине. Ожидаемое название: {}", expectedName);
        String actualName = getItemName();
        assertEquals(actualName, expectedName,
                "Название товара в корзине не совпадает. Ожидалось: " + expectedName + ", Фактически: " + actualName);
        assertTrue(isItemPresent(), "Товар должен отображаться в корзине");
        return this;
    }
}
