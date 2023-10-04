import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class SearchFunction {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        //set upan implicit wait in case of slow connections / page load etc
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        //1. Browse the petstore page
        driver.get("https://petstore.octoperf.com/");

        //2. Locate the Store enter button and Click
        driver.findElement(By.linkText("Enter the Store")).click();

        //3 enter term into search bar
        driver.findElement(By.name("keyword")).sendKeys("snake");
        driver.findElement(By.name("searchProducts")).click();

        //4. click the search result
        driver.findElement(By.linkText("Doubles as a watch dog")).click();

        //5. navigate to the animal page
        driver.findElement(By.linkText("EST-11")).click();
    }
}
