package Products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC04_ProductDescription {

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

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("inventory_item")
                )
        );

        List<WebElement> products =
                driver.findElements(
                        By.xpath("//div[@class='inventory_item']")
                );

        System.out.println("Number of products found: "
                + products.size());

        boolean allDescriptionsDisplayed = true;

        for (WebElement product : products) {

            String productName = product.findElement(
                    By.className("inventory_item_name")
            ).getText();

            WebElement description = product.findElement(
                    By.className("inventory_item_desc")
            );

            boolean displayed = description.isDisplayed();

            String descriptionText = description.getText();

            System.out.println("Product: " + productName);
            System.out.println("Description: " + descriptionText);
            System.out.println("Description displayed: " + displayed);
            System.out.println("-------------------------");

            if (!displayed || descriptionText.trim().isEmpty()) {
                allDescriptionsDisplayed = false;
            }
        }

        if (allDescriptionsDisplayed && !products.isEmpty()) {

            System.out.println(
                    "TC04 PASS: All product descriptions are displayed."
            );

        } else {

            System.out.println(
                    "TC04 FAIL: One or more product descriptions are not properly displayed."
            );
        }

        driver.quit();
    }
}