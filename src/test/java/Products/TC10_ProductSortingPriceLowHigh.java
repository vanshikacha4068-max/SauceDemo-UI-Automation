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

public class TC10_ProductSortingPriceLowHigh {

    @Test
    public void verifyProductSortingPriceLowToHigh() {

        BaseTest baseTest = new BaseTest();

        try {
            // Browser setup and login
            baseTest.setUp();

            WebDriver driver = baseTest.getDriver();

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            // Locate sorting dropdown
            WebElement dropdownElement = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            LocatorManager.sortDropdown()));

            Select sortDropdown = new Select(dropdownElement);

            // Select Price: Low to High
            sortDropdown.selectByValue("lohi");

            System.out.println(
                    "Sorting selected: Price (low to high)");

            // Wait until product prices are visible
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            LocatorManager.productPrice()));

            // Get product prices
            List<WebElement> priceElements =
                    driver.findElements(LocatorManager.productPrice());

            Assert.assertFalse(
                    priceElements.isEmpty(),
                    "TC10 FAIL: No product prices found.");

            // Store actual prices
            List<Double> actualPrices = new ArrayList<>();

            for (WebElement priceElement : priceElements) {

                String priceText = priceElement.getText();

                // Remove $ and convert String to double
                double price =
                        Double.parseDouble(priceText.replace("$", ""));

                actualPrices.add(price);

                System.out.println("Product price: " + priceText);
            }

            // Create expected Low to High order
            List<Double> expectedPrices =
                    new ArrayList<>(actualPrices);

            Collections.sort(expectedPrices);

            System.out.println("\nActual order:");

            for (Double price : actualPrices) {
                System.out.println(price);
            }

            System.out.println("\nExpected Low to High order:");

            for (Double price : expectedPrices) {
                System.out.println(price);
            }

            // Compare actual and expected
            Assert.assertEquals(
                    actualPrices,
                    expectedPrices,
                    "TC10 FAIL: Products are not sorted correctly from Low to High.");

            System.out.println(
                    "\nTC10 PASS: Products are sorted correctly from Low to High.");

        } finally {
            baseTest.tearDown();
        }
    }
}