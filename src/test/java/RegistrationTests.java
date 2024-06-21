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
import pageObject.RegistrationPage;
import pojo.User;

import java.time.Duration;

import static baseData.Data.*;


public class RegistrationTests extends DriverRule {
    DriverRule driverRule = new DriverRule();
    UserApi userApi;
    ApiUrl apiUrl;
    String name = NAME;
    String email = EMAIL;
    String passwordTrue = PASSWORD_TRUE;
    String passwordFalse = PASSWORD_FALSE;
    String baseUrl = URL;
    String accessToken;

    @Before
    public void setUp() {
        driverRule.initDriver();
        userApi = new UserApi();
        apiUrl = new ApiUrl();

        apiUrl.baseUrl();
    }

    @Step("Проверка возможности регистрации пользователя с валидными данными")
    @Test
    public void checkRegistrationNewUser() {
        User user = new User(EMAIL, PASSWORD_TRUE, NAME);
        WebDriver driver = driverRule.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(baseUrl);

        MainPage objMainPage = new MainPage(driver);
        objMainPage.personalAccountButtonClick();

        AuthPage objAuthPage = new AuthPage(driver);
        objAuthPage.registerButtonClick();

        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.setNameField(name);
        objRegistrationPage.setEmailField(email);
        objRegistrationPage.setPasswordField(passwordTrue);
        objRegistrationPage.registerButtonClick();

        String accessToken = userApi.authUser(user).then().extract().path("accessToken");
        Assert.assertNotNull(accessToken);
    }

    @Step("Регистрация с невалидным паролем")
    @Test
    public void checkRegistrationNotValidPassword() {
        WebDriver driver = driverRule.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.get(baseUrl);

        MainPage objMainPage = new MainPage(driver);
        objMainPage.personalAccountButtonClick();

        AuthPage objAuthPage = new AuthPage(driver);
        objAuthPage.registerButtonClick();

        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.setNameField(name);
        objRegistrationPage.setEmailField(email);
        objRegistrationPage.setPasswordField(passwordFalse);
        objRegistrationPage.registerButtonClick();
        objRegistrationPage.checkPasswordErrorText();
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
