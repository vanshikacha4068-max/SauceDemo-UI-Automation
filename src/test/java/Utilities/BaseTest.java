package Utilities;

import org.openqa.selenium.WebDriver;
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