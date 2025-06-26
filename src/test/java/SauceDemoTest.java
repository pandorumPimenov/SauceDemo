import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.HashMap;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SauceDemoTest {
    WebDriver driver; // Объявление переменной driver для управления браузером через WebDriver
    SoftAssert softAssert; // Объявление переменной softAssert для "мягких" проверок

    @BeforeMethod
    public void setup() {
        softAssert = new SoftAssert();

        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void testAddToCartAndVerify() {
        // a. Залогиниться
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Проверка успешного логина
        assertTrue(driver.findElement(By.className("title")).isDisplayed());

        // b. Сохраняем название и цену товара
        String expectedItemName = driver.findElement(
                By.cssSelector(".inventory_item_name")).getText();
        String expectedItemPrice = driver.findElement(
                By.cssSelector(".inventory_item_price")).getText();
        driver.findElement(
                By.cssSelector("button[data-test^='add-to-cart']")).click();

        // c. Перейти в корзину
        driver.findElement(By.className("shopping_cart_link")).click();

        // d. Проверка данных в корзине
        assertEquals(
                driver.findElement(By.cssSelector(".inventory_item_name")).getText(),
                expectedItemName,
                "Название товара не совпадает");

        assertEquals(
                driver.findElement(By.cssSelector(".inventory_item_price")).getText(),
                expectedItemPrice,
                "Цена товара не совпадает");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
