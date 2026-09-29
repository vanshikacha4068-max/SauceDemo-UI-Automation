
package Login;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TC17_Login_Enter_Key {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.saucedemo.com/");

            WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));

            driver.findElement(By.xpath("//input[@id='user-name']"))
                .sendKeys("standard_user");

            // Press Enter from password field
            driver.findElement(By.xpath("//input[@id='password']"))
                .sendKeys("secret_sauce", Keys.ENTER);

            WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[@class='title']")));

            if (title.getText().equals("Products")) {
                System.out.println("TC17 PASS: Login using Enter key");
            } else {
                System.out.println("TC17 FAIL");
            }

        } finally {
            driver.quit();
        }
    }
}