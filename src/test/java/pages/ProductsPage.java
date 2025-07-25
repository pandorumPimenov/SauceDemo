package pages;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 Класс страницы продуктов (каталога)
 Наследует базовые методы от BasePage
 */
public class ProductsPage extends BasePage{

    // Локатор заголовка страницы (проверка, что мы на нужной странице)
    private final By TITLE = By.className("title");
    // Локатор - название первого товара
    private final By FIRST_ITEM_NAME = By.cssSelector(".inventory_item_name");
    // Локатор - цена первого товара
    private final By FIRST_ITEM_PRICE = By.cssSelector(".inventory_item_price");
    // Локатор - кнопка "Add to cart"
    private final By ADD_TO_CART_BUTTON = By.cssSelector("button[data-test^='add-to-cart']");
    // Локатор - иконка корзины
    private final By SHOPPING_CART_LINK = By.className("shopping_cart_link");
    // Шаблон XPath для кнопки "Add to cart"
    // %s будет заменено на реальное название продукта при вызове метода
    private static final String ADD_TO_CART_PATTERN =
            "//div[text()='%s']/ancestor::div[@class='inventory_item']//button";

    // Конструктор класса
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    // Открывает страницу с продуктами
    @Override
    @Step("Открыть страницу с продуктами")
    public ProductsPage open() {
        driver.get(BASE_URL + "inventory.html");
        return this;
    }

    // Проверяет, что страница продуктов открыта и
    // возвращает true если заголовок страницы отображается
    @Override
    @Step("Проверить, что страница продуктов открыта")
    public ProductsPage isPageOpened() {
        driver.findElement(TITLE).isDisplayed();
        return this;
    }

    @Step("Проверить видимость заголовка страницы продуктов")
    public boolean isTitleDisplayed() {
        return driver.findElement(TITLE).isDisplayed();
    }

    // Получает название первого товара в списке и
    // возвращает текст названия товара
    @Step("Получить название первого товара в списке")
    public String getFirstProductName() {
        return driver.findElement(FIRST_ITEM_NAME).getText();
    }

    // Получает цену первого товара в списке
    // и возвращает название товара
    @Step("Получить цену первого товара в списке")
    public String getFirstProductPrice() {
        return driver.findElement(FIRST_ITEM_PRICE).getText();
    }

    // Добавляет первый товар из списка в корзину
    @Step("Добавить первый товар из списка в корзину")
    public ProductsPage addFirstProductToCart() {
        driver.findElement(ADD_TO_CART_BUTTON).click();
        return this;
    }

    // Переходит в корзину
    @Step("Перейти в корзину")
    public CartPage goToCart() {
        driver.findElement(SHOPPING_CART_LINK).click();
        return new CartPage(driver).isPageOpened();
    }

    // Метод добавляет товар в корзину по его названию
    @Step("Добавить товар {productName} в корзину")
    public ProductsPage addToCart(String productName) {
        String xpath = String.format(ADD_TO_CART_PATTERN, productName);
        driver.findElement(By.xpath(xpath)).click();
        return this;
    }
}
