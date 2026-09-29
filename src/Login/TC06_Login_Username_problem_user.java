
package Login;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TC06_Login_Username_problem_user {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.saucedemo.com/");

            WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));

            driver.findElement(By.xpath("//input[@id='user-name']"))
                .sendKeys("problem_user");

            driver.findElement(By.xpath("//input[@id='password']"))
                .sendKeys("secret_sauce");

            driver.findElement(By.xpath("//input[@id='login-button']"))
                .click();

            WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[@class='title']")));

            if (title.getText().equals("Products")) {
                System.out.println("TC06 PASS: Problem user login successful");
            } else {
                System.out.println("TC06 FAIL");
            }

        } finally {
            driver.quit();
        }
    }
}