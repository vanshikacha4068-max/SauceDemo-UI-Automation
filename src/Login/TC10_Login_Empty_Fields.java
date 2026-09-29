
package Login;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TC10_Login_Empty_Fields {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.saucedemo.com/");

            WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));

            // Click login without entering anything
            driver.findElement(By.xpath("//input[@id='login-button']"))
                .click();

            WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[@data-test='error']")));

            if (error.getText().contains("Username is required")) {
                System.out.println("TC10 PASS: Empty fields validation");
            } else {
                System.out.println("TC10 FAIL");
            }

        } finally {
            driver.quit();
        }
    }
}