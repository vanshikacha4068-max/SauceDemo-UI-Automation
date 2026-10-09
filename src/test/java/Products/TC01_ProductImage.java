package Products;

import java.util.List;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.BaseTest;
import Utilities.LocatorManager;

public class TC01_ProductImage  {

    @Test
    public void verifyProductImages() {

        BaseTest baseTest = new BaseTest();

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

            Assert.assertFalse(
                    products.isEmpty(),
                    "No products were displayed."
            );


        boolean allImagesAvailable = true;

        for (WebElement product : products) {

            WebElement image =
                    product.findElement(
                            By.tagName("img")
                    );

            String imageSource = image.getAttribute("src");

            System.out.println("Image src: " + imageSource);

            if (imageSource == null || imageSource.isEmpty()) {
                allImagesAvailable = false;
            }
        }

        Assert.assertTrue(
                allImagesAvailable,
                "One or more product images are missing."
        );

        System.out.println(
                "TC01 PASS: All product images are available."
        );
        }
        finally {
        	baseTest.tearDown();
        }
    }
}