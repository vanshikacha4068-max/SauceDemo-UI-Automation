package Products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC02_ProductDisplay {

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

        // Find all product cards
        List<WebElement> products =
                driver.findElements(
                        By.xpath("//div[@class='inventory_item']")
                );

        System.out.println("Number of products found: "
                + products.size());

        // Verify products are displayed
        boolean allProductsDisplayed = true;

        for (WebElement product : products) {

            boolean displayed = product.isDisplayed();

            String productName = product.findElement(
                    By.className("inventory_item_name")
            ).getText();

            System.out.println(
                    "Product: " + productName
                    + " | Displayed: " + displayed
            );

            if (!displayed) {
                allProductsDisplayed = false;
            }
        }

        // Final result
        if (allProductsDisplayed && !products.isEmpty()) {

            System.out.println(
                    "All available products are displayed."
            );

        } else {

            System.out.println(
                    "Products are not displayed properly."
            );
        }

        driver.quit();
    }
}