package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPasswordPage {
    private WebDriver driver;

    private final By enterButton = By.xpath("//*[text()='Войти']");

    public RecoveryPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать кнопку Войти")
    public void enterButtonClick() {
        driver.findElement(enterButton).click();
    }
}
