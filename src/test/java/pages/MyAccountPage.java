package pages;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;

	public class MyAccountPage {
		WebDriver driver;
		public MyAccountPage (WebDriver driver)
		{this.driver=driver;
		}
		By hearticon = By.className("icon-heart");

		//public void verifysignedinpage(){

		  public boolean existIcon()
		  {
			  return driver.findElements(hearticon).size()!=0;
			  
	}
	}
	
