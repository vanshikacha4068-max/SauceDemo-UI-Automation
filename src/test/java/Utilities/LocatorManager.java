package Utilities;

import org.openqa.selenium.By;

public class LocatorManager {

    public static By productItem() {
        return By.xpath(
                Propertiesmanager.getXpath("productItem")
        );
    }

    public static By productName() {
        return By.xpath(
                Propertiesmanager.getXpath("productName")
        );
    }

    public static By productPrice() {
        return By.xpath(
                Propertiesmanager.getXpath("productPrice")
        );
    }

    public static By sortDropdown() {
        return By.xpath(
                Propertiesmanager.getXpath("sortDropdown")
        );
    }
    
    
    public static By productTitleLink() {
        return By.xpath(
                Propertiesmanager.getXpath("productTitleLink")
        );
    }

    public static By backToProductsButton() {
        return By.xpath(
                Propertiesmanager.getXpath("backToProductsButton")
        );
    }
}