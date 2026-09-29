
package Login;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TC12_Login_Empty_Password {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.saucedemo.com/");

            WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));

            // Enter username only
            driver.findElement(By.xpath("//input[@id='user-name']"))
                .sendKeys("standard_user");

            driver.findElement(By.xpath("//input[@id='login-button']"))
                .click();

            WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[@data-test='error']")));

            if (error.getText().contains("Password is required")) {
                System.out.println("TC12 PASS: Empty password validation");
            } else {
                System.out.println("TC12 FAIL");
            }

        } finally {
            driver.quit();
        }
    }
}