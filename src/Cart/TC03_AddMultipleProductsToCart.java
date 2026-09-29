package Cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC03_AddMultipleProductsToCart {

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

        // Add Product 1 - Backpack
        driver.findElement(
                By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")
        ).click();

        // Add Product 2 - Bike Light
        driver.findElement(
                By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']")
        ).click();

        // Add Product 3 - Bolt T-Shirt
        driver.findElement(
                By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']")
        ).click();

        // Open Cart
        driver.findElement(
                By.xpath("//a[@class='shopping_cart_link']")
        ).click();

        // Verify products
        String product1 = driver.findElement(
                By.xpath("//div[text()='Sauce Labs Backpack']")
        ).getText();

        String product2 = driver.findElement(
                By.xpath("//div[text()='Sauce Labs Bike Light']")
        ).getText();

        String product3 = driver.findElement(
                By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']")
        ).getText();

        // Verify all three products
        if (product1.equals("Sauce Labs Backpack")
                && product2.equals("Sauce Labs Bike Light")
                && product3.equals("Sauce Labs Bolt T-Shirt")) {

            System.out.println("PASS - All three products are added to the cart");

        } else {

            System.out.println("FAIL - Products are not correctly displayed in cart");
        }

        // Close browser
        driver.quit();
    }
}