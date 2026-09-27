
package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC01_Login_Invalid_Username {

    public static void main(String[] args) {

        WebDriver verma = new ChromeDriver();

        try {

            verma.manage().window().maximize();

            verma.get("https://www.saucedemo.com/");

            Login_page varun = new Login_page(verma);

            varun.Loginn(
                "invalid_user",
                "secret_sauce"
            );

            // Get the error message
            String actualMessage = verma.findElement(
                By.cssSelector("[data-test='error']")
            ).getText();

            // Verify the invalid username is rejected
            if (actualMessage.contains(
                "Username and password do not match"
            )) {

                System.out.println(
                    "TC01_Login_Invalid_Username: PASS"
                );

            } else {

                System.out.println(
                    "TC01_Login_Invalid_Username: FAIL"
                );

            }

        } finally {

            verma.quit();

        }

    }

}
