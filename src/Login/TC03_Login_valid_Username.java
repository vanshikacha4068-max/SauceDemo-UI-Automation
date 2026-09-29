
package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class TC03_Login_valid_Username {

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

            // Enter valid username
            driver.findElement(
                By.xpath("//input[@id='user-name']")
            ).sendKeys("standard_user");

            // Enter valid password
            driver.findElement(
                By.xpath("//input[@id='password']")
            ).sendKeys("secret_sauce");

            // Click login button
            driver.findElement(
                By.xpath("//input[@id='login-button']")
            ).click();

            // Wait for Products page
            WebElement products = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[@class='title']")
                )
            );

            // Verify result
            String actual = products.getText();

            System.out.println("Page Title: " + actual);

            if (actual.equals("Products")) {

                System.out.println(
                    "TC03 PASS: Valid username login successful"
                );

            } else {

                System.out.println("TC03 FAIL");
            }

        } finally {

            // Close browser
            driver.quit();
        }
    }
}
