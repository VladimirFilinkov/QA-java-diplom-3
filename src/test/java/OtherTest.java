import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;

import static java.net.HttpURLConnection.HTTP_ACCEPTED;

public class OtherTest {

    protected WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private CreateTestUser testUser;
    private String accessToken;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", Client.chromeDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @After
    public void tearDown() {
        if (testUser != null && accessToken != null) {
            CreateTestUser.deleteUser(accessToken).statusCode(HTTP_ACCEPTED);
        }

        Allure.step("Закрываем браузер", () -> {
            if (driver != null) {
                driver.quit();
            }
        });
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет».")
    public void testPersonalAccount() {
        testUser = CreateTestUser.randomUser();
        accessToken = CreateTestUser.createUser(testUser);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);

        Allure.step("Открываем страницу входа", () -> {
            driver.get(Client.LOGIN_PAGE);
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginButtonLocator()));
        });

        Allure.step("Вводим Email и пароль тестового пользователя", () -> {
            loginPage.login(testUser.getEmail(), testUser.getPassword());
        });

        Allure.step("Ожидаем загрузку главной страницы", () -> {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getPersonalAccountButtonLocator()));
        });

        Allure.step("Кликаем по кнопке Личный кабинет", () -> {
            mainPage.clickPersonalAccountButton();;
        });

        Allure.step("Проверяем, что открылась страница Личного кабинета", () -> {
            wait.until(ExpectedConditions.urlToBe(Client.PROFILE_PAGE));
            Assert.assertEquals("Должна открыться страница входа, но что-то пошло не так", Client.PROFILE_PAGE, driver.getCurrentUrl());;
        });
    }

    @Test
    @DisplayName("Переход по клику на «Конструктор» из личного кабинета")
    public void testConstructorButtonFromPersonalAccount() {
        testUser = CreateTestUser.randomUser();
        accessToken = CreateTestUser.createUser(testUser);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);

        Allure.step("Открываем страницу входа", () -> {
            driver.get(Client.LOGIN_PAGE);
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginButtonLocator()));
        });

        Allure.step("Вводим Email и пароль тестового пользователя", () -> {
            loginPage.login(testUser.getEmail(), testUser.getPassword());
        });

        Allure.step("Ожидаем загрузку главной страницы", () -> {
            wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getPersonalAccountButtonLocator()));
        });

        Allure.step("Кликаем по кнопке Личный кабинет", () -> {
            mainPage.clickPersonalAccountButton();;
        });

        Allure.step("Кликаем по кнопке Конструктор", () -> {
            wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getConstructorLocator()));
            mainPage.clickConstructor();
        });

        Allure.step("Проверяем что открылась страница конструктора", () -> {
            wait.until(ExpectedConditions.urlToBe(Client.MAIN_PAGE));
            Assert.assertEquals("Должна открыться страница конструктора, но что-то пошло не так", Client.MAIN_PAGE, driver.getCurrentUrl());
        });
    }

    @Test
    @DisplayName("Переход по клику на логотип Stellar Burgers из личного кабинета")
    public void testLogoFromPersonalAccount() {
        testUser = CreateTestUser.randomUser();
        accessToken = CreateTestUser.createUser(testUser);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);

        Allure.step("Открываем страницу входа", () -> {
            driver.get(Client.LOGIN_PAGE);
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginButtonLocator()));
        });

        Allure.step("Вводим Email и пароль тестового пользователя", () -> {
            loginPage.login(testUser.getEmail(), testUser.getPassword());
        });

        Allure.step("Ожидаем загрузку главной страницы", () -> {
            wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getPersonalAccountButtonLocator()));
        });

        Allure.step("Кликаем по кнопке Личный кабинет", () -> {
            mainPage.clickPersonalAccountButton();;
        });

        Allure.step("Кликаем по логотипу", () -> {
            wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getLogoLocator()));
            mainPage.clickLogo();
        });

        Allure.step("Проверяем, что открылась страница Конструктора", () -> {
            wait.until(ExpectedConditions.urlToBe(Client.MAIN_PAGE));
            Assert.assertEquals("Должна открыться страница конструктора, но что-то пошло не так", Client.MAIN_PAGE, driver.getCurrentUrl());
        });
    }

    @Test
    @DisplayName("Dыход по кнопке «Выйти» в личном кабинете")
    public void testLogoutButtonFromPersonalAccount() {
        testUser = CreateTestUser.randomUser();
        accessToken = CreateTestUser.createUser(testUser);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        Allure.step("Открываем страницу входа", () -> {
            driver.get(Client.LOGIN_PAGE);
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginButtonLocator()));
        });

        Allure.step("Вводим Email и пароль тестового пользователя", () -> {
            loginPage.login(testUser.getEmail(), testUser.getPassword());
        });

        Allure.step("Ожидаем загрузку главной страницы", () -> {
            wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getPersonalAccountButtonLocator()));
        });

        Allure.step("Кликаем по кнопке Личный кабинет", () -> {
            mainPage.clickPersonalAccountButton();;
        });

        Allure.step("Кликаем по кнопке Выход", () -> {
            wait.until(ExpectedConditions.visibilityOfElementLocated(profilePage.getLogoutButtonLocator()));
            profilePage.logoutButtonClick();
        });

        Allure.step("Проверяем, что открылась страница входа", () -> {
            wait.until(ExpectedConditions.urlToBe(Client.LOGIN_PAGE));
            Assert.assertEquals("Должна открыться страница входа, но что-то пошло не так", Client.LOGIN_PAGE, driver.getCurrentUrl());
        });

        Allure.step("Кликаем по кнопке Личный кабинет", () -> {
            mainPage.clickPersonalAccountButton();;
        });

        Allure.step("Проверяем, что личный кабинет не доступен, пользователь вышел из системы", () -> {
            wait.until(ExpectedConditions.urlToBe(Client.LOGIN_PAGE));
            Assert.assertEquals("Должна открыться страница входа, но что-то пошло не так", Client.LOGIN_PAGE, driver.getCurrentUrl());
        });
    }

    @Test
    @DisplayName("Проверяем, что работают переходы к разделам")
    public void testScrollBetweenSections() throws InterruptedException {
        mainPage = new MainPage(driver);

        Allure.step("Открываем страницу Конструктора", () -> {
            driver.get(Client.MAIN_PAGE);
        });

        // находим все элементы для получения координат
        WebElement buttonsMenu = driver.findElement(mainPage.getButtonsMenuLocator());
        WebElement titleBuns = driver.findElement(mainPage.getTitleBunsLocator());
        WebElement titleSauce = driver.findElement(mainPage.getTitleSauceLocator());
        WebElement titleFilling = driver.findElement(mainPage.getTitleFillingLocator());

        // Кликаем по кнопке "Начинки" и проверяем координаты
        Allure.step("Кликаем по кнопке Начинки и проверяем координаты", () -> {
            mainPage.clickButtonFilling();
            Thread.sleep(1000);

            int buttonsMenuYAfter = buttonsMenu.getLocation().getY() + buttonsMenu.getSize().getHeight();
            int titleFillingYAfter = titleFilling.getLocation().getY();

            Assert.assertEquals("Координаты заголовка 'Начинки' не совпадают с координатами панели кнопок после клика",
                                buttonsMenuYAfter, titleFillingYAfter);
        });

        // Кликаем по кнопке "Соусы" и проверяем координаты
        Allure.step("Кликаем по кнопке Соусы и проверяем координаты", () -> {
            mainPage.clickButtonSauce();
            Thread.sleep(1000);

            int buttonsMenuYAfter = buttonsMenu.getLocation().getY() + buttonsMenu.getSize().getHeight();
            int titleSauceYAfter = titleSauce.getLocation().getY();

            Assert.assertEquals("Координаты заголовка 'Соусы' не совпадают с координатами панели кнопок после клика",
                                buttonsMenuYAfter, titleSauceYAfter);
        });

        // Кликаем по кнопке "Булки" и проверяем координаты
        Allure.step("Кликаем по кнопке Булки и проверяем координаты", () -> {
            mainPage.clickButtonBuns();
            Thread.sleep(1000);

            int buttonsMenuYAfter = buttonsMenu.getLocation().getY() + buttonsMenu.getSize().getHeight();
            int titleBunsYAfter = titleBuns.getLocation().getY();

            Assert.assertEquals("Координаты заголовка 'Булки' не совпадают с координатами панели кнопок после клика",
                                buttonsMenuYAfter, titleBunsYAfter);
        });
    }

}
