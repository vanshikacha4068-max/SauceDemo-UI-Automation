package Products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC01_ProductImage {

    public static void main(String[] args) {

        // Open the website
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        // Login with username and password
        driver.findElement(By.id("user-name"))
                .sendKeys("standard_user");

        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");

        driver.findElement(By.id("login-button"))
                .click();

        System.out.println("Login completed");
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // Wait until products are available
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("inventory_item")
                )
        );

        // Find all products
        List<WebElement> products = driver.findElements(
                By.xpath("//div[@class='inventory_item']")
        );

        System.out.println("Number of products found: " + products.size());

        boolean allImagesDisplayed = true;

        // Check image of every product
        for (WebElement product : products) {

            String productName = product.findElement(
                    By.className("inventory_item_name")
            ).getText();

            WebElement image = product.findElement(
                    By.xpath(".//img")
            );

            // Wait for this particular product image
            wait.until(
                    ExpectedConditions.visibilityOf(image)
            );

            boolean imageDisplayed = image.isDisplayed();

            System.out.println("Product: " + productName);
            System.out.println("Image displayed: " + imageDisplayed);
            System.out.println("Image width: "
                    + image.getSize().getWidth());
            System.out.println("Image height: "
                    + image.getSize().getHeight());
            System.out.println("Image src: "
                    + image.getAttribute("src"));
            System.out.println("Image alt: "
                    + image.getAttribute("alt"));
            System.out.println("-------------------------");

            // If even one image is not displayed,
            // the complete test should fail
            if (!imageDisplayed) {
                allImagesDisplayed = false;
            }
        }

        // Overall test result
        if (allImagesDisplayed) {
            System.out.println(
                    "TC01 PASS: All product images are displayed."
            );
        } else {
            System.out.println(
                    "TC01 FAIL: One or more product images are not displayed."
            );
        }

        // Close browser
        driver.quit();
    }
}