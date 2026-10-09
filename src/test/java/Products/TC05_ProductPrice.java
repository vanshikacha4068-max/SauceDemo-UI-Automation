package Products;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.BaseTest;
import Utilities.LocatorManager;

public class TC05_ProductPrice  {

    @Test
    public void verifyProductPricesDisplayed() {
    	 BaseTest baseTest = new BaseTest();

         try {

             baseTest.setUp();

             WebDriver driver = baseTest.getDriver();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        LocatorManager.productItem()
                )
        );

        List<WebElement> products =
                driver.findElements(
                        LocatorManager.productItem()
                );

        System.out.println(
                "Number of products found: " + products.size()
        );

        boolean allPricesDisplayed = true;

        for (WebElement product : products) {

            WebElement price =
                    product.findElement(
                            LocatorManager.productPrice()
                    );

            boolean displayed = price.isDisplayed();

            String priceText = price.getText();

            System.out.println(
                    "Product Price: " + priceText
            );

            System.out.println(
                    "Price displayed: " + displayed
            );

            System.out.println("-------------------------");

            if (!displayed || priceText.trim().isEmpty()) {
                allPricesDisplayed = false;
            }
        }

        Assert.assertTrue(
                allPricesDisplayed && !products.isEmpty(),
                "One or more products do not have a visible price."
        );

        System.out.println(
                "TC05 PASS: All products have a visible price."
        );
        }
         finally {
        	 baseTest.tearDown();
        	 }
         
    }
}