package Login;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;


public class TC01_Login_Invalid_Username {
	
	 public static void main(String[] args) {
		 
		 WebDriver verma = new ChromeDriver();
		 
		 verma.get("https://www.saucedemo.com/");
		 
		 Login_page varun = new Login_page(verma);
		 varun.Loginn(
				 "invalid_user", "secret_sauce");
		 
		 verma.quit();

	 }


}
