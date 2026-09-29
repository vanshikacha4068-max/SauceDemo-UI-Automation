
package Login;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TC13_Login_All_Usernames_Wrong_Password {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        String[] users = {
            "standard_user",
            "locked_out_user",
            "problem_user",
            "performance_glitch_user",
            "error_user",
            "visual_user"
        };

        try {
            driver.get("https://www.saucedemo.com/");

            WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));

            for (String user : users) {

                driver.findElement(By.xpath("//input[@id='user-name']"))
                    .sendKeys(user);

                driver.findElement(By.xpath("//input[@id='password']"))
                    .sendKeys("wrong_password");

                driver.findElement(By.xpath("//input[@id='login-button']"))
                    .click();

                WebElement error = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h3[@data-test='error']")));

                if (error.getText().contains(
                    "Username and password do not match")) {

                    System.out.println(user + " PASS");

                } else {

                    System.out.println(user + " FAIL");
                }

                // Refresh before testing next username
                driver.navigate().refresh();

                wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@id='user-name']")));
            }

        } finally {
            driver.quit();
        }
    }
}
