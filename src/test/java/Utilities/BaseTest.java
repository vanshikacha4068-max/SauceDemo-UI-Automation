package Utilities;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseTest {

    protected WebDriver driver;

    
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1280, 800));

        driver.get(
                Propertiesmanager.getOther("baseUrl")
        );
        
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for username field
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("user-name")
                )
        );
        //Enter user name
        driver.findElement(
                By.id("user-name")
        ).sendKeys(
                Propertiesmanager.getOther("username")
        );
        //Enter password
        driver.findElement(
                By.id("password")
        ).sendKeys(
                Propertiesmanager.getOther("password")
        );
        //Click Login
        driver.findElement(
                By.id("login-button")
        ).click();
    }
    
    public WebDriver getDriver() {
        return driver;
    }

    
    public void tearDown() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}