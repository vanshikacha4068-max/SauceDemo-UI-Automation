
package Login;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TC16_Login_Close_Error_Message {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.saucedemo.com/");

            WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));

            driver.findElement(By.xpath("//input[@id='user-name']"))
                .sendKeys("invalid_user");

            driver.findElement(By.xpath("//input[@id='password']"))
                .sendKeys("wrong_password");

            driver.findElement(By.xpath("//input[@id='login-button']"))
                .click();

            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[@data-test='error']")));

            // Close error message
            driver.findElement(
                By.xpath("//button[@data-test='error-button']")
            ).click();

            // Verify error message disappears
            boolean disappeared = wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//h3[@data-test='error']")));

            if (disappeared) {
                System.out.println("TC16 PASS: Error message closed");
            } else {
                System.out.println("TC16 FAIL");
            }

        } finally {
            driver.quit();
        }
    }
}
