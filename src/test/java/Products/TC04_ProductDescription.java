package Products;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.BaseTest;
import Utilities.LocatorManager;

public class TC04_ProductDescription  {

    @Test
    public void verifyProductDescriptionsDisplayed() {
    	
    	BaseTest baseTest = new BaseTest();

        try {

            baseTest.setUp();
            WebDriver driver=baseTest.getDriver();

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

        boolean allDescriptionsDisplayed = true;

        for (WebElement product : products) {

            WebElement description =
                    product.findElement(
                            By.className("inventory_item_desc")
                    );

            boolean displayed = description.isDisplayed();

            String text = description.getText();

            System.out.println(
                    "Product Description: " + text
            );

            System.out.println(
                    "Description displayed: " + displayed
            );

            System.out.println("-------------------------");

            if (!displayed || text.trim().isEmpty()) {
                allDescriptionsDisplayed = false;
            }
        }

        Assert.assertTrue(
                allDescriptionsDisplayed && !products.isEmpty(),
                "One or more products do not have a visible description."
        );

        System.out.println(
                "TC04 PASS: All products have a visible description."
        );
        }
        finally {
        	baseTest.tearDown();
        }
    }
}