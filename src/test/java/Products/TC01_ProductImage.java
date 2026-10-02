package Products;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.BaseTest;
import Utilities.LocatorManager;

public class TC01_ProductImage extends BaseTest {

    @Test
    public void verifyProductImages() {

        List<WebElement> products =
                driver.findElements(LocatorManager.productItem());

        System.out.println("Total products: " + products.size());

        boolean allImagesAvailable = true;

        for (WebElement product : products) {

            WebElement image =
                    product.findElement(
                            org.openqa.selenium.By.tagName("img")
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
}