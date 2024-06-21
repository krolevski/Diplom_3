import api.ApiUrl;
import api.UserApi;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.AuthPage;
import pageObject.MainPage;
import pageObject.RecoveryPasswordPage;
import pageObject.RegistrationPage;
import pojo.User;

import java.time.Duration;

import static baseData.Data.*;

public class AuthTests extends DriverRule{
    DriverRule driverRule = new DriverRule();
    UserApi userApi;
    ApiUrl apiUrl;
    String name = NAME;
    String email = EMAIL;
    String passwordTrue = PASSWORD_TRUE;
    String baseUrl = URL;
    String accessToken;
    @Before
    public void setUp() {
        driverRule.initDriver();
        userApi = new UserApi();
        apiUrl = new ApiUrl();

        apiUrl.baseUrl();
        User user = new User(email, passwordTrue, name);
        userApi.creatingUser(user);
    }

    @Step("Проверка входа по кнопке Войти в аккаунт на главной")
    @Test
    public void checkButtonEnterInAccount() {
        WebDriver driver = driverRule.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(baseUrl);

        MainPage objMainPage = new MainPage(driver);
        objMainPage.enterInAccountButtonClick();

        AuthPage objAuthPage = new AuthPage(driver);
        objAuthPage.setEmailField(email);
        objAuthPage.setPasswordField(passwordTrue);
        objAuthPage.enterButtonClick();

        Assert.assertEquals(true, objMainPage.checkCreateOrderButton());
    }

    @Step("Проверка входа через кнопку Личный кабинет")
    @Test
    public void checkButtonPersonalAccountButton() {
        WebDriver driver = driverRule.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(baseUrl);

        MainPage objMainPage = new MainPage(driver);
        objMainPage.personalAccountButtonClick();

        AuthPage objAuthPage = new AuthPage(driver);
        objAuthPage.setEmailField(email);
        objAuthPage.setPasswordField(passwordTrue);
        objAuthPage.enterButtonClick();

        Assert.assertEquals(true, objMainPage.checkCreateOrderButton());
    }

    @Step("Проверка входа через кнопку в форме регистрации")
    @Test
    public void checkAuthRegistrationForm() {
        WebDriver driver = driverRule.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(baseUrl);

        MainPage objMainPage = new MainPage(driver);
        objMainPage.personalAccountButtonClick();

        AuthPage objAuthPage = new AuthPage(driver);
        objAuthPage.registerButtonClick();

        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.signInButtonClick();

        objAuthPage.setEmailField(email);
        objAuthPage.setPasswordField(passwordTrue);
        objAuthPage.enterButtonClick();

        Assert.assertEquals(true, objMainPage.checkCreateOrderButton());
    }

    @Step("Вход через кнопку в форме восстановления пароля")
    @Test
    public void checkAuthRecoveryPassword() {
        WebDriver driver = driverRule.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(baseUrl);

        MainPage objMainPage = new MainPage(driver);
        objMainPage.personalAccountButtonClick();

        AuthPage objAuthPage = new AuthPage(driver);
        objAuthPage.recoveryPasswordButtonClick();

        RecoveryPasswordPage objRecoveryPasswordPage = new RecoveryPasswordPage(driver);
        objRecoveryPasswordPage.enterButtonClick();

        objAuthPage.setEmailField(email);
        objAuthPage.setPasswordField(passwordTrue);
        objAuthPage.enterButtonClick();

        Assert.assertEquals(true, objMainPage.checkCreateOrderButton());
    }

    @Step("Удаление данных после теста")
    @After
    public void tearDown() {
        WebDriver driver = driverRule.getDriver();
        driver.quit();
        User user = new User("krolevski@mail.ru", "123456");

        accessToken = userApi.authUser(user).then().extract().path("accessToken");
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
    }
}
