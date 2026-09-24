package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;




public class Login_page {
	WebDriver varun;
	By username = By.id("Pleease enter user name");
	By passward = By.id("Enter your passward")	;
	By Login_Button = By.id("Login_button");
	
	
	public Login_page(WebDriver driver)

	{
		this.varun = varun;
	
	}
	
	public void USERNAME(String x) 
	{
		varun.findElement(username).sendKeys(x);
		
		
	}
	
	public void PASSWARD(String y) 
	{
		varun.findElement(passward).sendKeys(y);
		
	}
	
	
	public void LOGIN_BUTTON() {
		varun.findElement(Login_Button).click();
	}
	
	public void Loginn(String x, String y) {
		USERNAME(x);
		PASSWARD(y);
		LOGIN_BUTTON();
		
	}

}
