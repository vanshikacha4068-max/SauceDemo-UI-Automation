package Cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC01_AddProductToCart {

    public static void main(String[] args)
    {

        WebDriver driver = new ChromeDriver();
        
        driver.manage().window().maximize();

        // Open website
        driver.get("https://www.saucedemo.com/");

        // Login
        driver.findElement(By.xpath("//input[@id='user-name']"))
              .sendKeys("standard_user");

        driver.findElement(By.xpath("//input[@id='password']"))
              .sendKeys("secret_sauce");

        driver.findElement(By.xpath("//input[@id='login-button']"))
              .click();

        // Add product to cart
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']"))
              .click();

        // Open cart
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']"))
              .click();

        // Verify product
        String productName = driver.findElement(
                By.xpath("//div[@class='inventory_item_name']")
        ).getText();

        if (productName.equals("Sauce Labs Backpack")) {
            System.out.println("PASS - Product added successfully");
        } else {
            System.out.println("FAIL - Product not found in cart");
        }

        driver.quit();
    }
}
