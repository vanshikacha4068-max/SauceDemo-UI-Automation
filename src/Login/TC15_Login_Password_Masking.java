
package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC15_Login_Password_Masking {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            driver.get("https://www.saucedemo.com/");

            // Enter password
            driver.findElement(By.id("password"))
                  .sendKeys("secret_sauce");

            // Get password field type
            String type = driver.findElement(
                    By.id("password")
            ).getAttribute("type");

            // Verify password masking
            if ("password".equals(type)) {

                System.out.println("TEST CASE PASSED");
                System.out.println("Password is masked.");

            } else {

                System.out.println("TEST CASE FAILED");
                System.out.println("Password is not masked.");
            }

        } finally {
            driver.quit();
        }
    }
}
