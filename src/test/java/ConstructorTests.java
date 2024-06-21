import api.ApiUrl;
import api.UserApi;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.MainPage;
import pojo.User;

import static baseData.Data.*;
import static baseData.Data.URL;
import static org.junit.Assert.assertTrue;

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
        driver.get(baseUrl);

        MainPage objMainPage = new MainPage(driver);
        objMainPage.fillingsButtonClick();
        objMainPage.bunsButtonClick();

        boolean actual = objMainPage.bunsButtonIsDisplayed();
        assertTrue(actual);
    }

    @Step("Проверка перехода в Соусы")
    @Test
    public void checkEnterSauces() {
        WebDriver driver = driverRule.getDriver();
        driver.get(baseUrl);

        MainPage objMainPage = new MainPage(driver);
        objMainPage.fillingsButtonClick();
        objMainPage.saucesButtonClick();

        boolean actual = objMainPage.saucesButtonIsDisplayed();
        assertTrue(actual);
    }

    @Step("Проверка перехода в Начинки")
    @Test
    public void checkEnterFillings() {
        WebDriver driver = driverRule.getDriver();
        driver.get(baseUrl);

        MainPage objMainPage = new MainPage(driver);
        objMainPage.fillingsButtonClick();

        assertTrue(objMainPage.fillingsButtonIsDisplayed());
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
