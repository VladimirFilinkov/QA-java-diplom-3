package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
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

    // Локатор для поля ввода email
    private final By emailField = By.xpath(".//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    // Локатор для поля ввода пароля
    private final By passwordField = By.xpath(".//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    // Локатор для кнопки Войти в аккаунт
    private final By loginButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");


    // Локатор для ссылки Зарегистрироваться
    private final By registrationLink = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[1]/a");
    // Локатор для ссылки Восстановить Пароль
    private final By restorePasswordLink = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[2]/a");

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

    // Метод для клика по полю ввода email
    public void clickEmailField(){
        driver.findElement(emailField).click();
    }

    // Метод для клика по полю ввода пароля
    public void clickPasswordField(){
        driver.findElement(passwordField).click();
    }

    // Метод для клика по кнопке Войти
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Метод для клика по ссылке Зарегистрироваться
    public void clickRegistrationLink() {
        driver.findElement(registrationLink).click();
    }

    // Метод для клика по ссылке Восстановить пароль
    public void clickRestoreLink() {
        driver.findElement(restorePasswordLink).click();
    }

    // Метод для возвращения локатора кнопки Входа
    public By getLoginButtonLocator() {
        return loginButton;
    }

    // Метод для входа в систему
    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        Allure.step("Кликаем кнопку Вход", () -> {
            driver.findElement(loginButton).click();
        });

    }
}