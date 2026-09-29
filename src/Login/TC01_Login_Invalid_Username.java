
package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class TC01_Login_Invalid_Username {

    public static void main(String[] args) {

        // Open browser
        WebDriver driver = new ChromeDriver();

        try {

            // Open website
            driver.get("https://www.saucedemo.com/");

            // Wait for username field
            WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10)
            );

            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@id='user-name']")
                )
            );

            // Enter invalid username
            driver.findElement(
                By.xpath("//input[@id='user-name']")
            ).sendKeys("invalid_user");

            // Enter valid password
            driver.findElement(
                By.xpath("//input[@id='password']")
            ).sendKeys("secret_sauce");

            // Click login button
            driver.findElement(
                By.xpath("//input[@id='login-button']")
            ).click();

            // Wait for error message
            WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[@data-test='error']")
                )
            );

            // Get actual error message
            String actual = error.getText();

            System.out.println("Actual Error: " + actual);

            // Verify result
            if (actual.contains(
                "Username and password do not match"
            )) {

                System.out.println(
                    "TC01 PASS: Invalid username rejected"
                );

            } else {

                System.out.println("TC01 FAIL");
            }

        } finally {

            // Close browser
            driver.quit();
        }
    }
}
     