package Products;


import java.util.List;



import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.BaseTest;
import Utilities.LocatorManager;

public class TC03_ProductNameTitle  {

    @Test
    public void verifyProductNamesDisplayed() {
    	
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

        boolean allNamesDisplayed = true;

        for (WebElement product : products) {

            WebElement productName =
                    product.findElement(
                            By.className(
                                    "inventory_item_name"
                            )
                    );

            boolean displayed = productName.isDisplayed();

            String name = productName.getText();

            System.out.println("Product Name: " + name);
            System.out.println("Name displayed: " + displayed);
            System.out.println("-------------------------");

            if (!displayed || name.trim().isEmpty()) {
                allNamesDisplayed = false;
            }
        }

        Assert.assertTrue(
                allNamesDisplayed && !products.isEmpty(),
                "One or more products do not have a visible name/title."
        );

        System.out.println(
                "TC03 PASS: All products have a visible name/title."
        );
        }
        finally {
        	baseTest.tearDown();
        }
    }
}
