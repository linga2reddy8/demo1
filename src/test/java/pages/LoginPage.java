package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;
	public LoginPage (WebDriver driver)
	{this.driver=driver;
	}

	By registeredElement = By.className("page-subheading");
	By emailid = By.name("email");
	By password = By.id("passwd");
	By signinbutton = By.xpath("//*[@type='submit' and @name='SubmitLogin']");
	//By signinbutton = By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div[2]/div[1]/form/div/div[3]/button/");
	
	// /html/body/div[1]/div[2]/div/div[3]/div/div[2]/div[1]/form/div/div[3]
	//   /html/body/div[1]/div[2]/div/div[3]/div/div[2]/div[1]/form/div/div[3]/button/


public boolean existsRegisteredElement() {
	try{
	driver.findElement(registeredElement);
	}
	catch(NoSuchElementException e) {
		return false;
		
	}
	return true;
}
	public void setUserName(){

    driver.findElement(emailid).sendKeys("sowmyagraph@gmail.com");
}


public void setPassword(){

     driver.findElement(password).sendKeys("TestPwd");

}

public void clickLogin(){

    driver.findElement(signinbutton).click();

}
}


