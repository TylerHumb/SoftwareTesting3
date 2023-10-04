import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpiraTestConfiguration(
        //following are REQUIRED
        url = "https://rmit.spiraservice.net",
        login = "S3947682",
        rssToken = "{DEE2C1F9-FD55-43BE-AF93-B492757F8C75}",
        projectId = 78

)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlaceOrderTest {

    private static ChromeDriver driver;


    @BeforeAll
    //Setup my driver here through @BeforeAll, this method is running once
    //all test classes
    public static void setup()
    {
        System.setProperty("Webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    @Order(1)
    @SpiraTestCase(testCaseId = 4044)
    public void NavigateToCategory()
    {
        //1. Browse the petstore page
        driver.get("https://petstore.octoperf.com/");
        //2. Locate the Store enter button and Click
        driver.findElement(By.linkText("Enter the Store")).click();
        //3. navigate to a category page
        driver.findElement(By.xpath("//*[@id=\"QuickLinks\"]/a[1]")).click();
        List<WebElement> elements = driver.findElements(By.linkText("FI-SW-01"));
        assertEquals(1, elements.size());
    }

    @Test
    @Order(2)
    @SpiraTestCase(testCaseId = 4045)
    public void AddProductToCart()
    {
        //4. navigate to a product page
        driver.findElement(By.linkText("FI-SW-01")).click();

        //5. Add item to cart (doesnt matter which one)
        driver.findElement(By.linkText("Add to Cart")).click();

        // if executed correctly, user should be on cart page with one item in cart, therefore on remove button
        List<WebElement> elements = driver.findElements(By.linkText("Remove"));
        assertEquals(1, elements.size());
    }
    @Test
    @Order(3)
    @SpiraTestCase(testCaseId = 4047)
    public void SignIn(){
        //6. proceed to checkout
        driver.findElement(By.linkText("Proceed to Checkout")).click();
        //7. sign in using existing account
        //7.1 enter username
        driver.findElement(By.name("username")).sendKeys("TestUsername");
        //7.2 enter password
        WebElement passField = driver.findElement(By.name("password"));
        passField.clear();
        passField.sendKeys("password");
        //7.4 click login button
        driver.findElement(By.name("signon")).click();

        //if executed correctly user should be signed in on homepage, therefore a sign out button should be avaiable
        List<WebElement> elements = driver.findElements(By.linkText("Sign Out"));
        assertEquals(1, elements.size());
    }
    @Test
    @Order(4)
    @SpiraTestCase(testCaseId = 4048)
    public void CompleteOrder(){
        //8. Navigate back to check out
        driver.findElement(By.name("img_cart")).click();
        driver.findElement(By.linkText("Proceed to Checkout")).click();
        //9. enter card details (shipping details pre-filled from account
        //9.1 select card type
        Select cardtype = new Select(driver.findElement(By.name("order.cardType")));
        cardtype.selectByIndex(1);
        //9.2 Enter card number
        driver.findElement(By.name("order.creditCard")).clear();
        driver.findElement(By.name("order.creditCard")).sendKeys("3333 4444 5555 6666");
        //9.3 enter expirary date
        driver.findElement(By.name("order.expiryDate")).clear();
        driver.findElement(By.name("order.expiryDate")).sendKeys("23/40");
        //10 click continue
        driver.findElement(By.name("newOrder")).click();
        //11 confirm details
        driver.findElement(By.linkText("Confirm")).click();

        //if completed the user should be on the order summary page
        String text = driver.findElement(By.xpath("/html/body/div[2]/ul/li")).getText();
        assertEquals("Thank you, your order has been submitted.",text);
    }

    @AfterAll
    public static void CloseBrowser()
    {
        driver.close();
    }
}