package PomPkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageAfterLog {
	WebDriver driver;
	
	@FindBy(xpath="//input[@id='Email']")
	private WebElement emailID;
	
	@FindBy (xpath="//input[@id='Password']")
	private WebElement password;
	@FindBy(xpath="//button[text()='Log in']")
	private WebElement loginButton;
	@FindBy(xpath="//a[text()='Logout']")
	private WebElement logout;
	

	public HomePageAfterLog(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
		
	}
	
	public void TextBoxEmail(String email)
	{
		emailID.clear();
		emailID.sendKeys(email);
	}
	public void TextBoxPassword(String pwd)
	{
		password.clear();
		password.sendKeys(pwd);
		
	}
	
	public void Button()
	{
		loginButton.click();
	}
	
	public void LogOutPage()
	{
		logout.click();
	}
}
