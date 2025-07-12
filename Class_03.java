package project_package;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Class_03 {
	WebDriver driver;
	WebDriverWait wait;
	
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
	public void closeChrome() throws Exception {
		Thread.sleep(3000);
		driver.close();
	}

	public static long factorial(int f) {
		long fact = 1;
		for (int i = 1; i <= f; i++) {
			fact = fact * i;
		}
		return fact;
	}

	@DataProvider(name = "Numbers")
	public Object[][] dp() {
		Object[][] obj = new Object[7][2];
		for (int i = 0; i < 7; i++) {
			obj[i][0] = 4 + i;
			int input = (int) obj[i][0];
			obj[i][1] = factorial(input);
		}
		return obj;
	}

	@Test(dataProvider = "Numbers")
	public void check(int n, long f) {
		WebElement inputBox = driver.findElement(By.xpath("//input[@type='text']"));
        WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));

        inputBox.clear();
        inputBox.sendKeys(String.valueOf(n));
        submitBtn.click();

    	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("resultDiv"), String.valueOf(f)));
    	WebElement res= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='resultDiv']")));
		String actRes= res.getText();
		String actResN = "";
		if (actRes.contains(":")) {
		    String[] parts = actRes.split(":");
		    actResN = parts[1].trim().replaceAll("[^0-9]", "");
		}
		        long actual = Long.parseLong(actResN);
		        Assert.assertEquals(actual, f, "Mismatched");
		    

	}
}