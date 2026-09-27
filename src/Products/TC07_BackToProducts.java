package Products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC07_BackToProducts {

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

        // Find all products
        List<WebElement> products =
                driver.findElements(
                        By.xpath("//div[@class='inventory_item']")
                );

        System.out.println("Number of products found: "
                + products.size());

        boolean allBackNavigationSuccessful = true;

        // Test every product
        for (int i = 0; i < products.size(); i++) {

            // Re-find products after coming back to Products page
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

            // Find product title
            WebElement productLink =
                    product.findElement(
                            By.xpath(".//a[contains(@id,'title_link')]")
                    );

            // Click product title
            productLink.click();
            // Wait for Product Details page
            wait.until(
                    ExpectedConditions.urlContains(
                            "inventory-item.html"
                    )
            );

            System.out.println(
                    "Product Details URL: "
                    + driver.getCurrentUrl()
            );

            // Find Back to Products button
            WebElement backButton =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    By.xpath(
                                            "//button[@id='back-to-products']"
                                    )
                            )
                    );

            // Check button visibility
            boolean buttonDisplayed =
                    backButton.isDisplayed();

            System.out.println(
                    "Back to Products button displayed: "
                    + buttonDisplayed
            );

            // Click Back to Products
            backButton.click();

            // Wait until Products page is loaded
            wait.until(
                    ExpectedConditions.urlToBe(
                            "https://www.saucedemo.com/inventory.html"
                    )
            );
            // Verify Products page
            List<WebElement> productsAfterBack =
                    wait.until(
                            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                    By.xpath("//div[@class='inventory_item']")
                            )
                    );

            boolean productsDisplayed =
                    !productsAfterBack.isEmpty();

            System.out.println(
                    "Returned to Products page: "
                    + productsDisplayed
            );

            // Product-specific result
            if (buttonDisplayed && productsDisplayed) {

                System.out.println(
                        "Result: PASS"
                );

            } else {

                System.out.println(
                        "Result: FAIL"
                );

                allBackNavigationSuccessful = false;
            }

            System.out.println("-------------------------");
        }

        // Final result
        if (allBackNavigationSuccessful
                && !products.isEmpty()) {

            System.out.println(
                    "TC07 PASS: Back to Products functionality works correctly for all products."
            );

        } else {
            System.out.println(
                    "TC07 FAIL: Back to Products functionality failed for one or more products."
            );
        }

        driver.quit();
    }
}