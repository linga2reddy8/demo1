package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HomePage {
	
WebDriver driver;

public HomePage (WebDriver driver)
{
	this.driver = driver;
}

	By signin = By.className("login");

	/*
	 * public boolean verifytitle() { boolean b=
	 * driver.getTitle().contains("Sign in"); return b; }
	 */


public boolean existsSignInElement() {
    try {
        driver.findElement(signin);
    } catch (NoSuchElementException e) {
        return false;
    }
    return true;
}

public void signinClick() {
	driver.findElement(signin).click();
}

}

 



