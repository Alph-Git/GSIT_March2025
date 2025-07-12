package project_package;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Class_02 {
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

    @Test (priority = 3)
    public void verifyPlaceholder() {
        WebElement inputBox = driver.findElement(By.xpath("//input[@type='text']"));
        String placeholder = inputBox.getDomAttribute("placeholder");
        Assert.assertEquals(placeholder, "Enter an integer");
        System.out.println("Placeholder = " + placeholder);
        
    }

    @Test (priority = 1)
    public void verifyTitle() {
        String title = driver.getTitle();
        SoftAssert s= new SoftAssert();
        s.assertEquals(title, "Factorial", "Title mismatched");;
        System.out.println("Page Title = " + title);
    }

    @Test (priority = 2)
    public void verifyURL() {
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.startsWith("https"));
        System.out.println(currentURL+" starts with https");
        
    }
}