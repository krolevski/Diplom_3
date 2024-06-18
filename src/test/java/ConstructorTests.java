import api.ApiUrl;
import api.UserApi;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.MainPage;
import pojo.User;

import java.time.Duration;

import static baseData.Data.*;
import static baseData.Data.URL;

public class ConstructorTests {
    DriverRule driverRule = new DriverRule();
    UserApi userApi;
    ApiUrl apiUrl;
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
    }

    @Step("Проверка перехода в Булки")
    @Test
    public void checkEnterBuns() {
        WebDriver driver = driverRule.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(baseUrl);

        MainPage objMainPage = new MainPage(driver);
        objMainPage.fillingsButtonClick();
        objMainPage.bunsButtonClick();

        boolean actual = objMainPage.headerBunsIsDisplayed();
        Assert.assertTrue(actual);
    }

    @Step("Проверка перехода в Соусы")
    @Test
    public void checkEnterSauces() {
        WebDriver driver = driverRule.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(baseUrl);

        MainPage objMainPage = new MainPage(driver);
        objMainPage.fillingsButtonClick();
        objMainPage.saucesButtonClick();

        boolean actual = objMainPage.headerSaucesIsDisplayed();
        Assert.assertTrue(actual);
    }

    @Step("Проверка перехода в Начинки")
    @Test
    public void checkEnterFillings() {
        WebDriver driver = driverRule.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(baseUrl);

        MainPage objMainPage = new MainPage(driver);
        objMainPage.fillingsButtonClick();

        boolean actual = objMainPage.headerFillingsIsDisplayed();
        Assert.assertTrue(actual);
    }

    @Step("Удаление данных после теста")
    @After
    public void tearDown() {
        WebDriver driver = driverRule.getDriver();
        driver.quit();
        User user = new User(email, passwordTrue);

        accessToken = userApi.authUser(user).then().extract().path("accessToken");
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
    }
}
