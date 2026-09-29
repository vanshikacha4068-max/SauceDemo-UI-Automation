
package Login;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TC05_Login_Username_locked_out_user {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.saucedemo.com/");

            WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));

            driver.findElement(By.xpath("//input[@id='user-name']"))
                .sendKeys("locked_out_user");

            driver.findElement(By.xpath("//input[@id='password']"))
                .sendKeys("secret_sauce");

            driver.findElement(By.xpath("//input[@id='login-button']"))
                .click();

            WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[@data-test='error']")));

            if (error.getText().contains("locked out")) {
                System.out.println("TC05 PASS: Locked-out user rejected");
            } else {
                System.out.println("TC05 FAIL");
            }

        } finally {
            driver.quit();
        }
    }
}