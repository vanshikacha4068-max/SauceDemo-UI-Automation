package Products;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TC01_ProductImage {

	public static void main(String[] args) {
		//Open The Website
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		
		//Login with User Name and Password
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		
		
		driver.findElement(By.id("login-button")).click();
		
		System.out.println("Login completed");
		System.out.println("Current URL: " + driver.getCurrentUrl());
		
		List<WebElement> products = driver.findElements(
		        By.xpath("//div[@class='inventory_item']")
		);

		System.out.println("Number of products found: " + products.size());
		
		
		//Using X path Find Element
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		
		
		for (WebElement product : products) {

		    String productName = product.findElement(
		            By.className("inventory_item_name")
		    ).getText();

		    WebElement image = product.findElement(By.xpath(".//img"));

		    System.out.println("Product: " + productName);
		    System.out.println("Image displayed: " + image.isDisplayed());
		    System.out.println("-------------------------");
		}
		
		
	}

}
