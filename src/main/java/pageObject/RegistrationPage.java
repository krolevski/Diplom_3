package pageObject;

import io.qameta.allure.Step;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.Matchers.startsWith;

public class RegistrationPage {
    private WebDriver driver;

    private final By titleHeader = By.className("Auth_login__3hAey");
    private final By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//*[text()='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By passwordErrorText = By.xpath(".//p[text()='Некорректный пароль']");
    private final By signInButton = By.xpath(".//a[text()='Войти']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Заполнение поля Имя")
    public void setNameField(String name) {
        driver.findElement(nameField).click();
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Заполнение поля Email")
    public void setEmailField(String email) {
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Заполнение поля Пароль")
    public void setPasswordField(String password) {
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажать кнопку Зарегистрироваться")
    public void registerButtonClick() {
        driver.findElement(registerButton).click();
    }

    @Step("Проверка сообщения об ошибке")
    public void checkPasswordErrorText(){
        String errorText = driver.findElement(passwordErrorText).getText();
        MatcherAssert.assertThat(errorText, startsWith("Некорректный пароль"));
    }

    @Step("Нажать на кнопку Войти")
    public void signInButtonClick() {
        driver.findElement(signInButton).click();
    }
}