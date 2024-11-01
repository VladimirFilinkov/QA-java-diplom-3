import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;

import static java.net.HttpURLConnection.HTTP_ACCEPTED;

public class LoginPageTest {
    private CreateTestUser testUser;
    private String accessToken;
    protected WebDriver driver;
    private WebDriverWait wait;

    private String browserType;

    @Before
    public void setUp() {
        browserType = System.getProperty("browserType", "chrome");

        if ("yandex".equalsIgnoreCase(browserType)) {
            System.setProperty("webdriver.chrome.driver", Client.yandexDriverPath);
            driver = new ChromeDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", Client.chromeDriverPath);
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Создаем тестового пользователя через API
        testUser = CreateTestUser.randomUser();
        accessToken = CreateTestUser.createUser(testUser);
    }

    @After //Удаляем пользователя, если он был создан и закрываем браузер
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
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void testSuccessfulLoginMainPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        Allure.step("Открываем Главную страницу сайта", () -> {
            driver.get(Client.MAIN_PAGE);
        });

        Allure.step("Нажимаем на кнопку «Войти в аккаунт»", () -> {
            wait.until(ExpectedConditions.elementToBeClickable(mainPage.getButtonLoginLocator()));
            mainPage.clickButtonLogin();
        });

        Allure.step("Ожидаем загрузки страницы входа", () -> {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginButtonLocator()));
        });

        Allure.step("Вводим Email и пароль тестового пользователя", () -> {
            loginPage.login(testUser.getEmail(), testUser.getPassword());
        });

          Allure.step("Проверяем, что после входа открылась главная страница", () -> {
            wait.until(ExpectedConditions.urlToBe(Client.MAIN_PAGE));
            Assert.assertEquals("Должна открыться главная страница, но что-то пошло не так", Client.MAIN_PAGE, driver.getCurrentUrl());
        });
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void testSuccessfulLoginForPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        Allure.step("Открываем Главную страницу сайта", () -> {
            driver.get(Client.MAIN_PAGE);
        });

        Allure.step("Кликаем по кнопке Личный кабинет", () -> {
            wait.until(ExpectedConditions.elementToBeClickable(mainPage.getPersonalAccountButtonLocator()));
            mainPage.clickPersonalAccountButton();
        });

        Allure.step("Ожидаем загрузки страницы входа", () -> {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginButtonLocator()));
        });

        Allure.step("Вводим Email и пароль тестового пользователя", () -> {
            loginPage.login(testUser.getEmail(), testUser.getPassword());
        });

        Allure.step("Проверяем, что после входа открылась главная страница", () -> {
            wait.until(ExpectedConditions.urlToBe(Client.MAIN_PAGE));
            Assert.assertEquals("Должна открыться главная страница, но что-то пошло не так", Client.MAIN_PAGE, driver.getCurrentUrl());
        });
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    public void testSuccessfulLoginFromButtonInRegistrationPage() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        Allure.step("Открываем страницу регистрации", () -> {
        driver.get(Client.REGISTER_PAGE);
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.getPersonalAccountButtonLocator()));
        });

        Allure.step("Кликаем по ссылке Войти", () -> {
        registrationPage.clickLoginLink();
        });

        Allure.step("Ожидаем загрузку страницы входа", () -> {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginButtonLocator()));
        });

        Allure.step("Вводим Email и пароль тестового пользователя", () -> {
            loginPage.login(testUser.getEmail(), testUser.getPassword());
        });

        Allure.step("Проверяем, что после входа открылась главная страница", () -> {
            wait.until(ExpectedConditions.urlToBe(Client.MAIN_PAGE));
            Assert.assertEquals("Должна открыться главная страница, но что-то пошло не так", Client.MAIN_PAGE, driver.getCurrentUrl());
        });
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testSuccessfulLoginFromButtonInForgetPage() {
        ForgetPage forgetPage = new ForgetPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        Allure.step("Открываем страницу Восстановления пароля", () -> {
            driver.get(Client.FORGET_PAGE);
            wait.until(ExpectedConditions.elementToBeClickable(forgetPage.getLoginLinkLocator()));
        });

        Allure.step("Кликаем по ссылке Войти", () -> {
            forgetPage.loginLinkClick();
        });

        Allure.step("Ожидаем загрузку страницы входа", () -> {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginButtonLocator()));
        });

        Allure.step("Вводим Email и пароль тестового пользователя", () -> {
            loginPage.login(testUser.getEmail(), testUser.getPassword());
        });

        Allure.step("Проверяем, что после входа открылась главная страница", () -> {
            wait.until(ExpectedConditions.urlToBe(Client.MAIN_PAGE));
            Assert.assertEquals("Должна открыться главная страница, но что-то пошло не так", Client.MAIN_PAGE, driver.getCurrentUrl());
        });
    }
}