package Login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_page {

    WebDriver varun;
    WebDriverWait wait;

    By username = By.id("user-name");
    By passward = By.id("password");
    By Login_Button = By.id("login-button");

    public Login_page(WebDriver driver) {

        this.varun = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public void USERNAME(String x) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(username))
            .sendKeys(x);

    }

    public void PASSWARD(String y) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(passward))
            .sendKeys(y);

    }

    public void LOGIN_BUTTON() {

        wait.until(ExpectedConditions.elementToBeClickable(Login_Button))
            .click();

    }

    public void Loginn(String x, String y) {

        USERNAME(x);

        PASSWARD(y);

        LOGIN_BUTTON();

    }
}
