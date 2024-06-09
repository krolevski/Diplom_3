package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By enterInAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By headerBurger = By.xpath("//h1[text()='Соберите бургер']");
    private final By bunsButton = By.xpath("//span[text()='Булки']");;
    private final By headerBuns = By.xpath(".//span[text() ='Булки']/parent::div");
    private final By saucesButton = By.xpath(".//span[text() ='Соусы']");
    private final By headerSauces = By.xpath(".//h2[text() ='Соусы']");
    private final By fillingsButton = By.xpath("//span[text()='Начинки']");
    private final By headerFillings = By.xpath(".//h2[text() ='Соусы']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Метод нажатия на кнопку Личный кабинет")
    public void personalAccountButtonClick() {
        driver.findElement(personalAccountButton).isEnabled();
        driver.findElement(personalAccountButton).click();
    }

    @Step("Метод нажатия кнопки Войти в аккаунт")
    public void enterInAccountButtonClick() {
        driver.findElement(enterInAccountButton).isEnabled();
        driver.findElement(enterInAccountButton).click();
    }

    @Step("Наличие заголовка 'Оформить заказ'")
    public boolean checkCreateOrderButton() {
        return driver.findElement(createOrderButton).isDisplayed();
    }

    @Step("Проверка видимости заголовка контруктора")
    public boolean headerBurgerIsEnabled() {
        return driver.findElement(headerBurger).isDisplayed();
    }

    @Step("Нажать кнопку булки")
    public void bunsButtonClick() {
        driver.findElement(bunsButton).click();
    }

    @Step("Видимость надписи Булки")
    public boolean headerBunsIsDisplayed() {
        return driver.findElement(headerBuns).isDisplayed();
    }

    @Step("Нажать кнопку Соусы")
    public void saucesButtonClick() {
        driver.findElement(saucesButton).click();
    }

    @Step("Видиммость надписи Соусы")
    public boolean headerSaucesIsDisplayed() {
        return driver.findElement(headerSauces).isDisplayed();
    }

    @Step("Нажать кнопку Начинки")
    public void fillingsButtonClick() {
        driver.findElement(fillingsButton).click();
    }

    @Step("Видимость надписи Начинки")
    public boolean headerFillingsIsDisplayed() {
        return driver.findElement(headerFillings).isDisplayed();
    }
}
