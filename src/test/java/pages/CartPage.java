package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 Класс страницы корзины покупок.
 Предоставляет методы для взаимодействия с элементами корзины:
 - Проверка наличия товаров
 - Удаление товаров
 - Навигация (продолжить покупки/оформление заказа)
 - Получение информации о товарах
 */
public class CartPage extends BasePage {

    // Локатор контейнера товара в корзине
    private final By CART_ITEM = By.className("cart_item");

    // Локатор кнопки удаления товара
    private final By REMOVE_BUTTON = By.cssSelector("button[class*='btn_secondary']");

    // Локатор кнопки "Continue Shopping"
    private final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");

    // Локатор кнопки "Checkout"
    private final By CHECKOUT_BUTTON = By.id("checkout");

    // Локатор названия товара в корзине
    private final By ITEM_NAME = By.className("inventory_item_name");

    // Конструктор класса CartPage
    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Открывает страницу корзины
    public void open() {
        driver.get(BASE_URL + "cart.html"); // Передаем driver в родительский класс BasePage
    }

    // Проверяет наличие товаров в корзине
    public boolean isItemPresent() {
        return !driver.findElements(CART_ITEM).isEmpty(); // true если есть хотя бы один товар, false если корзина пуста
    }

    // Удаляет товар из корзины
    // Удаляет первый найденный товар (если в корзине несколько товаров)
    public void removeItem() {
        driver.findElement(REMOVE_BUTTON).click();
    }

    // Нажимает кнопку "Continue Shopping"
    // Возвращает пользователя на страницу продуктов
    public void continueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    // Нажимает кнопку "Checkout"
    // Переводит пользователя на страницу оформления заказа
    public void checkout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    // Получает название товара в корзине
    public String getItemName() {
        return driver.findElement(ITEM_NAME).getText(); // Получим название первого товара в корзине
    }
}
