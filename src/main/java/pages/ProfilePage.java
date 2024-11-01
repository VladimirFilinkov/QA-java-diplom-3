package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {

    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    // Локатор для кнопки Выход
    private final By loginOutButton = By.xpath(".//*[@id=\"root\"]/div/main/div/nav/ul/li[3]/button[text()='Выход']");

    // Метод для клика по кнопке Выход
    public void logoutButtonClick() {
        driver.findElement(loginOutButton).click();
    }

    // Метод для возвращения локатора кнопки Выход
    public By getLogoutButtonLocator() {
        return loginOutButton;
    }

}
