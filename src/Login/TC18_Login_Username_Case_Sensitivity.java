
package Login;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TC18_Login_Username_Case_Sensitivity {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.saucedemo.com/");

            WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));

            // Enter username with incorrect capitalization
            driver.findElement(By.xpath("//input[@id='user-name']"))
                .sendKeys("Standard_User");

            driver.findElement(By.xpath("//input[@id='password']"))
                .sendKeys("secret_sauce");

            driver.findElement(By.xpath("//input[@id='login-button']"))
                .click();

            WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[@data-test='error']")));

            if (error.getText().contains(
                "Username and password do not match")) {

                System.out.println(
                    "TC18 PASS: Username is case-sensitive");

            } else {

                System.out.println("TC18 FAIL");
            }

        } finally {
            driver.quit();
        }
    }
}
