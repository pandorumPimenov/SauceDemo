package tests;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.CartPage;

import pages.CheckoutOverviewPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.PropertyReader;
import listeners.TestListener;

import java.time.Duration;
import java.util.HashMap;

import static utils.AllureUtils.takeScreenshot;

@Log4j2
@Listeners(TestListener.class) // Класс, который будет выводить информацию
// о тестах в консоли (STARTING TEST, FINISHED TEST и Duration)
public class BaseTest {
    protected WebDriver driver;
    protected SoftAssert softAssert;
    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;
    protected CheckoutOverviewPage checkoutOverviewPage;

    String user = System.getProperty("user", PropertyReader.getProperty("user"));
    String password = System.getProperty("password",PropertyReader.getProperty("password"));

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true, description = "Настройка драйвера")
    public void setup(@Optional("chrome") String browser,ITestContext iTestContext) {
        log.info("Инициализация тестового окружения для браузера: {}", browser);
        if (browser.equalsIgnoreCase("chrome")) {
            log.info("Настройка ChromeOptions");
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
            log.info("ChromeDriver успешно инициализирован");
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        }

        softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        iTestContext.setAttribute("driver", driver); // Передаем driver в контекст теста для listener

        // Инициализация PageObjects
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
    }

    protected ProductsPage loginStandardUser() {
        log.info("Выполнение входа стандартного пользователя");
        return loginPage.open()
                .login("standard_user", "secret_sauce")
                .isPageOpened();
    }

    @AfterMethod(alwaysRun = true, description = "Закрытие браузера")
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            log.warn("Тест завершился с ошибкой - создание скриншота");
            takeScreenshot(driver);
        }
        log.info("Завершение работы драйвера");
        driver.quit();
    }
}
