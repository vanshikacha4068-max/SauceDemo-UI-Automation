package Utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

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

        driver.findElement(
                org.openqa.selenium.By.id("user-name")
        ).sendKeys(
                Propertiesmanager.getOther("username")
        );

        driver.findElement(
                org.openqa.selenium.By.id("password")
        ).sendKeys(
                Propertiesmanager.getOther("password")
        );

        driver.findElement(
                org.openqa.selenium.By.id("login-button")
        ).click();
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}