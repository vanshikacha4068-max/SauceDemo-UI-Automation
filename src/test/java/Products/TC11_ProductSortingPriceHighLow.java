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

public class TC11_ProductSortingPriceHighLow {

    @Test
    public void verifyProductSortingPriceHighToLow() {

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

            // Select Price: High to Low
            sortDropdown.selectByValue("hilo");

            System.out.println(
                    "Sorting selected: Price (high to low)");

            // Wait until product prices are visible
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            LocatorManager.productPrice()));

            // Get actual prices
            List<WebElement> priceElements =
                    driver.findElements(
                            LocatorManager.productPrice());

            Assert.assertFalse(
                    priceElements.isEmpty(),
                    "TC11 FAIL: No product prices found.");

            List<Double> actualPrices = new ArrayList<>();

            for (WebElement priceElement : priceElements) {

                String priceText = priceElement.getText();

                double price =
                        Double.parseDouble(priceText.replace("$", ""));

                actualPrices.add(price);

                System.out.println("Product price: " + priceText);
            }

            // Create expected High to Low order
            List<Double> expectedPrices =
                    new ArrayList<>(actualPrices);

            Collections.sort(
                    expectedPrices,
                    Collections.reverseOrder());

            System.out.println("\nActual order:");

            for (Double price : actualPrices) {
                System.out.println(price);
            }

            System.out.println("\nExpected High to Low order:");

            for (Double price : expectedPrices) {
                System.out.println(price);
            }

            // Verify actual and expected order
            Assert.assertEquals(
                    actualPrices,
                    expectedPrices,
                    "TC11 FAIL: Products are not sorted correctly from High to Low.");

            System.out.println(
                    "\nTC11 PASS: Products are sorted correctly from High to Low.");

        } finally {
            baseTest.tearDown();
        }
    }
}