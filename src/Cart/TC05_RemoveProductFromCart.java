package Cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC05_RemoveProductFromCart {

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

        // Add Sauce Labs Backpack
        driver.findElement(
                By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")
        ).click();

        // Open Cart
        driver.findElement(
                By.xpath("//a[@class='shopping_cart_link']")
        ).click();

        // Verify product is present before removing
        boolean productBeforeRemove = driver.findElements(
                By.xpath("//div[text()='Sauce Labs Backpack']")
        ).size() > 0;

        if (productBeforeRemove) {
            System.out.println("Product is present in cart");
        }

        // Click Remove button
        driver.findElement(
                By.xpath("//button[@id='remove-sauce-labs-backpack']")
        ).click();

        // Verify product is removed
        boolean productAfterRemove = driver.findElements(
                By.xpath("//div[text()='Sauce Labs Backpack']")
        ).size() > 0;

        if (!productAfterRemove) {

            System.out.println("PASS - Product removed successfully from cart");

        } else {

            System.out.println("FAIL - Product was not removed from cart");
        }

        // Close browser
        driver.quit();
    }
}