package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("Login Page")
@Feature("Authentication")
public class LoginTest extends BaseTest {

    // DataProvider для теста с пустым паролем
    @DataProvider(name = "EmptyPasswordData")
    public Object[][] emptyPasswordData() {
        return new Object[][] {
                {user, "", "Epic sadface: Password is required"}
        };
    }

    // DataProvider для теста с пустым логином
    @DataProvider(name = "EmptyUsernameData")
    public Object[][] emptyUsernameData() {
        return new Object[][] {
                {"", password, "Epic sadface: Username is required"}
        };
    }

    // DataProvider для теста с неверными кредами
    @DataProvider(name = "InvalidCredentialsData")
    public Object[][] invalidCredentialsData() {
        return new Object[][] {
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}
        };
    }

    @Test(groups = "authentication",
            priority = 1,
            description = "Проверка входа с валидными кредами",
            testName = "Позитивный тест входа")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Pimenov S.I")
    @Link(name = "SauceDemo", url = "https://www.saucedemo.com/")
    @TmsLink("TM-001")
    @Issue("BUG-001")
    @Description("Проверка успешной авторизации с корректными учетными данными")
    public void testSuccessfulLogin() {
        boolean isOpened = loginPage
                .open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened() != null;

        assertTrue(isOpened, "Страница продуктов не загрузилась после входа");
    }

    @Test(groups = "authentication",
            dataProvider = "EmptyPasswordData",
            priority = 2,
            description = "Проверка входа без пароля",
            testName = "Негативный тест: пустой пароль")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Pimenov S.I")
    @Story("Negative Login Scenarios")
    public void testLoginWithoutPassword(String user,
                                         String password,
                                         String expectedMessage) {
        String actualMessage = loginPage
                .open()
                .isPageOpened()
                .attemptLogin(user, password)
                .getErrorMessage();

        assertEquals(actualMessage, expectedMessage,
                "Неверное сообщение об ошибке при пустом пароле");
    }

    @Test(groups = "authentication",
            dataProvider = "EmptyUsernameData",
            priority = 3,
            description = "Проверка входа без логина",
            testName = "Негативный тест: пустой логин")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Pimenov S.I")
    @Story("Negative Login Scenarios")
    public void testLoginWithoutUsername(String user,
                                         String password,
                                         String expectedMessage) {
        String actualMessage = loginPage
                .open()
                .isPageOpened()
                .attemptLogin(user, password)
                .getErrorMessage();

        assertEquals(actualMessage, expectedMessage,
                "Неверное сообщение об ошибке при пустом логине");
    }
    @Test(groups = "authentication",
            dataProvider = "InvalidCredentialsData",
            priority = 4,
            description = "Проверка входа с неверными кредами",
            testName = "Негативный тест: невалидные данные")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Pimenov S.I")
    @Story("Negative Login Scenarios")
    public void testLoginWithInvalidCredentials(String user,
                                                String password,
                                                String expectedMessage) {
        String actualMessage = loginPage
                .open()
                .isPageOpened()
                .attemptLogin(user, password)
                .getErrorMessage();

        assertEquals(actualMessage, expectedMessage,
                "Неверное сообщение об ошибке при невалидных данных");
    }
}
