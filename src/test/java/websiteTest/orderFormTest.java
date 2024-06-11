package websiteTest;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.lang.Math;
import java.time.LocalDate;;

public class orderFormTest {
    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriverManager.chromedriver().clearResolutionCache().setup();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://127.0.0.1:8000/orders/");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    
    @Test
    public void orderFormSuccessful(){
        driver.findElement(By.className("minimal")).click();
        String[] options = {"blu", "ban", "mix"};
        int val = (int) (Math.random() * 3);
        driver.findElement(By.cssSelector("option[value="+options[val]+"]")).click();
        driver.findElement(By.id("quantity")).sendKeys("20");


        LocalDate date = LocalDate.now();
        date = date.plusDays(2);
        driver.findElement(By.id("delivery_date")).sendKeys(date.getMonthValue()+"/"+date.getDayOfMonth()+"/"+date.getYear());
        

        driver.findElements(By.className("checkbox")).get((int) (Math.random() * 4)).click();
        driver.findElement(By.id("name")).sendKeys("Test Jonny");

        // Future: add section to randomly generate 10 numbers between 0 and 9 
        driver.findElement(By.id("phone")).sendKeys("1112223432");

        driver.findElement(By.className("input-comments")).sendKeys("Test Test Test...");
        driver.findElement(By.id("promo")).sendKeys("test-123");
        driver.findElement(By.className("apply_code")).click();
        driver.findElement(By.className("place-order")).click();
    }
    @Test
    public void orderFormQuantityValidation(){
        driver.findElement(By.className("minimal")).click();
        String[] options = {"blu", "ban", "mix"};
        driver.findElement(By.cssSelector("option[value="+options[(int) (Math.random() * 3)]+"]")).click();
        driver.findElement(By.id("quantity")).sendKeys("-2");
        
        LocalDate date = LocalDate.now();
        date = date.plusDays(2);
        driver.findElement(By.id("delivery_date")).sendKeys(date.getMonthValue()+"/"+date.getDayOfMonth()+"/"+date.getYear());
        
        driver.findElements(By.className("checkbox")).get((int) (Math.random() * 4)).click();
        driver.findElement(By.id("name")).sendKeys("Test Jonny");
        driver.findElement(By.id("phone")).sendKeys("1112223432");
        driver.findElement(By.className("place-order")).click();
        String message = driver.findElement(By.xpath("//p[\'Quantity needs to be at-least 1\'] ")).getText();
        Assert.assertTrue(message.contains("Quantity needs to be at-least 1"),"\n Message Does Not contain at-least 1");
    }
    @Test
    public void orderFormDateValidation(){
        driver.findElement(By.className("minimal")).click();
        String[] options = {"blu", "ban", "mix"};
        driver.findElement(By.cssSelector("option[value="+options[(int) (Math.random() * 3)]+"]")).click();
        driver.findElement(By.id("quantity")).sendKeys("2");
       
        LocalDate date = LocalDate.now();
        date = date.minusDays(2);
        driver.findElement(By.id("delivery_date")).sendKeys(date.getMonthValue()+"/"+date.getDayOfMonth()+"/"+date.getYear());
        
        driver.findElements(By.className("checkbox")).get((int) (Math.random() * 4)).click();
        driver.findElement(By.id("name")).sendKeys("Test Jonny");
        driver.findElement(By.id("phone")).sendKeys("1112223432");
        driver.findElement(By.className("place-order")).click();    
        String message = driver.findElement(By.xpath("//p[\'Please Selext a valid date\'] ")).getText();
        Assert.assertTrue(message.contains("Please Select a valid date"),"\n Message Does Not contain select valid date");
    }
    @Test
    public void orderFormPhoneValidation(){
        driver.findElement(By.className("minimal")).click();
        String[] options = {"blu", "ban", "mix"};
        driver.findElement(By.cssSelector("option[value="+options[(int) (Math.random() * 3)]+"]")).click();
        driver.findElement(By.id("quantity")).sendKeys("2");
       
        LocalDate date = LocalDate.now();
        date = date.plusDays(2);
        driver.findElement(By.id("delivery_date")).sendKeys(date.getMonthValue()+"/"+date.getDayOfMonth()+"/"+date.getYear());
        
        driver.findElements(By.className("checkbox")).get((int) (Math.random() * 4)).click();
        driver.findElement(By.id("name")).sendKeys("Test Jonny");
        driver.findElement(By.className("place-order")).click();    
        String message = driver.findElement(By.xpath("//p['Please enter your phone number']")).getText();
        Assert.assertTrue(message.contains("Please enter your phone number"),"\n Message Does contains enter your phone number");
    }
    
}
