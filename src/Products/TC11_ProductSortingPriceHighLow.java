package Products;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import Utilities.Propertiesmanager;

public class TC11_ProductSortingPriceHighLow {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

            // Open SauceDemo
            driver.get(Propertiesmanager.getOther("baseUrl"));

            // Login
            driver.findElement(By.id("user-name"))
                   .sendKeys(Propertiesmanager.getOther("username"));

            driver.findElement(By.id("password"))
                   .sendKeys(Propertiesmanager.getOther("password"));

            driver.findElement(By.id("login-button"))
                   .click();

            // Select Price High to Low
            Select sortDropdown = new Select(
                    driver.findElement(
                            By.xpath(Propertiesmanager.getXpath("sortDropdown"))
                    )
            );

            sortDropdown.selectByValue("hilo");



            // Get actual prices
            List<Double> actualPrices = new ArrayList<>();

            List<WebElement> priceElements =
                    driver.findElements(
                            By.xpath(Propertiesmanager.getXpath("productPrice"))
                    );

            for (WebElement priceElement : priceElements) {

                String priceText = priceElement.getText();

                double price =
                        Double.parseDouble(priceText.replace("$", ""));

                actualPrices.add(price);

                System.out.println("Product price: " + priceText);
            }

            // Create expected High to Low order
            List<Double> expectedPrices =
                    new ArrayList<>(actualPrices);

            Collections.sort(
                    expectedPrices,
                    Collections.reverseOrder()
            );

            System.out.println("Actual order:");
            for (Double price : actualPrices) {
                System.out.println(price);
            }

            System.out.println("Expected High to Low order:");
            for (Double price : expectedPrices) {
                System.out.println(price);
            }

            // Verify
            if (actualPrices.equals(expectedPrices)) {
                System.out.println(
                        "TC11 PASS: Products are sorted correctly from High to Low."
                );
            } else {
                System.out.println(
                        "TC11 FAIL: Products are NOT sorted correctly from High to Low."
                );
            }

        } finally {
            driver.quit();
        }
    }
}