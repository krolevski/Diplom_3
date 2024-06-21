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
    private final By bunsButtonActiv = By.xpath("//div[@class='tab_tab__1SPyG " +
            "tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Булки']");
    private final By bunsButtonNoActiv = By.xpath("//div[@class='tab_tab__1SPyG  " +
            "pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Булки']");
    private final By saucesButtonActiv = By.xpath("//div[@class='tab_tab__1SPyG " +
            "tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']");
    private final By saucesButtonNoActive = By.xpath("//div[@class='tab_tab__1SPyG  " +
            "pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']");
    private final By fillingsButtonActiv = By.xpath("//div[@class='tab_tab__1SPyG " +
            "tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");
    private final By fillingsButtonNoActiv =  By.xpath("//div[@class='tab_tab__1SPyG  " +
            "pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");

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
        driver.findElement(bunsButtonNoActiv).click();
    }

    @Step("Видимость надписи Булки")
    public boolean bunsButtonIsDisplayed() {
        return driver.findElement(bunsButtonActiv).isDisplayed();
    }

    @Step("Нажать кнопку Соусы")
    public void saucesButtonClick() {
        driver.findElement(saucesButtonNoActive).click();
    }

    @Step("Видимость надписи Соусы")
    public boolean saucesButtonIsDisplayed() {
        return driver.findElement(saucesButtonActiv).isDisplayed();
    }

    @Step("Нажать кнопку Начинки")
    public void fillingsButtonClick() {
        driver.findElement(fillingsButtonNoActiv).click();
    }

    @Step("Видимость надписи Начинки")
    public boolean fillingsButtonIsDisplayed() {
        return driver.findElement(fillingsButtonActiv).isDisplayed();
    }
}
