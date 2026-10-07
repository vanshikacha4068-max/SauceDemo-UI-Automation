package Login;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC01 {
	public static void main(String args[]) {
		
		
		ChromeDriver obj = new ChromeDriver();
		//open browser
		obj.get("https://saucedemo.com/");
		String x = obj.getTitle();
		if(x.equals("Swag Labs")) {
			System.out.println("TC01 Passed");
			
		}
		else {
			System.out.println("TC01 failed");
		}
	}

}
     
