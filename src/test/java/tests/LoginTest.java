package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends  BaseTest {

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
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test (dataProvider = "EmptyPasswordData",priority = 2,
            description = "Проверка входа в систему без пароля",
            testName = "Негативный тест ввода логина без пароля")
    @Severity(SeverityLevel.MINOR)
    @Owner("Pimenov S.I")
    @Link("https://www.saucedemo.com/")
    @Epic("Login Page")
    @Feature("Log in")
    @Story("Login Without Password")
    @TmsLink("ITM-4")
    @Issue("ITM-4-1")
    @Description("Проверка что пользователь не может войти в магазин, когда вводит пустой пароль")
    public void checkLoginWithoutPassword(String user, String password,String expectedMessage) {
        loginPage.open();
        loginPage.login(user,password);
        assertEquals(loginPage.getErrorMessage(), expectedMessage,
                "Сообщение не соответствует");
    }

    @Test (dataProvider = "EmptyUsernameData",priority = 3,
            description = "Проверка входа в систему без логина",
            testName = "Негативный тест ввода пароля без логина")
    @Description("Проверка, что вход невозможен без логина, отображается нужное сообщение")
    public void checkLoginWithoutUsername(String user, String password,String expectedMessage) {
        loginPage.open();
        loginPage.login(user,password);
        assertEquals(loginPage.getErrorMessage(), expectedMessage,
                "Сообщение не соответствует");
    }

    @Test (dataProvider = "InvalidCredentialsData",priority = 4,
            description = "Проверка входа в систему с неверном логином и паролем",
            testName = "Негативный тест ввода неверного логина и пароля")
    @Description("Проверка ошибки при вводе неправильного логина и пароля")
    public void checkLoginWithNegativeValue(String user, String password,String expectedMessage) {
        loginPage.open();
        loginPage.login(user,password);
        assertEquals(loginPage.getErrorMessage(), expectedMessage,
                "Сообщение не соответствует");
    }

    @Test (priority = 1, description = "Проверка входа в систему с валидными кредами",
            testName = "Позитивный тест ввода валидного логина и пароля")
    @Description("Проверка успешного входа с правильными логином и паролем")
    public void checkLogin() {
        loginPage.open();
        loginPage.login(user, password);
       assertTrue(productsPage.isPageOpened(),
               "После успешного входа должна открываться страница продуктов");
    }
}
