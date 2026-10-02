package Products;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.BaseTest;
import Utilities.LocatorManager;

public class TC02_ProductDisplay extends BaseTest {

    @Test
    public void verifyProductsDisplayed() {

        List<WebElement> products =
                driver.findElements(
                        LocatorManager.productItem()
                );

        System.out.println("Total products: " + products.size());

        boolean allProductsDisplayed = true;

        for (WebElement product : products) {

            if (product.isDisplayed()) {

                System.out.println("Product is displayed.");

            } else {

                System.out.println("Product is NOT displayed.");

                allProductsDisplayed = false;
            }
        }

        Assert.assertTrue(
                allProductsDisplayed && !products.isEmpty(),
                "One or more products are not displayed."
        );

        System.out.println(
                "TC02 PASS: All products are displayed."
        );
    }
}