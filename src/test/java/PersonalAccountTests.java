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
import pageObject.PersonalAccountPage;
import pojo.User;

import java.time.Duration;

import static baseData.Data.*;
import static baseData.Data.URL;

public class PersonalAccountTests {
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

    @Step("Проверка перехода в Личный кабинет")
    @Test
    public void checkEnterPersonalAccount() {
        WebDriver driver = driverRule.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(baseUrl);

        MainPage objMainPage = new MainPage(driver);
        objMainPage.personalAccountButtonClick();

        AuthPage objAuthPage = new AuthPage(driver);
        objAuthPage.setEmailField(email);
        objAuthPage.setPasswordField(passwordTrue);
        objAuthPage.enterButtonClick();

        objMainPage.personalAccountButtonClick();

        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        boolean actual = objPersonalAccountPage.profileButtonIsEnabled();
        Assert.assertTrue(actual);
    }

    @Step("Проверка перехода в конcтруктор из личного кабинета по кнопке Контруктор")
    @Test
    public void checkEnterConstructorButtonConstructor() {
        WebDriver driver = driverRule.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(baseUrl);

        MainPage objMainPage = new MainPage(driver);
        objMainPage.personalAccountButtonClick();

        AuthPage objAuthPage = new AuthPage(driver);
        objAuthPage.setEmailField(email);
        objAuthPage.setPasswordField(passwordTrue);
        objAuthPage.enterButtonClick();

        objMainPage.personalAccountButtonClick();

        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.constructorButtonClick();

        boolean actual = objMainPage.headerBurgerIsEnabled();
        Assert.assertTrue(actual);
    }

    @Step("Проверка перехода в конструктор из личного кабинета по нажатию лого")
    @Test
    public void checkEnterConstructorClickLogo() {
        WebDriver driver = driverRule.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(baseUrl);

        MainPage objMainPage = new MainPage(driver);
        objMainPage.personalAccountButtonClick();

        AuthPage objAuthPage = new AuthPage(driver);
        objAuthPage.setEmailField(email);
        objAuthPage.setPasswordField(passwordTrue);
        objAuthPage.enterButtonClick();

        objMainPage.personalAccountButtonClick();

        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.logoClick();

        boolean actual = objMainPage.headerBurgerIsEnabled();
        Assert.assertTrue(actual);
    }

    @Step("Проверка выхода из аккаунта")
    @Test
    public void checkLogout() {
        WebDriver driver = driverRule.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(baseUrl);

        MainPage objMainPage = new MainPage(driver);
        objMainPage.personalAccountButtonClick();

        AuthPage objAuthPage = new AuthPage(driver);
        objAuthPage.setEmailField(email);
        objAuthPage.setPasswordField(passwordTrue);
        objAuthPage.enterButtonClick();

        objMainPage.personalAccountButtonClick();

        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.exitButtonClick();

        boolean actual = objAuthPage.authHeaderIsDisplayed();

        Assert.assertTrue(actual);
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
