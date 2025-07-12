package project_package;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Class_01 {
	WebDriver driver = new ChromeDriver();
	WebDriverWait wait;

	@DataProvider (name="TestData")
	public Object[][] method(){
		Object[][] obj=new Object[5][1];
		obj[0][0]="//input[@type='text']";
		obj[1][0]="//button[@type='submit']";
		obj[2][0]="//a[text()='About']";
		obj[3][0]="//a[text()='Terms and Conditions']";
		obj[4][0]="//a[text()='Privacy']";
		return obj;
		
	}
	
	@Parameters("browser")
	@BeforeClass
	public void setup(@Optional("chrome") String browser) {
	    if (browser.equalsIgnoreCase("chrome")) {
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	    } else if (browser.equalsIgnoreCase("edge")) {
	        WebDriverManager.edgedriver().setup();
	        driver = new EdgeDriver();
	    }

	    driver.manage().window().maximize();
	    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    driver.get("https://qainterview.pythonanywhere.com/");
	}


	
	@AfterClass
	public void closeChrome() throws Exception{
		Thread.sleep(3000);
		driver.close();
	}
	@Test (dataProvider = "TestData")
	public void isAvailable(String s) {
		try {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(s)));
		System.out.println("Displayed");
		}catch (Exception e) {
			System.out.println("Not Displayed");
		}
	}
	@Test (dataProvider = "TestData")
	public void isClickable(String s) {
	try {
	wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(s)));
	System.out.println("Clickable");
	}catch(Exception e) {
		System.out.println("Not Clickable");
	}

}}
