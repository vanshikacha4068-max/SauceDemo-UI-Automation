
package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class TC04_Login_Username_standard_user {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {

            // Open SauceDemo
            driver.get("https://www.saucedemo.com/");

            WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10)
            );

            // Enter username
            driver.findElement(
                By.xpath("//input[@id='user-name']")
            ).sendKeys("standard_user");

            // Enter password
            driver.findElement(
                By.xpath("//input[@id='password']")
            ).sendKeys("secret_sauce");

            // Click login button
            driver.findElement(
                By.xpath("//input[@id='login-button']")
            ).click();

            // Wait for Products page
            WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[@class='title']")
                )
            );

            // Verify result
            String actual = title.getText();

            System.out.println("Page Title: " + actual);

            if (actual.equals("Products")) {

                System.out.println(
                    "TC04 PASS: standard_user login successful"
                );

            } else {

                System.out.println("TC04 FAIL");
            }

        } finally {

            driver.quit();
        }
    }
}