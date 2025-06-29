package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SauceDemoTest extends BaseTest{

    @Test
    public void testAddToCartAndVerify() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        assertTrue(driver.findElement(By.className("title")).isDisplayed());
        String expectedItemName = driver.findElement(
                By.cssSelector(".inventory_item_name")).getText();
        String expectedItemPrice = driver.findElement(
                By.cssSelector(".inventory_item_price")).getText();
        driver.findElement(
                By.cssSelector("button[data-test^='add-to-cart']")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        softAssert.assertEquals(
                driver.findElement(By.cssSelector(".inventory_item_name")).getText(),
                expectedItemName,
                "Название товара не совпадает");

        softAssert.assertEquals(
                driver.findElement(By.cssSelector(".inventory_item_price")).getText(),
                expectedItemPrice,
                "Цена товара не совпадает");
        softAssert.assertAll();
    }
}
