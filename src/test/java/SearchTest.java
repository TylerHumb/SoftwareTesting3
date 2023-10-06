import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;
import jdk.jfr.Timespan;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpiraTestConfiguration(
        //following are REQUIRED
        url = "https://rmit.spiraservice.net",
        login = "s3947682",
        rssToken = "{51DDF051-A531-440F-8710-51A2C6263852}",
        projectId = 78
)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchTest {
    public static ChromeDriver driver;
    @BeforeAll
    //Setup my driver here through @BeforeAll, this method is running once
    //all test classes
    public static void setup()
    {
        System.setProperty("Webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    @Order(1)
    @SpiraTestCase(testCaseId = 4856)
    public void SearchTerm()
    {
        //1. Browse the petstore page
        driver.get("https://petstore.octoperf.com/");

        //2. Locate the Store enter button and Click
        driver.findElement(By.linkText("Enter the Store")).click();

        //3 enter term into search bar
        driver.findElement(By.name("keyword")).sendKeys("snake");
        driver.findElement(By.name("searchProducts")).click();

        List<WebElement> elements = driver.findElements(By.linkText("Doubles as a watch dog"));
        assertEquals(1, elements.size());
    }
    @Test
    @Order(2)
    @SpiraTestCase(testCaseId = 4855)
    public void NavigateToAnimalPage()
    {
        //4. click the search result
        driver.findElement(By.linkText("Doubles as a watch dog")).click();

        //5. navigate to the animal page
        driver.findElement(By.linkText("EST-11")).click();

        List<WebElement> elements = driver.findElements(By.linkText("Add to Cart"));
        assertEquals(1, elements.size());
    }

    @AfterAll
    public static void CloseBrowser()
    {
        driver.close();
    }
}
