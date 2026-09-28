package Products;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TC10_ProductSortingPriceLowHigh {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {

            driver.manage().window().maximize();

            // Open SauceDemo
            driver.get("https://www.saucedemo.com/");

            // Login
            driver.findElement(By.id("user-name"))
                  .sendKeys("standard_user");

            driver.findElement(By.id("password"))
                  .sendKeys("secret_sauce");

            driver.findElement(By.id("login-button"))
                  .click();

            // Locate sorting dropdown
            Select sortDropdown = new Select(
                    driver.findElement(
                            By.xpath("//select[@data-test='product-sort-container']")
                    )
            );

            // Select Price: Low to High
            sortDropdown.selectByValue("lohi");

            System.out.println("Sorting selected: Price (low to high)");

            // Get product prices
            List<WebElement> priceElements =
                    driver.findElements(By.className("inventory_item_price"));

            if (priceElements.isEmpty()) {

                System.out.println("TC10 FAIL: No product prices found.");
                return;
            }

            // Store actual prices
            List<Double> actualPrices = new ArrayList<>();

            for (WebElement priceElement : priceElements) {

                String priceText = priceElement.getText();

                // Remove $ and convert String to double
                double price =
                        Double.parseDouble(priceText.replace("$", ""));

                actualPrices.add(price);

                System.out.println("Product price: " + priceText);
            }

            // Create expected Low to High order
            List<Double> expectedPrices =
                    new ArrayList<>(actualPrices);

            Collections.sort(expectedPrices);

            System.out.println("\nActual order:");

            for (Double price : actualPrices) {
                System.out.println(price);
            }

            System.out.println("\nExpected Low to High order:");

            for (Double price : expectedPrices) {
                System.out.println(price);
            }

            // Compare actual and expected
            if (actualPrices.equals(expectedPrices)) {

                System.out.println(
                        "\nTC10 PASS: Products are sorted correctly from Low to High."
                );

            } else {

                System.out.println(
                        "\nTC10 FAIL: Products are not sorted correctly from Low to High."
                );
            }

        } finally {

            driver.quit();
        }
    }
}