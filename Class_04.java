package project_package;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Class_04 {

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
	public void linksNames() {
		driver.findElement(By.xpath("//a[text()='About']")).click();
		List<WebElement> links = driver.findElements(By.tagName("a"));
		int size=links.size();
		SoftAssert s=new SoftAssert();
		s.assertTrue(size>5, "Number of links is greater than 5");
		
		for(WebElement ref: links) {
			String str=ref.getText().trim();
			System.out.println(str);
		}
	}
	
}
