
package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC04_Login_Username_standard_user {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            // Open SauceDemo website
            driver.get("https://www.saucedemo.com/");

            // Enter username
            driver.findElement(By.id("user-name"))
                  .sendKeys("standard_user");

            // Enter password
            driver.findElement(By.id("password"))
                  .sendKeys("secret_sauce");

            // Click login button
            driver.findElement(By.id("login-button"))
                  .click();

            // Verify login
            if (driver.getCurrentUrl().contains("inventory.html")) {

                System.out.println("TEST CASE PASSED");
                System.out.println("Login successful!");

            } else {

                System.out.println("TEST CASE FAILED");
                System.out.println("Login unsuccessful!");
            }

        } finally {
            driver.quit();
        }
    }
}
