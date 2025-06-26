import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;

public class LocatorsTest {
    @Test
    public void checkLocators() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://www.saucedemo.com/");

        // 1. Локатор id
        driver.findElement(By.id("user-name"));

        // 2. Локатор name
        driver.findElement(By.name("login-button"));

        // 3. Локатор className
        driver.findElement(By.className("form_column"));

        // 4. Локатор tagName
        driver.findElement(By.tagName("input"));

        // Для поиска следующего локатора необходимо авторизоваться: ввод логина/пароля и нажатие кнопки "Login"
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // 5. Локатор linkText
        driver.findElement(By.linkText("Twitter"));

        // Для поиска следующего локатора совершаем клик по бургер-меню
        driver.findElement(By.id("react-burger-menu-btn")).click();
        // 6. Локатор partialLinkText
        driver.findElement(By.partialLinkText("State"));
        // Закрытие меню
        driver.findElement(By.id("react-burger-cross-btn")).click();

        // 7. Поиск по атрибуту, например By.xpath("//tag[@attribute='value']");
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']"));

        // 8. Поиск по тексту, например By.xpath("//tag[text()='text']");
        driver.findElement(By.xpath("//div[text()='Swag Labs']"));

        // 9. Поиск по частичному совпадению атрибута, например
        // By.xpath("//tag[contains(@attribute,'text')]");
        driver.findElement(By.xpath("//button[contains(@id,'react-burger-menu-btn')]"));

        // 10. Поиск по частичному совпадению текста, например
        // By.xpath("//tag[contains(text(),'text')]");
        driver.findElement(By.xpath("//div[contains(text(),'Backp')]"));

        // 11. ancestor, например //*[text()='Enterprise Testing']//ancestor::div
        // div.inventory_item, который является предком для текста "Sauce Labs Backpack"
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']"));

        // 12. descendant (Найти кнопку "Add to cart" для первого товара в списке)
        driver.findElement(By.xpath("//div[@class='inventory_list']//descendant::button[contains(text(), 'Add to cart')][1]"));

        // 13. following (Найти следующий товар после "Sauce Labs Backpack")
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']/following::div[@class='inventory_item'][1]"));

        // 14. parent (Найти родителя кнопки с текстом "Add to cart")
        driver.findElement(By.xpath("//button[contains(text(), 'Add to cart')]/parent::div"));

        // 15. preceding (Найти предыдущий товар перед "Sauce Labs Bike Light")
        driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']/preceding::div[@class='inventory_item'][1]"));

        // 16. Поиск элемента с условием AND (Найти заголовок товара "Sauce Labs Bike Light")
        driver.findElement(By.xpath("//div[@class='inventory_item_name ' and text()='Sauce Labs Bike Light']"));

        // CSS:
        // 17. .class (Найти элемент по классу inventory_item (например, товар в списке))
        driver.findElement(By.cssSelector(".inventory_item"));

        // 18. .class1.class2 (Найти элемент (кнопку) с двумя классами одновременно)
        driver.findElement(By.cssSelector(".btn.btn_primary"));

        // 19. .class1 .class2 (Поиск вложенного элемента - название товара ("Sauce Labs Backpack"),
        // которое находится ВНУТРИ элемента с классом "inventory_item")
        driver.findElement(By.cssSelector(".inventory_item .inventory_item_name"));

        // 20. #id
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-bike-light"));

        // 21. tagName (Найти все элементы с указанным тегом)
        driver.findElements(By.cssSelector("a"));

        // 22. tagName.class (Найти все карточки товаров - комбинация тега и класса)
        driver.findElements(By.cssSelector("div.inventory_item"));

        // 23. [attribute=value] - точное совпадение значения атрибута
        driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']"));

        // 24. [attribute~=value] - ищет значение в списке, разделённом пробелами
        driver.findElement(By.cssSelector("[class~='btn']"));

        // 25. [attribute|=value] - ищет элементы, у которых атрибут начинается с указанного значения,
        // за которым следует дефис или оно полностью совпадает
        driver.findElement(By.cssSelector("[id|=add-to-cart]"));

        // 26. [attribute^=value] - атрибут начинается с
        driver.findElement(By.cssSelector("[id^='add-to-cart']"));

        // 27. [attribute$=value] - атрибут заканчивается на
        driver.findElement(By.cssSelector("[id$='backpack']"));

        // 28. [attribute*=value] - частичное совпадение
        driver.findElement(By.cssSelector("[id*='cart']"));

        driver.quit();
    }
}
