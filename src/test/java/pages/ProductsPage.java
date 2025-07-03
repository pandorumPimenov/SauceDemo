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

    // Конструктор класса
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

 // Шаблон XPath для кнопки "Add to cart" 
// %s будет заменено на реальное название продукта при вызове метода
private static final String ADD_TO_CART_PATTERN = 
    "//div[text()='%s']/ancestor::div[@class='inventory_item']//button";
 
    // Открывает страницу с продуктами
    public void open() {
        driver.get(BASE_URL + "inventory.html");
    }

    // Проверяет, что страница продуктов открыта
    public boolean isPageOpened() {
        return driver.findElement(TITLE).isDisplayed();
    }
 
     // Метод добавляет товар в корзину по его названию
    public void addToCart(String productName) {
        String xpath = String.format(ADD_TO_CART_PATTERN, productName);
     // Находим элемент кнопки "Add to cart" по XPath и кликаем по ней
        driver.findElement(By.xpath(xpath)).click();
    }
}
