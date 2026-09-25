package Products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC05_ProductPrice {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        // Login
        driver.findElement(By.id("user-name"))
                .sendKeys("standard_user");

        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");

        driver.findElement(By.id("login-button"))
                .click();

        System.out.println("Login completed");
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // Wait for Products page
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("inventory_item")
                )
        );

        // Find all products
        List<WebElement> products =
                driver.findElements(
                        By.xpath("//div[@class='inventory_item']")
                );

        System.out.println("Number of products found: "
                + products.size());

        boolean allPricesDisplayed = true;

        // Check price of every product
        for (WebElement product : products) {

            // Get product name
            String productName =
                    product.findElement(
                            By.className("inventory_item_name")
                    ).getText();

            // Find price inside current product
            WebElement price =
                    product.findElement(
                            By.xpath(".//div[@class='inventory_item_price']")
                    );

            // Check whether price is displayed
            boolean displayed = price.isDisplayed();

            // Get price text
            String priceText = price.getText();

            System.out.println("Product: " + productName);
            System.out.println("Price: " + priceText);
            System.out.println("Price displayed: " + displayed);
            System.out.println("-------------------------");

            // Validate price
            if (!displayed || priceText.trim().isEmpty()) {
                allPricesDisplayed = false;
            }
        }

        // Final result
        if (allPricesDisplayed && !products.isEmpty()) {

            System.out.println(
                    "TC05 PASS: All products have a visible price."
            );

        } else {

            System.out.println(
                    "TC05 FAIL: One or more products do not have a visible price."
            );
        }

        // Close browser
        driver.quit();
    }
}