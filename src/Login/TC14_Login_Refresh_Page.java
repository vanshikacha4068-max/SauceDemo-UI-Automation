
package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC14_Login_Refresh_Page {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");

            // Refresh the login page
            driver.navigate().refresh();

            // Verify login page is displayed
            boolean usernameDisplayed = driver.findElement(
                    By.id("user-name")
            ).isDisplayed();

            boolean passwordDisplayed = driver.findElement(
                    By.id("password")
            ).isDisplayed();

            boolean loginButtonDisplayed = driver.findElement(
                    By.id("login-button")
            ).isDisplayed();

            if (usernameDisplayed
                    && passwordDisplayed
                    && loginButtonDisplayed) {

                System.out.println("TEST CASE PASSED");
                System.out.println("Login page displayed after refresh.");

            } else {

                System.out.println("TEST CASE FAILED");
            }

        } finally {
            driver.quit();
        }
    }
}
