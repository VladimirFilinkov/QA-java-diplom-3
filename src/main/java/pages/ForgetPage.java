package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgetPage {
    private WebDriver driver;

    public ForgetPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локатор для ссылки Войти
    private final By loginLink = By.xpath(".//*[@id=\"root\"]/div/main/div/div/p/a[text()='Войти']");

    // Метод для клика по С Войти в аккаунт
    public void loginLinkClick() {
        driver.findElement(loginLink).click();
    }

    // Метод для возвращения локатора кнопки войти
    public By getLoginLinkLocator() {
        return loginLink;
    }

}
