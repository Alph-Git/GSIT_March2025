package project_package;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Class_05 {
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
	public void quitChrome() throws Exception {
		Thread.sleep(3000);
		driver.quit();
	}
	@Test
	public void verifySubmitWhileLoadingPreviousResult() {
	    WebElement inputBox = driver.findElement(By.id("number"));
	    WebElement submitBtn = driver.findElement(By.id("getFactorial"));
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    // First input
	    inputBox.clear();
	    inputBox.sendKeys("5");
	    submitBtn.click();

	    // Immediately submit a second input before result for 5 arrives
	    inputBox.clear();
	    inputBox.sendKeys("6");
	    submitBtn.click();

	    // Wait for updated result
	    WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultDiv")));
	    String text = result.getText();
	    

	    System.out.println("Result after rapid submit: " +text);
	    System.out.println("The expected result: The factorial of 6 is: 720");
	}
}
