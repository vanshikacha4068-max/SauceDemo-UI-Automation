package Cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class TC04_VerifyProductDetails {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        // Login
        driver.findElement(By.xpath("//input[@id='user-name']"))
                .sendKeys("standard_user");

        driver.findElement(By.xpath("//input[@id='password']"))
                .sendKeys("secret_sauce");

        driver.findElement(By.xpath("//input[@id='login-button']"))
                .click();

        // Wait for Products page
        wait.until(ExpectedConditions.urlContains("inventory.html"));

        // Backpack name
        String productName = driver.findElement(
                By.xpath("//div[text()='Sauce Labs Backpack']")
        ).getText();

        // Backpack price
        String productPrice = driver.findElement(
                By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']")
        ).getText();

        System.out.println("Product Name: " + productName);
        System.out.println("Product Price: " + productPrice);

        // Add Backpack
        driver.findElement(
                By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")
        ).click();

        // Open Cart
        driver.findElement(
                By.xpath("//a[@class='shopping_cart_link']")
        ).click();

        // Wait for cart page
        wait.until(ExpectedConditions.urlContains("cart.html"));

        // Cart product name
        String cartProductName = driver.findElement(
                By.xpath("//div[text()='Sauce Labs Backpack']")
        ).getText();

        // Cart product price
        String cartProductPrice = driver.findElement(
                By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']")
        ).getText();

        System.out.println("Cart Product Name: " + cartProductName);
        System.out.println("Cart Product Price: " + cartProductPrice);

        if (productName.equals(cartProductName)
                && productPrice.equals(cartProductPrice)) {

            System.out.println("PASS - Product details match");

        } else {

            System.out.println("FAIL - Product details do not match");
        }

        driver.quit();
    }
}