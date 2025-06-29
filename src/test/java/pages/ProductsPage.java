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

    // Открывает страницу с продуктами
    public void open() {
        driver.get(BASE_URL + "inventory.html");
    }

    // Проверяет, что страница продуктов открыта
    public boolean isPageOpened() {
        return driver.findElement(TITLE).isDisplayed();
    }

    /*
     Формирование XPath для поиска кнопки добавления в корзину:
    1. Находим div с текстом = названию товара
    2. Поднимаемся до родительского div с классом inventory_item
    3. Ищем внутри него кнопку (Add to cart)
     */
    public void addToCart(String productName) {
        driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button")).click();
    }
}
