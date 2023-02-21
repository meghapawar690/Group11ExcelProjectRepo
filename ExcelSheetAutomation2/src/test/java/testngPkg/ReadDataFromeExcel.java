package testngPkg;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PomPkg.HomePageAfterLog;
import utility.ExcelUtilityClass;

public class ReadDataFromeExcel {
 WebDriver driver;
	@BeforeClass
	public void setup()
	{
		System.out.println("BeforeClassChrome");
		System.out.println("BeforeClassChrome");
        System.setProperty("webdriver.chrome.driver",
	             	"E:\\selenium\\chromedriver.exe");

        driver =new ChromeDriver();
        
	
	
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	       
	}
	@Test(dataProvider="LoginData")
	public void loginTest(String user, String pwd, String exp)
	{
		driver.get("https://admin-demo.nopcommerce.com/login?");
		HomePageAfterLog loginPage=new HomePageAfterLog(driver);
		loginPage.TextBoxEmail(user);
		loginPage.TextBoxPassword(pwd);
		loginPage.Button();
		
		String exp_title= "Dashboard / nopCommerce administration";
		String act_title=driver.getTitle();
		
		if(exp.equals("Valid"))
		{
			if(exp_title.equals(act_title))
			{
				loginPage.LogOutPage();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		else if(exp.equals("Invalid"))
		{
			if(exp_title.equals(act_title))
			{
				loginPage.LogOutPage();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
	}
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		/*String loginData[][]= {
				{"admin@yourstore.com","admin","Valid"},
				{"admin@yourstore.com","adm","Invalid"},
				{"adm@yourstore.com","admin","INvalid"},
				{"adm@yourstore.com","adm","Invalid"}
				
		};*/
		String path="C:\\Users\\Megha\\eclipse-workspace\\ExcelSheetAutomation\\datafiles\\LoginData1.xlsx";
		ExcelUtilityClass excelUtility=new  ExcelUtilityClass(path);
		
		 int totalrows=excelUtility.getRowCount("Sheet1");
		 int totalcols=excelUtility.getCellCount("Sheet1", 1);
		
		 String loginData[][]=new String[ totalrows][ totalcols];
		
		 for(int i=1;i<=totalrows;i++)//1
		{
			for(int j=0;j<totalcols;j++)//0
			{
				loginData[i-1][j]=excelUtility.getCellData("Sheet1", i, j);
			}
		}
		
	return loginData;
	}
	@AfterClass
	void tearDown()
	{
		System.out.println("after class");
		driver.close();
	}
}
