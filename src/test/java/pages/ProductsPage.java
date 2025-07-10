package pages;

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
    public void open() {
        driver.get(BASE_URL + "inventory.html");
    }

    // Проверяет, что страница продуктов открыта и
    // возвращает true если заголовок страницы отображается
    public boolean isPageOpened() {
        return driver.findElement(TITLE).isDisplayed();
    }
    // Получает название первого товара в списке и
    // возвращает текст названия товара
    public String getFirstProductName() {
        return driver.findElement(FIRST_ITEM_NAME).getText();
    }

    // Получает цену первого товара в списке
    // и возвращает название товара
    public String getFirstProductPrice() {
        return driver.findElement(FIRST_ITEM_PRICE).getText();
    }

    // Добавляет первый товар из списка в корзину
    public void addFirstProductToCart() {
        driver.findElement(ADD_TO_CART_BUTTON).click();
    }

    // Переходит в корзину
    public void goToCart() {
        driver.findElement(SHOPPING_CART_LINK).click();
    }
 
     // Метод добавляет товар в корзину по его названию
    public void addToCart(String productName) {
        String xpath = String.format(ADD_TO_CART_PATTERN, productName);
     // Находим элемент кнопки "Add to cart" по XPath и кликаем по ней
        driver.findElement(By.xpath(xpath)).click();
    }
}
