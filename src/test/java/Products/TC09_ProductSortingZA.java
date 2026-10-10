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

public class TC09_ProductSortingZA {

    @Test
    public void verifyProductSortingZA() {

        BaseTest baseTest = new BaseTest();

        try {
            // Step 1: Setup browser and login
            baseTest.setUp();

            WebDriver driver = baseTest.getDriver();

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            // Step 2: Wait for the product sorting dropdown
            WebElement dropdownElement = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            LocatorManager.sortDropdown()
                    )
            );

            Select sortDropdown = new Select(dropdownElement);

            // Step 3: Select Name (Z to A)
            sortDropdown.selectByVisibleText("Name (Z to A)");

            // Step 4: Wait for the sorting operation to update the products
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    LocatorManager.productItem()
            ));

            // Step 5: Get the actual product names
            List<WebElement> productElements =
                    driver.findElements(LocatorManager.productItem());

            List<String> actualNames = new ArrayList<>();

            for (WebElement product : productElements) {
                actualNames.add(
                        product.findElement(LocatorManager.productName())
                               .getText()
                );
            }

            // Step 6: Create an independent expected product list
            List<String> expectedNames = new ArrayList<>(List.of(
                    "Test.allTheThings() T-Shirt (Red)",
                    "Sauce Labs Onesie",
                    "Sauce Labs Fleece Jacket",
                    "Sauce Labs Bolt T-Shirt",
                    "Sauce Labs Bike Light",
                    "Sauce Labs Backpack"
            ));

            // Step 7: Verify the result
            Assert.assertEquals(
                    actualNames,
                    expectedNames,
                    "TC09 FAIL: Products are not sorted correctly from Z to A."
            );

            System.out.println(
                    "TC09 PASS: Products are sorted correctly from Z to A."
            );

        } finally {
            baseTest.tearDown();
        }
    }
}