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
public class SignInTest {
    static String username;
    public static ChromeDriver driver;
    @BeforeAll
    //Setup my driver here through @BeforeAll, this method is running once
    //all test classes
    public static void setup()
    {
        System.setProperty("Webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        username = UsernameGenerator.GenerateUsername();
    }

    @Test
    @Order(1)
    @SpiraTestCase(testCaseId = 4701)
    public void NavigatetoRegister()
    {
        //1. Browse the petstore page
        driver.get("https://petstore.octoperf.com/");

        //2. Locate the Store enter button and Click
        driver.findElement(By.linkText("Enter the Store")).click();

        //3. Navigate to the sign in section
        driver.findElement(By.linkText("Sign In")).click();

        //4. Navigate to the register user section
        driver.findElement(By.linkText("Register Now!")).click();

        List<WebElement> elements = driver.findElements(By.name("newAccount"));
        assertEquals(1, elements.size());

    }
    @Test
    @Order(2)
    @SpiraTestCase(testCaseId = 4706)
    public void EnterDetails(){
        //5 enter user details
        //5.1 Enter User ID
        System.out.println(username);
        driver.findElement(By.name("username")).sendKeys(username);
        //5.2 Enter Password
        driver.findElement(By.name("password")).sendKeys("password");
        //5.2.1 confirm Password
        driver.findElement(By.xpath("/html/body/div[2]/div/form/table[1]/tbody/tr[3]/td[2]/input")).sendKeys("password");
        //5.3 enter name
        driver.findElement(By.name("account.firstName")).sendKeys("John");
        //5.4 enter lastname
        driver.findElement(By.name("account.lastName")).sendKeys("smith");
        //5.5 enter password
        driver.findElement(By.name("account.email")).sendKeys("JohnSmith@gmail.com");
        //5.6 enter phone
        driver.findElement(By.name("account.phone")).sendKeys("0000000000");
        //5.7 enter Address1
        driver.findElement(By.name("account.address1")).sendKeys("401 swanston street");
        //5.7.1 enter address2
        driver.findElement(By.name("account.address2")).sendKeys("527 woolongong drive");
        //5.8 enter City
        driver.findElement(By.name("account.city")).sendKeys("Lockington");
        //5.9 enter State
        driver.findElement(By.name("account.state")).sendKeys("Bavaria");
        //5.10 enter zip
        driver.findElement(By.name("account.zip")).sendKeys("3001");
        //5.11 enter country
        driver.findElement(By.name("account.country")).sendKeys("Australia");
        //5.12 select language preference
        Select langpref = new Select(driver.findElement(By.name("account.languagePreference")));
        langpref.selectByIndex(1);
        //5.13 select favourite animal
        Select favAnimal = new Select(driver.findElement(By.name("account.favouriteCategoryId")));
        favAnimal.selectByIndex(2);
        //5.14 enable myList
        driver.findElement(By.name("account.listOption")).click();
        //5.15 enable MyBanner
        driver.findElement(By.name("account.bannerOption")).click();

        //6. Save Account Information
        driver.findElement(By.name("newAccount")).click();
        //after creating an account the user is sent back to the home page, not signed in, therefore there should be 1 sign in button
        List<WebElement> elements = driver.findElements(By.linkText("Sign In"));
        assertEquals(1, elements.size());
    }
    @Test
    @Order(3)
    @SpiraTestCase(testCaseId = 4707)
    public void Login(){
        //7. Log into new account
        System.out.println(username);
        //7.1 navigate to Sign in page
        driver.findElement(By.linkText("Sign In")).click();
        //7.2 enter username
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(username);
        //7.3 enter password
        WebElement passField = driver.findElement(By.name("password"));
        passField.clear();
        passField.sendKeys("password");
        //7.4 click login button
        driver.findElement(By.name("signon")).click();

        //once signed in, the user is transported back to the homepage and a welcome message is present
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"WelcomeContent\"]"));
        assertEquals(1, elements.size());
    }
    @AfterAll
    public static void CloseBrowser()
    {
        driver.close();
    }
}
