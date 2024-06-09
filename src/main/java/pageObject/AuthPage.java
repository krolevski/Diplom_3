package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthPage {
    private WebDriver driver;

    private final By registerButton = By.xpath(".//a[(@class = 'Auth_link__1fOlj' and text()= 'Зарегистрироваться')]");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By enterButton = By.xpath("//*[text()='Войти']");
    private final By recoveryPasswordButton = By.xpath("//a[text()='Восстановить пароль']");
    private final By authHeader = By.xpath("//h2[text()='Вход']");

    public AuthPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать кнопку Зарегистрироваться")
    public void registerButtonClick() {
        driver.findElement(registerButton).click();
    }

    @Step("Заполнение поля Email")
    public void setEmailField(String email) {
        driver.findElement(emailField).click();
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Заполнение поля Пароль")
    public void setPasswordField(String password) {
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажать кнопку Войти")
    public void enterButtonClick() {
        driver.findElement(enterButton).click();
    }

    @Step("Нажать кнопку Восстановления пароля")
    public void recoveryPasswordButtonClick() {
        driver.findElement(recoveryPasswordButton).click();
    }

    @Step("Проверить, что заголовок Вход виден")
    public boolean authHeaderIsDisplayed() {
        return driver.findElement(authHeader).isDisplayed();
    }
}
