package Products;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.BaseTest;
import Utilities.LocatorManager;

public class TC02_ProductDisplay  {

    @Test
    public void verifyProductsDisplayed() {
    	
    	BaseTest baseTest=new BaseTest();
    	
        try {

            baseTest.setUp();

            WebDriver driver = baseTest.getDriver();

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            List<WebElement> products =
                    wait.until(
                            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                    LocatorManager.productItem()
                            )
                    );

            System.out.println(
                    "Total products: " + products.size()
            );

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
        finally {
        	baseTest.tearDown();
        }
    }
}