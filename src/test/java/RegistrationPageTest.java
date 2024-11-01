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
import pages.Client;
import pages.CreateTestUser;
import pages.RegistrationPage;

import java.time.Duration;

import static java.net.HttpURLConnection.HTTP_ACCEPTED;

public class RegistrationPageTest {
    protected WebDriver driver;
    private WebDriverWait wait;
    private RegistrationPage registrationPage;
    private String accessToken;
    private CreateTestUser testUser;

    private String browserType; // Переменная для типа браузера

    @Before
    public void setUp() {
        // Получаем тип браузера из системного свойства, по умолчанию "chrome"
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
        registrationPage = new RegistrationPage(driver);
    }

    @After // Удаляем пользователя, если он был создан и закрываем браузер
    public void tearDown() {
        if (testUser != null) {
            accessToken = CreateTestUser.logInAndGetToken(testUser);
            if (accessToken != null) {
                CreateTestUser.deleteUser(accessToken).statusCode(HTTP_ACCEPTED);
                System.out.println("Тесты окончены, тестовый пользователь удалён");
            }
        }

        Allure.step("Закрываем браузер", () -> {
            if (driver != null) {
                driver.quit();
            }
        });
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void testSuccessfulRegistration() {
        testUser = CreateTestUser.randomUser();

        Allure.step("Открываем Страницу регистрации", () -> {
            driver.get(Client.REGISTER_PAGE);
            wait.until(ExpectedConditions.visibilityOfElementLocated(registrationPage.getNameFieldLocator()));
        });

        Allure.step("Заполняем форму регистрации тестовыми данными", () -> {
            registrationPage.Registration(testUser.getName(), testUser.getEmail(), testUser.getPassword());
        });

        Allure.step("Проверяем, что после регистрации открылась страница входа", () -> {
            wait.until(ExpectedConditions.urlToBe(Client.LOGIN_PAGE));
            Assert.assertEquals("Должна открыться страница входа, но что-то пошло не так", Client.LOGIN_PAGE, driver.getCurrentUrl());
        });
    }

    @Test
    @DisplayName("Проверяем ошибку при вводе короткого пароля (5 символов)")
    public void testNotSuccessfulRegistrationPassword5() {
        testUser = CreateTestUser.randomUser();
        Allure.step("Открываем Страницу регистрации", () -> {
            driver.get(Client.REGISTER_PAGE);
            wait.until(ExpectedConditions.visibilityOfElementLocated(registrationPage.getNameFieldLocator()));
        });

        Allure.step("Заполняем форму регистрации тестовыми данными", () -> {
            registrationPage.Registration(CreateTestUser.randomUser().getName(), CreateTestUser.randomUser().getEmail(), "P@ss1");
        });

        Allure.step("Ошибка \"Некорректный пароль\" отображается", () -> {
            wait.until(ExpectedConditions.visibilityOf(registrationPage.findErrorIncorrectPassword()));
            Assert.assertTrue("Ожидаемая ошибка 'Некорректный пароль' не отображается", registrationPage.findErrorIncorrectPassword().isDisplayed());
        });
    }

    @Test
    @DisplayName("Проверяем отсутствие ошибки при вводе допустимого пароля (6 символов)")
    public void testSuccessfulRegistrationPassword6() throws InterruptedException {
        testUser = CreateTestUser.randomUser();
        Allure.step("Открываем Страницу регистрации", () -> {
            driver.get(Client.REGISTER_PAGE);
            wait.until(ExpectedConditions.visibilityOfElementLocated(registrationPage.getNameFieldLocator()));
        });

        Allure.step("Заполняем форму регистрации тестовыми данными", () -> {
            registrationPage.Registration(CreateTestUser.randomUser().getName(), CreateTestUser.randomUser().getEmail(), "P@ssw1");
        });

        Allure.step("Ошибка \"Некорректный пароль\" не отображается", () -> {
            Thread.sleep(2000);
            Assert.assertTrue("Ошибка 'Некорректный пароль' отображается, хотя не должна",
                              registrationPage.isErrorIncorrectPasswordNotDisplayed());
        });
    }
}