package Products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.BaseTest;
import Utilities.LocatorManager;

public class TC06_ProductDetailsNavigation {

    
		@Test
		 public void productDetailsNavigation() {
			 // Create BaseTest object
	        BaseTest baseTest = new BaseTest();

	        try {

	            // Initialize browser and login
	            baseTest.setUp();

	            // Get WebDriver from BaseTest object
	            WebDriver driver = baseTest.getDriver();

	            WebDriverWait wait =
	                    new WebDriverWait(driver, Duration.ofSeconds(10));

	            // Wait for Products page
	            wait.until(
	                    ExpectedConditions.visibilityOfElementLocated(
	                            LocatorManager.productItem()
	                    )
	            );

	            // Find all products
	            List<WebElement> products =
	                    driver.findElements(
	                            LocatorManager.productItem()
	                    );

	            System.out.println(
	                    "Number of products found: " + products.size()
	            );

	            Assert.assertFalse(
	                    products.isEmpty(),
	                    "No products were found on the Products page."
	            );

	            boolean allNavigationSuccessful = true;

	            // Check every product
	            for (int i = 0; i < products.size(); i++) {

	                // Re-find products after returning from details page
	                products =
	                        wait.until(
	                                ExpectedConditions
	                                        .visibilityOfAllElementsLocatedBy(
	                                                LocatorManager.productItem()
	                                        )
	                        );

	                WebElement product = products.get(i);

	                // Get product name
	                String productName =
	                        product.findElement(
	                                LocatorManager.productName()
	                        ).getText();

	                System.out.println(
	                        "Testing product: " + productName
	                );

	                // Find clickable product title
	                WebElement productLink =
	                        product.findElement(
	                                LocatorManager.productTitleLink()
	                        );
	                
	             // Wait until the product title is clickable
	                wait.until(
	                        ExpectedConditions.elementToBeClickable(productLink)
	                );

	                // Click product title
	                productLink.click();
	                try {
	                // Wait for Product Details page
	                wait.until(
	                        ExpectedConditions.urlContains(
	                                "inventory-item.html"
	                        )
	                );
	                } catch (org.openqa.selenium.TimeoutException e) {

	                    System.out.println(
	                            "Normal click did not navigate for: " + productName
	                    );

	                    System.out.println(
	                            "Trying JavaScript click..."
	                    );

	                    // Fallback if the normal click does not navigate
	                    ((org.openqa.selenium.JavascriptExecutor) driver)
	                            .executeScript("arguments[0].click();", productLink);

	                    // Wait again for Product Details page
	                    wait.until(
	                            ExpectedConditions.urlContains(
	                                    "inventory-item.html"
	                            )
	                    );
	                }
	             // Get the current URL after navigation
	                String currentUrl = driver.getCurrentUrl();

	                System.out.println(
	                        "Details URL: " + currentUrl
	                );

	                // Find product name on the details page
	                WebElement detailsProductName =
	                        wait.until(
	                                ExpectedConditions.visibilityOfElementLocated(
	                                        By.className("inventory_details_name")
	                                )
	                        );

	                String detailsName =
	                        detailsProductName.getText();

	                System.out.println(
	                        "Details page product: " + detailsName
	                );

	                // Verify navigation and product identity
	                if (currentUrl.contains("inventory-item.html")
	                        && productName.equals(detailsName)) {

	                    System.out.println("Result: PASS");

	                } else {

	                    System.out.println("Result: FAIL");

	                    allNavigationSuccessful = false;
	                }

	                System.out.println("-------------------------");

	                // Return to Products page
	                driver.navigate().back();

	                wait.until(
	                        ExpectedConditions.visibilityOfElementLocated(
	                                LocatorManager.productItem()
	                        )
	                );
	            }

	            // Final assertion
	            Assert.assertTrue(
	                    allNavigationSuccessful,
	                    "Product details navigation failed for one or more products."
	            );

	            System.out.println(
	                    "TC06 PASS: Product details navigation works correctly "
	                            + "for all products."
	            );

	        } finally {

	            // Close browser even if the test fails
	            baseTest.tearDown();
	        }
    }
}
