package Products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC08_ProductSortingAZ {

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

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for Products page
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("inventory_item")
                )
        );

        // Locate sorting dropdown
        Select sortDropdown =
                new Select(
                        driver.findElement(
                                By.xpath("//select[@data-test='product-sort-container']")
                        )
                );

        sortDropdown.selectByVisibleText("Name (A to Z)");

        System.out.println("Sorting selected: Name (A to Z)");

        // Wait for products after sorting
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("inventory_item_name")
                )
        );

        // Get all product names in displayed order
        List<WebElement> productElements =
                driver.findElements(
                        By.className("inventory_item_name")
                );

        List<String> actualNames =
                new ArrayList<>();

        for (WebElement product : productElements) {

            String productName = product.getText();

            actualNames.add(productName);

            System.out.println(
                    "Product: " + productName
            );
        }

        System.out.println("-------------------------");

        // Create expected alphabetical order
        List<String> expectedNames =
                new ArrayList<>(actualNames);

        Collections.sort(expectedNames);

        System.out.println("Actual order:");
        for (String name : actualNames) {
            System.out.println(name);
        }

        System.out.println("-------------------------");

        System.out.println("Expected A-Z order:");
        for (String name : expectedNames) {
            System.out.println(name);
        }

        System.out.println("-------------------------");

        // Compare actual order with expected order
        if (actualNames.equals(expectedNames)) {

            System.out.println(
                    "TC08 PASS: Products are sorted correctly from A to Z."
            );

        } else {

            System.out.println(
                    "TC08 FAIL: Products are not sorted correctly from A to Z."
            );
        }

        driver.quit();
    }
}