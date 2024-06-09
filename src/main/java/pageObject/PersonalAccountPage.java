package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage {
    private WebDriver driver;

    private final By profileButton = By.xpath(".//a[text()='Профиль']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By logo = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");
    private final By exitButton = By.xpath("//button[text()='Выход']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Наличие кнопки Профиль")
    public boolean profileButtonIsEnabled() {
        return driver.findElement(profileButton).isDisplayed();
    }

    @Step("Нажать кнопку Контруктор")
    public void constructorButtonClick() {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажать на лого")
    public void logoClick() {
        driver.findElement(logo).click();
    }

    @Step("Нажать кнопку Выход")
    public void exitButtonClick() {
        driver.findElement(exitButton).click();
    }
}
