package Products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC03_ProductNameTitle {

    public static void main(String[] args) {

        WebDriver dr = new ChromeDriver();

        dr.get("https://www.saucedemo.com/");

        // Login
        dr.findElement(By.id("user-name"))
                .sendKeys("standard_user");

        dr.findElement(By.id("password"))
                .sendKeys("secret_sauce");

        dr.findElement(By.id("login-button"))
                .click();

        System.out.println("Login completed");
        System.out.println("Current URL: " + dr.getCurrentUrl());

        // Wait for Products page
        WebDriverWait wait =
                new WebDriverWait(dr, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("inventory_item")
                )
        );

        // Find all product cards
        List<WebElement> products =
                dr.findElements(
                        By.xpath("//div[@class='inventory_item']")
                );

        System.out.println("Number of products found: "
                + products.size());

        boolean allNamesDisplayed = true;

        // Check product name/title for every product
        for (WebElement product : products) {

            WebElement productName =
                    product.findElement(
                            By.className("inventory_item_name")
                    );

            boolean displayed = productName.isDisplayed();

            String name = productName.getText();

            System.out.println("Product Name: " + name);
            System.out.println("Name displayed: " + displayed);
            System.out.println("-------------------------");

            if (!displayed || name.trim().isEmpty()) {
                allNamesDisplayed = false;
            }
        }

        // Final result
        if (allNamesDisplayed && !products.isEmpty()) {

            System.out.println(
                    "TC03 PASS: All products have a visible name/title."
            );

        } else {

            System.out.println(
                    "TC03 FAIL: One or more products do not have a visible name/title."
            );
        }

        dr.quit();
    }
}