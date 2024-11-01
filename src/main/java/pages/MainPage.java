package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
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

    // Локатор для кнопки Личный кабинет
    private final By buttonLogin = By.xpath(".//*[@id=\"root\"]/div/main/section[2]/div/button");

    // Локатор для панели кнопок
    private final By buttonsMenu = By.xpath(".//*[@id=\"root\"]/div/main/section[1]/div[1]");
    // Локатор для кнопки Булки
    private final By buttonBugs = By.xpath(".//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]");
    // Локатор для кнопки Соусы
    private final By buttonSauce = By.xpath(".//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]");
    // Локатор для кнопки Начинки
    private final By buttonFilling = By.xpath(".//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]");

    //Локатор для заголовка Булки
    private final By titleBugs = By.xpath(".//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[1]");
    // Локатор для заголовка Соусы
    private final By titleSauce = By.xpath(".//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[2]");
    // Локатор для заголовка Начинки
    private final By titleFilling = By.xpath(".//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[3]");

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

    // Метод для клика по кнопке Войти в аккаунт
    public void clickButtonLogin() {
        driver.findElement(buttonLogin).click();
    }

    // Метод для клика по кнопке Булки
    public void clickButtonBuns() {
        driver.findElement(buttonBugs).click();
    }

    // Метод для клика по кнопке Соусы
    public void clickButtonSauce() {
        driver.findElement(buttonSauce).click();
    }

    // Метод для клика по кнопке Начинки
    public void clickButtonFilling() {
        driver.findElement(buttonFilling).click();
    }

    // Метод для возвращения локатора кнопки войти
    public By getButtonLoginLocator(){
        return buttonLogin;
    }

    // Метод для возвращения локатора кнопки Личный кабинет
    public By getPersonalAccountButtonLocator(){
        return personalAccountButton;
    }

    // Метод для возвращения локатора кнопки Конструктор
    public By getConstructorLocator(){
        return constructor;
    }

    // Метод для возвращения локатора логотипа
    public By getLogoLocator(){
        return logo;
    }

    // Метод для возвращения панели с кнопками
    public By getButtonsMenuLocator(){
        return buttonsMenu;
    }

    // Метод для возвращения локатора заголовка Булки
    public By getTitleBunsLocator(){
        return titleBugs;
    }

    // Метод для возвращения локатора заголовка Соусы
    public By getTitleSauceLocator(){
        return titleSauce;
    }

    // Метод для возвращения локатора заголовка Начинки
    public By getTitleFillingLocator(){
        return titleFilling;
    }

}
