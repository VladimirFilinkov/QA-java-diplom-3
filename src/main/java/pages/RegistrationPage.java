package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage {
    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локатор для кнопки Конструктор
    private final By constructor = By.xpath(".//*[@id=\"root\"]/div/header/nav/ul/li[1]/a");
    // Локатор для кнопки Лента заказов
    private final By orders = By.xpath(".//*[@id=\"root\"]/div/header/nav/ul/li[2]/a");
    // Локатор для логотипа
    private final By logo = By.xpath(".//*[@id=\"root\"]/div/header/nav/div/a");
    // Локатор для кнопки Личный кабинет
    private final By personalAccountButton = By.xpath(".//a[@class='AppHeader_header__link__3D_hX' and @href='/account']");

    // Локатор для поля ввода Имя
    private final By nameField = By.xpath(".//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    // Локатор для поля ввода email
    private final By emailField = By.xpath(".//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    // Локатор для поля ввода пароля
    private final By passwordField = By.xpath(".//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input");
    // Локатор для кнопки Зарегистрироваться
    private final By RegistrationButton = By.xpath(".//*[@id=\"root\"]/div/main/div/form/button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");

    // Локатор для ссылки Войти
    private final By loginLink = By.xpath(".//*[@id=\"root\"]/div/main/div/div/p/a[@href='/login']");

    // Локатор для ошибки Некорректный пароль
    private final By errorIncorrectPassword = By.xpath(".//p[text()='Некорректный пароль']");

    // Метод для клика кнопке Конструктор
    public void clickConstructor() {
        driver.findElement(constructor).click();
    }

    // Метод для клика кнопке Лента заказов
    public void clickOrders() {
        driver.findElement(orders).click();
    }

    // Метод для клика по логотипу Stellar Burgers
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    // Метод для клика по кнопке Личный кабинет
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    // Метод для клика по полю ввода Имени
    public void clickNameField(){
        driver.findElement(nameField).click();
    }

    // Метод для клика по полю ввода email
    public void clickEmailField(){
        driver.findElement(emailField).click();
    }

    // Метод для клика по полю ввода пароля
    public void clickPasswordField(){
        driver.findElement(passwordField).click();
    }

    // Метод для клика по кнопке Регистрация
    public void clickLoginButton() {
        driver.findElement(RegistrationButton).click();
    }

    // Метод для клика по ссылке Войти
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    // Метод для поиска ошибки о Некорректном пароле
    public WebElement findErrorIncorrectPassword(){
        return driver.findElement(errorIncorrectPassword);
    }


    // Метод для проверки отсутствия ошибки "Некорректный пароль"
    public boolean isErrorIncorrectPasswordNotDisplayed() {
        try {
            WebElement errorElement = driver.findElement(errorIncorrectPassword);
            return !errorElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public By getPersonalAccountButtonLocator() {
        return personalAccountButton;
    }

    public By getNameFieldLocator() {
        return nameField;
    }


    // Метод для регистрации
    public void Registration(String name, String email, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        Allure.step("Кликаем по кнопке Зарегистрироваться", () -> {
            driver.findElement(RegistrationButton).click();
        });
    }
}
