package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC02_Login_Invalid_passward {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            
            driver.get("https://www.saucedemo.com/");

            
            Login_page willer = new Login_page(driver);

            
            willer.Loginn(
                    "standard_user",
                    "wrong_password"
            );

            
            String errorMessage = driver.findElement(
                    By.cssSelector("[data-test='error']")
            ).getText();

            
            if (errorMessage.contains(
                    "Username and password do not match")) {

                System.out.println("TEST CASE PASSED");
                System.out.println("Invalid password error displayed.");

            } else {

                System.out.println("TEST CASE FAILED");
            }

        } finally {
            driver.quit();
        }
    }
}

