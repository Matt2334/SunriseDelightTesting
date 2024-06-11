package websiteTest;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;

public class homePageTest {
    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriverManager.chromedriver().clearResolutionCache().setup();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://127.0.0.1:8000/");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void HomePageNavigator(){
        driver.findElement(By.className("order-now")).click();
        String message = driver.findElement(By.xpath("//h2[\'Sunrise Delight Order Form\'] ")).getText();
        Assert.assertTrue(message.contains("Sunrise Delight Order Form"),"\n Message Does Not Contain Order Form");
    }

}
