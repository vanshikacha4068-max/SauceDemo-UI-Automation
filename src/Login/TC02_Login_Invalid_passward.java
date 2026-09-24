package Login;


import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

public class TC02_Login_Invalid_passward {
	
	  public static void main(String[] args) {
		  
		  WebDriver driver = new ChromeDriver();

		  driver.get("https://www.saucedemo.com/");
		  
		  Login_page willer = new Login_page(driver);
		  
		  willer.Loginn(
	                "standard_user",
	                "wrong_password"
	        );

		driver.quit();
	  }
}
