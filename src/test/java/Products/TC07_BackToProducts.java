package Products;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.BaseTest;
import Utilities.LocatorManager;

public class TC07_BackToProducts {

    @Test
    public void backToProducts() {
    	BaseTest baseTest = new BaseTest();

    	try {
    	    baseTest.setUp();

    	    WebDriver driver = baseTest.getDriver();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        // Find all products
        List<WebElement> products =
                wait.until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                LocatorManager.productItem()
                        )
                );

        System.out.println(
                "Number of products found: " + products.size()
        );

        // SauceDemo currently contains 6 products
        Assert.assertEquals(
                products.size(),
                6,
                "Expected 6 products on Products page"
        );

        boolean allBackNavigationSuccessful = true;

        // Test every product
        for (int i = 0; i < products.size(); i++) {

            /*
             * After returning from Product Details,
             * find the product elements again.
             */
            List<WebElement> currentProducts =
                    wait.until(
                            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                    LocatorManager.productItem()
                            )
                    );

            WebElement product = currentProducts.get(i);

            // Get product name
            String productName =
                    product.findElement(
                            LocatorManager.productName()
                    ).getText();

            System.out.println(
                    "Testing product: " + productName
            );

            // Find product title link
            WebElement productLink =
                    product.findElement(
                            LocatorManager.productTitleLink()
                    );

            /*
             * Wait until the product title is clickable.
             */
            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            productLink
                    )
            );

            /*
             * Normal Selenium click.
             */
            productLink.click();

            /*
             * In our previous runs, SauceDemo occasionally
             * did not process the click when repeatedly navigating
             * between Products and Details.
             *
             * If normal click does not navigate, use JavaScript
             * click as a fallback.
             */
            try {

                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(3)
                ).until(
                        ExpectedConditions.urlContains(
                                "inventory-item.html"
                        )
                );

            } catch (Exception e) {

                System.out.println(
                        "Normal click did not navigate for: "
                                + productName
                );

                System.out.println(
                        "Using JavaScript click fallback..."
                );

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].click();",
                        productLink
                );

                wait.until(
                        ExpectedConditions.urlContains(
                                "inventory-item.html"
                        )
                );
            }

            System.out.println(
                    "Product Details URL: "
                            + driver.getCurrentUrl()
            );

            // Find Back to Products button
            WebElement backButton =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    LocatorManager.backToProductsButton()
                            )
                    );

            boolean buttonDisplayed =
                    backButton.isDisplayed();

            System.out.println(
                    "Back to Products button displayed: "
                            + buttonDisplayed
            );

            // Click Back to Products
            backButton.click();

            try {

                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(3)
                ).until(
                        ExpectedConditions.urlContains(
                                "inventory.html"
                        )
                );

            } catch (Exception e) {

                System.out.println(
                        "Normal Back to Products click did not navigate for: "
                                + productName
                );

                System.out.println(
                        "Using JavaScript click fallback..."
                );

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].click();",
                        backButton
                );

                wait.until(
                        ExpectedConditions.urlContains(
                                "inventory.html"
                        )
                );
            }

            /*
             * Verify Products page contains products.
             */
            List<WebElement> productsAfterBack =
                    wait.until(
                            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                    LocatorManager.productItem()
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

            System.out.println(
                    "-------------------------"
            );
        }

        /*
         * Final TC07 result.
         */
        Assert.assertTrue(
                allBackNavigationSuccessful,
                "Back to Products functionality failed for one or more products."
        );

        System.out.println(
                "TC07 PASS: Back to Products functionality works correctly for all products."
        );
    	}
    	finally {
    		baseTest.tearDown();
    	}
    }
}