package websiteTest;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.lang.Math;

public class orderFormTest {
    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriverManager.chromedriver().clearResolutionCache().setup();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("orders/");
    }

    // @AfterMethod
    // public void tearDown(){
    //     driver.quit();
    // }
    
    @Test
    public void orderFormSuccessful(){
        driver.findElement(By.className("minimal")).click();
        String[] options = {"blu", "ban", "mix"};
        int val = (int) (Math.random() * 3);
        driver.findElement(By.cssSelector("option[value="+options[val]+"]")).click();
        driver.findElement(By.id("quantity")).sendKeys("20");
        driver.findElement(By.id("delivery_date")).sendKeys("06/15/2024");
        
        String[] delivery_times = {"9am", "11am", "1pm", "3pm"};
        // int z = (int) (Math.random() * 4);
        // driver.findElement(By.cssSelector("input[value="+delivery_times[(int) (Math.random() * 4)]+"]")).click();
        driver.findElements(By.className("checkbox")).get((int) (Math.random() * 4)).click();
        driver.findElement(By.id("name")).sendKeys("Test Jonny");

        // Future: add section to randomly generate 10 numbers between 0 and 9 
        driver.findElement(By.id("phone")).sendKeys("1112223432");

        
        driver.findElement(By.className("input-comments")).sendKeys("Test Test Test...");
        driver.findElement(By.id("promo")).sendKeys("test-123");
        // driver.findElement(By.className("apply_code")).click();
        // driver.findElement(By.className("place-order")).click();
    }
    
}
