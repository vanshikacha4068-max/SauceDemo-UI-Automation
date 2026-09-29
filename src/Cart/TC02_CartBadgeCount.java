package Cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC02_CartBadgeCount {

    public static void main(String[] args) {

        // Launch browser
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        // Open SauceDemo
        driver.get("https://www.saucedemo.com/");

        // Login
        driver.findElement(By.xpath("//input[@id='user-name']"))
              .sendKeys("standard_user");

        driver.findElement(By.xpath("//input[@id='password']"))
              .sendKeys("secret_sauce");

        driver.findElement(By.xpath("//input[@id='login-button']"))
              .click();

        // Add one product
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']"))
              .click();

        // Get cart badge count
        String cartCount = driver.findElement(
                By.xpath("//span[@class='shopping_cart_badge']")
        ).getText();

        // Verify cart count
        if (cartCount.equals("1")) {

            System.out.println("PASS - Cart badge displays 1");

        } else {

            System.out.println("FAIL - Cart badge count is incorrect");
        }

        // Close browser
        driver.quit();
    }
}