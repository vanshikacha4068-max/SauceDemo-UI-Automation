package Products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC06_ProductDetailsNavigation {

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

        // Get number of products
        List<WebElement> products =
                driver.findElements(
                        By.xpath("//div[@class='inventory_item']")
                );

        System.out.println("Number of products found: "
                + products.size());

        boolean allNavigationSuccessful = true;

        // Check every product
        for (int i = 0; i < products.size(); i++) {

            // Re-find products after returning from details page
            products = driver.findElements(
                    By.xpath("//div[@class='inventory_item']")
            );

            WebElement product = products.get(i);

            // Get product name
            String productName =
                    product.findElement(
                            By.className("inventory_item_name")
                    ).getText();

            System.out.println("Testing product: "
                    + productName);

            // Find clickable product title
            WebElement productLink =
                    product.findElement(
                            By.xpath(".//a[contains(@id,'title_link')]")
                    );

            // Click product title
            productLink.click();

            // Wait for details page
            wait.until(
                    ExpectedConditions.urlContains(
                            "inventory-item.html"
                    )
            );

            String currentUrl = driver.getCurrentUrl();

            System.out.println("Details URL: "
                    + currentUrl);

            // Get product name from details page
            WebElement detailsProductName =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.className("inventory_details_name")
                            )
                    );

            String detailsName =
                    detailsProductName.getText();

            System.out.println("Details page product: "
                    + detailsName);

            // Verify navigation and product identity
            if (currentUrl.contains("inventory-item.html")
                    && productName.equals(detailsName)) {

                System.out.println(
                        "Result: PASS"
                );

            } else {

                System.out.println(
                        "Result: FAIL"
                );

                allNavigationSuccessful = false;
            }

            System.out.println("-------------------------");

            // Return to Products page
            driver.navigate().back();

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.className("inventory_item")
                    )
            );
        }

        // Final result
        if (allNavigationSuccessful && !products.isEmpty()) {

            System.out.println(
                    "TC06 PASS: Product details navigation works correctly for all products."
            );

        } else {

            System.out.println(
                    "TC06 FAIL: Product details navigation failed for one or more products."
            );
        }

        driver.quit();
    }
}