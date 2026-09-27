package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC03_Login_valid_Username {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {

            
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");

            
            Login_page willer = new Login_page(driver);

            
            willer.Loginn(
                    "standard_user",
                    "secret_sauce"
            );

        
            if (driver.getCurrentUrl().contains("inventory.html")) {

                System.out.println("TEST CASE PASSED");
                System.out.println("Valid username login successful!");

            } else {

                System.out.println("TEST CASE FAILED");
                System.out.println("Login unsuccessful!");
            }

        } finally {

            // Close browser
            driver.quit();
        }
    }
}
