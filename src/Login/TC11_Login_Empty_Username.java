
package Login;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TC11_Login_Empty_Username {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.saucedemo.com/");

            WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));

            // Enter password only
            driver.findElement(By.xpath("//input[@id='password']"))
                .sendKeys("secret_sauce");

            driver.findElement(By.xpath("//input[@id='login-button']"))
                .click();

            WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[@data-test='error']")));

            if (error.getText().contains("Username is required")) {
                System.out.println("TC11 PASS: Empty username validation");
            } else {
                System.out.println("TC11 FAIL");
            }

        } finally {
            driver.quit();
        }
    }
}