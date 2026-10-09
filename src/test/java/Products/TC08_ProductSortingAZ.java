package Products;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.BaseTest;
import Utilities.LocatorManager;

public class TC08_ProductSortingAZ {

    @Test
    public void verifyProductSortingAZ() {

        BaseTest baseTest = new BaseTest();

        try {

            // Step 1: Setup browser and login
            baseTest.setUp();

            WebDriver driver = baseTest.getDriver();

            WebDriverWait wait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(10)
                    );

            System.out.println("Login completed");

            System.out.println(
                    "Current URL: " + driver.getCurrentUrl()
            );

            // Step 2: Wait for the Products page
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            LocatorManager.productItem()
                    )
            );

            // Step 3: Locate the sorting dropdown
            Select sortDropdown =
                    new Select(
                            wait.until(
                                    ExpectedConditions.elementToBeClickable(
                                            LocatorManager.sortDropdown()
                                    )
                            )
                    );

            // Step 4: Select Name (A to Z)
            sortDropdown.selectByVisibleText("Name (A to Z)");

            System.out.println(
                    "Sorting selected: Name (A to Z)"
            );

            // Step 5: Wait for the product names
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            LocatorManager.productName()
                    )
            );

            // Step 6: Retrieve product names in displayed order
            List<WebElement> productElements =
                    driver.findElements(
                            LocatorManager.productName()
                    );

            List<String> actualNames = new ArrayList<>();

            for (WebElement product : productElements) {

                String productName = product.getText();

                actualNames.add(productName);

                System.out.println(
                        "Product: " + productName
                );
            }

            System.out.println("-------------------------");

            // Step 7: Create expected alphabetical order
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

            // Step 8: Compare actual and expected order
            Assert.assertEquals(
                    actualNames,
                    expectedNames,
                    "Products are not sorted correctly from A to Z."
            );

            System.out.println(
                    "TC08 PASS: Products are sorted correctly from A to Z."
            );

        } finally {

            // Step 9: Close browser
            baseTest.tearDown();
        }
    }
}