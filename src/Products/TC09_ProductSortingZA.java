package Products;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TC09_ProductSortingZA {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {

            driver.manage().window().maximize();

            // Open SauceDemo
            driver.get("https://www.saucedemo.com/");

            // Login
            driver.findElement(By.id("user-name"))
                  .sendKeys("standard_user");

            driver.findElement(By.id("password"))
                  .sendKeys("secret_sauce");

            driver.findElement(By.id("login-button"))
                  .click();

            // Locate sorting dropdown
            Select sortDropdown = new Select(
                    driver.findElement(
                            By.xpath("//select[@data-test='product-sort-container']")
                    )
            );

            // Select Z to A
            sortDropdown.selectByVisibleText("Name (Z to A)");

            System.out.println("Sorting selected: Name (Z to A)");

            // Get actual product names
            List<WebElement> productElements =
                    driver.findElements(By.className("inventory_item_name"));

            List<String> actualNames = new ArrayList<>();

            for (WebElement product : productElements) {

                String name = product.getText();

                actualNames.add(name);

                System.out.println("Product: " + name);
            }

            // Create expected Z-A order
            List<String> expectedNames =
                    new ArrayList<>(actualNames);

            Collections.sort(
                    expectedNames,
                    Collections.reverseOrder()
            );

            System.out.println("\nActual order:");

            for (String name : actualNames) {
                System.out.println(name);
            }

            System.out.println("\nExpected Z-A order:");

            for (String name : expectedNames) {
                System.out.println(name);
            }

            // Compare actual and expected
            if (actualNames.equals(expectedNames)) {

                System.out.println(
                        "\nTC09 PASS: Products are sorted correctly from Z to A."
                );

            } else {

                System.out.println(
                        "\nTC09 FAIL: Products are not sorted correctly from Z to A."
                );
            }

        } finally {

            driver.quit();
        }
    }
}