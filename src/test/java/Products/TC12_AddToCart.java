package Products;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.BaseTest;
import Utilities.LocatorManager;

public class TC12_AddToCart {

    @DataProvider(name = "products")
    public Object[][] getProducts() {

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

            List<WebElement> productElements =
                    driver.findElements(LocatorManager.productItem());

            List<String> productNames = new ArrayList<>();

            for (WebElement product : productElements) {

                String productName = product
                        .findElement(LocatorManager.productName())
                        .getText()
                        .trim();

                if (!productName.isEmpty()) {
                    productNames.add(productName);
                }
            }

            Assert.assertFalse(
                    productNames.isEmpty(),
                    "TC12 FAIL: No products were discovered."
            );

            Object[][] data = new Object[productNames.size()][1];

            for (int i = 0; i < productNames.size(); i++) {
                data[i][0] = productNames.get(i);
            }

            System.out.println(
                    "Products discovered: " + productNames.size()
            );

            return data;

        } finally {
            baseTest.tearDown();
        }
    }

    @Test(dataProvider = "products")
    public void verifyAddToCartForProduct(String selectedProduct) {

        BaseTest baseTest = new BaseTest();

        try {
            baseTest.setUp();

            WebDriver driver = baseTest.getDriver();

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            // Find the selected product dynamically.
            List<WebElement> products =
                    driver.findElements(LocatorManager.productItem());

           
            WebElement selectedProductElement =
                    selectedProductElementFor(driver, selectedProduct);

            WebElement addToCartButton =
                    selectedProductElement.findElement(
                            LocatorManager.addToCartButton()
                    );

            wait.until(
                    ExpectedConditions.elementToBeClickable(addToCartButton)
            ).click();

            System.out.println(
                    "Added product to cart: " + selectedProduct
            );

            // Verify the cart badge.
            WebElement cartBadge = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            LocatorManager.cartBadge()
                    )
            );

            Assert.assertEquals(
                    cartBadge.getText(),
                    "1",
                    "TC12 FAIL: Incorrect cart count for " + selectedProduct
            );

            // Open the shopping cart.
            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            LocatorManager.shoppingCartLink()
                    )
            ).click();

            // Verify that the selected product is in the cart.
            List<WebElement> cartItems = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            LocatorManager.cartItemName()
                    )
            );

            boolean productFound = false;

            for (WebElement cartItem : cartItems) {

                if (cartItem.getText().trim().equals(selectedProduct)) {
                    productFound = true;
                    break;
                }
            }

            Assert.assertTrue(
                    productFound,
                    "TC12 FAIL: Product missing from cart: " + selectedProduct
            );

            System.out.println(
                    "TC12 PASS: " + selectedProduct
                            + " was added and verified successfully."
            );

        } finally {
            baseTest.tearDown();
        }
    }

    private WebElement selectedProductElementFor(
            WebDriver driver, String selectedProduct) {

        for (WebElement product :
                driver.findElements(LocatorManager.productItem())) {

            String actualName = product
                    .findElement(LocatorManager.productName())
                    .getText()
                    .trim();

            if (actualName.equals(selectedProduct)) {
                return product;
            }
        }

        throw new AssertionError(
                "Product not found: " + selectedProduct
        );
    }
}